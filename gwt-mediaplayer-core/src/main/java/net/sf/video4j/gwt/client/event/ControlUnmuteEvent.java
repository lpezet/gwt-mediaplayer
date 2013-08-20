package net.sf.video4j.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author gumatias
 */
public class ControlUnmuteEvent extends GwtEvent<ControlUnmuteEvent.ControlUnmuteHandler> {
    
    protected ControlUnmuteEvent() {
    }

    public static void fire(HasHandlers pSource) {
        ControlUnmuteEvent oEventInstance = new ControlUnmuteEvent();
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, ControlUnmuteEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface HasControlUnmuteHandlers extends HasHandlers {
        HandlerRegistration addControlUnmuteHandler(ControlUnmuteHandler pHandler);
    }

    public interface ControlUnmuteHandler extends EventHandler {
        public void onControlUnmuteEvent(ControlUnmuteEvent pEvent);
    }

    private static final Type<ControlUnmuteHandler> TYPE = new Type<ControlUnmuteHandler>();

    public static Type<ControlUnmuteHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ControlUnmuteHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ControlUnmuteHandler pHandler) {
        pHandler.onControlUnmuteEvent(this);
    }
    
}