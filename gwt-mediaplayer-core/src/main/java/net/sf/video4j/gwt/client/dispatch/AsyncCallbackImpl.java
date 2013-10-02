package net.sf.video4j.gwt.client.dispatch;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author gumatias
 */
public abstract class AsyncCallbackImpl<T> implements AsyncCallback<T> {
    
    protected Logger mLogger = Logger.getLogger(this.getClass().getName());
    
    @Override
    public void onFailure(Throwable caught) {
        mLogger.log(Level.SEVERE, "Communication to the server has failed");
    }
    
}