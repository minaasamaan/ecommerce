/**
 * 
 */
package de.rakuten.ecommerce.builder;

import de.rakuten.ecommerce.productcategory.dto.ProductCategoryDto;

/**
 * @author Mina
 *
 */
public class ProductCategoryDtoBuilder {
	private ProductCategoryDto dto = new ProductCategoryDto();

	public ProductCategoryDtoBuilder id(Long id) {
		dto.setId(id);
		return this;
	}

	public ProductCategoryDtoBuilder name(String name) {
		dto.setName(name);
		return this;
	}

	public ProductCategoryDtoBuilder description(String description) {
		dto.setDescription(description);
		return this;
	}

	public ProductCategoryDtoBuilder parentId(Long parentId) {
		dto.setParentId(parentId);
		return this;
	}

	public ProductCategoryDto build() {
		return dto;
	}
}
