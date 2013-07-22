/**
 * 
 */
package net.sf.video4j.gwt.client.vast;


import java.util.ArrayList;
import java.util.List;


import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;

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
			oResult.add(c);
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
		Linear oLinear = new Linear();
		NodeList oChildren = pNode.getChildNodes();
		for (int i = 0; i < oChildren.getLength(); i++) {
			Node n = oChildren.item(i);
			if ("Duration".equalsIgnoreCase(n.getNodeName())) {
				int oSeconds = parseTimeToSeconds(n.getNodeValue());
				oLinear.setDurationInSeconds(oSeconds);
			} else if ("TrackingEvents".equalsIgnoreCase(n.getNodeName())) {
				List<Tracking> oTE = parseTrackingEvents(n);
				oLinear.setTrackingEvents(oTE);
			} else if ("AdParameters".equalsIgnoreCase(n.getNodeName())) {
				oLinear.setAdParameters(n.getNodeValue());
			} else if ("VideoClicks".equalsIgnoreCase(n.getNodeName())) {
				VideoClicks oClicks = parseVideoClicks(n);
				oLinear.setVideoClicks(oClicks);
			} else if ("MediaFiles".equalsIgnoreCase(n.getNodeName())) {
				List<MediaFile> oMediaFiles = parseMediaFiles(n);
				oLinear.setMediaFiles(oMediaFiles);
			}
		}
		return new Linear();
	}

	private List<MediaFile> parseMediaFiles(Node pNode) {
		List<MediaFile> oResult = new ArrayList<MediaFile>();
		NodeList oChildren = pNode.getChildNodes();
		for (int i = 0; i < oChildren.getLength(); i++) {
			Node n = oChildren.item(i);
			MediaFile m = new MediaFile();
			m.setApiFramework( getAttribute(n, "apiFramework", null));
			m.setBitrate( getAttribute(n, "bitrate", 0) );
			m.setWidth( getAttribute(n, "width", 0) );
			m.setHeight( getAttribute(n, "height", 0) );
			m.setId( getAttribute(n, "id", null));
			m.setType( getAttribute(n, "type", null));
			m.setScalable( getAttribute(n, "scalable", false));
			m.setMaintainAspectRatio( getAttribute(n, "maintainAspectRatio", false) );
			m.setDelivery( Delivery.parse( getAttribute(n, "delivery", "progressive") ) );
			m.setURI( newURI(n.getNodeValue()) );
			oResult.add(m);
		}
		return oResult;
	}

	private VideoClicks parseVideoClicks(Node pNode) {
		VideoClicks oResult = new VideoClicks();
		NodeList oChildren = pNode.getChildNodes();
		for (int i = 0; i < oChildren.getLength(); i++) {
			Node n = oChildren.item(i);
			IdURI oIdURI = new IdURI();
			oIdURI.setURI(newURI(n.getNodeValue()));
			oIdURI.setId( getAttribute(n, "id", null));
			if ("ClickThrough".equalsIgnoreCase(n.getNodeName())) {
				oResult.setClickThrough(oIdURI);
			} else if ("ClickTracking".equalsIgnoreCase(n.getNodeName())) {
				oResult.getClickTrackings().add(oIdURI);
			} else if ("CustomClick".equalsIgnoreCase(n.getNodeName())) {
				oResult.getCustomClicks().add(oIdURI);
			}
		}
		return oResult;
	}

	private List<Tracking> parseTrackingEvents(Node pNode) {
		List<Tracking> oResult = new ArrayList<Tracking>();
		NodeList oChildren = pNode.getChildNodes();
		for (int i = 0; i < oChildren.getLength(); i++) {
			Node n = oChildren.item(i);
			if ("tracking".equalsIgnoreCase(n.getNodeName())) {
				Tracking t = new Tracking();
				t.setURI(newURI(n.getNodeValue()));
				t.setEvent(TrackingEvent.parse( getAttribute(n, "event", "creativeView")));
			}
		}
		return oResult;
	}

	private int parseTimeToSeconds(String pNodeValue) {
		String[] oTimes = pNodeValue.split(":");
		switch (oTimes.length) {
			case 0:
				return 0;
			case 1:
				return Integer.parseInt(oTimes[0]);
			case 2:
				return Integer.parseInt(oTimes[0]) * 60 + Integer.parseInt(oTimes[1]);
			case 3:
				return Integer.parseInt(oTimes[0]) * 3600 + Integer.parseInt(oTimes[1]) * 60 + Integer.parseInt(oTimes[2]);
		}
		return -1;
	}

	private Impression parseImpression(Node pNode) {
		Impression oResult = new Impression();
		oResult.setId(getAttribute(pNode, "id", null));
		oResult.setURI(newURI(pNode.getNodeValue()));
		return oResult;
	}

	private SafeUri newURI(String pValue) {
		return UriUtils.fromString(pValue);
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
	
	private boolean getAttribute(Node pNode, String pName, boolean pDefault) {
		Node n = pNode.getAttributes().getNamedItem(pName);
		if (n == null) return pDefault;
		return Boolean.parseBoolean(n.getNodeValue());
	}
}
