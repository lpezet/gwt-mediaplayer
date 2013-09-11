package net.sf.video4j.gwt.plugin.client.vast;


/**
 * @author gumatias
 */
public interface AdRequestCallback {
    void onResponseReceived(VAST pVAST);
    void onError(Throwable pException);
}