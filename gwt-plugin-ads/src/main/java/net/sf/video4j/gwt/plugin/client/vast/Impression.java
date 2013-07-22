/**
 * 
 */
package net.sf.video4j.gwt.plugin.client.vast;

import com.google.gwt.safehtml.shared.SafeUri;

/**
 * @author luc
 *
 */
public class Impression {

	private String mId;
	private SafeUri mURI;
	
	public String getId() {
		return mId;
	}
	public void setId(String pId) {
		mId = pId;
	}
	public SafeUri getURI() {
		return mURI;
	}
	public void setURI(SafeUri pURI) {
		mURI = pURI;
	}
}
