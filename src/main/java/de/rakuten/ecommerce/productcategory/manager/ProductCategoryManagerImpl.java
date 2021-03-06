/**
 * 
 */
package de.rakuten.ecommerce.productcategory.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import de.rakuten.ecommerce.base.tree.manager.AbstractTreeNodeManager;
import de.rakuten.ecommerce.product.repository.ProductRepository;
import de.rakuten.ecommerce.productcategory.manager.exception.CannotDeleteCategoryAssignedToProducts;
import de.rakuten.ecommerce.productcategory.model.ProductCategory;
import de.rakuten.ecommerce.productcategory.repository.ProductCategoryRepository;

/**
 * @author Mina
 *
 */
@Service
@Validated
public class ProductCategoryManagerImpl extends AbstractTreeNodeManager<ProductCategory>
		implements ProductCategoryManager {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

	@Autowired
	private ProductRepository productRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#
	 * getEntityDao()
	 */
	@Override
	public ProductCategoryRepository getEntityRepository() {
		return productCategoryRepository;
	}

	@Override
	protected void doBeforeDelete(ProductCategory productCategory) {
		super.doBeforeDelete(productCategory);
		// check here if category has any products already, and hence it
		// can't
		// be deleted, throw CannotDeleteCategoryAssignedToProducts
		if (getProductRepository().getAssignedProductsCountToCategory(productCategory.getId()) > 0) {
			throw new CannotDeleteCategoryAssignedToProducts(productCategory.getId());
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#
	 * getEntityClass()
	 */
	@Override
	protected Class<ProductCategory> getEntityClass() {
		return ProductCategory.class;
	}

	/**
	 * @return the productRepository
	 */
	public ProductRepository getProductRepository() {
		return productRepository;
	}

	/**
	 * @param productRepository
	 *            the productRepository to set
	 */
	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
}
