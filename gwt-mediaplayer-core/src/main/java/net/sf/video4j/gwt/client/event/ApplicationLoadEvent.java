/**
 * 
 */
package net.sf.video4j.gwt.client.event;

import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.model.IApplication;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author luc
 *
 */
public class ApplicationLoadEvent extends GwtEvent<ApplicationLoadEvent.ApplicationLoadHandler> {
    
	private IApplication mApplication;
	
	protected ApplicationLoadEvent(IApplication pApplication) {
		mApplication = pApplication;
    }
	
	public IApplication getApplication() {
		return mApplication;
	}

    public static void fire(HasHandlers pSource, IApplication pApplication) {
    	ApplicationLoadEvent oEventInstance = new ApplicationLoadEvent(pApplication);
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
