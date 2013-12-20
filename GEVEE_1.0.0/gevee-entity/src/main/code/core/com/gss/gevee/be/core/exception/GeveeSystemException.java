package com.gss.gevee.be.core.exception;


public class GeveeSystemException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public GeveeSystemException() {
		 
	}

	/**
	 * @param message
	 *            : message d'exception
	 */
	public GeveeSystemException(String message) {
		super(message);
		 
	}

	/**
	 * @param cause
	 */
	public GeveeSystemException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 *            : message d'exception
	 * @param cause
	 */
	public GeveeSystemException(String message, Throwable cause) {
		super(message, cause);
	}

	// @Override
	// public String toString() {
	// return super.toString();
	// }
}
