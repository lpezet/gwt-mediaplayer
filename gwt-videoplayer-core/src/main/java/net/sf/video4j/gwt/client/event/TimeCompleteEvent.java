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
public class TimeCompleteEvent extends GwtEvent<TimeCompleteEvent.TimeCompleteHandler> {
	
    protected TimeCompleteEvent() {
    }
    
    public static void fire(HasHandlers pSource) {
    	TimeCompleteEvent oEventInstance = new TimeCompleteEvent();
        pSource.fireEvent(oEventInstance);
    }

    //public static void fire(HasHandlers pSource, TimerCompleteEvent pEventInstance) {
    //    pSource.fireEvent(pEventInstance);
    //}

    public interface TimeCompleteHandlers extends HasHandlers {
        HandlerRegistration addTimeCompleteHandler(TimeCompleteHandler pHandler);
    }

    public interface TimeCompleteHandler extends EventHandler {
        public void onTimeCompleteEvent(TimeCompleteEvent pEvent);
    }

    private static final Type<TimeCompleteHandler> TYPE = new Type<TimeCompleteHandler>();

    public static Type<TimeCompleteHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<TimeCompleteHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(TimeCompleteHandler pHandler) {
        pHandler.onTimeCompleteEvent(this);
    }
    
}
