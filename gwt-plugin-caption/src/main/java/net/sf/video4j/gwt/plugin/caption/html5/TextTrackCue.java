/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.html5;

/**
 * @author luc
 *
 */
public interface TextTrackCue {
	
	//  readonly attribute TextTrack? track;
	public TextTrack getTrack();
	
	// attribute DOMString id;
	public String getId();
	public void setId(String pId);
	
    // attribute double startTime;
	public double getStartTime();
	public void setStartTime(double pTime);
	
    // attribute double endTime;
	public double getEndTime();
	public void setEndTime(double pTime);
	
    // attribute boolean pauseOnExit;
	public boolean isPauseOnExit();
	public void setPauseOnExit(boolean pValue);
	
    // attribute EventHandler onenter;
	
    // attribute EventHandler onexit;
}
