/**
 * 
 */
package net.sf.video4j.gwt.client.controller;

import static org.junit.Assert.assertEquals;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent;
import net.sf.video4j.gwt.client.event.PlayerPlayEndedEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEndedEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.model.Application;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.util.PlayItemBeanFactory;

import org.junit.Before;
import org.junit.Test;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.testing.CountingEventBus;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;
import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author luc
 *
 */
@GwtModule("net.sf.video4j.gwt.client.controller.PlaylistController")
public class PlaylistControllerTest extends GwtTest {

	private PlaylistController mPlaylistController;
	
	private CountingEventBus mEventBus;
	
	private PlayItemBeanFactory mPlayItemBeanFactory;
	
	@Before
    public void setUp() throws Exception {
		mEventBus = new CountingEventBus();
		mPlayItemBeanFactory = GWT.create(PlayItemBeanFactory.class);
		mPlaylistController = new PlaylistController(mEventBus, mPlayItemBeanFactory);
    }
	
	@Test
	public void onApplicationLoadEvent() throws Exception {
		executeOnApplicationLoadEvent();
		
		ApplicationReadyEvent.fire(mPlaylistController);
		assertEquals(1, mEventBus.getFiredCount(PlaylistPlayEvent.getType()));
		
		PlayerPlayEndedEvent.fire(mPlaylistController);
		assertEquals(2, mEventBus.getFiredCount(PlaylistPlayEvent.getType()));
		
		// nothing more to play. No event should be triggered then
		PlayerPlayEndedEvent.fire(mPlaylistController);
		assertEquals(2, mEventBus.getFiredCount(PlaylistPlayEvent.getType()));
	}

	@Test
	public void whenPlayerPlayEnd_shouldFirePlaylistPlayEndedEvent() throws Exception {
		executeOnApplicationLoadEvent();

		PlayerPlayEndedEvent.fire(mPlaylistController);
		assertEquals(1, mEventBus.getFiredCount(PlaylistPlayEndedEvent.getType()));
	}

	private void executeOnApplicationLoadEvent() {
		ApplicationConfig oConfig = new ApplicationConfig();
		JSONArray oPlaylist = new JSONArray();
		JSONObject oPlayItem1 = new JSONObject();
		oPlayItem1.put("url", new JSONString("http://videos.tripfilms.com/360p/4BAE2BA4EE543703AF8BC2DF6550BB73.mp4"));
		oPlaylist.set(0, oPlayItem1);
		JSONObject oPlayItem2 = new JSONObject();
		oPlayItem2.put("url", new JSONString("http://videos.tripfilms.com/480p/ECAA41D5E30E4BECC13FC1D3DA823750.mp4"));
		oPlaylist.set(1, oPlayItem2);
		oConfig.setPlaylist(oPlaylist);
		Application oApp = new Application(oConfig);
		ApplicationLoadEvent.fire(mPlaylistController, oApp);
	}

}