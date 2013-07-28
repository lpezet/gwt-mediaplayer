/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.webvtt;

/**
 * Reference: http://dev.w3.org/html5/webvtt/#vttcue-interface
 * 
 * @author luc
 *
 */
public enum AlignSetting {
	Start("start"),
	Middle("middle"),
	End("end"),
	Left("left"),
	Right("right");
	
	private String mValue;
	
	private AlignSetting(String pValue) {
		mValue = pValue;
	}
	
	public String getValue() {
		return mValue;
	}
	
	public static AlignSetting fromValue(String pValue) {
		for (AlignSetting k : values()) {
			if (k.getValue().equalsIgnoreCase(pValue)) return k;
		}
		return null;
	}
}
