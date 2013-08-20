/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import com.google.gwt.user.client.Timer;

/**
 * @author luc
 *
 */
public class TimerBasedTimeTracker extends BaseTimeTracker {
	
	private long mStartTime;
	private long mCheckpointTime;
	private Timer mTimer;

	public TimerBasedTimeTracker(PlayItem pPlayItem) {
		super(pPlayItem);
	}
	
	public void start() {
		mStartTime = getClockTime();
		if (mTimer != null) mTimer.cancel();
		
		mTimer = new Timer() {
			@Override
			public void run() {
				monitor();
			}
		};
		mTimer.scheduleRepeating(50);
		mTimer.run();
	}
	
	public void stop() {
		mCheckpointTime = getClockTime();
		mTimer.cancel();
	}
	
	public void setCheckpoint(long pTimeInMillis) {
		mCheckpointTime = pTimeInMillis;
	}
	
	private void monitor() {
		long oPlayItemDuration = getPlayItemDuration();
		long oTimePassed = getTimePassed();
		if (oTimePassed >= oPlayItemDuration) {
			stop();
			fireTimeComplete();
		} else {
			fireTimeUpdate(oTimePassed);
		}
	}
	
	public long getTimePassed() {
		long oNow = getClockTime();
		long oResult = mCheckpointTime + (oNow - mStartTime);
		return oResult;
	}

}
