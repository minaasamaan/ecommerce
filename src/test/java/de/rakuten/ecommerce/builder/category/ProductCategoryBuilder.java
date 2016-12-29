/**
 * 
 */
package de.rakuten.ecommerce.builder.category;

import de.rakuten.ecommerce.productcategory.model.ProductCategory;

/**
 * @author Mina
 *
 */
public class ProductCategoryBuilder {
	private ProductCategory entity = new ProductCategory();

	public ProductCategoryBuilder id(Long id) {
		entity.setId(id);
		return this;
	}

	public ProductCategoryBuilder name(String name) {
		entity.setName(name);
		return this;
	}

	public ProductCategoryBuilder description(String description) {
		entity.setDescription(description);
		return this;
	}

	public ProductCategoryBuilder parentId(Long parentId) {
		entity.setParentId(parentId);
		return this;
	}

	public ProductCategoryBuilder parent(ProductCategory parent) {
		entity.setParent(parent);
		return this;
	}

	public ProductCategory build() {
		return entity;
	}
}
