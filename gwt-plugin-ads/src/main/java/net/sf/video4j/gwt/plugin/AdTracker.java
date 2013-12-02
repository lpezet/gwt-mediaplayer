package net.sf.video4j.gwt.plugin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.video4j.gwt.plugin.shared.vast.Tracking;
import net.sf.video4j.gwt.plugin.shared.vast.TrackingEvent;

/**
 * @author Gustavo Matias
 */
public class AdTracker implements IAdTracker {

	private IHTTPRequestBuilder			mHTTPRequestBuilder;

	private Map<TrackingEvent, String>	mTrackings	= new HashMap<TrackingEvent, String>();

	public AdTracker(List<Tracking> pTrackings) {
		convertToMap(pTrackings);
	}

	@Override
	public void track(TrackingEvent pEvent) {
		mHTTPRequestBuilder.get(mTrackings.get(pEvent));
	}

	private void convertToMap(List<Tracking> pTrackings) {
		for (Tracking t : pTrackings)
			mTrackings.put(t.getEvent(), t.getURI());
	}

	public Map<TrackingEvent, String> getTrackings() {
		return mTrackings;
	}

	public void setTrackings(Map<TrackingEvent, String> pTrackings) {
		mTrackings = pTrackings;
	}

	public IHTTPRequestBuilder getHTTPRequestBuilder() {
		return mHTTPRequestBuilder;
	}

	public void setHTTPRequestBuilder(IHTTPRequestBuilder pHTTPRequestBuilder) {
		mHTTPRequestBuilder = pHTTPRequestBuilder;
	}

}