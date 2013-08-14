/**
 * 
 */
package net.sf.video4j.gwt.client.player;

import net.sf.video4j.gwt.client.util.NumberUtils;


/**
 * @author luc
 *
 */
public class PlayItem implements Comparable<PlayItem> {

	private Track mTrack;
	private boolean mAutoPlay;
	private int mStart = 0;
	private int mEnd = -1;
	private PlayItem mNext;
	private PlayItem mPrevious;
	private int mTotalPlays = -1; // -1 = infinite. Each play : totalPlays = totalPlays - 1. If reaches 0, then don't play
	private boolean mInStream;
	
	public PlayItem(Track pTrack) {
		mTrack = pTrack;
	}
	
	public PlayItem(Track pTrack, int pStart, int pEnd) {
		mTrack = pTrack;
		mStart = pStart;
		mEnd = pEnd;
	}
	
	public void setNext(PlayItem pNext) {
		mNext = pNext;
	}
	public void setPrevious(PlayItem pPrevious) {
		mPrevious = pPrevious;
	}
	public PlayItem getNext() {
		return mNext;
	}
	public PlayItem getPrevious() {
		return mPrevious;
	}
	public Track getTrack() {
		return mTrack;
	}

	public int getStart() {
		return mStart;
	}

	public void setStart(int pStart) {
		mStart = pStart;
	}

	public int getEnd() {
		return mEnd;
	}

	public void setEnd(int pEnd) {
		mEnd = pEnd;
	}

	@Override
	public int compareTo(PlayItem pOther) {
		if (pOther == null) return 1;
		Track oOtherTrack = pOther.getTrack();
		Track oThisTrack = getTrack();
		if (oOtherTrack.getId() != oThisTrack.getId()) return NumberUtils.compare(oThisTrack.getId(), oOtherTrack.getId());
		if (pOther.getStart() != getStart()) return NumberUtils.compare(getStart(), pOther.getStart());
		return NumberUtils.compare(getEnd(), pOther.getEnd());
	}

	public boolean isAutoPlay() {
		return mAutoPlay;
	}

	public void setAutoPlay(boolean pAutoPlay) {
		mAutoPlay = pAutoPlay;
	}

	public int getTotalPlays() {
		return mTotalPlays;
	}

	public void setTotalPlays(int pTotalPlays) {
		mTotalPlays = pTotalPlays;
	}
	
	public int decrementPlays() {
		return mTotalPlays--;
	}

	public boolean isInStream() {
		return mInStream;
	}

	public void setInStream(boolean pInStream) {
		mInStream = pInStream;
	}
}
