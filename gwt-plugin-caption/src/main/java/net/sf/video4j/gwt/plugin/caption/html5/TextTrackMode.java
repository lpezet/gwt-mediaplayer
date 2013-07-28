/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.html5;

/**
 * Reference: http://www.w3.org/TR/html51/single-page.html#texttrackmode
 * 
 * @author luc
 *
 */
public enum TextTrackMode {
	Disabled("disabled"),
	Hidden("hidden"),
	Showing("showing");
	
	private String mValue;
	
	private TextTrackMode(String pValue) {
		mValue = pValue;
	}
	
	public String getValue() {
		return mValue;
	}
	
	public static TextTrackMode fromValue(String pValue) {
		for (TextTrackMode k : values()) {
			if (k.getValue().equalsIgnoreCase(pValue)) return k;
		}
		return null;
	}
}
