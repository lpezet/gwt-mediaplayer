/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import java.util.List;

import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.user.client.Timer;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.HandlerRegistration;

import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.events.VideoPlayingEvent;
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;
import fr.hd3d.html5.video.client.handlers.VideoPlayingHandler;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;

/**
 * @author luc
 *
 */
public class PlayTimeMonitor {

	private static class VideoEndedHandlerImpl implements VideoEndedHandler {
		
		private boolean mEnded = false;
		
		@Override
		public void onVideoEnded(VideoEndedEvent pEvent) {
			mEnded = true;
		}
		
		public boolean isEnded() {
			return mEnded;
		}
	}
	
	private static class VideoTimeUpdateHandlerImpl implements VideoTimeUpdateHandler {
		private transient double mLastTime;
		private transient double mDuration;
		
		@Override
		public void onTimeUpdated(VideoTimeUpdateEvent pEvent) {
			mLastTime = pEvent.getCurrentTime();
			mDuration = pEvent.getDuration();
		}
		
		public double getDuration() {
			return mDuration;
		}
		
		public double getLastTime() {
			return mLastTime;
		}
		
	}
	
	private Timer mTimer;
	private PlayItem mPlayItem;
	private EventBus mEventBus;
	private VideoEndedHandlerImpl mVideoEndedHandler = new VideoEndedHandlerImpl();
	private VideoTimeUpdateHandlerImpl mVideoTimeUpdateHandler = new VideoTimeUpdateHandlerImpl();
	private final transient List<HandlerRegistration> mHandlerRegistrations = new java.util.ArrayList<HandlerRegistration>();
	
	public PlayTimeMonitor(EventBus pEventBus, PlayItem pPlayItem) {
		mTimer = new Timer() {
			@Override
			public void run() {
				monitor();
			}
		};
		mTimer.scheduleRepeating(50);
		mPlayItem = pPlayItem;
		mEventBus = pEventBus;
		setupListeners();
	}
	
	public <T> void addRegisteredHandler(Type<T> pType, T pHandler) {
		mHandlerRegistrations.add( mEventBus.addHandler(pType, pHandler) );
	}
	
	private void setupListeners() {
		addRegisteredHandler( VideoEndedEvent.getType(), mVideoEndedHandler );
		addRegisteredHandler( VideoTimeUpdateEvent.getType(), mVideoTimeUpdateHandler);
	}

	private void monitor() {
		if (checkPlayEnded()) {
			shutdown();
			//mEventBus.fireEvent(new PlaytimeCompletedEvent(mPlayItem));
		}
	}
	
	public void shutdown() {
		mTimer.cancel();
		for (HandlerRegistration r : mHandlerRegistrations) {
			r.removeHandler();
		}
		mHandlerRegistrations.clear();
	}

	/**
	 * Listen to CurrentPlayTimeUpdateEvent???
	 * @return
	 */
	private double getCurrentPlayTime() {
		return mVideoTimeUpdateHandler.getLastTime();
	}
	
	private boolean checkPlayEnded() {
		if (mVideoEndedHandler.isEnded()) {
			return true;
		}
		if (mPlayItem.getEnd() > 0) {
			return mPlayItem.getEnd() <= getCurrentPlayTime(); // TODO: use a second timer to monitor current time from player whether it's stalling or not (and also make sure player state is OK and not in ERROR or STALLED loading data).
		} else {
			// playing item until the end...relying on VideoEndedHandler here then.
			return false;
		}
	}
	
}
