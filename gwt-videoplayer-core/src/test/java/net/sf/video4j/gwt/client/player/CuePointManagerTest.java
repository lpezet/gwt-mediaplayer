/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import static org.easymock.EasyMock.anyObject;
import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.getCurrentArguments;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import net.sf.video4j.gwt.client.player.CuePoint;
import net.sf.video4j.gwt.client.player.CuePointManager;

import org.easymock.IAnswer;
import org.junit.Test;

import com.google.gwt.event.shared.GwtEvent;

import fr.hd3d.html5.video.client.IVideoPlayer;
import fr.hd3d.html5.video.client.events.VideoCuePointEvent;
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;

/**
 * @author luc
 *
 */
public class CuePointManagerTest {

	@Test
	public void doIt() throws Exception {
		IVideoPlayer oPlayer = createMock(IVideoPlayer.class);
		expect(oPlayer.addTimeUpdateHandler(anyObject(VideoTimeUpdateHandler.class))).andReturn(null);
		oPlayer.fireEvent(anyObject());
		expectLastCall().andAnswer(new IAnswer<Object>() {
			@Override
			public Object answer() throws Throwable {
				GwtEvent<?> oEvent = (GwtEvent<?>) getCurrentArguments()[0];
				if (oEvent instanceof VideoCuePointEvent) {
					VideoCuePointEvent oCuePointEvent = (VideoCuePointEvent) oEvent;
					CuePoint oCP = oCuePointEvent.getCuePoint();
					System.out.println("Got CuePoint: " + oCP.toString());
				} else {
					System.out.println("Got a non-VideoCuePointEvent event: " + oEvent.getClass().getName());
				}
				return null;
			}
		}).anyTimes();
		
		expect(oPlayer.isPaused()).andReturn(false).anyTimes();
		expect(oPlayer.isEnded()).andReturn(false).anyTimes();
		replay(oPlayer);
		
		CuePointManager oManager = new CuePointManager(oPlayer);
		
		CuePoint oActual = oManager.add(1000, "A", null);
		assertNotNull(oActual);
		List<CuePoint> oActualList = oManager.getCuePoints();
		assertNotNull(oActualList);
		assertEquals(1, oActualList.size());
		
		oActual = oManager.add(2000, "B", null);
		assertNotNull(oActual);
		assertEquals(2, oActualList.size());
		assertEquals("B", oActualList.get(1).getName());
		
		
		VideoTimeUpdateEvent oEvent = new VideoTimeUpdateEvent();
		oEvent.setCurrentTime(0);
		oManager.onTimeUpdated(oEvent);
		
		oEvent.setCurrentTime(0.998);
		oManager.onTimeUpdated(oEvent);
		
		oEvent.setCurrentTime(1.002);
		oManager.onTimeUpdated(oEvent);
		
		oEvent.setCurrentTime(1.997);
		oManager.onTimeUpdated(oEvent);
		
		oEvent.setCurrentTime(1.999);
		oManager.onTimeUpdated(oEvent);
		
		
		oEvent.setCurrentTime(19.999);
		oManager.onTimeUpdated(oEvent);
		
		verify(oPlayer);
	}
}
