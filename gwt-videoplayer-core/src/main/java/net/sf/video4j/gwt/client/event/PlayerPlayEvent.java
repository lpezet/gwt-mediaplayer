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
public class PlayerPlayEvent extends GwtEvent<PlayerPlayEvent.PlayHandler> {
    
    protected PlayerPlayEvent() {
    }

    public static void fire(HasHandlers pSource) {
    	PlayerPlayEvent oEventInstance = new PlayerPlayEvent();
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, PlayerPlayEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface PlayHandlers extends HasHandlers {
        HandlerRegistration addPlayHandler(PlayHandler pHandler);
    }

    public interface PlayHandler extends EventHandler {
        public void onPlayEvent(PlayerPlayEvent pEvent);
    }

    private static final Type<PlayHandler> TYPE = new Type<PlayHandler>();

    public static Type<PlayHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<PlayHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayHandler pHandler) {
        pHandler.onPlayEvent(this);
    }
    
}
