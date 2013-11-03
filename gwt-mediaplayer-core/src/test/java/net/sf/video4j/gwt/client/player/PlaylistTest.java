/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
	public void cascade() {
		Playlist oList = new Playlist();
		
		Media oT1 = new Media();
		Media oT2 = new Media();
		Media oT3 = new Media();
		
		assertEquals(0, oList.count());
		oList.add(oT1);
		assertEquals(oT1, oList.getHead().getMedia());
		assertEquals(oT1, oList.getTail().getMedia());
		assertEquals(1, oList.count());
		
		oList.add(oT2);
		assertEquals(oT1, oList.getHead().getMedia());
		assertEquals(oT2, oList.getTail().getMedia());
		assertEquals(2, oList.count());
		
		oList.add(oT3);
		assertEquals(oT1, oList.getHead().getMedia());
		assertEquals(oT3, oList.getTail().getMedia());
		assertEquals(3, oList.count());
		
		assertPlaylistEquals(oList, oT1, oT2, oT3);
		assertStartEndsEqual(oList, 
				0, -1,	// T1
				0, -1,	// T2
				0, -1	// T3
		);
	}
		
	/**
	 * 
	 * Track 1   --------[###2###]-----[###4###]-----------
	 * Track 2   --[##1##]--------------------------------- // pre-roll
	 * Track 3 ------------------[##3##]------------------- // mid-roll
	 * Track 4   ------------------------------[##5##]----- // post-roll
	 * 
	 */
	@Test
	public void preMidAndPostRoll() {
		Playlist oList = new Playlist();
		
		Media oT1 = new Media();
		oT1.setDurationInSeconds(100);
		
		Media oPreRoll = new Media();		
		Media oMidRoll = new Media();
		Media oPostRoll = new Media();
		
		oList.add(oT1);
		assertEquals(oT1, oList.getHead().getMedia());
		assertEquals(oT1, oList.getTail().getMedia());
		
		oList.addChild(oPreRoll, oT1, 0);
		//printList(oList);
		oList.addChild(oMidRoll, oT1, 15);
		//printList(oList);
		oList.addChild(oPostRoll, oT1, -1);
		//printList(oList);
		
		assertPlaylistEquals(oList, oPreRoll, oT1, oMidRoll, oT1, oPostRoll);
		assertStartEndsEqual(oList, 
				0, -1,	// #1: pre-roll: from 0 until the end
				0, 15,	// #2: feature split: from 0 until cut-off
				0, -1,	// #3: mid-roll
				15, -1,	// #4: feature split (second): from 15 (cut-off) until the end
				0, -1	// #5: post-roll
		);
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
		
		assertPlaylistEquals(oList, oT1, oT2, oT21, oT2, oT3);
		assertStartEndsEqual(oList, 
				0, -1, 	// T1
				0, 10, 	// T2, first
				0, -1, 	// T2.1
				10, -1,	// T2, second
				0, -1	// T3
				);		
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
		oList.addChild(oT211, oT21, 15);
		
		assertPlaylistEquals(oList, oT1, oT2, oT21, oT211, oT21, oT2, oT3);
		assertStartEndsEqual(oList, 
				0, -1, 	// T1
				0, 10, 	// T2, first
				0, 15, 	// T21, first
				0, -1, 	// T211
				15, -1, // T21, second
				10, -1, // T2, second
				0, -1 	// T3
		);		
	}
	
	private void assertStartEndsEqual(Playlist pList, int... pStartEnds) {
		PlayItem oCurrent = pList.getHead();
		for (int i = 0; i < pStartEnds.length -1; i += 2) {
			assertStartEndEqual(pStartEnds[i], pStartEnds[i+1], oCurrent);
			if (oCurrent != null) oCurrent = oCurrent.getNext();
		}
		if (oCurrent != null) fail("More items in playlist!!! Current = " + oCurrent + ".");
	}

	private void assertPlaylistEquals(Playlist pList, Media... pMediaInOrder) {
		PlayItem oCurrent = pList.getHead();
		for (int i = 0; i < pMediaInOrder.length; i++) {
			assertEquals(pMediaInOrder[i], oCurrent == null ? null : oCurrent.getMedia());
			if (oCurrent != null) oCurrent = oCurrent.getNext();
		}
		if (oCurrent != null) fail("More items in playlist!!! Current = " + oCurrent + ".");
	}

	private void assertStartEndEqual(int pStart, int pEnd, PlayItem pItem) {
		assertEquals(pStart, pItem.getStart());
		assertEquals(pEnd, pItem.getEnd());
	}

	private void printList(Playlist pList) {
		PlaylistNavigator oNav = new PlaylistNavigator(pList);
		int i = 0;
		int oMaxLoops = 100;
		while (oNav.hasNext()) {
			i++;
			System.out.print(oNav.next());
			if (oNav.hasNext()) System.out.print(" --> ");
			if (i >= oMaxLoops) {
				System.out.println("[INFINITE LOOP]");
				break;
			}
		}
		System.out.println();
	}
}
