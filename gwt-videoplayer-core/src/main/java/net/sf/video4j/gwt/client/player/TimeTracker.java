/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import net.sf.video4j.gwt.client.event.TimeCompleteEvent;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.Timer;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author luc
 *
 */
public class TimeTracker implements HasHandlers {
	
	private long mStartTime;
	private long mCheckpointTime;
	private Timer mTimer;
	private PlayItem mPlayItem;
	private EventBus mEventBus;
	
	public TimeTracker(EventBus pEventBus, PlayItem pPlayItem) {
		mPlayItem = pPlayItem;
		mEventBus = pEventBus;
	}
	
	@Override
	public void fireEvent(GwtEvent<?> pEvent) {
		mEventBus.fireEvent(pEvent);
	}
	
	public void start() {
		if (mTimer != null) mTimer.cancel();
		
		mTimer = new Timer() {
			@Override
			public void run() {
				monitor();
			}
		};
		mTimer.scheduleRepeating(50);
		mStartTime = getClockTime();
		mTimer.run();
	}
	
	public void setCheckpoint(long pTimeInMillis) {
		mCheckpointTime = pTimeInMillis;
	}
	
	private long getPlayItemDuration() {
		if (mPlayItem.getEnd() < 0) return -1;
		return mPlayItem.getEnd() - mPlayItem.getStart();
	}
	
	protected void monitor() {
		long oPlayItemDuration = getPlayItemDuration();
		long oTimePassed = getTimePassed();
		if (oTimePassed >= oPlayItemDuration) {
			stop();
			TimeCompleteEvent.fire(this); // TODO: Async???
		}
	}

	public void stop() {
		mTimer.cancel();
		mCheckpointTime = getClockTime();
	}
	
	public void shutdown() {
		mTimer.cancel();
	}
	
	public long getTimePassed() {
		long oNow = getClockTime();
		long oResult = mCheckpointTime + (oNow - mStartTime);
		return oResult;
	}
	
	public long getClockTime() {
		return System.currentTimeMillis();
	}
	
}
