/**
 * 
 */
package net.sf.video4j.gwt.plugin.shared.vast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luc
 * 
 */
public class NonLinearAds extends Creative {

    private List<Tracking> mTrackingEvents = new ArrayList<Tracking>();

    @Override
    public String toString() {
        return "NonLinearAds [mTrackingEvents=" + mTrackingEvents + "]";
    }

    // Any number of companions in any desired pixel dimensions.

}
