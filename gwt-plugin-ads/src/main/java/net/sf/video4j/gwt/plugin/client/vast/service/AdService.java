package net.sf.video4j.gwt.plugin.client.vast.service;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.video4j.gwt.plugin.client.vast.AdRequestCallback;
import net.sf.video4j.gwt.plugin.client.vast.IVASTParser;

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
        oGETRequest.setCallback(new RequestCallback() {
            
            @Override
            public void onResponseReceived(Request pRequest, Response pResponse) {
                mLogger.log(Level.FINEST, "Response Received [" + pResponse.getText() + "], parsing...");
                pCallback.onResponseReceived(mVASTParser.parse(pResponse.getText()));
                mLogger.log(Level.FINEST, "Done parsing response");
            }
            
            @Override
            public void onError(Request pRequest, Throwable pException) {
                mLogger.log(Level.SEVERE, "Ad Request [" + pRequest + "] failed.", pException);
                pCallback.onError(pException);
            }
        });
        try {
            oGETRequest.send();
        } catch (RequestException e) {
            mLogger.log(Level.SEVERE, "Failed to send Ad Request", e);
        }
    }

}