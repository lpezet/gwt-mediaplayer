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
public class PlayerPlayEndedEvent extends GwtEvent<PlayerPlayEndedEvent.PlayerPlayEndedHandler> {
    
    protected PlayerPlayEndedEvent() {
    }

    public static void fire(HasHandlers pSource) {
    	PlayerPlayEndedEvent oEventInstance = new PlayerPlayEndedEvent();
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, PlayerPlayEndedEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface PlayerPlayEndedHandlers extends HasHandlers {
        HandlerRegistration addPlayerPlayEndedHandler(PlayerPlayEndedHandler pHandler);
    }

    public interface PlayerPlayEndedHandler extends EventHandler {
        public void onPlayerPlayEndedEvent(PlayerPlayEndedEvent pEvent);
    }

    private static final Type<PlayerPlayEndedHandler> TYPE = new Type<PlayerPlayEndedHandler>();

    public static Type<PlayerPlayEndedHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<PlayerPlayEndedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayerPlayEndedHandler pHandler) {
        pHandler.onPlayerPlayEndedEvent(this);
    }
    
}
