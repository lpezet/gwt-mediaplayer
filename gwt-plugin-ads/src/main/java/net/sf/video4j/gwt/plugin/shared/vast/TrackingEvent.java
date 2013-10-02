/**
 * 
 */
package net.sf.video4j.gwt.plugin.shared.vast;

/**
 * @author luc
 *
 */
public enum TrackingEvent {
	CreativeView,
	Start,
	Midpoint,
	FirstQuartile,
	ThirdQuartile,
	Complete,
	Mute,
	Unmute,
	Pause,
	Rewind,
	Resume,
	Fullscreen,
	Expand,
	Collapse,
	AcceptInvitation,
	Close;
	
	public static TrackingEvent parse(String pValue) {
		TrackingEvent[] oValues = values();
		for (TrackingEvent e : oValues) {
			if (e.name().equalsIgnoreCase(pValue)) return e;
		}
		return null;
	}
}
