/**
 * 
 */
package de.rakuten.ecommerce.base.manager;

import de.rakuten.ecommerce.base.model.Entity;

/**
 * @author Mina
 *
 */
public interface BusinessEntityManager<E extends Entity> {

	E create(E entity);

	E read(Long id);

	E update(E entity);

	void delete(E entity);
}
