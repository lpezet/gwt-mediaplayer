/**
 * 
 */
package net.sf.video4j.gwt.plugin.ads.shared.vast;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author luc
 * 
 */
public class IdURI implements IsSerializable {

    private String  mId;

    private String mURI;

    public String getId() {
        return mId;
    }

    public void setId(String pId) {
        mId = pId;
    }

    public String getURI() {
        return mURI;
    }

    public void setURI(String pURI) {
        mURI = pURI;
    }

    @Override
    public String toString() {
        return "IdURI [mId=" + mId + ", mURI=" + mURI + "]";
    }
}
