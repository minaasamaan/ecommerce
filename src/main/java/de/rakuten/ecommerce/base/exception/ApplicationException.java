/**
 * 
 */
package de.rakuten.ecommerce.base.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Mina
 *
 */
public abstract class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private HttpStatus httpStatus;

	private String message;

	/**
	 * @param httpStatus
	 * @param message
	 */
	public ApplicationException(HttpStatus httpStatus, String message) {
		this.setHttpStatus(httpStatus);
		this.setMessage(message);
	}

	/**
	 * @return the message
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the httpStatus
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	/**
	 * @param httpStatus
	 *            the httpStatus to set
	 */
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
}
