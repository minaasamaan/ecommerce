/**
 * 
 */
package de.rakuten.ecommerce.base.exception;

/**
 * Exception specific for the business manager layer in the application
 * 
 * @author Mina
 *
 */
public class ManagerException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param exception
	 * @param rootCause
	 * @param source
	 */
	public ManagerException(Throwable exception, RootCause rootCause, Class<?> source) {
		super(exception, rootCause, source);
	}
}
