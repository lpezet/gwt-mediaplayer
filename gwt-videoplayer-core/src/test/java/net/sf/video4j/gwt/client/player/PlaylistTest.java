/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.googlecode.gwt.test.GwtModule;
import com.googlecode.gwt.test.GwtTest;

/**
 * @author luc
 *
 */
@GwtModule("net.sf.video4j.gwt.client.player.Playlist")
public class PlaylistTest extends GwtTest {

	/**
	 * Track 1   --[################]-----------------------------
	 * Track 2   -------------------[###########]-----------------
	 * Track 3   -------------------------------[###########]-----
	 *  
	 */
	@Test
	public void simple() {
		Playlist oList = new Playlist();
		
		Track oT1 = new Track();
		Track oT2 = new Track();
		Track oT3 = new Track();
		
		oList.add(oT1);
		oList.add(oT2);
		oList.add(oT3);
		
		assertTrue(oList.hasNext());
		PlayItem oActual = oList.next();
		assertNotNull(oActual);
		assertEquals(oT1, oActual.getTrack());
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT2, oActual.getTrack());
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT3, oActual.getTrack());
	}
	
	/**
	 * 
	 * Track 1   --[################]--------------------------------------
	 * Track 2   -------------------[####]-------[#######]-----------------
	 * Track 2.1 ------------------------[#######]-------------------------
	 * Track 3   ----------------------------------------[###########]-----
	 * 
	 */
	@Test
	public void midRoll1() {
		Playlist oList = new Playlist();
		
		Track oT1 = new Track();
		Track oT2 = new Track();
		Track oT3 = new Track();
		
		oList.add(oT1);
		oList.add(oT2);
		oList.add(oT3);
		
		Track oT21 = new Track();
		oList.addChild(oT21, oT2, 10);
		
		// T1
		assertTrue(oList.hasNext());
		PlayItem oActual = oList.next();
		assertNotNull(oActual);
		assertEquals(oT1, oActual.getTrack());
		
		// T2
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT2, oActual.getTrack());
		
		// T21
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT21, oActual.getTrack());
		
		// T2
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT2, oActual.getTrack());
		
		// T3
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT3, oActual.getTrack());
	}
	
	/**
	 * Track 1     --[################]--------------------------------------
	 * Track 2     -------------------[####]--------------[####]-------------
	 * Track 2.1   ------------------------[####]-----[###]------------------
	 * Track 2.1.1 -----------------------------[#####]----------------------
	 * Track 3     --------------------------------------------[###########]-
	 * 
	 */
	@Test
	public void deepList() {
		Playlist oList = new Playlist();
		
		Track oT1 = new Track();
		Track oT2 = new Track();
		Track oT3 = new Track();
		
		oList.add(oT1);
		oList.add(oT2);
		oList.add(oT3);
		
		Track oT21 = new Track();
		oList.addChild(oT21, oT2, 10);
		
		Track oT211 = new Track();
		oList.addChild(oT211, oT21, 10);
		
		// T1
		assertTrue(oList.hasNext());
		PlayItem oActual = oList.next();
		assertNotNull(oActual);
		assertEquals(oT1, oActual.getTrack());
		
		// T2
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT2, oActual.getTrack());
		
		// T21
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT21, oActual.getTrack());
		
		// T211
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT211, oActual.getTrack());
		
		// T21
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT21, oActual.getTrack());
		
		// T2
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT2, oActual.getTrack());
		
		// T3
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT3, oActual.getTrack());
	}
}
