/**
 * 
 */
package de.rakuten.ecommerce.base.exception.handler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;

import de.rakuten.ecommerce.base.exception.ApplicationException;

/**
 * @author Mina
 *
 */
@ControllerAdvice
public final class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	private static final String NA = "NA";

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ApplicationErrorResponse> applicationBusinessException(HttpServletRequest request,
			ApplicationException exception) {
		ApplicationErrorResponse error = new ApplicationErrorResponse(exception.getMessage(), NA, request.getMethod(),
				request.getRequestURI());
		return new ResponseEntity<ApplicationErrorResponse>(error, exception.getHttpStatus());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApplicationErrorResponse> validationException(HttpServletRequest request,
			MethodArgumentNotValidException exception) {
		ApplicationErrorResponse response = ApplicationErrorResponse.getNewErrorResponse(request.getMethod(),
				request.getRequestURI());
		exception.getBindingResult().getAllErrors()
				.forEach(error -> response.addNewError(error.getDefaultMessage(), error.toString()));
		return new ResponseEntity<ApplicationErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ApplicationErrorResponse> validationException(HttpServletRequest request,
			ConstraintViolationException exception) {
		ApplicationErrorResponse response = ApplicationErrorResponse.getNewErrorResponse(request.getMethod(),
				request.getRequestURI());
		exception.getConstraintViolations().forEach(violation -> response
				.addNewError(violation.getPropertyPath() + " " + violation.getMessage(), violation.toString()));
		return new ResponseEntity<ApplicationErrorResponse>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ApplicationErrorResponse> badRequestFormat(HttpServletRequest request,
			HttpMessageNotReadableException exception) {
		logError(request, exception);
		ApplicationErrorResponse error = new ApplicationErrorResponse("Malformed REST request.", exception.getMessage(),
				request.getMethod(), request.getRequestURI());
		return new ResponseEntity<ApplicationErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ RestClientException.class })
	public ResponseEntity<ApplicationErrorResponse> clientServiceError(HttpServletRequest request,
			RestClientException exception) {
		logError(request, exception);
		ApplicationErrorResponse error = new ApplicationErrorResponse("Error while contacting external service(s).",
				exception.getMessage(), request.getMethod(), request.getRequestURI());
		return new ResponseEntity<ApplicationErrorResponse>(error, HttpStatus.BAD_GATEWAY);
	}

	@ExceptionHandler({ Throwable.class })
	public ResponseEntity<ApplicationErrorResponse> generalError(HttpServletRequest request, Throwable exception) {
		logError(request, exception);
		ApplicationErrorResponse error = new ApplicationErrorResponse(
				"Unknown error happened while server trying to fulfill the request.", exception.getMessage(),
				request.getMethod(), request.getRequestURI());
		return new ResponseEntity<ApplicationErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void logError(HttpServletRequest req, Throwable ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
	}
}
