/**
 * 
 */
package net.sf.video4j.gwt.plugin.shared.vast;

/**
 * Indicates source ad server
 * 
 * @author luc
 * 
 */
public class AdSystem {

    private String mName;
    private String mVersion;

    public String getName() {
        return mName;
    }

    public void setName(String pName) {
        mName = pName;
    }

    public String getVersion() {
        return mVersion;
    }

    public void setVersion(String pVersion) {
        mVersion = pVersion;
    }

    @Override
    public String toString() {
        return "AdSystem [mName=" + mName + ", mVersion=" + mVersion + "]";
    }
}
