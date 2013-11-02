/**
 * 
 */
package net.sf.video4j.gwt.client.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import net.sf.video4j.gwt.client.event.ApplicationLoadEvent;
import net.sf.video4j.gwt.client.event.ApplicationReadyEvent;
import net.sf.video4j.gwt.client.event.PlayerPlayEndedEvent;
import net.sf.video4j.gwt.client.event.PlaylistPlayEvent;
import net.sf.video4j.gwt.client.model.Application;
import net.sf.video4j.gwt.client.model.ApplicationConfig;
import net.sf.video4j.gwt.client.util.PlayItemBeanFactory;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.testing.CountingEventBus;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONString;

/**
 * @author luc
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PlaylistControllerTest {

	private PlaylistController mPlaylistController;
	
	private CountingEventBus mEventBus = new CountingEventBus();
	
	@Mock
	private PlayItemBeanFactory mPlayItemBeanFactory;
	
	@Before
    public void setUp() throws Exception {
		mPlaylistController = new PlaylistController(mEventBus, mPlayItemBeanFactory);
    }
	
	@Test
	public void onApplicationLoadEvent() {
		ApplicationConfig oConfig = new ApplicationConfig();
		JSONArray oPlaylist = mock(JSONArray.class);
		JSONObject oPlayItem1 = mock(JSONObject.class);
		oPlayItem1.put("url", new JSONString("http://videos.tripfilms.com/360p/4BAE2BA4EE543703AF8BC2DF6550BB73.mp4"));
		oPlaylist.set(0, oPlayItem1);
		JSONObject oPlayItem2 = mock(JSONObject.class);
		oPlayItem2.put("url", new JSONString("http://videos.tripfilms.com/480p/ECAA41D5E30E4BECC13FC1D3DA823750.mp4"));
		oPlaylist.set(1, oPlayItem2);
		oConfig.setPlaylist(oPlaylist);
		Application oApp = new Application(oConfig);
		ApplicationLoadEvent.fire(mPlaylistController, oApp);
		
		ApplicationReadyEvent.fire(mPlaylistController);
		assertEquals(1, mEventBus.getHandlerCount(PlaylistPlayEvent.getType()));
		
		PlayerPlayEndedEvent.fire(mPlaylistController);
		assertEquals(2, mEventBus.getHandlerCount(PlaylistPlayEvent.getType()));
		
		// nothing more to play. No event should be triggered then
		PlayerPlayEndedEvent.fire(mPlaylistController);
		assertEquals(2, mEventBus.getHandlerCount(PlaylistPlayEvent.getType()));
	}
}
