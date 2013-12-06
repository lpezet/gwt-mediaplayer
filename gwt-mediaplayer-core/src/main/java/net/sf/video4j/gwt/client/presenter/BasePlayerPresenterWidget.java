package net.sf.video4j.gwt.client.presenter;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.client.event.ApplicationInitEvent;
import net.sf.video4j.gwt.client.event.ApplicationInitEvent.ApplicationInitHandler;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent.ApplicationLoadHandler;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent.ApplicationReadyHandler;
import net.sf.video4j.gwt.client.event.ControlFullScreenEvent;
import net.sf.video4j.gwt.client.event.ControlFullScreenEvent.ControlFullScreenHandler;
import net.sf.video4j.gwt.client.event.ControlMuteEvent;
import net.sf.video4j.gwt.client.event.ControlMuteEvent.ControlMuteHandler;
import net.sf.video4j.gwt.client.event.ControlPauseEvent;
import net.sf.video4j.gwt.client.event.ControlPauseEvent.ControlPauseHandler;
import net.sf.video4j.gwt.client.event.ControlPlayEvent;
import net.sf.video4j.gwt.client.event.ControlPlayEvent.ControlPlayHandler;
import net.sf.video4j.gwt.client.event.ControlSeekedEvent;
import net.sf.video4j.gwt.client.event.ControlSeekedEvent.ControlSeekedHandler;
import net.sf.video4j.gwt.client.event.ControlUnmuteEvent;
import net.sf.video4j.gwt.client.event.ControlUnmuteEvent.ControlUnmuteHandler;
import net.sf.video4j.gwt.client.event.ControlVolumeChangeEvent;
import net.sf.video4j.gwt.client.event.ControlVolumeChangeEvent.ControlVolumeChangeHandler;
import net.sf.video4j.gwt.client.event.PlayerErrorEvent;
import net.sf.video4j.gwt.client.event.PlayerPauseEvent;
import net.sf.video4j.gwt.client.event.PlayerPlayEndedEvent;
import net.sf.video4j.gwt.client.event.PlayerPlayingEvent;
import net.sf.video4j.gwt.client.event.PlayerTimeUpdateEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEndedEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEndedEvent.PlaylistPlayEndedHandler;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent.PlaylistPlayHandler;
import net.sf.video4j.gwt.client.event.PluginReadyEvent;
import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;
import net.sf.video4j.gwt.client.model.IPlugin;
import net.sf.video4j.gwt.client.player.PlayItem;

import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;

/**
 * @author Gustavo Matias
 */
public abstract class BasePlayerPresenterWidget<T extends View> extends PresenterWidget<T>
		implements IPlugin, ApplicationLoadHandler, ApplicationInitHandler, ApplicationReadyHandler,
		PlaylistPlayEndedHandler, PlaylistPlayHandler, ControlPlayHandler, ControlPauseHandler,
		ControlMuteHandler, ControlUnmuteHandler, ControlSeekedHandler, ControlFullScreenHandler,
		ControlVolumeChangeHandler, PlayerUiHandlers {

	public BasePlayerPresenterWidget(EventBus pEventBus, T pView) {
		super(pEventBus, pView);
	}

	protected Logger	mLogger	= Logger.getLogger(this.getClass().getName());

	protected abstract PlayItem getPlayingItem();

	@Override
	protected void onBind() {
		super.onBind();
		registerHandlers();
	}

	private void registerHandlers() {
		addRegisteredHandler(ApplicationLoadEvent.getType(), this);
		addRegisteredHandler(ApplicationReadyEvent.getType(), this);
		addRegisteredHandler(ApplicationInitEvent.getType(), this);
		addRegisteredHandler(ControlPlayEvent.getType(), this);
		addRegisteredHandler(ControlPauseEvent.getType(), this);
		addRegisteredHandler(ControlMuteEvent.getType(), this);
		addRegisteredHandler(ControlUnmuteEvent.getType(), this);
		addRegisteredHandler(ControlSeekedEvent.getType(), this);
		addRegisteredHandler(ControlFullScreenEvent.getType(), this);
		addRegisteredHandler(ControlVolumeChangeEvent.getType(), this);
		addRegisteredHandler(PlaylistPlayEvent.getType(), this);
		addRegisteredHandler(PlaylistPlayEndedEvent.getType(), this);
	}

	@Override
	public void onApplicationLoadEvent(ApplicationLoadEvent pEvent) {
		mLogger.log(Level.INFO, "Received ApplicationLoadEvent.");
		pEvent.getApplication().addPlugin(this);
	}

	@Override
	public void onApplicationInitEvent(ApplicationInitEvent pEvent) {
		mLogger.log(Level.INFO, "Received ApplicationInitEvent.");
		PluginReadyEvent.fire(this, this);
		mLogger.log(Level.INFO, "PluginReadyEvent fired.");
	}

	@Override
	public void onTimeUpdate(double pCurrentTime) {
		mLogger.info("onTimeUpdate() : " + pCurrentTime);
		mLogger.log(Level.INFO, "PlayerTimeUpdateEvent: firing...");
		PlayerTimeUpdateEvent.fire(this, pCurrentTime);
		mLogger.log(Level.INFO, "PlayerTimeUpdateEvent: fired.");
	}

	@Override
	public void onError() {
		mLogger.info("onError()...");
		mLogger.log(Level.INFO, "PlayerErrorEvent: firing...");
		PlayerErrorEvent.fire(this);
		mLogger.log(Level.INFO, "PlayerErrorEvent: firing...");
	}

	@Override
	public void onPlaying() {
		mLogger.log(Level.INFO, "PlayerPlayingEvent: firing...");
		PlayerPlayingEvent.fire(this, getPlayingItem());
		mLogger.log(Level.INFO, "PlayerPlayingEvent: fired.");
	}

	@Override
	public void onPause() {
		mLogger.log(Level.INFO, "PlayerPauseEvent: firing...");
		PlayerPauseEvent.fire(this);
		mLogger.log(Level.INFO, "PlayerPauseEvent: fired.");
	}

	@Override
	public void onEnded() {
		mLogger.info("onEnded()...");
		mLogger.log(Level.INFO, "PlayerPlayEndedEvent: firing...");
		PlayerPlayEndedEvent.fire(this);
		mLogger.log(Level.INFO, "PlayerPlayEndedEvent: firing...");
	}

	@Override
	public void onDurationChanged(double pNewDuration) {
		mLogger.info("onDurationChanged() : " + pNewDuration);
	}

	@Override
	public void onControlVolumeChangeEvent(ControlVolumeChangeEvent pEvent) {
	}

	@Override
	public void onControlFullScreenEvent(ControlFullScreenEvent pEvent) {
	}

	@Override
	public void onControlSeekedEvent(ControlSeekedEvent pEvent) {
	}

	@Override
	public void onControlUnmuteEvent(ControlUnmuteEvent pEvent) {
	}

	@Override
	public void onControlMuteEvent(ControlMuteEvent pEvent) {
	}

	@Override
	public void onControlPauseEvent(ControlPauseEvent pEvent) {
	}

	@Override
	public void onControlPlayEvent(ControlPlayEvent pEvent) {
	}

	@Override
	public void onApplicationReadyEvent(ApplicationReadyEvent pEvent) {
	}

	@Override
	public void onPlaylistPlayEndedEvent(PlaylistPlayEndedEvent pEvent) {
	}

	@Override
	public void onPlaylistPlayEvent(PlaylistPlayEvent pEvent) {
	}

}
