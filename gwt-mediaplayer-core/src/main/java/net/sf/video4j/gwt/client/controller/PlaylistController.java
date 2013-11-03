/**
 * 
 */
package net.sf.video4j.gwt.client.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.ApplicationInitEvent.ApplicationInitHandler;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent.ApplicationLoadHandler;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent.ApplicationReadyHandler;
import net.sf.video4j.gwt.client.event.PlayerPlayEndedEvent;
import net.sf.video4j.gwt.client.event.PlayerPlayEndedEvent.PlayerPlayEndedHandler;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.model.IApplicationConfig;
import net.sf.video4j.gwt.client.model.IPlugin;
import net.sf.video4j.gwt.client.model.PlayItemBean;
import net.sf.video4j.gwt.client.model.PlaylistBean;
import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.PlayItem;
import net.sf.video4j.gwt.client.player.Playlist;
import net.sf.video4j.gwt.client.player.PlaylistNavigator;
import net.sf.video4j.gwt.client.util.BeanFactory;
import net.sf.video4j.gwt.client.util.PlayItemBeanFactory;
import net.sf.video4j.gwt.client.util.PlaylistBeanFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author luc
 *
 */
public class PlaylistController extends BaseController implements 
		ApplicationLoadHandler,
		ApplicationInitHandler, 
		ApplicationReadyHandler, 
		PlayerPlayEndedHandler,
		IPlugin {
	
	//private PlaylistCon mConfig;
	private PlaylistNavigator mPlaylistNavigator;
	private PlayItemBeanFactory mPlayItemBeanFactory;

	@Inject
	public PlaylistController(EventBus pBus, PlayItemBeanFactory pPlayItemBeanFactory) {
		super(pBus);
		mPlayItemBeanFactory = pPlayItemBeanFactory;
		registerHandlers();
	}
	
	private void registerHandlers() {
		addRegisteredHandler(ApplicationLoadEvent.getType(), this);
		addRegisteredHandler(ApplicationReadyEvent.getType(), this);
		addRegisteredHandler(ApplicationInitEvent.getType(), this);
		addRegisteredHandler(PlayerPlayEndedEvent.getType(), this);
	}
	
	@Override
	public String getPluginId() {
		return this.getClass().getName();
	}
	
	@Override
	public void onApplicationLoadEvent(ApplicationLoadEvent pEvent) {
		mLogger.log(Level.INFO, "Received ApplicationLoadEvent.");
		pEvent.getApplication().addPlugin(this);
		IApplicationConfig oConfig = pEvent.getApplication().getConfig();
		
		// TODO: check if that's all good...test!!!!
		BeanFactory<PlayItemBean, PlayItemBeanFactory> oFactory = new BeanFactory<PlayItemBean, PlayItemBeanFactory>(PlayItemBean.class, mPlayItemBeanFactory);
		List<AutoBean<PlayItemBean>> oPlayItemBeans = new ArrayList<AutoBean<PlayItemBean>>();
		for (int i = 0; i < oConfig.getPlaylist().size(); i++) {
			oPlayItemBeans.add(oFactory.makeABFrom(oConfig.getPlaylist().get(i).isObject()));
		}
		mLogger.log(Level.INFO, "Found " + oPlayItemBeans.size() + " play items from config.");
		Playlist oPlaylist = new Playlist();
		for (AutoBean<PlayItemBean> oItemAutoBean : oPlayItemBeans) {
			PlayItemBean oItemBean = oItemAutoBean.as();
			Media oMedia = new Media();
			oMedia.setURI(oItemBean.getURL());
			Map<String, Object> oProps = AutoBeanUtils.getAllProperties(oItemAutoBean);
			oMedia.setProperties(oProps);
			oPlaylist.add(oMedia);
		}
		mLogger.log(Level.INFO, "Playlist: " + oPlaylist.count() + " items.");
		mPlaylistNavigator = new PlaylistNavigator(oPlaylist);
	}
	
	@Override
	public void onApplicationInitEvent(ApplicationInitEvent pEvent) {
		mLogger.log(Level.INFO, "Received ApplicationInitEvent.");
		PluginReadyEvent.fire(this, this);
		mLogger.log(Level.INFO, "PluginReadyEvent fired.");
	}
	
	@Override
	public void onPlayerPlayEndedEvent(PlayerPlayEndedEvent pEvent) {
		play();
	}

	@Override
	public void onApplicationReadyEvent(ApplicationReadyEvent pEvent) {
		mLogger.log(Level.INFO, "Received ApplicationReadyEvent. Starting playlist...");
		play();
	}

	private void play() {
		if (!mPlaylistNavigator.hasNext()) {
			mLogger.log(Level.INFO, "Nothing to play.");
			return;
		}
		GWT.runAsync(new RunAsyncCallback() {
			
			@Override
			public void onSuccess() {
				mLogger.log(Level.INFO, "Getting play item...");
				PlayItem oNext = mPlaylistNavigator.next();
				mLogger.log(Level.INFO, "Firing PlayListPlayEvent...");
				PlaylistPlayEvent.fire(PlaylistController.this, oNext);
				mLogger.log(Level.INFO, "PlayListPlayEvent fired.");
			}
			
			@Override
			public void onFailure(Throwable pReason) {
				mLogger.log(Level.SEVERE, "Could not start playlist.", pReason);
			}
		});
	}

}
