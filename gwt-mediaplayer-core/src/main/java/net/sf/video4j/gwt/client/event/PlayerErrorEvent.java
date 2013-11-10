/**
 * 
 */
package net.sf.video4j.gwt.client.event;

import net.sf.video4j.gwt.client.event.PlayerPauseEvent.PlayerPauseHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author luc
 *
 */
public class PlayerErrorEvent  extends GwtEvent<PlayerErrorEvent.PlayerErrorHandler> {
    
	public PlayerErrorEvent() {
		
    }

    public static void fire(HasHandlers pSource) {
    	PlayerPauseEvent oEventInstance = new PlayerPauseEvent();
        pSource.fireEvent(oEventInstance);
    }

    //public static void fire(HasHandlers pSource, PlayerPlayingEvent pEventInstance) {
    //    pSource.fireEvent(pEventInstance);
    //}

    public interface PlayerErrorHandlers extends HasHandlers {
        HandlerRegistration addPlayerErrorHandler(PlayerErrorHandler pHandler);
    }

    public interface PlayerErrorHandler extends EventHandler {
        public void onPlayerErrorEvent(PlayerErrorEvent pEvent);
    }

    private static final Type<PlayerErrorHandler> TYPE = new Type<PlayerErrorHandler>();

    public static Type<PlayerErrorHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<PlayerErrorHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayerErrorHandler pHandler) {
        pHandler.onPlayerErrorEvent(this);
    }


}
