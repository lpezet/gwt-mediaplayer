package net.sf.video4j.gwt.plugin.client.vast;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author gumatias
 *
 */
public class VAST {

	private List<Ad> mAds = new ArrayList<Ad>();

	public List<Ad> getAds() {
		return mAds;
	}

	public void setAds(List<Ad> pAds) {
		mAds = pAds;
	}
	
}