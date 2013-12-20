/**
 * 
 */
package com.gss.gevee.be.core.exception;

public class GeveePersistenceException extends BaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public GeveePersistenceException() {
	}

	/**
	 * @param message
	 *            : message d'exception
	 */
	public GeveePersistenceException(String message) {
		super(message);
		 
	}

	/**
	 * @param cause
	 */
	public GeveePersistenceException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 *            : message d'exception
	 * @param cause
	 */
	public GeveePersistenceException(String message, Throwable cause) {
		super(message, cause);
	}

	// @Override
	// public String toString() {
	// return super.toString();
	// }
}
