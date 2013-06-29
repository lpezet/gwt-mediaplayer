/**
 * 
 */
package net.sf.video4j.gwt.client;

/**
 * @author luc
 *
 */
public class CuePoint {

	private long mTimeInMillis;
	private String mName;
	private Object mParameters;
	
	
	public CuePoint(long pTimeInMillis, String pName, Object pParameters) {
		mTimeInMillis = pTimeInMillis;
		mName = pName;
		mParameters = pParameters;
	}
	
	@Override
	public String toString() {
		StringBuffer oBuf = new StringBuffer();
		oBuf.append("[name: ").append(mName).append(", time: ").append(mTimeInMillis).append("ms");
		if (getParameters() != null) 
			oBuf.append(", params:").append(mParameters.toString());
		oBuf.append("]");
		return oBuf.toString();
	}
	
	@Override
	public int hashCode() {
		int h = 1;
		h = h * 17 + Long.valueOf(mTimeInMillis).hashCode();
		h = h * 31 + (mName == null ? 0 : mName.hashCode());
		h = h * 13 + (mParameters == null ? 0 : mParameters.hashCode());
		return h;
	}
	/*
	@Override
	public boolean equals(Object pObj) {
		if (!(pObj instanceof CuePoint)) return false;
		CuePoint oOther = (CuePoint) pObj;
		if ((oOther.getName() == null && getName() != null)
				|| (oOther.getName() != null && getName() == null)) return false;
		if (oOther.getName() != null && !oOther.getName().equals(getName())) return false;
		
		if ((oOther.getParameters() == null && getParameters() != null)
			|| (oOther.getParameters() != null && getParameters() == null)) return false;
		
		if (oOther.getParameters() != null && !oOther.getParameters().equals(getParameters())) return false;
		return 
				getName().equals(oOther.getName())
				&& getTimeInMillis() == oOther.getTimeInMillis()
				&& getParameters().equals(oOther.getParameters());
	}
	*/

	public long getTimeInMillis() {
		return mTimeInMillis;
	}


	public String getName() {
		return mName;
	}


	public Object getParameters() {
		return mParameters;
	}
	
	
}
