package net.sf.video4j.gwt.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author gumatias
 */
public class ControlFullScreenEvent extends GwtEvent<ControlFullScreenEvent.ControlFullScreenHandler> {
    
    protected ControlFullScreenEvent() {
    }

    public static void fire(HasHandlers pSource) {
        ControlFullScreenEvent oEventInstance = new ControlFullScreenEvent();
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, ControlFullScreenEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface HasControlFullScreenHandlers extends HasHandlers {
        HandlerRegistration addControlFullScreenHandler(ControlFullScreenHandler pHandler);
    }

    public interface ControlFullScreenHandler extends EventHandler {
        public void onControlFullScreenEvent(ControlFullScreenEvent pEvent);
    }

    private static final Type<ControlFullScreenHandler> TYPE = new Type<ControlFullScreenHandler>();

    public static Type<ControlFullScreenHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ControlFullScreenHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ControlFullScreenHandler pHandler) {
        pHandler.onControlFullScreenEvent(this);
    }
    
}