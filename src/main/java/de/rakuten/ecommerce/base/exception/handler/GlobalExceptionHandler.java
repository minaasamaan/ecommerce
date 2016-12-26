/**
 * 
 */
package de.rakuten.ecommerce.base.exception.handler;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestClientException;

import de.rakuten.ecommerce.base.exception.ApplicationErrorResponse;
import de.rakuten.ecommerce.base.exception.ApplicationException;

/**
 * @author Mina
 *
 */
@ControllerAdvice
public final class GlobalExceptionHandler {
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(ApplicationException.class)
	public ResponseEntity<ApplicationErrorResponse> rulesForCustomerNotFound(HttpServletRequest req,
			ApplicationException e) {
		ApplicationErrorResponse error = new ApplicationErrorResponse(e.getMessage(), req.getRequestURI());
		return new ResponseEntity<ApplicationErrorResponse>(error, e.getHttpStatus());
	}

	@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Data integrity violation")
	@ExceptionHandler(DataIntegrityViolationException.class)
	public void conflict() {
		// Nothing to do
	}

	@ExceptionHandler({ RestClientException.class })
	@ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "Error while contacting external service(s)")
	public void clientServiceError(HttpServletRequest req, Exception ex) {
		logError(req, ex);
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Unknown error")
	@ExceptionHandler(Exception.class)
	public void handleError(HttpServletRequest req, Exception ex) {
		logError(req, ex);
	}

	private void logError(HttpServletRequest req, Exception ex) {
		logger.error("Request: " + req.getRequestURL() + " raised " + ex);
	}
}
