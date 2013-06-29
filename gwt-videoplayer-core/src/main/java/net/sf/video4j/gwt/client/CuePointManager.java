/**
 * 
 */
package net.sf.video4j.gwt.client;

import java.util.LinkedList;
import java.util.List;

import fr.hd3d.html5.video.client.IVideoPlayer;
import fr.hd3d.html5.video.client.events.VideoCuePointEvent;
import fr.hd3d.html5.video.client.events.VideoTimeUpdateEvent;
import fr.hd3d.html5.video.client.handlers.VideoTimeUpdateHandler;

/**
 * @author luc
 *
 */
public class CuePointManager implements VideoTimeUpdateHandler {

	private static final int MAX_LINEAR_SEARCH = 50;
	private List<CuePoint> mCuePoints = new LinkedList<CuePoint>();
	private int mCurrentCuePointIndex = 0;
	private double mCuePointTolerance = 200 / 100; // Firefox fires the timeupdate event once per frame. Safari 5 and Chrome 6 fire every 250ms. Opera 10.50 fires every 200ms.
	private IVideoPlayer mVideoPlayer;
	
	public CuePointManager(IVideoPlayer pVideoPlayer) {
		mVideoPlayer = pVideoPlayer;
		mVideoPlayer.addTimeUpdateHandler(this);
	}
	
	public void reset() {
		mCuePoints.clear();
		mCurrentCuePointIndex = 0;
	}
	
	List<CuePoint> getCuePoints() {
		return mCuePoints;
	}
	
	public void play(long pTimeInMillis) {
		//double oNow = mVideoPlayer.getCurrentTime();
		if (isVideoPlaying() && !mCuePoints.isEmpty()) {
			System.out.println("Index = " + mCurrentCuePointIndex + ", size = " + mCuePoints.size());
			while (mCurrentCuePointIndex < mCuePoints.size() 
					&& mCuePoints.get(mCurrentCuePointIndex).getTimeInMillis() <= (pTimeInMillis + mCuePointTolerance)) {
				mVideoPlayer.fireEvent(new VideoCuePointEvent(mCuePoints.get(mCurrentCuePointIndex++)));
			}
		}
		/*
		 var now:Number = _owner.getVideoPlayer(_id).playheadTime;
			if (_owner.getVideoPlayer(_id).stateResponsive && asCuePoints != null) {
				while ( _asCuePointIndex < asCuePoints.length &&
						asCuePoints[_asCuePointIndex].time <= now + _asCuePointTolerance ) {
					_owner.dispatchEvent(new MetadataEvent(MetadataEvent.CUE_POINT, false, false, deepCopyObject(asCuePoints[_asCuePointIndex++]), _id));
				}
			}
		 */
	}
	
	private boolean isVideoPlaying() {
		// TODO: provide a getState() in VideoWidget? state machine?
		// TODO: actually, the player might be stopped is there was an error while playing for example. In that case, paused=false and ended=false, but the player is not playing anymore.
		return !mVideoPlayer.isPaused() && !mVideoPlayer.isEnded();
	}

	public CuePoint add(int pTimeInMillis, String pName, Object pParameters) {
		CuePoint oCP = new CuePoint(pTimeInMillis, pName, pParameters);
		if (mCuePoints.isEmpty()) {
			mCuePoints.add(oCP);
		} else {
			int oIndex = getCuePointIndex(mCuePoints, pTimeInMillis, null, -1, -1, true);
			oIndex = (mCuePoints.get(oIndex).getTimeInMillis() > oCP.getTimeInMillis()) ? 0 : oIndex + 1;
			mCuePoints.add(oIndex, oCP);
		}
		
		// adjust current cuepoint index...
		
		return oCP;
	}
	
	public CuePoint remove(int pTimeInMillis, String pName) {
		boolean oTimeSpecified = pTimeInMillis >= 0;
		boolean oNameSpecified = pName != null;
		
		int oIndex = getCuePointIndex(mCuePoints, pTimeInMillis, pName, -1, -1, false);
		if (oIndex < 0) return null;
		CuePoint oCP = mCuePoints.remove(oIndex);
		
		// adjust current cuepoint index...
		
		
		return oCP;
	}

	private int getCuePointIndex(List<CuePoint> pList, int pTimeInMillis, String pName, int pStart, int pLength, boolean pCloseIsOk) {
		if (pList == null || pList.isEmpty()) {
			return -1;
		}
		
		int oStart = (pStart < 0) ? 0 : pStart;
		int oLength = (pLength < 0) ? pList.size() : pLength;
		
		boolean oTimeUndefined = pTimeInMillis < 0;
		boolean oNameUndefined = pName == null;

		// name is passed in and time is undefined or closeIsOK is
		// true, search for first name starting at either start
		// parameter index or index at or after passed in time, respectively
		if (!oNameUndefined && (pCloseIsOk || oTimeUndefined)) {
			int oFirstIndex;
			int oIndex;
			if (oTimeUndefined) {
				oFirstIndex = oStart;
			} else {
				oFirstIndex = getCuePointIndex(pList, pTimeInMillis, null, -1, -1, pCloseIsOk);
			}
			for (oIndex = oFirstIndex; oIndex >= oStart; oIndex--) {
				if (pList.get(oIndex).getName().equals(pName)) break;
			}
			if (oIndex >= oStart) return oIndex;
			for (oIndex = oFirstIndex + 1; oIndex < oLength; oIndex++) {
				if (pList.get(oIndex).getName().equals(pName)) break;
			}
			if (oIndex < oLength) return oIndex;
			return -1;
		}

		int oResult;

		// iteratively check if short length
		if (oLength <= MAX_LINEAR_SEARCH) {
			int oMax = oStart + oLength;
			int i = oStart;
			for (; i < oMax; i++) {
				oResult = cuePointCompare(pTimeInMillis, pName, pList.get(i));
				if (oResult == 0) return i;
				if (oResult < 0) break;
			}
			System.out.println(">>> Iteratively : i = " + i);
			if (pCloseIsOk) {
				if (i > 0) return i - 1;
				return 0;
			}
			return -1;
		}

		// split list and recurse
		int oHalfLength = oLength / 2;
		int oSplitIndex = oStart + oHalfLength;
		oResult = cuePointCompare(pTimeInMillis, pName, pList.get(oSplitIndex));
		if (oResult < 0) {
			return getCuePointIndex( pList, pTimeInMillis, pName, pStart, oHalfLength, pCloseIsOk);
		}
		if (oResult > 0) {
			return getCuePointIndex( pList, pTimeInMillis, pName, oSplitIndex + 1, oHalfLength - 1 + (oLength % 2), pCloseIsOk);
		}
		return oSplitIndex;
	}

	private int cuePointCompare(int pTimeInMillis, String pName, CuePoint pCuePoint) {
		int oResult = Long.compare(pTimeInMillis, pCuePoint.getTimeInMillis());
		if (oResult == 0 && pName != null) {
			return pName.compareTo(pCuePoint.getName());
		}
		return 0;
	}

	@Override
	public void onTimeUpdated(VideoTimeUpdateEvent pEvent) {
		play((int) (pEvent.getCurrentTime() * 1000)); // currentTime is in seconds like 1.6403322...
	}
}
