/**
 * 
 */
package net.sf.video4j.gwt.plugin.caption.webvtt.impl;

import net.sf.video4j.gwt.plugin.caption.html5.TextTrack;
import net.sf.video4j.gwt.plugin.caption.webvtt.AlignSetting;
import net.sf.video4j.gwt.plugin.caption.webvtt.DirectionSetting;
import net.sf.video4j.gwt.plugin.caption.webvtt.VTTCue;

/**
 * @author luc
 *
 */
public class VTTCueImpl implements VTTCue {
	
	private String mText;
	private String mId;
	private double mStartTime;
	private double mEndTime;
	private boolean mPauseOnExit;
	private String mRegionId;
	private DirectionSetting mDirectionSetting;
	private boolean mSnapToLines;
	private long mLine;
	private long mPosition;
	private long mSize;
	private AlignSetting mAlignSetting;
	private String mCueAsHTML;
	
	public VTTCueImpl() {
	}

	@Override
	public TextTrack getTrack() {
		return null;
	}

	@Override
	public String getId() {
		return mId;
	}

	@Override
	public void setId(String pId) {
		mId = pId;
	}

	@Override
	public double getStartTime() {
		return mStartTime;
	}

	@Override
	public void setStartTime(double pTime) {
		mStartTime = pTime;
	}

	@Override
	public double getEndTime() {
		return mEndTime;
	}

	@Override
	public void setEndTime(double pTime) {
		mEndTime = pTime;
	}

	@Override
	public boolean isPauseOnExit() {
		return mPauseOnExit;
	}

	@Override
	public void setPauseOnExit(boolean pValue) {
		mPauseOnExit = pValue;
	}

	@Override
	public String getRegionId() {
		return mRegionId;
	}

	@Override
	public void setRegionId(String pId) {
		mRegionId = pId;
	}

	@Override
	public DirectionSetting getVertical() {
		return mDirectionSetting;
	}

	@Override
	public void setVertical(DirectionSetting pSetting) {
		mDirectionSetting = pSetting;
	}

	@Override
	public boolean isSnapToLines() {
		return mSnapToLines;
	}

	@Override
	public void setSnapToLines(boolean pSnapToLines) {
		mSnapToLines = pSnapToLines;
	}

	@Override
	public long getLine() {
		return mLine;
	}

	@Override
	public void setLine(long pLine) {
		mLine = pLine;
	}

	@Override
	public long getPosition() {
		return mPosition;
	}

	@Override
	public void setPosition(long pPosition) {
		mPosition = pPosition;
	}

	@Override
	public long getSize() {
		return mSize;
	}

	@Override
	public void setSize(long pSize) {
		mSize = pSize;
	}

	@Override
	public AlignSetting getAlign() {
		return mAlignSetting;
	}

	@Override
	public void setAlign(AlignSetting pSetting) {
		mAlignSetting = pSetting;
	}

	@Override
	public String getText() {
		return mText;
	}

	@Override
	public void setText(String pText) {
		mText = pText;
	}

	@Override
	public String getCueAsHTML() {
		return mCueAsHTML;
	}

	@Override
	public void setCueAsHTML(String pHTML) {
		mCueAsHTML = pHTML;
	}

}
