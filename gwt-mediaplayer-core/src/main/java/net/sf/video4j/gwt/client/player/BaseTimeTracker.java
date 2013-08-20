/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import net.sf.video4j.gwt.client.event.TimeCompleteEvent;
import net.sf.video4j.gwt.client.event.TimeCompleteEvent.TimeCompleteHandler;
import net.sf.video4j.gwt.client.event.TimeCompleteEvent.TimeCompleteHandlers;
import net.sf.video4j.gwt.client.event.TimeUpdateEvent;
import net.sf.video4j.gwt.client.event.TimeUpdateEvent.TimeUpdateHandler;
import net.sf.video4j.gwt.client.event.TimeUpdateEvent.TimeUpdateHandlers;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerManager;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author luc
 *
 */
public abstract class BaseTimeTracker implements 
	ITimeTracker, 
	TimeCompleteHandlers,
	TimeUpdateHandlers {
	
	private PlayItem mPlayItem;
	private HandlerManager mHandlerManager;
	
	public BaseTimeTracker(PlayItem pPlayItem) {
		mPlayItem = pPlayItem;
	}
	
	@Override
	public HandlerRegistration addTimeUpdateHandler(TimeUpdateHandler pHandler) {
		return mHandlerManager.addHandler(TimeUpdateEvent.getType(), pHandler);
	}
	
	@Override
	public HandlerRegistration addTimeCompleteHandler(TimeCompleteHandler pHandler) {
		return mHandlerManager.addHandler(TimeCompleteEvent.getType(), pHandler);
	}
	
	@Override
	public void fireEvent(GwtEvent<?> pEvent) {
		mHandlerManager.fireEvent(pEvent);
	}
	
	protected long getPlayItemDuration() {
		if (mPlayItem.getEnd() < 0) return -1;
		return mPlayItem.getEnd() - mPlayItem.getStart();
	}
	
	protected void fireTimeUpdate(double pCurrentTime) {
		TimeUpdateEvent.fire(this, pCurrentTime);
	}
	
	protected void fireTimeComplete() {
		TimeCompleteEvent.fire(this);
	}
	
	public long getClockTime() {
		return System.currentTimeMillis();
	}
	
}
