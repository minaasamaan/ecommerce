/**
 * 
 */
package de.rakuten.ecommerce.base.manager.exception;

import org.springframework.http.HttpStatus;

import de.rakuten.ecommerce.base.exception.ApplicationException;

/**
 * @author Mina
 *
 */
public class EntityNotFound extends ApplicationException {

	/**
	 * @param httpStatus
	 * @param message
	 */
	public EntityNotFound(Long entityId) {
		super(HttpStatus.NOT_FOUND, "Couldn't find entity with id: " + entityId);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
