/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import java.util.HashMap;
import java.util.Map;


/**
 * @author luc
 *
 */
public class Media {

	private int mId;
	private String mTitle;
	private String mURI;
	private int mDurationInSeconds = -1;
	private Map<String, Object> mMetaData = new HashMap<String, Object>(); // TODO: Should metadata and URL be part of a separate class: Resource??
	private Map<String, Object> mProperties = new HashMap<String, Object>();
	private boolean mAd = false;
	private MediaType mType;
	
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String pTitle) {
		mTitle = pTitle;
	}
	public String getURI() {
		return mURI;
	}
	public void setURI(String pURI) {
		mURI = pURI;
	}
	public int getDurationInSeconds() {
		return mDurationInSeconds;
	}
	public void setDurationInSeconds(int pDurationInSeconds) {
		mDurationInSeconds = pDurationInSeconds;
	}
	public Map<String, Object> getMetaData() {
		return mMetaData;
	}
	public void setMetaData(Map<String, Object> pMetaData) {
		mMetaData = pMetaData;
	}
	public int getId() {
		return mId;
	}
	public void setId(int pId) {
		mId = pId;
	}
	
	@Override
	public String toString() {
		return "Track #" + mId;
	}
	public Map<String, Object> getProperties() {
		return mProperties;
	}
	public void setProperties(Map<String, Object> pProperties) {
		mProperties = pProperties;
	}
	public boolean isAd() {
		return mAd;
	}
	public void setAd(boolean pAd) {
		mAd = pAd;
	}
	public MediaType getType() {
		return mType;
	}
	public void setType(MediaType pType) {
		mType = pType;
	}
	
}
