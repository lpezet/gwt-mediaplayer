/**
 * 
 */
package net.sf.video4j.gwt.client.player;


/**
 * @author luc
 *
 */
public class SimpleTimeTracker extends BaseTimeTracker {

	public SimpleTimeTracker(PlayItem pPlayItem) {
		super(pPlayItem);
	}
	
	@Override
	public void start() {
		// nop
	}
	
	@Override
	public void stop() {
		// nop
	}
	
	@Override
	public void setCheckpoint(long pTimeInMillis) {
		// nop
	}
	
	public void currentTime(double pTime) {
		checkDurationReached(pTime);
	}

	private void checkDurationReached(double pTime) {
		if (getPlayItemDuration() <= pTime) {
			fireTimeComplete();
		} else {
			fireTimeUpdate(pTime);
		}
	}

	
	

}
