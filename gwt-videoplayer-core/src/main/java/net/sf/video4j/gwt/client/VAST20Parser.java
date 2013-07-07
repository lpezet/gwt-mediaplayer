/**
 * 
 */
package net.sf.video4j.gwt.client;


import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

import net.sf.video4j.gwt.client.be.Ad;
import net.sf.video4j.gwt.client.be.AdSystem;
import net.sf.video4j.gwt.client.be.CompanionAds;
import net.sf.video4j.gwt.client.be.Creative;
import net.sf.video4j.gwt.client.be.Impression;
import net.sf.video4j.gwt.client.be.InLine;
import net.sf.video4j.gwt.client.be.Linear;
import net.sf.video4j.gwt.client.be.NonLinearAds;
import net.sf.video4j.gwt.client.be.VAST;
import net.sf.video4j.gwt.client.be.Wrapper;

/**
 * @author luc
 *
 */
public class VAST20Parser {

	
	public VAST parse(Node pRoot) {
		VAST oVAST = new VAST();
		NodeList oChildren = pRoot.getChildNodes();
		for (int i = 0; i < oChildren.getLength(); i++) {
			Node n = oChildren.item(i);
			if (n.getNodeName().equalsIgnoreCase("ad")) {
				Ad oAd = parseAd(n);
				oVAST.getAds().add(oAd);
			} else {
				// invalid VAST document??
			}
		}
		return oVAST;
	}

	private Ad parseAd(Node pNode) {
		Ad oResult = null;
		String oId = getAttribute(pNode, "id", null);
		Node oChild = pNode.getFirstChild();
		if ("InLine".equalsIgnoreCase(oChild.getNodeName())) {
			oResult = parseInLine(oChild);
		} else if ("Wrapper".equalsIgnoreCase(oChild.getNodeName())) {
			oResult = parseWrapper(oChild);
		}
		if (oResult == null) {
			// invalid VAST doc??
		}
		oResult.setId(oId);
		return oResult;
	}

	private Ad parseWrapper(Node pChild) {
		// TODO
		return new Wrapper();
	}

	private InLine parseInLine(Node pNode) {
		InLine oResult = new InLine();
		NodeList oChildren = pNode.getChildNodes();
		for (int i = 0; i < oChildren.getLength(); i++) {
			Node n = oChildren.item(i);
			if ("AdSystem".equalsIgnoreCase(n.getNodeName())) {
				AdSystem oAdSys = new AdSystem();
				oAdSys.setVersion(getAttribute(n, "version", null));
				oAdSys.setName(n.getNodeValue());
				oResult.setAdSystem(oAdSys);
			} else if ("AdTitle".equalsIgnoreCase(n.getNodeName())) {
				oResult.setAdTitle(n.getNodeValue());
			} else if ("Description".equalsIgnoreCase(n.getNodeName())) {
				oResult.setDescription(n.getNodeValue());
			} else if ("Survey".equalsIgnoreCase(n.getNodeName())) {
				oResult.setSurvey(newURI(n.getNodeValue()));
			} else if ("Error".equalsIgnoreCase(n.getNodeName())) {
				oResult.setError(newURI(n.getNodeValue()));
			} else if ("Impression".equalsIgnoreCase(n.getNodeName())) {
				Impression oImp = parseImpression(n);
				oResult.getImpressions().add(oImp);
			} else if ("Creatives".equalsIgnoreCase(n.getNodeName())) {
				oResult.setCreatives(parseCreatives(n));
			}
		}
		return oResult;
	}

	private List<Creative> parseCreatives(Node pNode) {
		List<Creative> oResult = new ArrayList<Creative>();
		NodeList oChildren = pNode.getChildNodes();
		for (int i = 0; i < oChildren.getLength(); i++) {
			Node n = oChildren.item(i);
			Creative c = parseCreative(n);
		}
		return oResult;
	}

	private Creative parseCreative(Node pNode) {
		Node oCreativeNode = pNode.getFirstChild();
		Creative oResult = null;
		if ("Linear".equalsIgnoreCase(oCreativeNode.getNodeName())) {
			oResult = parseLinear(oCreativeNode);
		} else if ("CompanionAds".equalsIgnoreCase(oCreativeNode.getNodeName())) {
			oResult = parseCompanionAds(oCreativeNode);
		} else if ("NonLinearAds".equalsIgnoreCase(oCreativeNode.getNodeName())) {
			oResult = parseNonLinearAds(oCreativeNode);
		}
		if (oResult == null) {
			// invalid VAST doc?
		}
		oResult.setAdId(getAttribute(oCreativeNode, "AdID", null));
		oResult.setId(getAttribute(oCreativeNode, "id", null));
		oResult.setSequence(getAttribute(oCreativeNode, "Sequence", 1));
		return oResult;
	}

	private Creative parseNonLinearAds(Node pNode) {
		// TODO
		return new NonLinearAds();
	}

	private Creative parseCompanionAds(Node pNode) {
		// TODO
		return new CompanionAds();
	}

	private Creative parseLinear(Node pNode) {
		// TODO
		return new Linear();
	}

	private Impression parseImpression(Node pNode) {
		Impression oResult = new Impression();
		oResult.setId(getAttribute(pNode, "id", null));
		oResult.setURI(newURI(pNode.getNodeValue()));
		return oResult;
	}

	private URI newURI(String pValue) {
		try {
			return new URI(pValue);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	private String getAttribute(Node pNode, String pName, String pDefault) {
		Node n = pNode.getAttributes().getNamedItem(pName);
		if (n == null) return pDefault;
		return n.getNodeValue();
	}
	
	private int getAttribute(Node pNode, String pName, int pDefault) {
		Node n = pNode.getAttributes().getNamedItem(pName);
		if (n == null) return pDefault;
		return Integer.parseInt(n.getNodeValue());
	}
}
