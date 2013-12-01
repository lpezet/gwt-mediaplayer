package net.sf.video4j.gwt.client.model;

import net.sf.video4j.gwt.client.player.Media;

/**
 * @author gumatias
 */
public class PlayerParameters {

	private Media	mMedia;

	private int		mWidthInPixels;

	private int		mHeightInPixels;

	private boolean	mAutoPlay;

	private boolean	mControls;

	public Media getMedia() {
		return mMedia;
	}

	public PlayerParameters withMedia(Media pMedia) {
		mMedia = pMedia;
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