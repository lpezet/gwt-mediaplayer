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
public class PlayerPlayEndedEvent extends GwtEvent<PlayerPlayEndedEvent.PlayEndedHandler> {
    
    protected PlayerPlayEndedEvent() {
    }

    public static void fire(HasHandlers pSource) {
    	PlayerPlayEndedEvent oEventInstance = new PlayerPlayEndedEvent();
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, PlayerPlayEndedEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface PlayEndedHandlers extends HasHandlers {
        HandlerRegistration addPlayEndedHandler(PlayEndedHandler pHandler);
    }

    public interface PlayEndedHandler extends EventHandler {
        public void onPlayEndedEvent(PlayerPlayEndedEvent pEvent);
    }

    private static final Type<PlayEndedHandler> TYPE = new Type<PlayEndedHandler>();

    public static Type<PlayEndedHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<PlayEndedHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayEndedHandler pHandler) {
        pHandler.onPlayEndedEvent(this);
    }
    
}
