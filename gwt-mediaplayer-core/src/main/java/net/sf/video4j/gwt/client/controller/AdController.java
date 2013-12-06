/**
 * 
 */
package net.sf.video4j.gwt.client.controller;

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
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent.PlaylistPlayHandler;
import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;
import net.sf.video4j.gwt.client.model.IPlugin;

import com.google.web.bindery.event.shared.EventBus;

/**
 * @author luc
 *
 */
public class AdController extends BaseController
		implements IPlugin, PlaylistPlayHandler, ApplicationLoadHandler, ApplicationInitHandler,
		ApplicationReadyHandler, PlayerUiHandlers, ControlPlayHandler, ControlPauseHandler,
		ControlMuteHandler, ControlUnmuteHandler, ControlSeekedHandler, ControlFullScreenHandler,
		ControlVolumeChangeHandler {

	public AdController(EventBus pEventBus) {
		super(pEventBus);
		registerHandlers();
	}

	private void registerHandlers() {
		addRegisteredHandler(PlaylistPlayEvent.getType(), this);
		addRegisteredHandler(ApplicationLoadEvent.getType(), this);
		addRegisteredHandler(ApplicationInitEvent.getType(), this);
		addRegisteredHandler(ApplicationReadyEvent.getType(), this);
		addRegisteredHandler(ControlPlayEvent.getType(), this);
		addRegisteredHandler(ControlPauseEvent.getType(), this);
		addRegisteredHandler(ControlMuteEvent.getType(), this);
		addRegisteredHandler(ControlUnmuteEvent.getType(), this);
		addRegisteredHandler(ControlSeekedEvent.getType(), this);
		addRegisteredHandler(ControlFullScreenEvent.getType(), this);
		addRegisteredHandler(ControlVolumeChangeEvent.getType(), this);
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
	public void onTimeUpdate(double pCurrentTime) {
	}

	@Override
	public void onError() {
	}

	@Override
	public void onPlaying() {
	}

	@Override
	public void onPause() {
	}

	@Override
	public void onEnded() {
	}

	@Override
	public void onDurationChanged(double pNewDuration) {
	}

	@Override
	public void onApplicationReadyEvent(ApplicationReadyEvent pEvent) {
	}

	@Override
	public void onApplicationInitEvent(ApplicationInitEvent pEvent) {
	}

	@Override
	public void onApplicationLoadEvent(ApplicationLoadEvent pEvent) {
	}

	@Override
	public void onPlaylistPlayEvent(PlaylistPlayEvent pEvent) {
	}

	@Override
	public String getPluginId() {
		return this.getClass().getName();
	}

}