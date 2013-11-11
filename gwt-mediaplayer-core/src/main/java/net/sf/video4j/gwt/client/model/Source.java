package net.sf.video4j.gwt.client.model;

/**
 * @author Gustavo Matias
 */
public class Source {

	private String	mURI;

	private String	mType;

	private int		mBitrate;

	public String getURI() {
		return mURI;
	}

	public void setURI(String pURI) {
		mURI = pURI;
	}

	public String getType() {
		return mType;
	}

	public void setType(String pType) {
		mType = pType;
	}

	public int getBitrate() {
		return mBitrate;
	}

	public void setBitrate(int pBitrate) {
		mBitrate = pBitrate;
	}

}