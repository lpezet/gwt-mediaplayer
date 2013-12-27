/**
 * 
 */
package net.sf.video4j.gwt.plugin.ads.server.vast.dao;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import java.util.List;

import net.sf.video4j.gwt.plugin.ads.AbstractVASTParserTest;
import net.sf.video4j.gwt.plugin.ads.server.vast.dao.IVASTParser;
import net.sf.video4j.gwt.plugin.ads.server.vast.dao.VASTParser;
import net.sf.video4j.gwt.plugin.ads.shared.vast.Linear;
import net.sf.video4j.gwt.plugin.ads.shared.vast.Tracking;
import net.sf.video4j.gwt.plugin.ads.shared.vast.TrackingEvent;
import net.sf.video4j.gwt.plugin.ads.shared.vast.VAST;
import net.sf.video4j.gwt.plugin.ads.shared.vast.Wrapper;

import org.junit.Test;

/**
 * @author luc
 *
 */
public class VASTParserTest extends AbstractVASTParserTest {
	
	private IVASTParser	mParser	= new VASTParser();

	@Override
	protected VAST parse(String pXML) {
		return mParser.parse(pXML);
	}

	@Test
	public void givenCreativesWithTrackingEvents_shouldParserTrackings() throws Exception {
		VAST oVAST = parse(getXMLAsString("/vast2_wrapper.xml"));

		Wrapper oWrapper = (Wrapper) oVAST.getAds().get(0);
		List<Tracking> oTrackingEvents = ((Linear) oWrapper.getCreatives().get(0)).getTrackingEvents();
		assertThat(oTrackingEvents.get(0).getEvent(), equalTo(TrackingEvent.CreativeView));
		assertThat(oTrackingEvents.get(0).getURI(), equalTo("http://myTrackingURL/wrapper/creativeView"));
		assertThat(oTrackingEvents.get(1).getEvent(), equalTo(TrackingEvent.Start));
		assertThat(oTrackingEvents.get(1).getURI(), equalTo("http://myTrackingURL/wrapper/start"));
		assertThat(oTrackingEvents.get(2).getEvent(), equalTo(TrackingEvent.Midpoint));
		assertThat(oTrackingEvents.get(2).getURI(), equalTo("http://myTrackingURL/wrapper/midpoint"));
		assertThat(oTrackingEvents.get(3).getEvent(), equalTo(TrackingEvent.FirstQuartile));
		assertThat(oTrackingEvents.get(3).getURI(), equalTo("http://myTrackingURL/wrapper/firstQuartile"));
		assertThat(oTrackingEvents.get(4).getEvent(), equalTo(TrackingEvent.ThirdQuartile));
		assertThat(oTrackingEvents.get(4).getURI(), equalTo("http://myTrackingURL/wrapper/thirdQuartile"));
		assertThat(oTrackingEvents.get(5).getEvent(), equalTo(TrackingEvent.Complete));
		assertThat(oTrackingEvents.get(5).getURI(), equalTo("http://myTrackingURL/wrapper/complete"));
		assertThat(oTrackingEvents.get(6).getEvent(), equalTo(TrackingEvent.Mute));
		assertThat(oTrackingEvents.get(6).getURI(), equalTo("http://myTrackingURL/wrapper/mute"));
		assertThat(oTrackingEvents.get(7).getEvent(), equalTo(TrackingEvent.Unmute));
		assertThat(oTrackingEvents.get(7).getURI(), equalTo("http://myTrackingURL/wrapper/unmute"));
		assertThat(oTrackingEvents.get(8).getEvent(), equalTo(TrackingEvent.Pause));
		assertThat(oTrackingEvents.get(8).getURI(), equalTo("http://myTrackingURL/wrapper/pause"));
		assertThat(oTrackingEvents.get(9).getEvent(), equalTo(TrackingEvent.Resume));
		assertThat(oTrackingEvents.get(9).getURI(), equalTo("http://myTrackingURL/wrapper/resume"));
		assertThat(oTrackingEvents.get(10).getEvent(), equalTo(TrackingEvent.Fullscreen));
		assertThat(oTrackingEvents.get(10).getURI(), equalTo("http://myTrackingURL/wrapper/fullscreen"));
	}

}
