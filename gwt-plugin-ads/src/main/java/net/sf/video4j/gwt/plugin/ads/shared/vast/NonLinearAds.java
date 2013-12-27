/**
 * 
 */
package net.sf.video4j.gwt.plugin.ads.shared.vast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luc
 * 
 */
public class NonLinearAds extends Creative {

    private List<Tracking> mTrackingEvents = new ArrayList<Tracking>();
    private List<NonLinearAd> mList = new ArrayList<NonLinearAd>();
    
    // Any number of companions in any desired pixel dimensions.

	public List<NonLinearAd> getList() {
		return mList;
	}
	public void setList(List<NonLinearAd> pList) {
		mList = pList;
	}
	public List<Tracking> getTrackingEvents() {
		return mTrackingEvents;
	}
	public void setTrackingEvents(List<Tracking> pTrackingEvents) {
		mTrackingEvents = pTrackingEvents;
	}
	
	@Override
    public String toString() {
        return "NonLinearAds [ads=" + mList + ", trackingEvents=" + mTrackingEvents + "]";
    }

}
