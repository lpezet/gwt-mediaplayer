/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

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
        IVideoPlayer oPlayer = mock(IVideoPlayer.class);
        when(oPlayer.addTimeUpdateHandler(any(VideoTimeUpdateHandler.class))).thenReturn(null);

        when(oPlayer.isPaused()).thenReturn(false);
        when(oPlayer.isEnded()).thenReturn(false);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock pInvocation) throws Throwable {
                GwtEvent<?> oEvent = (GwtEvent<?>) pInvocation.getArguments()[0];
                if (oEvent instanceof VideoCuePointEvent) {
                    VideoCuePointEvent oCuePointEvent = (VideoCuePointEvent) oEvent;
                    CuePoint oCP = oCuePointEvent.getCuePoint();
                    System.out.println("Got CuePoint: " + oCP.toString());
                } else {
                    System.out.println("Got a non-VideoCuePointEvent event: " + oEvent.getClass().getName());
                }
                return null;
            }
        }).when(oPlayer).fireEvent(anyObject());

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
    }
}
