/**
 * 
 */
package net.sf.video4j.gwt.plugin.shared.vast;

import java.util.ArrayList;
import java.util.List;

/**
 * Second-level element surrounding wrapper ad pointing to Secondary ad server.
 * 
 * @author luc
 */
public class Wrapper extends Ad {

	// Indicates source ad server
	private AdSystem			mAdSystem;

	// URL of ad tag of downstream Secondary Ad Server
	private String				mVASTAdTagURI;

	// URL to request if ad does not play due to error
	private String				mError;

	// URL to request to track an impression
	private List<Impression>	mImpressions	= new ArrayList<Impression>();

	private List<Creative>		mCreatives		= new ArrayList<Creative>();

	private List<Extension>		mExtensions		= new ArrayList<Extension>();

	public AdSystem getAdSystem() {
		return mAdSystem;
	}

	public void setAdSystem(AdSystem pAdSystem) {
		mAdSystem = pAdSystem;
	}

	public String getVASTAdTagURI() {
		return mVASTAdTagURI;
	}

	public void setVASTAdTagURI(String pVASTAdTagURI) {
		mVASTAdTagURI = pVASTAdTagURI;
	}

	public String getError() {
		return mError;
	}

	public void setError(String pError) {
		mError = pError;
	}

	public List<Impression> getImpressions() {
		return mImpressions;
	}

	public void setImpressions(List<Impression> pImpressions) {
		mImpressions = pImpressions;
	}

	public List<Creative> getCreatives() {
		return mCreatives;
	}

	public void setCreatives(List<Creative> pCreatives) {
		mCreatives = pCreatives;
	}

	public List<Extension> getExtensions() {
		return mExtensions;
	}

	public void setExtensions(List<Extension> pExtensions) {
		mExtensions = pExtensions;
	}
}
