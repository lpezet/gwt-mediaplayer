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
public class PlayerTimeUpdateEvent extends GwtEvent<PlayerTimeUpdateEvent.PlayerTimeUpdateHandler> {
    
	private double mCurrentTime;
	
    public PlayerTimeUpdateEvent(double pCurrentTime) {
    	mCurrentTime = pCurrentTime;
    }
    
    public double getCurrentTime() {
		return mCurrentTime;
	}

    public static void fire(HasHandlers pSource, double pCurrentTime) {
    	PlayerTimeUpdateEvent oEventInstance = new PlayerTimeUpdateEvent(pCurrentTime);
        pSource.fireEvent(oEventInstance);
    }

    //public static void fire(HasHandlers pSource, PlayerPlayingEvent pEventInstance) {
    //    pSource.fireEvent(pEventInstance);
    //}

    public interface PlayerTimeUpdateHandlers extends HasHandlers {
        HandlerRegistration addPlayerTimeUpdateHandler(PlayerTimeUpdateHandler pHandler);
    }

    public interface PlayerTimeUpdateHandler extends EventHandler {
        public void onPlayerTimeUpdateEvent(PlayerTimeUpdateEvent pEvent);
    }

    private static final Type<PlayerTimeUpdateHandler> TYPE = new Type<PlayerTimeUpdateHandler>();

    public static Type<PlayerTimeUpdateHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<PlayerTimeUpdateHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PlayerTimeUpdateHandler pHandler) {
        pHandler.onPlayerTimeUpdateEvent(this);
    }    

}
