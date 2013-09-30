/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import net.sf.video4j.gwt.client.util.NumberUtils;

/**
 * Use "durationchange" event to load the duration information from a track.
 * 
 * Example: Simple ("Cascade") Playlist:
 * Track 1   --[################]-----------------------------
 * Track 2   -------------------[###########]-----------------
 * Track 3   -------------------------------[###########]-----
 * 
 * Example: Playlist with mid-roll in Track 2
 * Track 1   --[################]-------------------------------------
 * Track 2   -------------------[####]------[#######]-----------------
 * Track 2.1 ------------------------[######]-------------------------
 * Track 3   ---------------------------------------[###########]-----
 * 
 * So Track 2 has 2 PlayItems.
 * 
 * @author luc
 *
 */
public class Playlist {
	
	private static class TrackPlayItems {
		
		private static class PlayItemComparator implements Comparator<PlayItem> {
			@Override
			public int compare(PlayItem pO1, PlayItem pO2) {
				return NumberUtils.compare(pO1.getStart(), pO2.getStart());
				/*
				if (pO1.getEnd() == -1) return -1;
				if (pO2.getEnd() == -1) return 1;
				return NumberUtils.compare(pO1.getEnd(), pO2.getEnd());
				*/
			}
		}
		
		private Media mTrack;
		private SortedSet<PlayItem> mItems = new TreeSet<PlayItem>(new PlayItemComparator());
		
		public TrackPlayItems(Media pTrack) {
			mTrack = pTrack;
		}
		
		public SortedSet<PlayItem> getItems() {
			return mItems;
		}
		
		public Media getTrack() {
			return mTrack;
		}
		
	}
	
	private int mTrackIdGenerator = 0;
	private PlayItem mHead;
	private PlayItem mTail;
	private Map<Integer, TrackPlayItems> mTrackPlayItemsById = new HashMap<Integer, Playlist.TrackPlayItems>();
	
	public PlayItem add(Media pTrack) {
		pTrack.setId(++mTrackIdGenerator);
		PlayItem oItem = new PlayItem(pTrack);
		if (mHead == null) {
			mHead = oItem;
			mTail = oItem;
		} else {
			mTail.setNext(oItem);
			oItem.setPrevious(mTail);
			mTail = oItem;
		}
		addTrackPlayItem(pTrack, oItem);
		return oItem;
	}
	
	public PlayItem addChild(Media pTrack, Media pParentTrack, int pCutOffTime) {
		pTrack.setId(++mTrackIdGenerator);
		PlayItem oItem = new PlayItem(pTrack);
		TrackPlayItems oParentTrackPlayItems = mTrackPlayItemsById.get(pParentTrack.getId());
		if (oParentTrackPlayItems == null) throw new RuntimeException("Parent track must exist or have valid id.");
		if (oParentTrackPlayItems.getItems().isEmpty()) throw new RuntimeException("Invalid state: parent track has no more nodes.");
		if (pCutOffTime == 0) {
			//System.out.println("Adding child BEFORE parent...");
			PlayItem oParentPlayItem = oParentTrackPlayItems.getItems().first();
			insertBefore(oItem, oParentPlayItem);			
		} else if (pCutOffTime == -1) {
			//System.out.println("Adding child AFTER parent...");
			PlayItem oParentPlayItem = oParentTrackPlayItems.getItems().last();
			//System.out.println("## Parent = " + oParentPlayItem);
			insertAfter(oItem, oParentPlayItem);
		} else {
			// TODO: problem: if duration is not specified for parent track, how do I know to add this play node after the parent?
			// TODO: 
			PlayItem oPivot = null;
			for (PlayItem oPItem : oParentTrackPlayItems.getItems()) {
				if (inBetween(pCutOffTime, oPItem.getStart(), oPItem.getEnd())) {
					oPivot = oPItem;
					break;
				}
			}
			if (oPivot != null) {
				//System.out.println("Pivot = " + oPivot);
				if (oPivot.getStart() == pCutOffTime) {
					//System.out.println("Pivot: insert before...");
					// insert before
					insertBefore(oItem, oPivot);
				} else if (oPivot.getEnd() == pCutOffTime) {
					//System.out.println("Pivot: insert after...");
					// insert after
					insertAfter(oItem, oPivot);
				} else {
					//System.out.println("Pivot: split...");
					PlayItem oBefore = new PlayItem(oPivot.getMedia(), oPivot.getStart(), pCutOffTime);
					PlayItem oAfter = new PlayItem(oPivot.getMedia(), pCutOffTime, oPivot.getEnd());
					
					setNext(oPivot.getPrevious(), oBefore); //setPrevious(oBefore, oPivot.getPrevious());
					setNext(oAfter, oPivot.getNext());
					
					setNext(oBefore, oItem);
					setNext(oItem, oAfter); //setPrevious(oAfter, oItem);
					
					oParentTrackPlayItems.getItems().remove(oPivot); // check
					oParentTrackPlayItems.getItems().add(oBefore);
					oParentTrackPlayItems.getItems().add(oAfter);
					
					if (mTail == oPivot) mTail = oAfter;
					
				}
			} else {
				throw new RuntimeException("How can this happen???");
			}
		}
		addTrackPlayItem(pTrack, oItem);
		return oItem;
	}
	
	private void setNext(PlayItem pItem, PlayItem pNext) {
		if (pItem == null || pNext == null) return; 
		pItem.setNext(pNext);
		pNext.setPrevious(pItem);
	}
	
	private void addTrackPlayItem(Media pTrack, PlayItem pItem) {
		TrackPlayItems oTrackPlayItems = mTrackPlayItemsById.get(pTrack.getId());
		if (oTrackPlayItems == null) {
			oTrackPlayItems = new TrackPlayItems(pTrack);
			mTrackPlayItemsById.put(pTrack.getId(), oTrackPlayItems);
		}
		oTrackPlayItems.getItems().add(pItem);
	}

	private boolean inBetween(int pTime, int pStart, int pEnd) {
		return pStart <= pTime
				&& (pTime <= pEnd || pEnd == -1);
	}

	private void insertAfter(PlayItem pItem, PlayItem pParentItem) {
		PlayItem oNext = pParentItem.getNext();
		setNext(pParentItem, pItem);
		setNext(pItem, oNext);		
		if (mTail == pParentItem) mTail = pItem;
	}
	
	private void insertBefore(PlayItem pItem, PlayItem pParentItem) {
		PlayItem oPrevious = pParentItem.getPrevious();
		setNext(oPrevious, pItem);
		setNext(oPrevious, pItem); //setPrevious(pItem, oPrevious);
		setNext(pItem, pParentItem); //setPrevious(pParentItem, pItem);
		setNext(pItem, pParentItem);
		
		if (mHead == pParentItem) mHead = pItem;
	}
	
	public int count() {
		return count(false);
	}
	
	public int count(boolean pIncludeAds) {
		int oTracks = 0;
		for (TrackPlayItems i : mTrackPlayItemsById.values()) {
			if (!i.getTrack().isAd() || pIncludeAds) oTracks++;
		}
		return oTracks;
	}
	
	protected PlayItem getHead() {
		return mHead;
	}
	
	protected PlayItem getTail() {
		return mTail;
	}
	
}
