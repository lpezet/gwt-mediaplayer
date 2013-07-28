/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.html5;


/**
 * Reference: http://www.w3.org/TR/html51/single-page.html#texttrack
 * 
 * @author luc
 *
 */
public interface TextTrack {
	
	
	// readonly attribute TextTrackKind kind;
	public TextTrackKind getKind();
	
	// readonly attribute DOMString label;
	public String getLabel();
	
	// readonly attribute DOMString language;
	public String getLanguage();
	
	// readonly attribute DOMString inBandMetadataTrackDispatchType;
	public String getInBandMetadataTrackDispatchType();
	
	// attribute TextTrackMode mode;
	public TextTrackMode getMode();
	public void setMode(TextTrackMode pMode);
	
	// readonly attribute TextTrackCueList? cues;
	public TextTrackCueList getCues();
	
	// readonly attribute TextTrackCueList? activeCues;
	public TextTrackCueList getActiveCues();
	
	// void addCue(TextTrackCue cue);
	public void addCue(TextTrackCue pCue);
	
	// void removeCue(TextTrackCue cue);
	public void removeCue(TextTrackCue pCue);
	
	// attribute EventHandler oncuechange;
}
