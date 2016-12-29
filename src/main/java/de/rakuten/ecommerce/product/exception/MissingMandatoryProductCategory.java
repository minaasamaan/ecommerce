/**
 * 
 */
package de.rakuten.ecommerce.product.exception;

import org.springframework.http.HttpStatus;

import de.rakuten.ecommerce.base.exception.ApplicationException;

/**
 * @author Mina
 *
 */
public class MissingMandatoryProductCategory extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param httpStatus
	 * @param message
	 */
	public MissingMandatoryProductCategory() {
		super(HttpStatus.PRECONDITION_REQUIRED, "Missing the mandatory product category in the provided request.");
	}

}
