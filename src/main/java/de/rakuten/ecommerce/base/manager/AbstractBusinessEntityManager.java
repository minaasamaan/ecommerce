/**
 * 
 */
package de.rakuten.ecommerce.base.manager;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import de.rakuten.ecommerce.base.manager.exception.EntityNotFound;
import de.rakuten.ecommerce.base.model.AbstractEntity;

/**
 * Parent for all business managers in the application.
 * 
 * @author Mina
 *
 */
@Transactional
public abstract class AbstractBusinessEntityManager<Entity extends AbstractEntity>
		implements BusinessEntityManager<Entity> {

	public abstract JpaRepository<Entity, Long> getEntityRepository();

	protected void doBeforePersist(Entity entity, boolean isNew) {
		// Do nothing by default
	}

	protected void doBeforeDelete(Entity entity) {
		// Do nothing by default
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.BusinessEntityManager#create(de.
	 * rakuten. ecommerce.base.model.Entity)
	 */
	@Override
	public Entity create(Entity entity) {
		doBeforePersist(entity, true);
		return getEntityRepository().saveAndFlush(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rakuten.ecommerce.base.manager.BusinessEntityManager#read(de.rakuten.
	 * ecommerce.base.model.Entity)
	 */
	@Transactional(readOnly = true)
	@Override
	public Entity read(Long id) {

		Entity entity = getEntityRepository().findOne(id);
		if (entity == null) {
			throw new EntityNotFound(id, getEntityClass());
		}
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.BusinessEntityManager#read()
	 */
	@Override
	public List<Entity> readAll() {
		return getEntityRepository().findAll();
	}

	/**
	 * @return
	 */
	protected abstract Class<Entity> getEntityClass();

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.BusinessEntityManager#update(de.
	 * rakuten. ecommerce.base.model.Entity)
	 */
	@Override
	public Entity update(Entity entity) {
		doBeforePersist(entity, false);
		return getEntityRepository().saveAndFlush(entity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.BusinessEntityManager#delete(de.
	 * rakuten. ecommerce.base.model.Entity)
	 */
	@Override
	public void delete(Long id) {
		// Refresh entity
		Entity entity = read(id);
		doBeforeDelete(entity);
		getEntityRepository().delete(entity);
	}

}
