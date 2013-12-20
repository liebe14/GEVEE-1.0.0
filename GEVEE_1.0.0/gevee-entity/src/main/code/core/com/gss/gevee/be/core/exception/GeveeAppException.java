/**
 * 
 */
package com.gss.gevee.be.core.exception;

public class GeveeAppException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public GeveeAppException() {
		 
	}

	/**
	 * @param message
	 *            : message d'exception
	 */
	public GeveeAppException(String message) {
		super(message);
		 
	}

	/**
	 * @param cause
	 */
	public GeveeAppException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 *            : message d'exception
	 * @param cause
	 */
	public GeveeAppException(String message, Throwable cause) {
		super(message, cause);
	}

	// @Override
	// public String toString() {
	// return super.toString();
	// }

}
