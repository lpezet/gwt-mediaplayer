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
public class CompanionAds extends Creative {

    private List<CompanionAd> mCompanions = new ArrayList<CompanionAd>();

    public List<CompanionAd> getCompanions() {
        return mCompanions;
    }

    public void setCompanions(List<CompanionAd> pCompanions) {
        mCompanions = pCompanions;
    }

    @Override
    public String toString() {
        return "CompanionAds [mCompanions=" + mCompanions + "]";
    }

}
