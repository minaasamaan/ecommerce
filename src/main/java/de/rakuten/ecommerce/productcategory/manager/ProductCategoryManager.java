/**
 * 
 */
package de.rakuten.ecommerce.productcategory.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rakuten.ecommerce.base.tree.manager.AbstractTreeNodeManager;
import de.rakuten.ecommerce.productcategory.model.ProductCategory;
import de.rakuten.ecommerce.productcategory.repository.ProductCategoryRepository;

/**
 * @author Mina
 *
 */
@Service
public class ProductCategoryManager extends AbstractTreeNodeManager<ProductCategory> {

	@Autowired
	private ProductCategoryRepository productCategoryRepository;

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
		// TODO check here if category has any products already, and hence it
		// can't
		// be deleted, throw CannotDeleteCategoryAssignedToProducts
		super.doBeforeDelete(productCategory);
	}
}
