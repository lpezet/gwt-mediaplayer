/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.webvtt;

/**
 * Reference: http://dev.w3.org/html5/webvtt/#extension-of-the-texttrack-interface-for-region-support
 * 
 * @author luc
 *
 */
public interface TextTrack {

	// attribute TextTrackRegionList? regions;
	public TextTrackRegionList getRegions();
	public void setRegions(TextTrackRegionList pRegions);
	
	// void addRegion(TextTrackRegion region);
	public void addRegion(TextTrackRegion pRegion);
	
	// void removeRegion(TextTrackRegion region);
	public void removeRegion(TextTrackRegion pRegion);
	
}
