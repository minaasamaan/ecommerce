/**
 * 
 */
package de.rakuten.ecommerce.base.tree.exception;

import org.springframework.http.HttpStatus;

import de.rakuten.ecommerce.base.exception.ApplicationException;

/**
 * @author Mina
 *
 */
public class CannotDeleteNonLeafNodes extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param httpStatus
	 * @param message
	 */
	public CannotDeleteNonLeafNodes(Long id) {
		super(HttpStatus.CONFLICT,
				"Can't delete parent entity with id: " + id + ", please update/remove childs first.");
	}

}
