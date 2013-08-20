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
public class ApplicationReadyEvent extends GwtEvent<ApplicationReadyEvent.ApplicationReadyHandler> {
    
	protected ApplicationReadyEvent() {
    }

    public static void fire(HasHandlers pSource) {
    	ApplicationReadyEvent oEventInstance = new ApplicationReadyEvent();
        pSource.fireEvent(oEventInstance);
    }

    //public static void fire(HasHandlers pSource, ApplicationReadyEvent pEventInstance) {
    //    pSource.fireEvent(pEventInstance);
    //}

    public interface ApplicationReadyHandlers extends HasHandlers {
        HandlerRegistration addApplicationReadyHandler(ApplicationReadyHandler pHandler);
    }

    public interface ApplicationReadyHandler extends EventHandler {
        public void onApplicationReadyEvent(ApplicationReadyEvent pEvent);
    }

    private static final Type<ApplicationReadyHandler> TYPE = new Type<ApplicationReadyHandler>();

    public static Type<ApplicationReadyHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ApplicationReadyHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ApplicationReadyHandler pHandler) {
        pHandler.onApplicationReadyEvent(this);
    }
    
}
