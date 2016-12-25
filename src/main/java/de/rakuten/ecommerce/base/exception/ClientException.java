package de.rakuten.ecommerce.base.exception;

/**
 * Exception specific for the service client layer in the application
 * 
 * @author Mina
 *
 */
public class ClientException extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param exception
	 * @param rootCause
	 * @param source
	 */
	public ClientException(Throwable exception, RootCause rootCause, Class<?> source) {
		super(exception, rootCause, source);
	}

}
