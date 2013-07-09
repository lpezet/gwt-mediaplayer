/**
 * 
 */
package net.sf.video4j.gwt.client.be;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * @author luc
 *
 */
public class CompanionAd {
	
	// Optional identifier
	private String mId;
	
	// Pixel dimensions of companion
	private int mWidth;
	
	// Pixel dimensions of companion
	private int mHeight;
	
	// Pixel dimensions of expanding companion ad when in expanded state
	private int mExpandedWidth;
	
	// Pixel dimensions of expanding companion ad when in expanded state
	private int mExpandedHeight;
	
	// The apiFramework defines the method to use for communication with the companion
	private String mApiFramework;
	
	private CompanionResource mResource;
	
	// The creativeView should always be requested when present. For Companions creativeView is the only supported event.
	private List<Tracking> mTrackingEvents = new ArrayList<Tracking>();
	
	// URL to open as destination page when user clicks on the the companion banner ad.
	private URI mClickThrough;
	
	// Alt text to be displayed when companion is rendered in HTML environment.
	private String mAltText;
	
	// Data to be passed into the companion ads. The apiFramework defines the method to use for communication (e.g. "FlashVar")
	private String mAdParameters;

	public String getId() {
		return mId;
	}

	public void setId(String pId) {
		mId = pId;
	}

	public int getWidth() {
		return mWidth;
	}

	public void setWidth(int pWidth) {
		mWidth = pWidth;
	}

	public int getHeight() {
		return mHeight;
	}

	public void setHeight(int pHeight) {
		mHeight = pHeight;
	}

	public int getExpandedWidth() {
		return mExpandedWidth;
	}

	public void setExpandedWidth(int pExpandedWidth) {
		mExpandedWidth = pExpandedWidth;
	}

	public int getExpandedHeight() {
		return mExpandedHeight;
	}

	public void setExpandedHeight(int pExpandedHeight) {
		mExpandedHeight = pExpandedHeight;
	}

	public String getApiFramework() {
		return mApiFramework;
	}

	public void setApiFramework(String pApiFramework) {
		mApiFramework = pApiFramework;
	}
}
