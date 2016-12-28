/**
 * 
 */
package de.rakuten.ecommerce.product.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.rakuten.ecommerce.base.context.ApplicationConfigurations;
import de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager;
import de.rakuten.ecommerce.product.client.FixerRestWSClient;
import de.rakuten.ecommerce.product.exception.MissingMandatoryProductCategory;
import de.rakuten.ecommerce.product.model.Product;
import de.rakuten.ecommerce.product.repository.ProductRepository;
import de.rakuten.ecommerce.productcategory.manager.ProductCategoryManagerImpl;

/**
 * @author Mina
 *
 */
@Service
public class ProductManagerImpl extends AbstractBusinessEntityManager<Product> implements ProductManager {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ApplicationConfigurations applicationConfigurations;

	@Autowired
	private FixerRestWSClient fixerRestWSClient;

	@Autowired
	private ProductCategoryManagerImpl productCategoryManager;

	@Override
	protected void doBeforePersist(Product entity, boolean isNew) {
		// Validate, then refresh product category using category id.
		if (entity.getProductCategory() == null) {
			throw new MissingMandatoryProductCategory();
		}
		entity.setProductCategory(getProductCategoryManager().read(entity.getProductCategory().getId()));

		if (isNew) { // Create operation
			String defaultCurrency = getApplicationConfigurations().getDefaultProductCurrency();
			if (entity.getCurrency() != null && !entity.getCurrency().equalsIgnoreCase(defaultCurrency)) {
				adjustProductPrice(entity);
			}
		}
	}

	/**
	 * @param entity
	 */
	private void adjustProductPrice(Product product) {
		Double rate = getFixerRestWSClient().getRateInDefaultCurrency(product.getCurrency());
		Double newPrice = product.getPrice() * rate;
		product.setPrice(newPrice);
		product.setCurrency(getApplicationConfigurations().getDefaultProductCurrency());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#
	 * getEntityDao()
	 */
	@Override
	public ProductRepository getEntityRepository() {
		return productRepository;
	}

	/**
	 * @return the applicationConfigurations
	 */
	public ApplicationConfigurations getApplicationConfigurations() {
		return applicationConfigurations;
	}

	/**
	 * @return the fixerRestWSClient
	 */
	public FixerRestWSClient getFixerRestWSClient() {
		return fixerRestWSClient;
	}

	/**
	 * @return the productCategoryManager
	 */
	public ProductCategoryManagerImpl getProductCategoryManager() {
		return productCategoryManager;
	}

	/**
	 * @param productCategoryManager
	 *            the productCategoryManager to set
	 */
	public void setProductCategoryManager(ProductCategoryManagerImpl productCategoryManager) {
		this.productCategoryManager = productCategoryManager;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#
	 * getEntityClass()
	 */
	@Override
	protected Class<Product> getEntityClass() {
		return Product.class;
	}

}
