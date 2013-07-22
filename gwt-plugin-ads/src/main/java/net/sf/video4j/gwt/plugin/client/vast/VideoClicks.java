/**
 * 
 */
package net.sf.video4j.gwt.plugin.client.vast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luc
 *
 */
public class VideoClicks {

	private IdURI mClickThrough;
	private List<IdURI> mClickTrackings = new ArrayList<IdURI>();
	private List<IdURI> mCustomClicks = new ArrayList<IdURI>();
	
	public IdURI getClickThrough() {
		return mClickThrough;
	}
	public void setClickThrough(IdURI pClickThrough) {
		mClickThrough = pClickThrough;
	}
	public List<IdURI> getClickTrackings() {
		return mClickTrackings;
	}
	public void setClickTrackings(List<IdURI> pClickTrackings) {
		mClickTrackings = pClickTrackings;
	}
	public List<IdURI> getCustomClicks() {
		return mCustomClicks;
	}
	public void setCustomClicks(List<IdURI> pCustomClicks) {
		mCustomClicks = pCustomClicks;
	}
}
