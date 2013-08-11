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
		
		assertEquals(3, oList.count());
		
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
		
		assertEquals(4, oList.count(false));
		
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
	
	//Test
	public void playItemDecision() {
		Playlist oList = new Playlist();
		IPlayItemDecisionManager oAdsOnceOnly = new IPlayItemDecisionManager() {
			@Override
			public boolean canPlay(Track pTrack) {
				Integer oTimesAdPlayed = (Integer) pTrack.getMetaData().get("ad");
				if (oTimesAdPlayed == null) return true; // not an ad
				return (oTimesAdPlayed.intValue() == 0);
			}
		};
		
		//oList.setPlayItemDecisionManager(oAdsOnceOnly);
		
		Track oPreRoll = new Track();
		oPreRoll.getMetaData().put("ad", 0);
		oPreRoll.setAd(true);
		Track oT1 = new Track();
		
		oList.add(oPreRoll);
		oList.add(oT1);
		
		assertEquals(1, oList.count()); // i.e. not counting preroll ad.
		assertEquals(1, oList.count(false)); // i.e. not counting preroll ad.
		assertEquals(2, oList.count(true));
		
		// PreRoll
		assertTrue(oList.hasNext());
		PlayItem oActual = oList.next();
		assertNotNull(oActual);
		assertEquals(oPreRoll, oActual.getTrack());
		
		// T1
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT1, oActual.getTrack());
		
		// Reset
		oList.reset();
		oPreRoll.getMetaData().put("ad", 1);
		
		// T1
		assertTrue(oList.hasNext());
		oActual = oList.next();
		assertEquals(oT1, oActual.getTrack());
		
	}
}
