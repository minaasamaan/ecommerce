/**
 * 
 */
package de.rakuten.ecommerce.product.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.rakuten.ecommerce.base.context.ApplicationConfigurations;
import de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager;
import de.rakuten.ecommerce.product.client.FixerRestWSClient;
import de.rakuten.ecommerce.product.model.Product;
import de.rakuten.ecommerce.product.repository.ProductRepository;

/**
 * @author Mina
 *
 */
@Component
// @Transactional
public class ProductManager extends AbstractBusinessEntityManager<Product> {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ApplicationConfigurations applicationConfigurations;

	@Autowired
	private FixerRestWSClient fixerRestWSClient;

	@Override
	protected void doBeforePersist(Product entity, boolean isNew) {
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
	 * @see
	 * de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#getEntityDao()
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

}
