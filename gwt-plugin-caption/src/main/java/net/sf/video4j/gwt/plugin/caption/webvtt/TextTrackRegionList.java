/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.webvtt;


/**
 * @author luc
 *
 */
public interface TextTrackRegionList {

	// readonly attribute unsigned long length;
	public long getLength();
	
	// getter TextTrackCue (unsigned long index);
	public TextTrackRegion get(long pIndex);
	
	// TextTrackCue? getCueById(DOMString id);
	public TextTrackRegion getRegionById(String pId);
	
}
