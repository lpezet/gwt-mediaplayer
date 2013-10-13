/**
 * 
 */
package net.sf.video4j.gwt.plugin.shared.vast;

/**
 * @author luc
 *
 */
public class VASTException extends RuntimeException {

	private static final long serialVersionUID = 6465598964034127240L;

	/**
	 * 
	 */
	public VASTException() {
	}

	/**
	 * @param pArg0
	 */
	public VASTException(String pArg0) {
		super(pArg0);
	}

	/**
	 * @param pArg0
	 */
	public VASTException(Throwable pArg0) {
		super(pArg0);
	}

	/**
	 * @param pArg0
	 * @param pArg1
	 */
	public VASTException(String pArg0, Throwable pArg1) {
		super(pArg0, pArg1);
	}

	/**
	 * @param pArg0
	 * @param pArg1
	 * @param pArg2
	 * @param pArg3
	 */
	public VASTException(String pArg0, Throwable pArg1, boolean pArg2, boolean pArg3) {
		super(pArg0, pArg1, pArg2, pArg3);
	}

}
