/**
 * 
 */
package de.rakuten.ecommerce.product.dto;

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

	private String name;

	private ProductCategoryDto productCategory;

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
