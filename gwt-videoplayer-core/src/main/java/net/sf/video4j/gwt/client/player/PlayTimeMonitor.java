/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.Timer;

import fr.hd3d.html5.video.client.events.VideoEndedEvent;
import fr.hd3d.html5.video.client.handlers.VideoEndedHandler;

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
	
	private Timer mTimer;
	private PlayItem mPlayItem;
	private EventBus mEventBus;
	private VideoEndedHandlerImpl mVideoEndedHandler = new VideoEndedHandlerImpl();
	private List<HandlerRegistration> mHandlersRegistered = new ArrayList<HandlerRegistration>(); 
	
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
	
	private void setupListeners() {
		mHandlersRegistered.add( mEventBus.addHandler(VideoEndedEvent.getType(), mVideoEndedHandler) );
	}

	private void monitor() {
		if (checkPlayEnded()) {
			shutdown();
			//mEventBus.fireEvent(new PlaytimeCompletedEvent(mPlayItem));
		}
	}
	
	public void shutdown() {
		mTimer.cancel();
		for (HandlerRegistration r : mHandlersRegistered) {
			r.removeHandler();
		}
	}

	/**
	 * Listen to CurrentPlayTimeUpdateEvent???
	 * @return
	 */
	private double getCurrentPlayTime() {
		//return getVideoPlayer().getCurrentTime();
		return 0.0;
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
