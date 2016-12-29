/**
 * 
 */
package de.rakuten.ecommerce.builder.product;

import de.rakuten.ecommerce.product.model.Product;
import de.rakuten.ecommerce.productcategory.model.ProductCategory;

/**
 * @author Mina
 *
 */
public class ProductBuilder {
	private Product entity = new Product();

	public ProductBuilder id(Long id) {
		entity.setId(id);
		return this;
	}

	public ProductBuilder name(String name) {
		entity.setName(name);
		return this;
	}

	public ProductBuilder description(String description) {
		entity.setDescription(description);
		return this;
	}

	public ProductBuilder category(ProductCategory productCategory) {
		entity.setProductCategory(productCategory);
		return this;
	}

	public Product build() {
		return entity;
	}
}
