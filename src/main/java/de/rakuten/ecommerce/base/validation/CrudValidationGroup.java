/**
 * 
 */
package de.rakuten.ecommerce.base.validation;

import javax.validation.groups.Default;

/**
 * @author Mina
 *
 */
public interface CrudValidationGroup {

	public interface Create extends Default {

	}

	public interface Update extends Default {

	}

	public interface Read extends Default {

	}

	public interface Delete extends Default {

	}
}
