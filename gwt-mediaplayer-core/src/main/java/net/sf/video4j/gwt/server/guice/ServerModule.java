package net.sf.video4j.gwt.server.guice;

import java.util.logging.Logger;

import net.sf.video4j.gwt.server.dispatch.FetchAdHandler;
import net.sf.video4j.gwt.shared.FetchAdAction;

import com.gwtplatform.dispatch.server.guice.HandlerModule;

/**
 * @author gumatias
 */
public class ServerModule extends HandlerModule {

    protected Logger mLogger = Logger.getLogger(this.getClass().getName());

    @Override
    protected void configureHandlers() {
        bindHandler(FetchAdAction.class, FetchAdHandler.class);
    }

}