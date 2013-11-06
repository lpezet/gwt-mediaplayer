package net.sf.video4j.gwt.plugin.shared.vast;

import java.util.ArrayList;
import java.util.List;

/**
 * Second-level element surrounding complete ad data for a single ad
 * 
 * @author luc
 * 
 */
public class InLine extends Ad {

    // Indicates source ad server
    private AdSystem         mAdSystem;

    // Common name of ad
    private String           mAdTitle;

    // Longer description of ad
    private String           mDescription;

    // URL of request to survey vendor
    private String          mSurvey;

    // URL to request if ad does not play due to error
    private String          mError;

    private List<Impression> mImpressions = new ArrayList<Impression>();

    private List<Creative>   mCreatives   = new ArrayList<Creative>();

    private List<Extension>  mExtensions  = new ArrayList<Extension>();

    public AdSystem getAdSystem() {
        return mAdSystem;
    }

    public void setAdSystem(AdSystem pAdSystem) {
        mAdSystem = pAdSystem;
    }

    public String getAdTitle() {
        return mAdTitle;
    }

    public void setAdTitle(String pAdTitle) {
        mAdTitle = pAdTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String pDescription) {
        mDescription = pDescription;
    }

    public String getSurvey() {
        return mSurvey;
    }

    public void setSurvey(String pSurvey) {
        mSurvey = pSurvey;
    }

    public String getError() {
        return mError;
    }

    public void setError(String pError) {
        mError = pError;
    }

    public List<Impression> getImpressions() {
        return mImpressions;
    }

    public void setImpressions(List<Impression> pImpressions) {
        mImpressions = pImpressions;
    }

    public List<Creative> getCreatives() {
        return mCreatives;
    }

    public void setCreatives(List<Creative> pCreatives) {
        mCreatives = pCreatives;
    }

    public List<Extension> getExtensions() {
        return mExtensions;
    }

    public void setExtensions(List<Extension> pExtensions) {
        mExtensions = pExtensions;
    }

    @Override
    public String toString() {
        return "InLine [mAdSystem=" + mAdSystem + ", mAdTitle=" + mAdTitle
                + ", mDescription=" + mDescription + ", mSurvey=" + mSurvey
                + ", mError=" + mError + ", mImpressions=" + mImpressions
                + ", mCreatives=" + mCreatives + ", mExtensions=" + mExtensions
                + "]";
    }
}
