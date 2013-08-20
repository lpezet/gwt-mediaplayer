/**
 * 
 */
package net.sf.video4j.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author luc
 *
 */
public class TimeUpdateEvent extends GwtEvent<TimeUpdateEvent.TimeUpdateHandler> {
	
	private double mCurrentTime;
	
    protected TimeUpdateEvent(double pCurrentTime) {
    	mCurrentTime = pCurrentTime;
    }
    
    public double getCurrentTime() {
		return mCurrentTime;
	}
    
    public static void fire(HasHandlers pSource, double pCurrentTime) {
    	TimeUpdateEvent oEventInstance = new TimeUpdateEvent(pCurrentTime);
        pSource.fireEvent(oEventInstance);
    }

    public interface TimeUpdateHandlers extends HasHandlers {
        HandlerRegistration addTimeUpdateHandler(TimeUpdateHandler pHandler);
    }

    public interface TimeUpdateHandler extends EventHandler {
        public void onTimeUpdateEvent(TimeUpdateEvent pEvent);
    }

    private static final Type<TimeUpdateHandler> TYPE = new Type<TimeUpdateHandler>();

    public static Type<TimeUpdateHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<TimeUpdateHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(TimeUpdateHandler pHandler) {
        pHandler.onTimeUpdateEvent(this);
    }
    
}
