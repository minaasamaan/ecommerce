/**
 * 
 */
package de.rakuten.ecommerce.productcategory.model;

import javax.persistence.Entity;

import de.rakuten.ecommerce.base.tree.model.TreeNode;

/**
 * @author Mina
 *
 */
@Entity(name = "product_category")
public class ProductCategory extends TreeNode<ProductCategory> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
