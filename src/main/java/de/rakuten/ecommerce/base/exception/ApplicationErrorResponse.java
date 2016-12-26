/**
 * 
 */
package de.rakuten.ecommerce.base.exception;

/**
 * @author Mina
 *
 */
public class ApplicationErrorResponse {

	private String errorMessage;

	private String requestUri;

	/**
	 * 
	 */
	public ApplicationErrorResponse(String errorMessage, String requestUri) {
		this.setErrorMessage(errorMessage);
		this.setRequestUri(requestUri);
	}

	/**
	 * @return the requestUri
	 */
	public String getRequestUri() {
		return requestUri;
	}

	/**
	 * @param requestUri the requestUri to set
	 */
	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
