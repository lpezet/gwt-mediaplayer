/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import net.sf.video4j.gwt.client.event.TimerCompleteEvent;

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
	private long mStoppedTime;
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
	
	private long getPlayItemDuration() {
		if (mPlayItem.getEnd() < 0) return -1;
		return mPlayItem.getEnd() - mPlayItem.getStart();
	}
	
	protected void monitor() {
		long oPlayItemDuration = getPlayItemDuration();
		long oTimePassed = getTimePassed();
		if (oTimePassed >= oPlayItemDuration) {
			stop();
			TimerCompleteEvent.fire(this); // TODO: Async???
		}
	}

	public void stop() {
		mTimer.cancel();
		mStoppedTime = getClockTime();
	}
	
	public void shutdown() {
		mTimer.cancel();
	}
	
	public long getTimePassed() {
		long oNow = getClockTime();
		long oResult = mStoppedTime + (oNow - mStartTime);
		return oResult;
	}
	
	public long getClockTime() {
		return System.currentTimeMillis();
	}
	
}
