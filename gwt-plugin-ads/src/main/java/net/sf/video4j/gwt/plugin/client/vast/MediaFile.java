/**
 * 
 */
package net.sf.video4j.gwt.plugin.client.vast;

import com.google.gwt.safehtml.shared.SafeUri;

/**
 * @author luc
 *
 */
public class MediaFile {

	private SafeUri mURI;
	
	// Optional identifier
	private String mId;
	
	// Method of delivery of ad
	private Delivery mDelivery;
	
	// MIME type. Popular MIME types include, but are not limited to "video/x-ms-wmv" for Windows Media, and "video/x-flv" for Flash Video. 
	// Image ads or interactive ads can be included in the MediaFiles section with appropriate Mime types
	private String mType;
	
	// Bitrate of encoded video in Kbps
	private int mBitrate;
	
	// Pixel dimensions of video
	private int mWidth;
	
	// Pixel dimensions of video
	private int mHeight;
	
	// Whether it is acceptable to scale the image.
	private boolean mScalable;
	
	// Whether the ad must have its aspect ratio maintained when scales
	private boolean mMaintainAspectRatio;
	
	// The apiFramework defines the method to use for communication if the MediaFile is interactive. Suggested values for this element are "VPAID", "FlashVars" (for Flash/Flex), "initParams" (for Silverlight) and "GetVariables" (variables placed in key/value pairs on the asset request).
	private String mApiFramework;

	public SafeUri getURI() {
		return mURI;
	}

	public void setURI(SafeUri pURI) {
		mURI = pURI;
	}

	public String getId() {
		return mId;
	}

	public void setId(String pId) {
		mId = pId;
	}

	public Delivery getDelivery() {
		return mDelivery;
	}

	public void setDelivery(Delivery pDelivery) {
		mDelivery = pDelivery;
	}

	public String getType() {
		return mType;
	}

	public void setType(String pType) {
		mType = pType;
	}

	public int getBitrate() {
		return mBitrate;
	}

	public void setBitrate(int pBitrate) {
		mBitrate = pBitrate;
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

	public boolean isScalable() {
		return mScalable;
	}

	public void setScalable(boolean pScalable) {
		mScalable = pScalable;
	}

	public boolean isMaintainAspectRatio() {
		return mMaintainAspectRatio;
	}

	public void setMaintainAspectRatio(boolean pMaintainAspectRatio) {
		mMaintainAspectRatio = pMaintainAspectRatio;
	}

	public String getApiFramework() {
		return mApiFramework;
	}

	public void setApiFramework(String pApiFramework) {
		mApiFramework = pApiFramework;
	}
}
