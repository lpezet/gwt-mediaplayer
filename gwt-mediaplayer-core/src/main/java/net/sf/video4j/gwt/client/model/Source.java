package net.sf.video4j.gwt.client.model;

/**
 * @author Gustavo Matias
 */
public class Source {

	private String		mURI;
	private String		mType;
	private int			mBitrate;
	private Delivery	mDelivery;

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

	public Delivery getDelivery() {
		return mDelivery;
	}

	public void setDelivery(Delivery pDelivery) {
		mDelivery = pDelivery;
	}

}