/**
 * 
 */
package net.sf.video4j.gwt.client.event;

import net.sf.video4j.gwt.client.event.PlayerPlayEvent.PlayHandler;
import net.sf.video4j.gwt.client.event.PlayerPlayingEvent.PlayerPlayingHandler;
import net.sf.video4j.gwt.client.player.PlayItem;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author luc
 *
 */
public class PlayerPauseEvent extends GwtEvent<PlayerPauseEvent.PlayerPauseHandler> {
    
	public PlayerPauseEvent() {
		
    }

    public static void fire(HasHandlers pSource) {
    	PlayerPauseEvent oEventInstance = new PlayerPauseEvent();
        pSource.fireEvent(oEventInstance);
    }

    //public static void fire(HasHandlers pSource, PlayerPlayingEvent pEventInstance) {
    //    pSource.fireEvent(pEventInstance);
    //}

    public interface PlayerPauseHandlers extends HasHandlers {
        HandlerRegistration addPlayerPauseHandler(PlayerPauseHandler pHandler);
    }

    public interface PlayerPauseHandler extends EventHandler {
        public void onPlayerPauseEvent(PlayerPauseEvent pEvent);
    }

    private static final Type<PlayerPauseHandler> TYPE = new Type<PlayerPauseHandler>();

    public static Type<PlayerPauseHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<PlayerPauseHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayerPauseHandler pHandler) {
        pHandler.onPlayerPauseEvent(this);
    }

}
