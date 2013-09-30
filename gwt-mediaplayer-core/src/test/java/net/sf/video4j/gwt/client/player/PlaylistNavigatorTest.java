/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author luc
 *
 */
public class PlaylistNavigatorTest {

	@Test
	public void simple() {
		Playlist oList = new Playlist();
		
		Media oT1 = new Media();
		Media oT2 = new Media();
		Media oT3 = new Media();
		
		oList.add(oT1);
		oList.add(oT2);
		oList.add(oT3);
		
		PlaylistNavigator oNavigator = new PlaylistNavigator(oList);
		assertTrue(oNavigator.hasNext());
		PlayItem oActual = oNavigator.next();
		assertNotNull(oActual);
		assertEquals(oT1, oActual.getMedia());
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT2, oActual.getMedia());
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT3, oActual.getMedia());
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
		
		Media oT1 = new Media();
		Media oT2 = new Media();
		Media oT3 = new Media();
		
		oList.add(oT1);
		oList.add(oT2);
		oList.add(oT3);
		
		Media oT21 = new Media();
		oList.addChild(oT21, oT2, 10);
		
		assertEquals(4, oList.count(false));
		
		PlaylistNavigator oNavigator = new PlaylistNavigator(oList);
		
		// T1
		assertTrue(oNavigator.hasNext());
		PlayItem oActual = oNavigator.next();
		assertNotNull(oActual);
		assertEquals(oT1, oActual.getMedia());
		
		// T2
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT2, oActual.getMedia());
		
		// T21
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT21, oActual.getMedia());
		
		// T2
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT2, oActual.getMedia());
		
		// T3
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT3, oActual.getMedia());
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
		
		Media oT1 = new Media();
		Media oT2 = new Media();
		Media oT3 = new Media();
		
		oList.add(oT1);
		oList.add(oT2);
		oList.add(oT3);
		
		Media oT21 = new Media();
		oList.addChild(oT21, oT2, 10);
		
		Media oT211 = new Media();
		oList.addChild(oT211, oT21, 10);
		
		PlaylistNavigator oNavigator = new PlaylistNavigator(oList);
		
		// T1
		assertTrue(oNavigator.hasNext());
		PlayItem oActual = oNavigator.next();
		assertNotNull(oActual);
		assertEquals(oT1, oActual.getMedia());
		
		// T2
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT2, oActual.getMedia());
		
		// T21
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT21, oActual.getMedia());
		
		// T211
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT211, oActual.getMedia());
		
		// T21
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT21, oActual.getMedia());
		
		// T2
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT2, oActual.getMedia());
		
		// T3
		assertTrue(oNavigator.hasNext());
		oActual = oNavigator.next();
		assertEquals(oT3, oActual.getMedia());
	}

}
