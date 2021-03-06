package net.sf.video4j.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author gumatias
 */
public class ControlSeekedEvent extends GwtEvent<ControlSeekedEvent.ControlSeekedHandler> {
	
	private double mValue;
    
    protected ControlSeekedEvent() {
    }
    
    protected ControlSeekedEvent(double pValue) {
    	mValue = pValue;
    }

    public static void fire(HasHandlers pSource, double pValue) {
        ControlSeekedEvent oEventInstance = new ControlSeekedEvent(pValue);
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, ControlSeekedEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface HasControlSeekedHandlers extends HasHandlers {
        HandlerRegistration addControlSeekedHandler(ControlSeekedHandler pHandler);
    }

    public interface ControlSeekedHandler extends EventHandler {
        public void onControlSeekedEvent(ControlSeekedEvent pEvent);
    }

    private static final Type<ControlSeekedHandler> TYPE = new Type<ControlSeekedHandler>();

    public static Type<ControlSeekedHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ControlSeekedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ControlSeekedHandler pHandler) {
        pHandler.onControlSeekedEvent(this);
    }
    
    public double getValue() {
		return mValue;
	}
    
}