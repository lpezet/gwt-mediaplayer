package net.sf.video4j.gwt.plugin.ads;

import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

import net.sf.video4j.gwt.plugin.ads.AdTracker;
import net.sf.video4j.gwt.plugin.ads.IHTTPRequestBuilder;
import net.sf.video4j.gwt.plugin.ads.shared.vast.Tracking;
import net.sf.video4j.gwt.plugin.ads.shared.vast.TrackingEvent;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * @author Gustavo Matias
 */
public class AdTrackerTest {

	private AdTracker			mAdTracker;

	private IHTTPRequestBuilder	mHTTPRequestBuilder;

	@Before
	public void setUp() throws Exception {
		List<Tracking> oTrackings = new ArrayList<Tracking>();
		oTrackings.add(newTracking(TrackingEvent.Start, "http://adtrackingsite.com?label=start"));
		oTrackings.add(newTracking(TrackingEvent.Midpoint, "http://adtrackingsite.com?label=midpoint"));
		oTrackings.add(newTracking(TrackingEvent.Complete, "http://adtrackingsite.com?label=complete"));
		oTrackings.add(newTracking(TrackingEvent.Pause, "http://adtrackingsite.com?label=pause"));
		oTrackings.add(newTracking(TrackingEvent.Resume, "http://adtrackingsite.com?label=resume"));
		oTrackings.add(newTracking(TrackingEvent.Mute, "http://adtrackingsite.com?label=mute"));
		oTrackings.add(newTracking(TrackingEvent.Unmute, "http://adtrackingsite.com?label=unmute"));

		mAdTracker = new AdTracker(oTrackings);
		mHTTPRequestBuilder = Mockito.mock(IHTTPRequestBuilder.class);
		mAdTracker.setHTTPRequestBuilder(mHTTPRequestBuilder);
	}

	@Test
	public void whenAdHasStarted_shouldTrackStartEvent() throws Exception {
		mAdTracker.track(TrackingEvent.Start);

		verify(mHTTPRequestBuilder).get("http://adtrackingsite.com?label=start");
	}

	@Test
	public void whenAdReachesMidPoint_shouldTrackMidPointEvent() throws Exception {
		mAdTracker.track(TrackingEvent.Midpoint);

		verify(mHTTPRequestBuilder).get("http://adtrackingsite.com?label=midpoint");
	}

	@Test
	public void whenAdHasCompleted_shouldTrackCompleteEvent() throws Exception {
		mAdTracker.track(TrackingEvent.Complete);

		verify(mHTTPRequestBuilder).get("http://adtrackingsite.com?label=complete");
	}

	@Test
	public void whenAdHasPaused_shouldTrackPauseEvent() throws Exception {
		mAdTracker.track(TrackingEvent.Pause);

		verify(mHTTPRequestBuilder).get("http://adtrackingsite.com?label=pause");
	}

	@Test
	public void whenAdHasResumed_shouldTrackResumeEvent() throws Exception {
		mAdTracker.track(TrackingEvent.Resume);

		verify(mHTTPRequestBuilder).get("http://adtrackingsite.com?label=resume");
	}

	@Test
	public void whenAdHasMuted_shouldTrackMuteEvent() throws Exception {
		mAdTracker.track(TrackingEvent.Mute);

		verify(mHTTPRequestBuilder).get("http://adtrackingsite.com?label=mute");
	}

	@Test
	public void whenAdHasUnmuted_shouldTrackUnmuteEvent() throws Exception {
		mAdTracker.track(TrackingEvent.Unmute);

		verify(mHTTPRequestBuilder).get("http://adtrackingsite.com?label=unmute");
	}

	private Tracking newTracking(TrackingEvent pTrackingEvent, String pURL) {
		Tracking oTracking = new Tracking();
		oTracking.setEvent(pTrackingEvent);
		oTracking.setURI(pURL);
		return oTracking;
	}

}