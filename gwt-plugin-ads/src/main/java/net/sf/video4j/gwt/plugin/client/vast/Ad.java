/**
 * 
 */
package net.sf.video4j.gwt.plugin.client.vast;

/**
 * @author luc
 * 
 */
public class Ad {

    private String mId;

    public String getId() {
        return mId;
    }

    public void setId(String pId) {
        mId = pId;
    }

    @Override
    public String toString() {
        return "Ad [mId=" + mId + "]";
    }
}