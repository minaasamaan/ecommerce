/**
 * 
 */
package de.rakuten.ecommerce.product.client.exception;

import org.springframework.http.HttpStatus;

import de.rakuten.ecommerce.base.exception.ApplicationException;

/**
 * @author Mina
 *
 */
public class CurrencyNotSupported extends ApplicationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param httpStatus
	 * @param message
	 */
	public CurrencyNotSupported(String currency) {
		super(HttpStatus.NOT_FOUND, "currency: " + currency + ", isn't supported, please choose supported currency");
	}

}
