/**
 * 
 */
package net.sf.video4j.gwt.plugin.ads.server.vast.dao;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author luc
 *
 */
public class UrlFetcher implements IUrlFetcher {
	
	private final Logger	mLogger	= LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String getContent(String pUrl) {
		try {
			URL oURL = new URL(pUrl);
			HttpURLConnection pConnection = (HttpURLConnection) oURL.openConnection();
			pConnection.setRequestMethod("GET");
			pConnection.setRequestProperty("Accept", "application/xml");
			InputStream oStream = pConnection.getInputStream();
			StringWriter oSW = new StringWriter();
			IOUtils.copy(oStream, oSW);
			return oSW.toString();
		} catch (Exception e) {
			mLogger.error("Error while fetching content for url: " + pUrl, e);
		}
		return null;
	}

}
