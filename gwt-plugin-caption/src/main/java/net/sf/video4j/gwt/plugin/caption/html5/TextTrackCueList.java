/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.html5;

/**
 * Reference: http://www.w3.org/TR/html51/single-page.html#texttrackcuelist
 * 
 * @author luc
 *
 */
public interface TextTrackCueList {
	
	// readonly attribute unsigned long length;
	public long getLength();
	
	// getter TextTrackCue (unsigned long index);
	public TextTrackCue get(long pIndex);
	
	// TextTrackCue? getCueById(DOMString id);
	public TextTrackCue getCueById(String pId);
	
}
