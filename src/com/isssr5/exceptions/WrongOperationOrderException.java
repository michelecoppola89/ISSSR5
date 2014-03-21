package com.isssr5.exceptions;

/*
 * Exception raised when parameter specified by client for an elementary service doesn't exist
 */

public class WrongOperationOrderException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongOperationOrderException() {
		super();
	}

}
