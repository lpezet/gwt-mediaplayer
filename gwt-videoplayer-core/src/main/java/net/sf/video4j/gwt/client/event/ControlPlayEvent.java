package net.sf.video4j.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author gumatias
 */
public class ControlPlayEvent extends GwtEvent<ControlPlayEvent.ControlPlayHandler> {
    
    protected ControlPlayEvent() {
    }

    public static void fire(HasHandlers pSource) {
        ControlPlayEvent oEventInstance = new ControlPlayEvent();
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, ControlPlayEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface HasControlPlayHandlers extends HasHandlers {
        HandlerRegistration addControlPlayHandler(ControlPlayHandler pHandler);
    }

    public interface ControlPlayHandler extends EventHandler {
        public void onControlPlayEvent(ControlPlayEvent pEvent);
    }

    private static final Type<ControlPlayHandler> TYPE = new Type<ControlPlayHandler>();

    public static Type<ControlPlayHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ControlPlayHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ControlPlayHandler pHandler) {
        pHandler.onControlPlayEvent(this);
    }
    
}