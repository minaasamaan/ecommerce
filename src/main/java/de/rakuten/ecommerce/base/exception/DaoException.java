/**
 * 
 */
package de.rakuten.ecommerce.base.exception;

/**
 * Exception specific for the DAO layer in the application
 * 
 * @author Mina
 *
 */
public class DaoException extends ApplicationException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param exception
	 * @param rootCause
	 * @param source
	 */
	public DaoException(Throwable exception, RootCause rootCause, Class<?> source) {
		super(exception, rootCause, source);
	}

}
