/**
 * 
 */
package net.sf.video4j.gwt.plugin.client.vast;

import com.google.gwt.safehtml.shared.SafeUri;

/**
 * @author luc
 *
 */
public class Tracking {

	private TrackingEvent mEvent;
	private SafeUri mURI;
	public TrackingEvent getEvent() {
		return mEvent;
	}
	public void setEvent(TrackingEvent pEvent) {
		mEvent = pEvent;
	}
	public SafeUri getURI() {
		return mURI;
	}
	public void setURI(SafeUri pURI) {
		mURI = pURI;
	}
}
