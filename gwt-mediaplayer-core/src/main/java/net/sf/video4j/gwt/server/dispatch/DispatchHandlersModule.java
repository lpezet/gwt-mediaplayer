package net.sf.video4j.gwt.server.dispatch;

import net.sf.video4j.gwt.shared.FetchAdAction;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

/**
 * @author gumatias
 */
public class DispatchHandlersModule extends HandlerModule {
    
    @Override
    protected void configureHandlers() {
        bindHandler(FetchAdAction.class, FetchAdHandler.class);
    }
    
}