package net.sf.video4j.gwt.client.model;

import fr.hd3d.html5.video.client.VideoSource.VideoType;

/**
 * @author gumatias
 */
public class PlayerParameters {

    private String mFileSource;
    
    private VideoType mVideoType;
    
    private int mWidthInPixels;
    
    private int mHeightInPixels;
    
    private boolean mAutoPlay;
    
    private boolean mControls;

    public String getFileSource() {
        return mFileSource;
    }

    public PlayerParameters withFileSource(String pFileSource) {
        mFileSource = pFileSource;
        return this;
    }

    public VideoType getVideoType() {
        return mVideoType;
    }

    public PlayerParameters withVideoType(VideoType pVideoType) {
        mVideoType = pVideoType;
        return this;
    }

    public int getWidthInPixels() {
        return mWidthInPixels;
    }

    public PlayerParameters withWidthInPixels(int pWidthInPixels) {
        mWidthInPixels = pWidthInPixels;
        return this;
    }

    public int getHeightInPixels() {
        return mHeightInPixels;
    }

    public PlayerParameters withHeightInPixels(int pHeightInPixels) {
        mHeightInPixels = pHeightInPixels;
        return this;
    }

    public boolean isAutoPlay() {
        return mAutoPlay;
    }

    public PlayerParameters withAutoPlay(boolean pAutoPlay) {
        mAutoPlay = pAutoPlay;
        return this;
    }

    public boolean hasControls() {
        return mControls;
    }

    public PlayerParameters withControls(boolean pControls) {
        mControls = pControls;
        return this;
    }

}