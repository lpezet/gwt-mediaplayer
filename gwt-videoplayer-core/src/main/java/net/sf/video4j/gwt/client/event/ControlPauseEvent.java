package net.sf.video4j.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author gumatias
 */
public class ControlPauseEvent extends GwtEvent<ControlPauseEvent.ControlPauseHandler> {
    
    protected ControlPauseEvent() {
    }

    public static void fire(HasHandlers pSource) {
        ControlPauseEvent oEventInstance = new ControlPauseEvent();
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, ControlPauseEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface HasControlPauseHandlers extends HasHandlers {
        HandlerRegistration addControlPauseHandler(ControlPauseHandler pHandler);
    }

    public interface ControlPauseHandler extends EventHandler {
        public void onControlPauseEvent(ControlPauseEvent pEvent);
    }

    private static final Type<ControlPauseHandler> TYPE = new Type<ControlPauseHandler>();

    public static Type<ControlPauseHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ControlPauseHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ControlPauseHandler pHandler) {
        pHandler.onControlPauseEvent(this);
    }
    
}