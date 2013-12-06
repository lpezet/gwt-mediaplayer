package net.sf.video4j.gwt.client.view;

import java.util.logging.Logger;

import net.sf.video4j.gwt.client.handler.PlayerUiHandlers;

import com.gwtplatform.mvp.client.ViewWithUiHandlers;

import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.events.VideoDurationChangeEvent;
import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.events.VideoErrorEvent;
import fr.hd3d.html5.video.client.events.VideoPauseEvent;
import fr.hd3d.html5.video.client.events.VideoPlayingEvent;
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.handlers.VideoDurationChangeHandler;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;
import fr.hd3d.html5.video.client.handlers.VideoErrorHandler;
import fr.hd3d.html5.video.client.handlers.VideoPauseHandler;
import fr.hd3d.html5.video.client.handlers.VideoPlayingHandler;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;

/**
 * @author Gustavo Matias
 */
public abstract class BasePlayerView extends ViewWithUiHandlers<PlayerUiHandlers> {

	protected Logger	mLogger	= Logger.getLogger(this.getClass().getName());

	protected abstract VideoWidget getVideoWidget();

	protected void setUpVideoWidgetHandlers() {
		getVideoWidget().addTimeUpdateHandler(new VideoTimeUpdateHandler() {
			@Override
			public void onTimeUpdated(VideoTimeUpdateEvent pEvent) {
				getUiHandlers().onTimeUpdate(pEvent.getCurrentTime());
			}
		});
		getVideoWidget().addErrorHandler(new VideoErrorHandler() {
			@Override
			public void onError(VideoErrorEvent pEvent) {
				getUiHandlers().onError();
			}
		});
		getVideoWidget().addPlayingHandler(new VideoPlayingHandler() {
			@Override
			public void onPlaying(VideoPlayingEvent pEvent) {
				getUiHandlers().onPlaying();
			}
		});
		getVideoWidget().addPauseHanlder(new VideoPauseHandler() {
			@Override
			public void onPause(VideoPauseEvent pEvent) {
				getUiHandlers().onPause();
			}
		});
		getVideoWidget().addEndedHandler(new VideoEndedHandler() {
			@Override
			public void onVideoEnded(VideoEndedEvent pEvent) {
				getUiHandlers().onEnded();
			}
		});
		getVideoWidget().addDurationChangeHandler(new VideoDurationChangeHandler() {
			@Override
			public void onDurationChange(VideoDurationChangeEvent pEvent) {
				getUiHandlers().onDurationChanged(getVideoWidget().getDuration());
			}
		});
	}

}