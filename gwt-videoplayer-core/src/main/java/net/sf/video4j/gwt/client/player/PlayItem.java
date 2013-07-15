/**
 * 
 */
package net.sf.video4j.gwt.client.player;


/**
 * @author luc
 *
 */
public class PlayItem implements Comparable<PlayItem> {

	private Track mTrack;
	private int mStart = 0;
	private int mEnd = -1;
	private PlayItem mNext;
	private PlayItem mPrevious;
	
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
		if (oOtherTrack.getId() != oThisTrack.getId()) return Integer.compare(oThisTrack.getId(), oOtherTrack.getId());
		if (pOther.getStart() != getStart()) return Integer.compare(getStart(), pOther.getStart());
		return Integer.compare(getEnd(), pOther.getEnd());
	}
}