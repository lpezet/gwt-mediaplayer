/**
 * 
 */
package net.sf.video4j.gwt.client.event;

import net.sf.video4j.gwt.client.model.ApplicationConfig;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author luc
 *
 */
public class ApplicationLoadEvent extends GwtEvent<ApplicationLoadEvent.ApplicationLoadHandler> {
    
	private ApplicationConfig mConfig;
	
	protected ApplicationLoadEvent(ApplicationConfig pConfig) {
		mConfig = pConfig;
    }
	
	public ApplicationConfig getConfig() {
		return mConfig;
	}

    public static void fire(HasHandlers pSource, ApplicationConfig pConfig) {
    	ApplicationLoadEvent oEventInstance = new ApplicationLoadEvent(pConfig);
        pSource.fireEvent(oEventInstance);
    }

    //public static void fire(HasHandlers pSource, ApplicationLoadEvent pEventInstance) {
    //    pSource.fireEvent(pEventInstance);
    //}

    public interface ApplicationLoadHandlers extends HasHandlers {
        HandlerRegistration addApplicationLoadHandler(ApplicationLoadHandler pHandler);
    }

    public interface ApplicationLoadHandler extends EventHandler {
        public void onApplicationLoadEvent(ApplicationLoadEvent pEvent);
    }

    private static final Type<ApplicationLoadHandler> TYPE = new Type<ApplicationLoadHandler>();

    public static Type<ApplicationLoadHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<ApplicationLoadHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(ApplicationLoadHandler pHandler) {
        pHandler.onApplicationLoadEvent(this);
    }
    
}
