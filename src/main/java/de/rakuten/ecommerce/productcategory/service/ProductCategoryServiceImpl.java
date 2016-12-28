/**
 * 
 */
package de.rakuten.ecommerce.productcategory.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rakuten.ecommerce.base.manager.BusinessEntityManager;
import de.rakuten.ecommerce.base.service.AbstractRestService;
import de.rakuten.ecommerce.productcategory.dto.ProductCategoryDto;
import de.rakuten.ecommerce.productcategory.manager.ProductCategoryManager;
import de.rakuten.ecommerce.productcategory.model.ProductCategory;
import io.swagger.annotations.Api;

/**
 * @author Mina
 *
 */
@RestController
@RequestMapping("/api/v1/category")
@Api(value = "Product Category Service")
public class ProductCategoryServiceImpl extends AbstractRestService<ProductCategoryDto, ProductCategory>
		implements ProductCategoryService {

	@Autowired
	private ProductCategoryManager productCategoryManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rakuten.ecommerce.base.service.AbstractRestService#getBusinessManager(
	 * )
	 */
	@Override
	public BusinessEntityManager<ProductCategory> getBusinessManager() {
		return productCategoryManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.service.AbstractRestService#getDtoClazz()
	 */
	@Override
	public Class<ProductCategoryDto> getDtoClazz() {
		return ProductCategoryDto.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rakuten.ecommerce.base.service.AbstractRestService#getEntityClazz()
	 */
	@Override
	public Class<ProductCategory> getEntityClazz() {
		return ProductCategory.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.service.AbstractRestService#getCrudURL()
	 */
	@Override
	protected String getCrudURL() {
		return "/api/v1/category/";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rakuten.ecommerce.base.service.AbstractRestService#getDtoListType()
	 */
	@Override
	protected Type getDtoListType() {
		return new TypeToken<List<ProductCategoryDto>>() {
		}.getType();
	}

}
