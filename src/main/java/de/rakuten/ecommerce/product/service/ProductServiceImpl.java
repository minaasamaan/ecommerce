/**
 * 
 */
package de.rakuten.ecommerce.product.service;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rakuten.ecommerce.base.manager.BusinessEntityManager;
import de.rakuten.ecommerce.base.service.AbstractRestService;
import de.rakuten.ecommerce.product.dto.ProductDto;
import de.rakuten.ecommerce.product.manager.ProductManager;
import de.rakuten.ecommerce.product.model.Product;
import io.swagger.annotations.Api;

/**
 * @author Mina
 *
 */
@RestController
@RequestMapping("/api/v1/product")
@Api(value = "ProductCategory Service")
public class ProductServiceImpl extends AbstractRestService<ProductDto, Product> implements ProductService {

	@Autowired
	private ProductManager productManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rakuten.ecommerce.base.service.AbstractRestService#getBusinessManager(
	 * )
	 */
	@Override
	public BusinessEntityManager<Product> getBusinessManager() {
		return productManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.service.AbstractRestService#getDtoClazz()
	 */
	@Override
	public Class<ProductDto> getDtoClazz() {
		return ProductDto.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rakuten.ecommerce.base.service.AbstractRestService#getEntityClazz()
	 */
	@Override
	public Class<Product> getEntityClazz() {
		return Product.class;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.service.AbstractRestService#getCrudURL()
	 */
	@Override
	protected String getCrudURL() {
		return "/api/v1/product/";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rakuten.ecommerce.base.service.AbstractRestService#getDtoListType()
	 */
	@Override
	protected Type getDtoListType() {
		return new TypeToken<List<ProductDto>>() {
		}.getType();
	}
}
