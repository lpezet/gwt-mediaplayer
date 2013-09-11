/**
 * 
 */
package net.sf.video4j.gwt.plugin.client.vast;

/**
 * Wraps each creative element within an InLine or Wrapper Ad
 * 
 * @author luc
 * 
 */
public class Creative {

    private String mId;

    // The preferred order in which multiple Creatives should be displayed
    private int    mSequence;

    // Ad-ID for the creative (formerly ISCI)
    private String mAdId;

    public String getId() {
        return mId;
    }

    public void setId(String pId) {
        mId = pId;
    }

    public int getSequence() {
        return mSequence;
    }

    public void setSequence(int pSequence) {
        mSequence = pSequence;
    }

    public String getAdId() {
        return mAdId;
    }

    public void setAdId(String pAdId) {
        mAdId = pAdId;
    }

    @Override
    public String toString() {
        return "Creative [mId=" + mId + ", mSequence=" + mSequence + ", mAdId="
                + mAdId + "]";
    }

}
