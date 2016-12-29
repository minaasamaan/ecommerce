/**
 * 
 */
package de.rakuten.ecommerce.product.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import de.rakuten.ecommerce.base.model.AbstractEntity;
import de.rakuten.ecommerce.productcategory.model.ProductCategory;

/**
 * @author Mina
 *
 */
@Entity
public class Product extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	private String name;

	@NotNull
	@ManyToOne(optional = false)
	@JoinColumn(name = "product_category_id")
	private ProductCategory productCategory;

	@NotNull
	private Double price;

	private String description;

	@Transient
	private String currency;

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
	 * @return the price
	 */
	public Double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Double price) {
		this.price = price;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency
	 *            the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the productCategory
	 */
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	/**
	 * @param productCategory
	 *            the productCategory to set
	 */
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
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
