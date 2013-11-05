package net.sf.video4j.gwt.plugin.server.vast.dao;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.video4j.gwt.plugin.shared.vast.VAST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

/**
 * @author gumatias
 */
public class AdService implements IAdService {
	
	private final Logger	mLogger	= LoggerFactory.getLogger(this.getClass());

	@Override
	public VAST fetchAds(String pURL) {
		Document oDoc = getXMLDocument(pURL);
		mLogger.info("xml node=" + oDoc.getFirstChild().getNodeName());
		return new VAST();
	}

	private Document getXMLDocument(String pURL) {
		try {
			URL oURL = new URL(pURL);
			HttpURLConnection pConnection = (HttpURLConnection) oURL.openConnection();
			pConnection.setRequestMethod("GET");
			pConnection.setRequestProperty("Accept", "application/xml");
			InputStream oStream = pConnection.getInputStream();
			DocumentBuilderFactory oBDF = DocumentBuilderFactory.newInstance();
			DocumentBuilder oDB = oBDF.newDocumentBuilder();
			return oDB.parse(oStream);
		} catch (Exception e) {
			mLogger.error("Error while fetching ad xml document", e);
		}
		return null;
	}

}
