/**
 * 
 */
package net.sf.video4j.gwt.client.be;

import java.net.URI;

/**
 * @author luc
 *
 */
public class IdURI {

	private String mId;
	
	private URI mURI;

	public String getId() {
		return mId;
	}

	public void setId(String pId) {
		mId = pId;
	}

	public URI getURI() {
		return mURI;
	}

	public void setURI(URI pURI) {
		mURI = pURI;
	}
}
