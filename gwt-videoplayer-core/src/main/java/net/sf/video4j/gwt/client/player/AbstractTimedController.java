/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import java.util.ArrayList;
import java.util.List;

import com.google.web.bindery.event.shared.HandlerRegistration;

import net.sf.video4j.gwt.client.event.TimeCompleteEvent;
import net.sf.video4j.gwt.client.event.TimeUpdateEvent;
import net.sf.video4j.gwt.client.event.TimeCompleteEvent.TimeCompleteHandler;
import net.sf.video4j.gwt.client.event.TimeUpdateEvent.TimeUpdateHandler;
import fr.hd3d.html5.video.client.VideoWidget;
import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.events.VideoErrorEvent;
import fr.hd3d.html5.video.client.events.VideoPauseEvent;
import fr.hd3d.html5.video.client.events.VideoPlayingEvent;
import fr.hd3d.html5.video.client.events.VideoSeekedEvent;
import fr.hd3d.html5.video.client.events.VideoWaitingEvent;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;
import fr.hd3d.html5.video.client.handlers.VideoErrorHandler;
import fr.hd3d.html5.video.client.handlers.VideoPauseHandler;
import fr.hd3d.html5.video.client.handlers.VideoPlayingHandler;
import fr.hd3d.html5.video.client.handlers.VideoSeekedHandler;
import fr.hd3d.html5.video.client.handlers.VideoWaitingHandler;

/**
 * @author luc
 *
 */
public abstract class AbstractTimedController implements 
	MediaController,
	VideoEndedHandler,
	VideoErrorHandler,
	VideoPauseHandler,
	VideoSeekedHandler,
	VideoPlayingHandler,
	VideoWaitingHandler,
	TimeCompleteHandler,
	TimeUpdateHandler {

	private VideoWidget mVideoWidget;
	private ITimeTracker mTimeTracker;
	private List<HandlerRegistration> mHandlerRegistrations = new ArrayList<HandlerRegistration>();
	
	/**
	 * 
	 */
	public AbstractTimedController(VideoWidget pVideoWidget, PlayItem pPlayItem) {
		mVideoWidget = pVideoWidget;
		mTimeTracker = newTimeTracker(pVideoWidget, pPlayItem);
		registerHandlers();
	}
	
	protected abstract ITimeTracker newTimeTracker(VideoWidget pVideoWidget, PlayItem pPlayItem);
	

	private void registerHandlers() {
		addHandlerRegistration( mVideoWidget.addEndedHandler(this) );
		addHandlerRegistration( mVideoWidget.addErrorHandler(this) );
		addHandlerRegistration( mVideoWidget.addPauseHanlder(this) );
		addHandlerRegistration( mVideoWidget.addSeekedHandler(this) );
		addHandlerRegistration( mVideoWidget.addPlayingHandler(this) );
		addHandlerRegistration( mVideoWidget.addWaitingHandler(this) );
		
		addHandlerRegistration( mTimeTracker.addTimeCompleteHandler(this) );
		addHandlerRegistration( mTimeTracker.addTimeUpdateHandler(this) );
	}
	
	private void addHandlerRegistration(HandlerRegistration pHandler) {
		mHandlerRegistrations.add(pHandler);
	}
	
	private void unregisterHandlers() {
		for (HandlerRegistration oHR : mHandlerRegistrations) oHR.removeHandler();
		mHandlerRegistrations.clear();
	}

	@Override
	public void onTimeCompleteEvent(TimeCompleteEvent pEvent) {
		unregisterHandlers();
		onDurationReached();
	}
	
	@Override
	public void onTimeUpdateEvent(TimeUpdateEvent pEvent) {
		
	}
	
	@Override
	public void onError(VideoErrorEvent pEvent) {
		//TODO:????
	}
	
	@Override
	public void onPause(VideoPauseEvent pEvent) {
		mTimeTracker.stop();
	}
	
	@Override
	public void onPlaying(VideoPlayingEvent pEvent) {
		mTimeTracker.start();
	}
	
	@Override
	public void onSeeked(VideoSeekedEvent pEvent) {
		mTimeTracker.setCheckpoint(Math.round(pEvent.getCurrentTime() * 1000));
	}
	
	@Override
	public void onVideoEnded(VideoEndedEvent pEvent) {
		mTimeTracker.stop(); //TODO: sure??
	}
	
	@Override
	public void onWaiting(VideoWaitingEvent pEvent) {
		mTimeTracker.stop();
	}
	
	
	
	protected void onDurationReached() {
		// TODO Auto-generated method stub
		
	}


}
