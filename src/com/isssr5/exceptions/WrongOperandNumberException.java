package com.isssr5.exceptions;

/* Exception raised when client doesn't specify number of operands in Customized 
 * MacroService or number of operands doesn't match parameter list's size
 */
public class WrongOperandNumberException extends Exception {

	private static final long serialVersionUID = 1L;

	public WrongOperandNumberException() {
		super();
	}

}
