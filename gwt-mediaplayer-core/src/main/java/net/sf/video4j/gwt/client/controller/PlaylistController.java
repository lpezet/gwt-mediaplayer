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
import net.sf.video4j.gwt.client.event.PlaylistPlayEndedEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.model.IApplicationConfig;
import net.sf.video4j.gwt.client.model.IPlayItemBean;
import net.sf.video4j.gwt.client.model.IPlugin;
import net.sf.video4j.gwt.client.model.Source;
import net.sf.video4j.gwt.client.player.Media;
import net.sf.video4j.gwt.client.player.PlayItem;
import net.sf.video4j.gwt.client.player.Playlist;
import net.sf.video4j.gwt.client.player.PlaylistNavigator;
import net.sf.video4j.gwt.client.util.BeanFactory;
import net.sf.video4j.gwt.client.util.PlayItemBeanFactory;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.json.client.JSONArray;
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
		//JSOApplicationConfig oJSOConfig = JSOApplicationConfig.build();
		//mLogger.log(Level.INFO, "#### JSOConfig = playlist = " + oJSOConfig.getPlaylist() + ", type = " + oJSOConfig.getPlaylist().getClass());		
		//mLogger.log(Level.INFO, "#### JSOConfig = common = " + oJSOConfig.getCommon() + " type = " + oJSOConfig.getCommon().getClass());
		//mLogger.log(Level.INFO, "#### JSOConfig = plugins = " + oJSOConfig.getPlugins() + " type = " + oJSOConfig.getPlugins().getClass());
		
		mLogger.log(Level.FINE, "Received ApplicationLoadEvent...");
		pEvent.getApplication().addPlugin(this);
		IApplicationConfig oConfig = pEvent.getApplication().getConfig();
		if (oConfig.getPlaylist().isNull() != null) {
			mLogger.log(Level.SEVERE, "No playlist found in configuration.");
			return;
		} else {
			mLogger.log(Level.FINE, "Playlist : " + oConfig.getPlaylist());
		}
		BeanFactory<IPlayItemBean, PlayItemBeanFactory> oFactory = new BeanFactory<IPlayItemBean, PlayItemBeanFactory>(IPlayItemBean.class, mPlayItemBeanFactory);
		List<AutoBean<IPlayItemBean>> oPlayItemBeans = new ArrayList<AutoBean<IPlayItemBean>>();
		JSONArray oPlaylistConfig = oConfig.getPlaylist();
		mLogger.log(Level.INFO, "Playlist configuration has " + oPlaylistConfig.size() + " items.");
		for (int i = 0; i < oPlaylistConfig.size(); i++) {
			oPlayItemBeans.add(oFactory.makeABFrom(oPlaylistConfig.get(i).isObject()));
		}
		Playlist oPlaylist = new Playlist();
		for (AutoBean<IPlayItemBean> oItemAutoBean : oPlayItemBeans) {
			IPlayItemBean oItemBean = oItemAutoBean.as();
			Media oMedia = new Media();
			//oMedia.setURI(oItemBean.getURL());
			if (oItemBean.getURL() != null) {
				// Simple Media
				Source oSource = new Source();
				oSource.setURI(oItemBean.getURL());
				oMedia.getSources().add(oSource);
			} else {
				//TODO: Handle bitrates/multiple types
			}
			Map<String, Object> oProps = AutoBeanUtils.getAllProperties(oItemAutoBean);
			oMedia.setProperties(oProps);
			oPlaylist.add(oMedia);
		}
		pEvent.getApplication().setPlaylist(oPlaylist);
		mLogger.log(Level.INFO, "Playlist created with " + oPlaylist.count() + " items.");
		mLogger.log(Level.FINE, "Done with ApplicationLoadEvent.");
		mPlaylistNavigator = new PlaylistNavigator(oPlaylist);
	}
	
	@Override
	public void onApplicationInitEvent(ApplicationInitEvent pEvent) {
		mLogger.log(Level.FINE, "Received ApplicationInitEvent.");
		PluginReadyEvent.fire(this, this);
		mLogger.log(Level.FINE, "PluginReadyEvent fired.");
	}
	
	@Override
	public void onPlayerPlayEndedEvent(PlayerPlayEndedEvent pEvent) {
		mLogger.log(Level.FINE, "Received PlayerPlayEndedEvent.");
		PlaylistPlayEndedEvent.fire(this, mPlaylistNavigator.getCurrentItem());
		mLogger.log(Level.FINE, "PlaylistPlayEndedEvent fired.");
		play();
	}

	@Override
	public void onApplicationReadyEvent(ApplicationReadyEvent pEvent) {
		mLogger.log(Level.FINE, "Received ApplicationReadyEvent. Starting playlist...");
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
