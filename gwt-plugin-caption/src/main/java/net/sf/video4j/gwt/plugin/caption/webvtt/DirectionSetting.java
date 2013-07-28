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
public enum DirectionSetting {
	Horizontal(""),
	RightLeft("rl"),
	LeftRight("lr");
	
	private String mValue;
	
	private DirectionSetting(String pValue) {
		mValue = pValue;
	}
	
	public String getValue() {
		return mValue;
	}
	
	public static DirectionSetting fromValue(String pValue) {
		for (DirectionSetting k : values()) {
			if (k.getValue().equalsIgnoreCase(pValue)) return k;
		}
		return null;
	}
}
