package com.bank.app.core;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class EngageExceptionHandler extends ResponseEntityExceptionHandler {
	private static Logger logger = Logger.getLogger(EngageExceptionHandler.class);

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		// TODO Auto-generated method stub
		ArrayList<Error> listError = new ArrayList<Error>();
		List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
		for (ObjectError error : allErrors) {
			System.out.println(error.getDefaultMessage());
			String defaultMessage = error.getDefaultMessage();
			Error errorObj = new Error(defaultMessage, defaultMessage);
			listError.add(errorObj);

		}
		EngageResponse engageResponse = new EngageResponse();
		engageResponse.setError(listError);
		return new ResponseEntity<>(engageResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request,
			HttpServletRequest httpRequest) {
		
		List<Error> errors = new ArrayList<>();

		Error error = new Error();
		error.setMessage(ex.getMessage());
		error.setDescription(ex.getLocalizedMessage());
		errors.add(error);

		EngageResponse response = new EngageResponse();
		response.setError(errors);
		response.setMessage(ex.getMessage());
		logger.error("exception raise"+ex);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(EngageNonFatalException.class)
	public final ResponseEntity<Object> handleEngageNonFatalException(EngageNonFatalException ex, WebRequest request,
			HttpServletRequest httpRequest) {
		String tag = ".handleEngageNonFatalException";
		System.out.println(tag);
		String message = ex.getMessage();
		List<Error> lstErrors = new ArrayList<>();
		Error error = new Error();
		error.setDescription(message);
		error.setMessage(message);
		lstErrors.add(error);

		EngageResponse response = new EngageResponse();
		response.setError(lstErrors);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(EngageFatalException.class)
	public final ResponseEntity<Object> handleEngageFatalException(EngageFatalException ex, WebRequest request,
			HttpServletRequest httpRequest) {
		String tag = ".handleEngageFatalException";
		System.out.println(tag);
		String message = ex.getMessage();
		List<Error> lstErrors = new ArrayList<>();
		Error error = new Error();
		error.setDescription(message);
		error.setMessage(message);
		lstErrors.add(error);

		EngageResponse response = new EngageResponse();
		response.setError(lstErrors);
		logger.error("handleEngageFatalException raise "+ex);
		return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
