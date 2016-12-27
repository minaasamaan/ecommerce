/**
 * 
 */
package de.rakuten.ecommerce.base.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import de.rakuten.ecommerce.base.validation.CrudValidationGroup.Create;
import de.rakuten.ecommerce.base.validation.CrudValidationGroup.Update;

/**
 * @author Mina
 *
 */
public abstract class AbstractDto implements Dto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Null(groups = Create.class, message = "Provided id value, while operation is create.")
	@NotNull(groups = Update.class, message = "Id value cannot be null, while operation is update.")
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
