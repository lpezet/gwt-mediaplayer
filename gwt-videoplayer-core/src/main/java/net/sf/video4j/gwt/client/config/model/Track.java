package net.sf.video4j.gwt.client.config.model;

/**
 * @author gumatias
 */
public class Track {

    private String mURL;

    public String getURL() {
        return mURL;
    }

    public void setURL(String pURL) {
        mURL = pURL;
    }

    @Override
    public String toString() {
        return "Track [mURL=" + mURL + "]";
    }
    
}
