/**
 * 
 */
package de.rakuten.ecommerce.builder.product;

import de.rakuten.ecommerce.product.dto.ProductDto;
import de.rakuten.ecommerce.productcategory.dto.ProductCategoryDto;

/**
 * @author Mina
 *
 */
public class ProductDtoBuilder {
	private ProductDto dto = new ProductDto();

	public ProductDtoBuilder id(Long id) {
		dto.setId(id);
		return this;
	}

	public ProductDtoBuilder name(String name) {
		dto.setName(name);
		return this;
	}

	public ProductDtoBuilder description(String description) {
		dto.setDescription(description);
		return this;
	}

	public ProductDtoBuilder parentId(ProductCategoryDto productCategoryDto) {
		dto.setProductCategory(productCategoryDto);
		return this;
	}

	public ProductDto build() {
		return dto;
	}
}
