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
public class PlayerPlayingEvent extends GwtEvent<PlayerPlayingEvent.PlayingHandler> {
    
	private double mCurrentTime;
	
    protected PlayerPlayingEvent(double pCurrentTime) {
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

    public interface PlayingHandlers extends HasHandlers {
        HandlerRegistration addPlayingHandler(PlayHandler pHandler);
    }

    public interface PlayingHandler extends EventHandler {
        public void onPlayingEvent(PlayerPlayingEvent pEvent);
    }

    private static final Type<PlayingHandler> TYPE = new Type<PlayingHandler>();

    public static Type<PlayingHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<PlayingHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayingHandler pHandler) {
        pHandler.onPlayingEvent(this);
    }
    
}
