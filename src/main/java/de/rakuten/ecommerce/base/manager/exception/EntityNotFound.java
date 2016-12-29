/**
 * 
 */
package de.rakuten.ecommerce.base.manager.exception;

import org.springframework.http.HttpStatus;

import de.rakuten.ecommerce.base.exception.ApplicationException;
import de.rakuten.ecommerce.base.model.AbstractEntity;

/**
 * @author Mina
 *
 */
public class EntityNotFound extends ApplicationException {

	/**
	 * @param httpStatus
	 * @param message
	 */
	public EntityNotFound(Long entityId, Class<? extends AbstractEntity> entityClass) {
		super(HttpStatus.NOT_FOUND, "Couldn't find entity(" + entityClass.getSimpleName() + ") with id: " + entityId);
	}

	/**
	 * @param entityClass
	 */
	public EntityNotFound(Class<? extends AbstractEntity> entityClass) {
		super(HttpStatus.NOT_FOUND, "Couldn't find entities(" + entityClass.getSimpleName() + ")");
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
