/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import net.sf.video4j.gwt.client.event.TimeCompleteEvent.TimeCompleteHandlers;
import net.sf.video4j.gwt.client.event.TimeUpdateEvent.TimeUpdateHandlers;

/**
 * @author luc
 *
 */
public interface ITimeTracker extends TimeCompleteHandlers, TimeUpdateHandlers {

	public void start();
	
	public void stop();
	
	public void setCheckpoint(long pTimeInMillis);
	
}
