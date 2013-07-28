/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.html5;

/**
 * Reference: http://www.w3.org/TR/html51/single-page.html#texttrackkind
 * 
 * @author luc
 *
 */
public enum TextTrackKind {
	Subtitles("subtitles"),
	Captions("captions"),
	Descriptions("descriptions"),
	Chapters("chapters"),
	Metadata("metadata");
	
	private String mValue;
	
	private TextTrackKind(String pValue) {
		mValue = pValue;
	}
	
	public String getValue() {
		return mValue;
	}
	
	public static TextTrackKind fromValue(String pValue) {
		for (TextTrackKind k : values()) {
			if (k.getValue().equalsIgnoreCase(pValue)) return k;
		}
		return null;
	}	
}
