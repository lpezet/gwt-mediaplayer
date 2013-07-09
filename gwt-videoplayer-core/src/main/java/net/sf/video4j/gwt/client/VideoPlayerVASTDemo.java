/**
 * 
 */
package net.sf.video4j.gwt.client;

import java.util.logging.Logger;

import net.sf.video4j.gwt.client.be.VAST;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;
import com.google.gwt.user.client.Window;

/**
 * @author luc
 *
 */
public class VideoPlayerVASTDemo implements EntryPoint {

	private Logger mLogger = Logger.getLogger(VideoPlayerVASTDemo.class.getName());
	
	@Override
	public void onModuleLoad() {
		String oVASTUrl = "http://localhost:8080/vast2_regular_linear.xml";
		
		RequestBuilder oRB = new RequestBuilder(RequestBuilder.GET, "/vast2_regular_linear.xml");
		//oRB.setRequestData("url=" + URL.encodeQueryString(oVASTUrl));
		//oRB.setHeader("Content-Type", "application/x-www-form-urlencoded"); // for POST
		oRB.setCallback( new RequestCallback() {
			
			@Override
			public void onResponseReceived(Request pRequest, Response pResponse) {
				if (pResponse.getStatusCode() == 200) {
					VAST oVAST = new VASTParser().parse(pResponse.getText());
					mLogger.info("Found : " + oVAST.getAds().size() + " ads.");
				}
			}
			
			@Override
			public void onError(Request pRequest, Throwable pException) {
				mLogger.severe("Error gettings VAST: " + pException.getMessage());
			}
		});
		
		try {
			oRB.send();
		} catch (RequestException e) {
			mLogger.severe("Error sending request: " + e.getMessage());
		}
		
	}

}
