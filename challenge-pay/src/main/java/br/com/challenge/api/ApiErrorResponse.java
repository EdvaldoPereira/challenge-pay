package br.com.challenge.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class ApiErrorResponse implements Serializable {
	
	@Serial
	private static final long serialVersionUID = -7884779548487779804L;
	
	private LocalDateTime localDateTime;
	private HttpStatus httpStatus;
	private String requestUri;
	private String method;
	@Setter
	private String message;
	
	public ApiErrorResponse() {
	}
	
	public ApiErrorResponse(final HttpStatus httpStatus, final HttpServletRequest request, final String message) {
		
		this.localDateTime = LocalDateTime.now();
		this.httpStatus = httpStatus;
		this.method = request.getMethod();
		this.message = message;
		this.requestUri = request.getRequestURI();
	}
	
	public ApiErrorResponse(final HttpStatus httpStatus, final HttpServletRequest request) {
		this(httpStatus, request, "Unknown Error");
	}
}
