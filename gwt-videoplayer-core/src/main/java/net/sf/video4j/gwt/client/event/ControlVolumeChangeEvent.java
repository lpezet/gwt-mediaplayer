package net.sf.video4j.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author gumatias
 */
public class ControlVolumeChangeEvent extends GwtEvent<ControlVolumeChangeEvent.ControlVolumeChangeHandler> {
    
    private double mValue;
    
    protected ControlVolumeChangeEvent() {
    }
    
    public ControlVolumeChangeEvent(double pValue) {
        mValue = pValue;
    }

    public static void fire(HasHandlers pSource, double pValue) {
        ControlVolumeChangeEvent oEventInstance = new ControlVolumeChangeEvent(pValue);
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, ControlVolumeChangeEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface HasControlVolumeChangeHandlers extends HasHandlers {
        HandlerRegistration addControlVolumeChangeHandler(ControlVolumeChangeHandler pHandler);
    }

    public interface ControlVolumeChangeHandler extends EventHandler {
        public void onControlVolumeChangeEvent(ControlVolumeChangeEvent pEvent);
    }

    private static final Type<ControlVolumeChangeHandler> TYPE = new Type<ControlVolumeChangeHandler>();

    public static Type<ControlVolumeChangeHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ControlVolumeChangeHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ControlVolumeChangeHandler pHandler) {
        pHandler.onControlVolumeChangeEvent(this);
    }
    
    public double getValue() {
        return mValue;
    }
    
}