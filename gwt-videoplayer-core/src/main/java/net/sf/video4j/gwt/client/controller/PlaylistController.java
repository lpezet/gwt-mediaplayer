/**
 * 
 */
package net.sf.video4j.gwt.client.controller;

import java.util.logging.Level;

import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.ApplicationInitEvent.ApplicationInitHandler;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent.ApplicationLoadHandler;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent.ApplicationReadyHandler;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.IPlugin;
import net.sf.video4j.gwt.client.player.PlayItem;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author luc
 *
 */
public class PlaylistController extends BaseController implements 
		ApplicationLoadHandler,
		ApplicationInitHandler, 
		ApplicationReadyHandler, 
		IPlugin {
	
	private ApplicationConfig mConfig;

	@Inject
	public PlaylistController(EventBus pBus) {
		super(pBus);
		registerHandlers();
	}
	
	private void registerHandlers() {
		addRegisteredHandler(ApplicationLoadEvent.getType(), this);
		addRegisteredHandler(ApplicationReadyEvent.getType(), this);
		addRegisteredHandler(ApplicationInitEvent.getType(), this);
	}
	
	@Override
	public String getPluginName() {
		return this.getClass().getName();
	}
	
	@Override
	public void onApplicationLoadEvent(ApplicationLoadEvent pEvent) {
		mLogger.log(Level.INFO, "Received ApplicationLoadEvent.");
		mConfig = pEvent.getConfig();
		mConfig.getPlugins().add(this);
	}
	
	@Override
	public void onApplicationInitEvent(ApplicationInitEvent pEvent) {
		mLogger.log(Level.INFO, "Received ApplicationInitEvent.");
		PluginReadyEvent.fire(this, this);
		mLogger.log(Level.INFO, "PluginReadyEvent fired.");
	}

	@Override
	public void onApplicationReadyEvent(ApplicationReadyEvent pEvent) {
		mLogger.log(Level.INFO, "Received ApplicationReadyEvent. Starting playlist...");
		if (!mConfig.getPlaylist().hasNext()) {
			mLogger.log(Level.INFO, "Nothing to play.");
			return;
		}
		PlayItem oNext = mConfig.getPlaylist().next();
		PlaylistPlayEvent.fire(this, oNext);
	}

}
