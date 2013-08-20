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
public class PlayerBeforePlayEvent extends GwtEvent<PlayerBeforePlayEvent.BeforePlayHandler> {
    
    protected PlayerBeforePlayEvent() {
    }

    public static void fire(HasHandlers pSource) {
    	PlayerBeforePlayEvent oEventInstance = new PlayerBeforePlayEvent();
        pSource.fireEvent(oEventInstance);
    }

    public static void fire(HasHandlers pSource, PlayerBeforePlayEvent pEventInstance) {
        pSource.fireEvent(pEventInstance);
    }

    public interface BeforePlayHandlers extends HasHandlers {
        HandlerRegistration addBeforePlayHandler(BeforePlayHandler pHandler);
    }

    public interface BeforePlayHandler extends EventHandler {
        public void onBeforePlayEvent(PlayerBeforePlayEvent pEvent);
    }

    private static final Type<BeforePlayHandler> TYPE = new Type<BeforePlayHandler>();

    public static Type<BeforePlayHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<BeforePlayHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(BeforePlayHandler pHandler) {
        pHandler.onBeforePlayEvent(this);
    }
    
}
