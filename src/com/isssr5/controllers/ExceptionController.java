package com.isssr5.controllers;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.OutOfRangeException;
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

	@ExceptionHandler(NullDataSeriesException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad sent dataseries")
	public String nullDataSeriesExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(BadDataSeriesUrlException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad dataseries url")
	public String badDataSeriesUrlExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(BadOperandInput.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "bad operand input")
	public String badOperandInputHandler() {
		return "err";
	}

	@ExceptionHandler(IOException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "I/O Exception")
	public String iOExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(NumberFormatException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "wrong data type")
	public String numberFormatExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(SQLException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "SQL exception")
	public String sqlExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(RequestedMacroServiceIsPrivateException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "Requested MacroService is private")
	public String requestedMacroServiceIsPrivateExceptionHandler() {
		return "err";
	}

	@ExceptionHandler(PrivateElementaryServiceException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Private elementary services in a public custom macro service")
	public String PrivateElementaryServiceExceptionHandler() {
		return "err";
	}
	@ExceptionHandler(NotExistingScaleException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Scale doesn't exist")
	public String NotExistingScaleExceptionExceptionHandler() {
		return "err";
	}
	@ExceptionHandler(NotExistingOperandException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Operand doesn't exist")
	public String NotExistingOperandExceptionExceptionHandler() {
		return "err";
	}
	
	@ExceptionHandler(NotExistingUserException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User doesn't exist")
	public String NotExistingUserExceptionHandler() {
		return "err";
	}
	
	@ExceptionHandler(OutOfRangeException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Alpha is not in the range (0,0.5]")
	public String OutOfRangeExceptionHandler() {
		return "err";
	}
	
	@ExceptionHandler(DimensionMismatchException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Sample dimesion mismatch")
	public String DimensionMismatchExceptionHandler() {
		return "err";
	}
	
	@ExceptionHandler(WrongScaleForMacroServiceId.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong Scale for MacroService Id")
	public String WrongScaleForMacroServiceIdExceptionHandler() {
		return "err";
	}
	
	@ExceptionHandler(WrongDistributionException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong distribution name")
	public String WrongDistributionExceptionHandler() {
		return "err";
	}
	
	@ExceptionHandler(ExistentUserException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User id already exists")
	public String ExistentUserExceptionHandler() {
		return "err";
	}
	
	@ExceptionHandler(NullUserException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing User id")
	public String NullUserExceptionHandler() {
		return "err";
	}
	
	@ExceptionHandler(NullUPswUserException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Missing User psw")
	public String NullUPswUserExceptionHandler() {
		return "err";
	}


}
