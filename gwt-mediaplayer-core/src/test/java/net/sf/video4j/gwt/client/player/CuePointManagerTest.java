/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import net.sf.video4j.gwt.client.event.CuePointEvent;
import net.sf.video4j.gwt.client.event.PlayerTimeUpdateEvent;

import org.junit.Test;

import com.google.web.bindery.event.shared.testing.CountingEventBus;

/**
 * @author luc
 * 
 */
public class CuePointManagerTest {

    @Test
    public void doIt() throws Exception {
    	/*
        IVideoPlayer oPlayer = mock(IVideoPlayer.class);
        when(oPlayer.addTimeUpdateHandler(any(VideoTimeUpdateHandler.class))).thenReturn(null);

        when(oPlayer.isPaused()).thenReturn(false);
        when(oPlayer.isEnded()).thenReturn(false);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock pInvocation) throws Throwable {
                GwtEvent<?> oEvent = (GwtEvent<?>) pInvocation.getArguments()[0];
                if (oEvent instanceof CuePointEvent) {
                    CuePointEvent oCuePointEvent = (CuePointEvent) oEvent;
                    CuePoint oCP = oCuePointEvent.getCuePoint();
                    System.out.println("Got CuePoint: " + oCP.toString());
                } else {
                    System.out.println("Got a non-VideoCuePointEvent event: " + oEvent.getClass().getName());
                }
                return null;
            }
        }).when(oPlayer).fireEvent(anyObject());
		*/
    	CountingEventBus oBus = new CountingEventBus();
        CuePointManager oManager = new CuePointManager(oBus);

        CuePoint oActual = oManager.add(1000, "A", null);
        assertNotNull(oActual);
        List<CuePoint> oActualList = oManager.getCuePoints();

        assertNotNull(oActualList);
        assertEquals(1, oActualList.size());

        oActual = oManager.add(2000, "B", null);
        assertNotNull(oActual);
        assertEquals(2, oActualList.size());
        assertEquals("B", oActualList.get(1).getName());
        
        oBus.fireEvent(new PlayerTimeUpdateEvent(0));
        assertEquals(0, oBus.getFiredCount(CuePointEvent.getType()));
        
        oBus.fireEvent(new PlayerTimeUpdateEvent(0.997));
        assertEquals(0, oBus.getFiredCount(CuePointEvent.getType()));
        
        oBus.fireEvent(new PlayerTimeUpdateEvent(1.002));
        assertEquals(1, oBus.getFiredCount(CuePointEvent.getType()));
        
        oBus.fireEvent(new PlayerTimeUpdateEvent(1.997));
        assertEquals(1, oBus.getFiredCount(CuePointEvent.getType()));
        
        oBus.fireEvent(new PlayerTimeUpdateEvent(1.999));
        assertEquals(2, oBus.getFiredCount(CuePointEvent.getType()));
        
        oBus.fireEvent(new PlayerTimeUpdateEvent(19.999));
        assertEquals(2, oBus.getFiredCount(CuePointEvent.getType()));
        
    }
}
