/**
 * 
 */
package net.sf.video4j.gwt.plugin.ads.shared.vast;

import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author luc
 * 
 */
public class Tracking implements IsSerializable {

    private TrackingEvent mEvent;
    private String       mURI;

    public TrackingEvent getEvent() {
        return mEvent;
    }

    public void setEvent(TrackingEvent pEvent) {
        mEvent = pEvent;
    }

    public String getURI() {
        return mURI;
    }

    public void setURI(String pURI) {
        mURI = pURI;
    }

    @Override
    public String toString() {
        return "Tracking [mEvent=" + mEvent + ", mURI=" + mURI + "]";
    }
}
