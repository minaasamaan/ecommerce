/**
 * 
 */
package de.rakuten.ecommerce.base.manager;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import de.rakuten.ecommerce.base.manager.exception.EntityNotFound;
import de.rakuten.ecommerce.base.model.AbstractEntity;
import de.rakuten.ecommerce.base.validation.CrudValidationGroup.Create;
import de.rakuten.ecommerce.base.validation.CrudValidationGroup.Update;

/**
 * Parent for all business managers in the application.
 * 
 * @author Mina
 *
 */
@Transactional
@Validated
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
	public Entity create(@Validated(value = Create.class) Entity entity) {
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
	public Entity read(@NotNull Long id) {

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
	@Transactional(readOnly = true)
	@Override
	public List<Entity> readAll() {
		return getEntityRepository().findAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.BusinessEntityManager#update(de.
	 * rakuten. ecommerce.base.model.Entity)
	 */
	@Override
	public Entity update(@Validated(value = Update.class) Entity entity) {
		if (!getEntityRepository().exists(entity.getId())) {
			throw new EntityNotFound(entity.getId(), getEntityClass());
		}
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
	public void delete(@NotNull Long id) {
		// Refresh entity
		Entity entity = read(id);
		doBeforeDelete(entity);
		getEntityRepository().delete(entity);
	}

	/**
	 * @return
	 */
	protected abstract Class<Entity> getEntityClass();

}
