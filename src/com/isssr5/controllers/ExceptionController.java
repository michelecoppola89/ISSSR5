package com.isssr5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.isssr5.exceptions.*;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(DomainException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "wrong domain")
	public String domainExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(NullDomainException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "null domain")
	public String nullDomainExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(IntervalDomainException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "min>=max error")
	public String intervalDomainExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(EnumerateDomainException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "empty scale point error")
	public String enumerateDomainExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(NullOperandException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "no operands specified")
	public String nullOperandExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(NullOperandTypeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "no/bad operand type specified")
	public String nullOperandTypeExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(NullOperandModeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad operand mode specified")
	public String nullOperandModeExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(NullElementaryServiceListException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "no elementary service specified")
	public String nullElementaryServiceListHandler() {
		return "err";
	}

	@ExceptionHandler(NullMacroServiceIdException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing macro service id")
	public String nullElementaryServiceListExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(NullOperationOrderException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing parameters for elemantary/macro service specificied")
	public String nullOperationOrderExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(WrongOperandNumberException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong operand number")
	public String wrongOperandNumberExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(WrongOperationOrderException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "input parameter for elementary/macro service doesn't exist")
	public String wrongOperationOrderExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(NotExistingMacroServiceException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "requested macro service does not exist")
	public String notExistingMacroServiceExceptionHandler() {
		return "err";
	}
	

}
