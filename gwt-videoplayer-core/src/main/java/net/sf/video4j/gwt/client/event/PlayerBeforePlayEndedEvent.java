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
public class PlayerBeforePlayEndedEvent extends GwtEvent<PlayerBeforePlayEndedEvent.BeforePlayEndedHandler> {
    
    protected PlayerBeforePlayEndedEvent() {
    }

    public static void fire(HasHandlers pSource) {
    	PlayerBeforePlayEndedEvent oEventInstance = new PlayerBeforePlayEndedEvent();
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, PlayerBeforePlayEndedEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface BeforePlayEndedHandlers extends HasHandlers {
        HandlerRegistration addBeforePlayEndedHandler(BeforePlayEndedHandler pHandler);
    }

    public interface BeforePlayEndedHandler extends EventHandler {
        public void onBeforePlayEndedEvent(PlayerBeforePlayEndedEvent pEvent);
    }

    private static final Type<BeforePlayEndedHandler> TYPE = new Type<BeforePlayEndedHandler>();

    public static Type<BeforePlayEndedHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<BeforePlayEndedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(BeforePlayEndedHandler pHandler) {
        pHandler.onBeforePlayEndedEvent(this);
    }
    
}
