/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.video4j.gwt.client.model.Source;


/**
 * @author luc
 *
 */
public class Media {

	private int mId;
	private String mTitle;
	private int mDurationInSeconds = -1;
	private Map<String, Object> mMetaData = new HashMap<String, Object>(); // TODO: Should metadata and URL be part of a separate class: Resource??
	private Map<String, Object> mProperties = new HashMap<String, Object>();
	private boolean mAd = false;
	
	private MediaType mType;
	private List<Source> mSources = new ArrayList<Source>();
	
	public String getTitle() {
		return mTitle;
	}
	public void setTitle(String pTitle) {
		mTitle = pTitle;
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
		return "Media #" + mId;
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

	public List<Source> getSources() {
		return mSources;
	}

	public void setSources(List<Source> pSources) {
		mSources = pSources;
	}
	
}
