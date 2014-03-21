package com.isssr5.exceptions;

/*
 * Exception raised when client doesn't specify MacroService or Elementary Service for customized MacroService
 */

public class NullElementaryServiceListException extends Exception {

	private static final long serialVersionUID = 1L;

	public NullElementaryServiceListException() {
		super();
	}

}
