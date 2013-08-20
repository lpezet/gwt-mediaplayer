/**
 * 
 */
package net.sf.video4j.gwt.client.event;

import net.sf.video4j.gwt.client.model.IPlugin;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

/**
 * @author luc
 *
 */
public class PluginReadyEvent extends GwtEvent<PluginReadyEvent.PluginReadyHandler> {
    
	private IPlugin mPlugin;
	
    protected PluginReadyEvent(IPlugin pPlugin) {
    	mPlugin = pPlugin;
    }
    
    public IPlugin getPlugin() {
		return mPlugin;
	}

    public static void fire(HasHandlers pSource, IPlugin pPlugin) {
    	PluginReadyEvent oEventInstance = new PluginReadyEvent(pPlugin);
        pSource.fireEvent(oEventInstance);
    }

    //public static void fire(HasHandlers pSource, PluginReadyEvent pEventInstance) {
    //    pSource.fireEvent(pEventInstance);
    //}

    public interface PluginReadyHandlers extends HasHandlers {
        HandlerRegistration addPluginReadyHandler(PluginReadyHandler pHandler);
    }

    public interface PluginReadyHandler extends EventHandler {
        public void onPluginReadyEvent(PluginReadyEvent pEvent);
    }

    private static final Type<PluginReadyHandler> TYPE = new Type<PluginReadyHandler>();

    public static Type<PluginReadyHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<PluginReadyHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(PluginReadyHandler pHandler) {
        pHandler.onPluginReadyEvent(this);
    }
    
}