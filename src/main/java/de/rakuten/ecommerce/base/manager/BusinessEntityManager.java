/**
 * 
 */
package de.rakuten.ecommerce.base.manager;

import java.util.List;

import de.rakuten.ecommerce.base.model.Entity;

/**
 * @author Mina
 *
 */
public interface BusinessEntityManager<E extends Entity> {

	E create(E entity);

	E read(Long id);

	List<E> readAll();

	E update(E entity);

	void delete(Long id);
}
