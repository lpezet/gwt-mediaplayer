/**
 * 
 */
package net.sf.video4j.gwt.plugin.shared.vast;

/**
 * @author luc
 *
 */
public class InvalidDocumentException extends VASTException {
	
	private static final long serialVersionUID = -3572107906698835354L;
	private String mFound;
	private String[] mExpected;

	/**
	 * 
	 */
	public InvalidDocumentException(String pFound, String... pExpected) {
		mFound = pFound;
		mExpected = pExpected;
	}

}
