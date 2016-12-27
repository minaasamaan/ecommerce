/**
 * 
 */
package de.rakuten.ecommerce.base.exception.handler;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mina
 *
 */
public class ApplicationErrorResponse {

	private String requestUri;

	private List<ErrorInfo> errors;

	/**
	 * { 02 "errors": [ 03 { 04 "userMessage":
	 * "Sorry, the requested resource does not exist", 05 "internalMessage":
	 * "No car found in the database", 06 "code": 34, 07 "more info":
	 * "http://dev.mwaysolutions.com/blog/api/v1/errors/12345" 08 } 09 ] 10 }
	 * 
	 */
	public ApplicationErrorResponse(String userMessage, String internalMessage, String requestHttpMethod,
			String requestUri) {
		this.setRequestUri(requestHttpMethod + " " + requestUri);
		this.addNewError(userMessage, internalMessage);
	}

	/**
	 * @param requestUri
	 * 
	 */
	private ApplicationErrorResponse(String requestUri) {
		setRequestUri(requestUri);
	}

	public ApplicationErrorResponse addNewError(String userMessage, String internalMessage) {
		getErrors().add(new ErrorInfo(userMessage, internalMessage));
		return this;
	}

	public static ApplicationErrorResponse getNewErrorResponse(String requestHttpMethod, String requestUri) {
		return new ApplicationErrorResponse(requestHttpMethod + " " + requestUri);
	}

	protected static class ErrorInfo {
		private String userMessage;
		private String internalMessage;

		/**
		 * @param userMessage
		 * @param internalMessage
		 */
		public ErrorInfo(String userMessage, String internalMessage) {
			super();
			this.userMessage = userMessage;
			this.internalMessage = internalMessage;
		}

		/**
		 * @return the userMessage
		 */
		public String getUserMessage() {
			return userMessage;
		}

		/**
		 * @return the internalMessage
		 */
		public String getInternalMessage() {
			return internalMessage;
		}
	}

	/**
	 * @return the requestUri
	 */
	public String getRequestUri() {
		return requestUri;
	}

	/**
	 * @param requestUri
	 *            the requestUri to set
	 */
	private void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	/**
	 * @return the errors
	 */
	public List<ErrorInfo> getErrors() {
		if (errors == null) {
			setErrors(new ArrayList<>());
		}
		return errors;
	}

	/**
	 * @param errors
	 *            the errors to set
	 */
	private void setErrors(List<ErrorInfo> errors) {
		this.errors = errors;
	}
}
