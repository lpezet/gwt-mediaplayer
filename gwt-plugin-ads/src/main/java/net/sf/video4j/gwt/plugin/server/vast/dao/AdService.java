package net.sf.video4j.gwt.plugin.server.vast.dao;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.video4j.gwt.plugin.shared.vast.VAST;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gumatias
 */
public class AdService implements IAdService {
	
	private final Logger	mLogger	= LoggerFactory.getLogger(this.getClass());

	@Override
	public VAST fetchAds(String pURL) {
		try {
			String oXML = getContent(pURL);
			//mLogger.info("xml node=" + oDoc.getFirstChild().getNodeName());
			VASTParser oParser = new VASTParser();
			return oParser.parse(oXML);
		}  catch (Throwable t) {
			mLogger.error("Error getting VAST.", t);
		}
		return new VAST();
	}

	private String getContent(String pURL) {
		try {
			URL oURL = new URL(pURL);
			HttpURLConnection pConnection = (HttpURLConnection) oURL.openConnection();
			pConnection.setRequestMethod("GET");
			pConnection.setRequestProperty("Accept", "application/xml");
			InputStream oStream = pConnection.getInputStream();
			StringWriter oSW = new StringWriter();
			IOUtils.copy(oStream, oSW);
			return oSW.toString();
		} catch (Exception e) {
			mLogger.error("Error while fetching ad xml document", e);
		}
		return null;
	}

}
