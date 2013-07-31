package net.sf.video4j.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author gumatias
 */
public class ControlMuteEvent extends GwtEvent<ControlMuteEvent.ControlMuteHandler> {
    
    protected ControlMuteEvent() {
    }

    public static void fire(HasHandlers pSource) {
        ControlMuteEvent oEventInstance = new ControlMuteEvent();
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, ControlMuteEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface HasControlMuteHandlers extends HasHandlers {
        HandlerRegistration addControlMuteHandler(ControlMuteHandler pHandler);
    }

    public interface ControlMuteHandler extends EventHandler {
        public void onControlMuteEvent(ControlMuteEvent pEvent);
    }

    private static final Type<ControlMuteHandler> TYPE = new Type<ControlMuteHandler>();

    public static Type<ControlMuteHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ControlMuteHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ControlMuteHandler pHandler) {
        pHandler.onControlMuteEvent(this);
    }
    
}