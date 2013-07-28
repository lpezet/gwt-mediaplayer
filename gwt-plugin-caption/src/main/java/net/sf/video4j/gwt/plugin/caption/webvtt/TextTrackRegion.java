/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.webvtt;

/**
 * @author luc
 *
 */
public interface TextTrackRegion {

	// readonly attribute TextTrack? track;
	public TextTrack getTrack();
	
    // attribute DOMString id;
	public String getId();
	public void setId(String pId);
	
    // attribute double width;
	public double getWidth();
	public void setWidth(double pWidth);
	
    // attribute long lines;
	public long getLines();
	public void setLines(long pLines);
	
    // attribute double regionAnchorX;
	public double getRegionAnchorX();
	public void setRegionAnchorX(double x);
	
    // attribute double regionAnchorY;
	public double getRegionAnchorY();
	public void setRegionAnchorY(double y);
	
    // attribute double viewportAnchorX;
	public double getViewportAnchorX();
	public void setViewportAnchorX();
	
    // attribute double viewportAnchorY;
	public double getViewportAnchorY();
	public void setViewportAnchorY();
	
    // attribute DOMString scroll;
	public String getScroll();
	public void setScroll(String pScroll);
	
}
