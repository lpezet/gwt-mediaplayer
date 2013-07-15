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
	
	private static class TrackNodes {
		
		private static class PlayItemComparator implements Comparator<PlayItem> {
			@Override
			public int compare(PlayItem pO1, PlayItem pO2) {
				if (pO1.getEnd() == -1) return -1;
				if (pO2.getEnd() == -1) return 1;
				return Integer.compare(pO1.getEnd(), pO2.getEnd());
			}
		}
		
		private Track mTrack;
		private Set<PlayItem> mNodes = new TreeSet<PlayItem>(new PlayItemComparator());
		
		public TrackNodes(Track pTrack) {
			mTrack = pTrack;
		}
		
		public Set<PlayItem> getNodes() {
			return mNodes;
		}
		
		public Track getTrack() {
			return mTrack;
		}
		
	}
	
	private AtomicInteger mTrackIdGenerator = new AtomicInteger(1);
	private PlayItem mHead;
	private PlayItem mTail;
	private PlayItem mCursor;
	private Map<Integer, TrackNodes> mTrackNodesById = new HashMap<Integer, Playlist.TrackNodes>();
	
	public void add(Track pTrack) {
		pTrack.setId(mTrackIdGenerator.getAndIncrement());
		PlayItem oNode = new PlayItem(pTrack);
		if (mHead == null) {
			mHead = oNode;
			mTail = oNode;
		} else {
			mTail.setNext(oNode);
			oNode.setPrevious(mTail);
			mTail = oNode;
		}
		addTrackNode(pTrack, oNode);
	}

	private void addTrackNode(Track pTrack, PlayItem pNode) {
		TrackNodes oTrackNodes = mTrackNodesById.get(pTrack.getId());
		if (oTrackNodes == null) {
			oTrackNodes = new TrackNodes(pTrack);
			mTrackNodesById.put(pTrack.getId(), oTrackNodes);
		}
		oTrackNodes.getNodes().add(pNode);
	}
	
	public void addChild(Track pTrack, Track pParentTrack, int pCutOffTime) {
		PlayItem oNode = new PlayItem(pTrack);
		TrackNodes oParentTrackNodes = mTrackNodesById.get(pParentTrack.getId());
		if (oParentTrackNodes == null) throw new RuntimeException("Parent track must exist or have valid id.");
		if (oParentTrackNodes.getNodes().isEmpty()) throw new RuntimeException("Invalid state: parent track has no more nodes.");
		if (pCutOffTime == 0) {
			PlayItem oParentNode = oParentTrackNodes.getNodes().iterator().next();
			insertBefore(oNode, oParentNode);			
		} else if (pCutOffTime == -1) {
			PlayItem oParentNode = oParentTrackNodes.getNodes().iterator().next();
			insertAfter(oNode, oParentNode);
		} else {
			// TODO: problem: if duration is not specified for parent track, how do I know to add this play node after the parent?
			// TODO: 
			PlayItem oPivot = null;
			for (PlayItem oPNode : oParentTrackNodes.getNodes()) {
				if (inBetween(pCutOffTime, oPNode.getStart(), oPNode.getEnd())) {
					oPivot = oPNode;
					break;
				}
			}
			if (oPivot != null) {
				if (oPivot.getStart() == pCutOffTime) {
					// insert before
					insertBefore(oNode, oPivot);
				} else if (oPivot.getEnd() == pCutOffTime) {
					// insert after
					insertAfter(oNode, oPivot);
				} else {
					PlayItem oBefore = new PlayItem(oPivot.getTrack(), oPivot.getStart(), pCutOffTime);
					PlayItem oAfter = new PlayItem(oPivot.getTrack(), pCutOffTime, oPivot.getEnd());
					oBefore.setPrevious(oPivot.getPrevious());
					oPivot.getPrevious().setNext(oBefore);
					
					oAfter.setNext(oPivot.getNext());
					oPivot.getNext().setPrevious(oAfter);
					
					oBefore.setNext(oNode);
					oNode.setPrevious(oBefore);
					oAfter.setPrevious(oNode);
					oNode.setNext(oAfter);
				}
			} else {
				throw new RuntimeException("How can this happen???");
			}
		}
		addTrackNode(pTrack, oNode);
	}

	private boolean inBetween(int pTime, int pStart, int pEnd) {
		return pStart <= pTime
				&& (pTime <= pEnd || pEnd == -1);
	}

	private void insertAfter(PlayItem pNode, PlayItem pParentNode) {
		PlayItem oNext = pParentNode.getNext();
		pNode.setPrevious(pParentNode);
		pNode.setNext(oNext);
		oNext.setPrevious(pNode);
		pParentNode.setNext(pNode);
	}
	
	private void insertBefore(PlayItem pNode, PlayItem pParentNode) {
		PlayItem oPrevious = pParentNode.getPrevious();
		pNode.setPrevious(oPrevious);
		oPrevious.setNext(pNode);
		pNode.setNext(pParentNode);
		pParentNode.setPrevious(pNode);
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
