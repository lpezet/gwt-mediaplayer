/**
 * 
 */
package net.sf.video4j.gwt.plugin.ads.shared.vast;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author luc
 * 
 */
public class Ad implements IsSerializable {

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