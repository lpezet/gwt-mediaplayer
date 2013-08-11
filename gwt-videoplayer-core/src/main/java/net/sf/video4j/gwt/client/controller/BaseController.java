/**
 * 
 */
package net.sf.video4j.gwt.client.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.gwt.event.shared.HasHandlers;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HandlerContainerImpl;

/**
 * @author luc
 *
 */
public class BaseController extends HandlerContainerImpl implements HasHandlers {

	private EventBus mEventBus;
	protected Logger mLogger = Logger.getLogger(this.getClass().getName());
	
	
	@Inject
	public BaseController(EventBus pEventBus) {
		mEventBus = pEventBus;
		mLogger.log(Level.INFO, this.getClass().getName() + " created.");
	}
	
	@Override
	public void fireEvent(GwtEvent<?> pEvent) {
		mEventBus.fireEvent(pEvent);
	}
	
	protected EventBus getEventBus() {
		return mEventBus;
	}
	
	public <T> void addRegisteredHandler(Type<T> pType, T pHandler) {
		registerHandler( mEventBus.addHandler(pType, pHandler) );
	}

}
