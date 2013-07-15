/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

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
 * So Track 2 has 2 PlayNodes.
 * 
 * @author luc
 *
 */
public class Playlist {
	
	private static class TrackPlayItems {
		
		private static class PlayItemComparator implements Comparator<PlayItem> {
			@Override
			public int compare(PlayItem pO1, PlayItem pO2) {
				if (pO1.getEnd() == -1) return -1;
				if (pO2.getEnd() == -1) return 1;
				return Integer.compare(pO1.getEnd(), pO2.getEnd());
			}
		}
		
		private Track mTrack;
		private Set<PlayItem> mItems = new TreeSet<PlayItem>(new PlayItemComparator());
		
		public TrackPlayItems(Track pTrack) {
			mTrack = pTrack;
		}
		
		public Set<PlayItem> getItems() {
			return mItems;
		}
		
		public Track getTrack() {
			return mTrack;
		}
		
	}
	
	private AtomicInteger mTrackIdGenerator = new AtomicInteger(1);
	private PlayItem mHead;
	private PlayItem mTail;
	private PlayItem mCursor;
	private Map<Integer, TrackPlayItems> mTrackPlayItemsById = new HashMap<Integer, Playlist.TrackPlayItems>();
	
	public void add(Track pTrack) {
		pTrack.setId(mTrackIdGenerator.getAndIncrement());
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
	}
	
	public void addChild(Track pTrack, Track pParentTrack, int pCutOffTime) {
		PlayItem oItem = new PlayItem(pTrack);
		TrackPlayItems oParentTrackPlayItems = mTrackPlayItemsById.get(pParentTrack.getId());
		if (oParentTrackPlayItems == null) throw new RuntimeException("Parent track must exist or have valid id.");
		if (oParentTrackPlayItems.getItems().isEmpty()) throw new RuntimeException("Invalid state: parent track has no more nodes.");
		if (pCutOffTime == 0) {
			PlayItem oParentPlayItem = oParentTrackPlayItems.getItems().iterator().next();
			insertBefore(oItem, oParentPlayItem);			
		} else if (pCutOffTime == -1) {
			PlayItem oParentPlayItem = oParentTrackPlayItems.getItems().iterator().next();
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
				if (oPivot.getStart() == pCutOffTime) {
					// insert before
					insertBefore(oItem, oPivot);
				} else if (oPivot.getEnd() == pCutOffTime) {
					// insert after
					insertAfter(oItem, oPivot);
				} else {
					PlayItem oBefore = new PlayItem(oPivot.getTrack(), oPivot.getStart(), pCutOffTime);
					PlayItem oAfter = new PlayItem(oPivot.getTrack(), pCutOffTime, oPivot.getEnd());
					oBefore.setPrevious(oPivot.getPrevious());
					oPivot.getPrevious().setNext(oBefore);
					
					oAfter.setNext(oPivot.getNext());
					oPivot.getNext().setPrevious(oAfter);
					
					oBefore.setNext(oItem);
					oItem.setPrevious(oBefore);
					oAfter.setPrevious(oItem);
					oItem.setNext(oAfter);
				}
			} else {
				throw new RuntimeException("How can this happen???");
			}
		}
		addTrackPlayItem(pTrack, oItem);
	}
	
	private void addTrackPlayItem(Track pTrack, PlayItem pItem) {
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
		pItem.setPrevious(pParentItem);
		pItem.setNext(oNext);
		oNext.setPrevious(pItem);
		pParentItem.setNext(pItem);
	}
	
	private void insertBefore(PlayItem pItem, PlayItem pParentItem) {
		PlayItem oPrevious = pParentItem.getPrevious();
		pItem.setPrevious(oPrevious);
		oPrevious.setNext(pItem);
		pItem.setNext(pParentItem);
		pParentItem.setPrevious(pItem);
	}

	public boolean hasNext() {
		return mCursor != mTail;
	}
	
	public PlayItem next() {
		if (!hasNext()) return null;
		if (mCursor == null) {
			mCursor = mHead;
		} else {
			mCursor = mCursor.getNext();
		}
		return mCursor;
	}
	
	public PlayItem previous() {
		if (!hasPrevious()) return null;
		mCursor = mCursor.getPrevious();
		return mCursor;
	}
	
	public boolean hasPrevious() {
		return mCursor != mHead;
	}
	
	/*
	 * Do we need it?
	 * 
	public int size() {
		return 
	}
	*/
}
