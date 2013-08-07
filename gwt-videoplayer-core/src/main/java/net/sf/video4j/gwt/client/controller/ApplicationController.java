/**
 * 
 */
package net.sf.video4j.gwt.client.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.event.PluginReadyEvent.PluginReadyHandler;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.IPlugin;
import net.sf.video4j.gwt.client.player.Playlist;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HandlerContainerImpl;

/**
 * @author luc
 *
 */
public class ApplicationController extends HandlerContainerImpl implements PluginReadyHandler, HasHandlers {
	
	private Logger mLogger = Logger.getLogger(ApplicationController.class.getName());
	private EventBus mEventBus;
	private ApplicationConfig mConfig;
	private Map<String, IPlugin> mPluginsReady = new HashMap<String, IPlugin>();

	@Inject
	public ApplicationController(EventBus pBus) {
		mEventBus = pBus;
		registerHandler( mEventBus.addHandler(PluginReadyEvent.getType(), this));
	}
	
	public void begin() {
		mLogger.log(Level.INFO, "Starting application controller...");
		try {
			Playlist oPlaylist = new Playlist();
			mConfig = new ApplicationConfig(oPlaylist);
			ApplicationInitEvent.fire(this, mConfig);
		} catch (Throwable t) {
			mLogger.log(Level.SEVERE, "Problem!!!!! ", t);
		}
		/*
		GWT.runAsync(new RunAsyncCallback() {
			
			@Override
			public void onSuccess() {
				ApplicationInitEvent.fire(mEventBus, mConfig);
			}
			
			@Override
			public void onFailure(Throwable pReason) {
				Window.alert("Error firing appInitEvent! Reason = " + pReason.getMessage());
			}
		});
		*/
	}
	
	@Override
	public void fireEvent(GwtEvent<?> pEvent) {
		mEventBus.fireEvent(pEvent);
	}
	
	@Override
	public void onPluginReadyEvent(PluginReadyEvent pEvent) {
		mPluginsReady.put(pEvent.getPlugin().getPluginName(), pEvent.getPlugin());
		if (mPluginsReady.size() == mConfig.getPlugins().size()) {
			GWT.runAsync(new RunAsyncCallback() {
				@Override
				public void onSuccess() {
					Window.alert("All plugins are ready!!!!");
					ApplicationReadyEvent.fire(ApplicationController.this);
				}
				@Override
				public void onFailure(Throwable pReason) {
					Window.alert("Error firing application ready event. Reason = " + pReason.getMessage());
				}
			});
		}
	}
	
	

}
