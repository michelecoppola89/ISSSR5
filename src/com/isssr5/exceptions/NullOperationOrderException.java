package com.isssr5.exceptions;

/*
 * Exception raised when client doesn't specify parameter for each 
 * elementary service composing customized MacroService
 */

public class NullOperationOrderException extends Exception {


	private static final long serialVersionUID = 1L;

	public NullOperationOrderException() {
		super();
	}

}
