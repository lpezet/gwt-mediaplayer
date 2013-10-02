package net.sf.video4j.gwt.plugin.shared.vast;


/**
 * @author gumatias
 */
public interface AdRequestCallback {
    void onResponseReceived(VAST pVAST);
    void onError(Throwable pException);
}