/**
 * 
 */
package net.sf.video4j.gwt.plugin.server.vast.dao;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import net.sf.video4j.gwt.plugin.shared.vast.VAST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 * @author luc
 *
 */
public class VASTParser implements IVASTParser {
	
	private Logger mLogger = LoggerFactory.getLogger(VASTParser.class);

	@Override
	public VAST parse(String pXML) {
		try {
			DocumentBuilderFactory oBuilderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder oBuilder = oBuilderFactory.newDocumentBuilder();
			InputSource oSource = new InputSource(new StringReader(pXML));
			Document oDoc = oBuilder.parse(oSource);
			Node n = oDoc.getDocumentElement();
			if (n.getNodeName().indexOf("VAST") >= 0) {
				Node oVN = n.getAttributes().getNamedItem("version");
				String oVersion = oVN.getNodeValue();
				if (oVersion.startsWith("2.")) return parse2x(n);
			} else {
				return parse1x(n);
			}
			return null;
		} catch (Throwable t) {
			mLogger.error("Error parsing VAST xml.", t);
		}
		return new VAST();
	}
	
	private VAST parse1x(Node pRoot) {
		mLogger.error("1x parser not yet implemented");
		return new VAST();
	}

	private VAST parse2x(Node pRoot) {
		VAST20Parser oParser = new VAST20Parser();
		return oParser.parse(pRoot);
	}

}
