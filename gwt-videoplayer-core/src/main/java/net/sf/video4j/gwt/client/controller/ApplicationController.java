/**
 * 
 */
package net.sf.video4j.gwt.client.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.event.PluginReadyEvent.PluginReadyHandler;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.IPlugin;
import net.sf.video4j.gwt.client.player.Playlist;
import net.sf.video4j.gwt.client.player.Track;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author luc
 *
 */
public class ApplicationController extends BaseController implements PluginReadyHandler {
	
	private ApplicationConfig mConfig;
	private Map<String, IPlugin> mPluginsReady = new HashMap<String, IPlugin>();
	

	@Inject
	public ApplicationController(EventBus pBus) {
		super(pBus);
		registerHandlers();
	}
	
	private void registerHandlers() {
		addRegisteredHandler(PluginReadyEvent.getType(), this);
	}
	
	public void begin() {
		mLogger.log(Level.INFO, "Starting application controller...");
		Playlist oPlaylist = new Playlist();
		Track t = new Track();
		t.setDurationInSeconds(100);
		t.setTitle("Test video");
		t.setURI("http://videos.tripfilms.com/720p/D93A130B1BC3E02EB7AB99812EFB8C00.mp4");
		oPlaylist.add(t);
		mConfig = new ApplicationConfig(oPlaylist);
		/*
		try {
			mLogger.log(Level.INFO, "ApplicationInitEvent: firing...");
			ApplicationInitEvent.fire(this, mConfig);
			mLogger.log(Level.INFO, "ApplicationInitEvent: fired.");
		} catch (Throwable t) {
			mLogger.log(Level.SEVERE, "Problem!!!!! ", t);
		}
		*/
		GWT.runAsync(new RunAsyncCallback() {
			@Override
			public void onSuccess() {
				mLogger.log(Level.INFO, "ApplicationLoadEvent: firing...");
				ApplicationLoadEvent.fire(ApplicationController.this, mConfig);
				mLogger.log(Level.INFO, "ApplicationLoadEvent: fired.");
				mLogger.log(Level.INFO, "ApplicationInitEvent: firing...");
				ApplicationInitEvent.fire(ApplicationController.this);
				mLogger.log(Level.INFO, "ApplicationInitEvent: fired.");
			}			
			@Override
			public void onFailure(Throwable pReason) {
				Window.alert("Error firing appInit/LoadEvent! Reason = " + pReason.getMessage());
			}
		});
	}
	
	@Override
	public void onPluginReadyEvent(PluginReadyEvent pEvent) {
		mPluginsReady.put(pEvent.getPlugin().getPluginName(), pEvent.getPlugin());
		//mLogger.log(Level.INFO, "Plugin " + pEvent.getPlugin().getPluginName() + " ready. Now: " + mPluginsReady.size() + " plugins ready, " + (mConfig.getPlugins().size() - mPluginsReady.size()) + " more to go.");
		if (mPluginsReady.size() == mConfig.getPlugins().size()) {
			/*
			mLogger.log(Level.INFO, "All pugins now ready. Sending ApplicationReadyEvent...");
			mLogger.log(Level.INFO, "ApplicationReadyEvent: firing...");
			ApplicationReadyEvent.fire(ApplicationController.this);
			mLogger.log(Level.INFO, "ApplicationReadyEvent: fired.");
			*/
			GWT.runAsync(new RunAsyncCallback() {
				@Override
				public void onSuccess() {
					mLogger.log(Level.INFO, "ApplicationReadyEvent: firing...");
					ApplicationReadyEvent.fire(ApplicationController.this);
					mLogger.log(Level.INFO, "ApplicationReadyEvent: fired.");
				}
				@Override
				public void onFailure(Throwable pReason) {
					mLogger.log(Level.SEVERE, "Error while sending ApplicationReadyEvent.", pReason);
					//Window.alert("Error firing application ready event. Reason = " + pReason.getMessage());
				}
			});
		} else {
			int oPluginsReady = mPluginsReady.size();
			int oRemainingPlugins = mConfig.getPlugins().size() - oPluginsReady;
			mLogger.log(Level.INFO, oPluginsReady + " plugins ready. Waiting for " + oRemainingPlugins + " more plugins...");
			//mLogger.log(Level.INFO, String.format("%s plugins ready. Waiting for %s more plugins to be ready.", mPluginsReady.size(), mConfig.getPlugins().size() - mPluginsReady.size()));
		}
	}
	
	

}
