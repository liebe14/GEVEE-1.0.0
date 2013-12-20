package com.gss.gevee.be.core.exception;


/**
 * 
 * Base exception class for exceptions in application 
 */
public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public BaseException() {
		 
	}

	/**
	 * @param message : message d'exception
	 */
	public BaseException(String message) {
		super(message);
		 
	}

	/**
	 * @param cause
	 */
	public BaseException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message : message d'exception
	 * @param cause
	 */
	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}
}
