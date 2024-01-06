package br.com.challenge.exceptions;

import br.com.challenge.api.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {
	
	@ExceptionHandler(ResourceDataIntegrityViolationException.class)
	protected ResponseEntity<ApiErrorResponse> handleResourceDataIntegrityViolationException(
			final ResourceDataIntegrityViolationException exception, final HttpServletRequest request) {
		
		final ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, request,
		                                                            exception.getMessage());
		return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValid(
			final MethodArgumentNotValidException exception, final HttpServletRequest request) {
		
		Map<String, String> errors = new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
		final ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, request, errors.toString());
		return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ApiErrorResponse> handleHttpMessageNotReadableException(
			final HttpMessageNotReadableException exception, final HttpServletRequest request) {
		
		final ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, request,
		                                                            exception.getMessage());
		return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
	}
	
	@ExceptionHandler(TransactionNotSupported.class)
	protected ResponseEntity<ApiErrorResponse> handleTransactionNotSupportedException(
			final TransactionNotSupported exception, final HttpServletRequest request) {
		
		final ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, request,
		                                                            exception.getMessage());
		return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
	}
	
	@ExceptionHandler(InsufficientFundsException.class)
	protected ResponseEntity<ApiErrorResponse> handleInsufficientFundsException(
			final InsufficientFundsException exception, final HttpServletRequest request) {
		
		final ApiErrorResponse errorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, request,
		                                                            exception.getMessage());
		return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
	}
	
	
	
}
