/**
 * 
 */
package de.rakuten.ecommerce.productcategory.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import de.rakuten.ecommerce.base.tree.model.TreeNode;

/**
 * @author Mina
 *
 */
@Entity
public class ProductCategory extends TreeNode<ProductCategory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;

	private String description;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
