package net.sf.video4j.gwt.client.model;

import java.util.ArrayList;
import java.util.List;

import fr.hd3d.html5.video.client.VideoSource;

/**
 * @author gumatias
 */
public class PlayerParameters {

    private List<VideoSource> mSources = new ArrayList<VideoSource>();
    
    private int mWidthInPixels;
    
    private int mHeightInPixels;
    
    private boolean mAutoPlay;
    
    private boolean mControls;
    
    public List<VideoSource> getSources() {
		return mSources;
	}

    public PlayerParameters withSource(VideoSource pSource) {
        mSources.add(pSource);
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