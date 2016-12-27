/**
 * 
 */
package de.rakuten.ecommerce.product.dto;

import javax.validation.constraints.NotNull;

import de.rakuten.ecommerce.base.dto.AbstractDto;
import de.rakuten.ecommerce.productcategory.dto.ProductCategoryDto;

/**
 * @author Mina
 *
 */
public class ProductDto extends AbstractDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull(message = "Product name must be provided")
	private String name;

	@NotNull(message = "Product category must be provided")
	private ProductCategoryDto productCategory;

	@NotNull(message = "Product price must be provided")
	private Double price;

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
	public ProductCategoryDto getProductCategory() {
		return productCategory;
	}

	/**
	 * @param productCategory
	 *            the productCategory to set
	 */
	public void setProductCategory(ProductCategoryDto productCategory) {
		this.productCategory = productCategory;
	}

}
