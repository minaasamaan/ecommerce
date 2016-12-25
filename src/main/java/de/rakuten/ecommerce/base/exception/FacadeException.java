/**
 * 
 */
package de.rakuten.ecommerce.base.exception;

/**
 * Exception specific for the facade layer in the application
 * 
 * @author Mina
 *
 */
public class FacadeException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param exception
	 * @param rootCause
	 * @param source
	 */
	public FacadeException(Throwable exception, RootCause rootCause, Class<?> source) {
		super(exception, rootCause, source);
	}
}
