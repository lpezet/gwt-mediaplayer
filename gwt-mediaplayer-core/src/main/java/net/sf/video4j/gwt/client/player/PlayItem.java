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

	private Media mMedia;
	private boolean mAutoPlay;
	private int mStart = 0;
	private int mEnd = -1;
	private PlayItem mNext;
	private PlayItem mPrevious;
	private int mTotalPlays = -1; // -1 = infinite. Each play : totalPlays = totalPlays - 1. If reaches 0, then don't play
	private boolean mInStream;
	
	public PlayItem(Media pMedia) {
		mMedia = pMedia;
	}
	
	public PlayItem(Media pMedia, int pStart, int pEnd) {
		mMedia = pMedia;
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
	public Media getMedia() {
		return mMedia;
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
		Media oOtherMedia = pOther.getMedia();
		Media oThisMedia = getMedia();
		if (oOtherMedia.getId() != oThisMedia.getId()) return NumberUtils.compare(oThisMedia.getId(), oOtherMedia.getId());
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
	@Override
	public String toString() {
	    return "PlayItem[hash:"+ hashCode() +", Media:" + ((mMedia == null) ? "NA" : mMedia.getId()) + ", start:"+ mStart +", end:"+ mEnd +"]";
	}	
}
