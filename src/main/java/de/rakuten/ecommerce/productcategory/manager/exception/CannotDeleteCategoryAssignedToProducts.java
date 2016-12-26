/**
 * 
 */
package de.rakuten.ecommerce.productcategory.manager.exception;

import org.springframework.http.HttpStatus;

import de.rakuten.ecommerce.base.exception.ApplicationException;

/**
 * @author Mina
 *
 */
public class CannotDeleteCategoryAssignedToProducts extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param httpStatus
	 * @param message
	 */
	public CannotDeleteCategoryAssignedToProducts(Long categoryId) {
		super(HttpStatus.CONFLICT,
				"Can't delete category with id: " + categoryId + ". Category is assigned to products.");
	}

}
