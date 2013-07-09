/**
 * 
 */
package net.sf.video4j.gwt.client.be;

import java.net.URI;

/**
 * @author luc
 *
 */
public class Tracking {

	private TrackingEvent mEvent;
	private URI mURI;
	public TrackingEvent getEvent() {
		return mEvent;
	}
	public void setEvent(TrackingEvent pEvent) {
		mEvent = pEvent;
	}
	public URI getURI() {
		return mURI;
	}
	public void setURI(URI pURI) {
		mURI = pURI;
	}
}
