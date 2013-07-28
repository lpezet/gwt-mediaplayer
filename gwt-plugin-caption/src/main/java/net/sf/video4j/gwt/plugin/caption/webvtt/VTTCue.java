/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.webvtt;

import net.sf.video4j.gwt.plugin.caption.html5.TextTrackCue;

/**
 * Reference: http://dev.w3.org/html5/webvtt/#vttcue-interface
 * 
 * @author luc
 *
 */
public interface VTTCue extends TextTrackCue {
	
	/*
	 * Returns a string representing the text track cue region identifier of the text track region that the VTTCue object belongs to. If the VTTCue doesn't belong to a text track region, returns the empty string.
	 * Can be set.
	 * 
	 * attribute DOMString regionId;
	 */
	public String getRegionId();
	public void setRegionId(String pId);
	
    // attribute DirectionSetting vertical;
	public DirectionSetting getVertical();
	public void setVertical(DirectionSetting pSetting);
	
    // attribute boolean snapToLines;
	public boolean isSnapToLines();
	public void setSnapToLines(boolean pSnapToLines);
	
    // attribute (long or AutoKeyword) line;
	public long getLine();
	public void setLine(long pLine);
	
    // attribute long position;
	public long getPosition();
	public void setPosition(long pPosition);
	
    // attribute long size;
	public long getSize();
	public void setSize(long pSize);
	
    // attribute AlignSetting align;
	public AlignSetting getAlign();
	public void setAlign(AlignSetting pSetting);
	
    // attribute DOMString text;
	public String getText();
	public void setText(String pText);
	
	// DocumentFragment getCueAsHTML();
	public String getCueAsHTML();
	public void setCueAsHTML(String pHTML);
}
