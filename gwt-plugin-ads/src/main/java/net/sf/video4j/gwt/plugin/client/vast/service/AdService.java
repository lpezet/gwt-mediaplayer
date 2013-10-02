package net.sf.video4j.gwt.plugin.client.vast.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.plugin.shared.vast.AdRequestCallback;
import net.sf.video4j.gwt.plugin.shared.vast.IVASTParser;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.inject.Inject;

/**
 * @author gumatias
 */
public class AdService implements IAdService {
    
    private Logger mLogger = Logger.getLogger(this.getClass().getName());
    
    private IVASTParser mVASTParser;
    
    @Inject
    public AdService(IVASTParser pVASTParser) {
        mVASTParser = pVASTParser;
    }

    @Override
    public void fetchAds(String pURL, final AdRequestCallback pCallback) {
        RequestBuilder oGETRequest = new RequestBuilder(RequestBuilder.GET, pURL);
        oGETRequest.setCallback(new RequestCallbackImpl(pCallback));
        try {
            oGETRequest.send();
        } catch (RequestException e) {
            mLogger.log(Level.SEVERE, "Failed to send Ad Request", e);
        }
    }
    
    class RequestCallbackImpl implements RequestCallback {
        
        private AdRequestCallback mCallback;
        
        public RequestCallbackImpl(AdRequestCallback pCallback) {
            mCallback = pCallback;
        }

        @Override
        public void onResponseReceived(Request pRequest, Response pResponse) {
            mLogger.log(Level.FINEST, "Response Received [" + pResponse.getText() + "], parsing...");
            mCallback.onResponseReceived(mVASTParser.parse(pResponse.getText()));
            mLogger.log(Level.FINEST, "Done parsing response");
        }
        
        @Override
        public void onError(Request pRequest, Throwable pException) {
            mLogger.log(Level.SEVERE, "Ad Request [" + pRequest + "] failed", pException);
            mCallback.onError(pException);
        }
        
    }

}