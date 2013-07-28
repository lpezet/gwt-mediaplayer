/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.webvtt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luc
 *
 */
public class WebVTT {

	private List<VTTCue> mCues = new ArrayList<VTTCue>();

	public List<VTTCue> getCues() {
		return mCues;
	}

	public void setCues(List<VTTCue> pCues) {
		mCues = pCues;
	}
}
