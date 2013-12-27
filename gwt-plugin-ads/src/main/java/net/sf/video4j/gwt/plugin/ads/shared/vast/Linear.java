/**
 * 
 */
package net.sf.video4j.gwt.plugin.ads.shared.vast;

import java.util.ArrayList;
import java.util.List;

/**
 * @author luc
 * 
 */
public class Linear extends Creative {

    private long            mDurationInSeconds;

    private List<Tracking>  mTrackingEvents = new ArrayList<Tracking>();

    private String          mAdParameters;

    private VideoClicks     mVideoClicks;

    private List<MediaFile> mMediaFiles     = new ArrayList<MediaFile>();

    public long getDurationInSeconds() {
        return mDurationInSeconds;
    }

    public void setDurationInSeconds(long pDurationInSeconds) {
        mDurationInSeconds = pDurationInSeconds;
    }

    public List<Tracking> getTrackingEvents() {
        return mTrackingEvents;
    }

    public void setTrackingEvents(List<Tracking> pTrackingEvents) {
        mTrackingEvents = pTrackingEvents;
    }

    public String getAdParameters() {
        return mAdParameters;
    }

    public void setAdParameters(String pAdParameters) {
        mAdParameters = pAdParameters;
    }

    public VideoClicks getVideoClicks() {
        return mVideoClicks;
    }

    public void setVideoClicks(VideoClicks pVideoClicks) {
        mVideoClicks = pVideoClicks;
    }

    public List<MediaFile> getMediaFiles() {
        return mMediaFiles;
    }

    public void setMediaFiles(List<MediaFile> pMediaFiles) {
        mMediaFiles = pMediaFiles;
    }

    @Override
    public String toString() {
        return "Linear [mDurationInSeconds=" + mDurationInSeconds
                + ", mTrackingEvents=" + mTrackingEvents + ", mAdParameters="
                + mAdParameters + ", mVideoClicks=" + mVideoClicks
                + ", mMediaFiles=" + mMediaFiles + "]";
    }

}
