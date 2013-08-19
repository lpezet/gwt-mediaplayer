/**
 * 
 */
package net.sf.video4j.gwt.client.event;

import net.sf.video4j.gwt.client.event.PlayerPlayEvent.PlayHandler;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author luc
 *
 */
public class PlayerPlayingEvent extends GwtEvent<PlayerPlayingEvent.PlayerPlayingHandler> {
    
	private double mCurrentTime;
	
    public PlayerPlayingEvent(double pCurrentTime) {
    	mCurrentTime = pCurrentTime;
    }
    
    public double getCurrentTime() {
		return mCurrentTime;
	}

    public static void fire(HasHandlers pSource, double pCurrentTime) {
    	PlayerPlayingEvent oEventInstance = new PlayerPlayingEvent(pCurrentTime);
        pSource.fireEvent(oEventInstance);
    }

    //public static void fire(HasHandlers pSource, PlayerPlayingEvent pEventInstance) {
    //    pSource.fireEvent(pEventInstance);
    //}

    public interface PlayerPlayingHandlers extends HasHandlers {
        HandlerRegistration addPlayerPlayingHandler(PlayHandler pHandler);
    }

    public interface PlayerPlayingHandler extends EventHandler {
        public void onPlayerPlayingEvent(PlayerPlayingEvent pEvent);
    }

    private static final Type<PlayerPlayingHandler> TYPE = new Type<PlayerPlayingHandler>();

    public static Type<PlayerPlayingHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<PlayerPlayingHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayerPlayingHandler pHandler) {
        pHandler.onPlayerPlayingEvent(this);
    }
    
}
