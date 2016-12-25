package de.rakuten.ecommerce.base.repository;

import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.rakuten.ecommerce.base.model.AbstractEntity;

public abstract class AbstractBaseDao<T extends AbstractEntity> implements BaseDao<T> {

	private final Logger log = LoggerFactory.getLogger(AbstractBaseDao.class);

	@PersistenceContext
	private EntityManager entityManager;

	private Class<T> clazz;

	/**
	 * 
	 */
	public AbstractBaseDao(Class<T> entityClazz) {
		clazz = entityClazz;
	}

	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		log.debug("Finding all items for type = " + clazz);
		final Query query = getEntityManager().createQuery("SELECT e FROM " + clazz.getName() + " AS e");
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return Collections.emptyList();
		}
	}

	@Override
	public T findById(final Long id) {
		log.debug("Finding " + clazz + " with id = " + id);
		if (id == null || id == 0L) {
			return null;
		}
		return getEntityManager().find(clazz, id);
	}

	@Override
	public T create(final T item) {
		log.debug("Creating item = " + String.valueOf(item));
		getEntityManager().persist(item);
		return item;
	}

	@Override
	public T update(final T item) {
		log.debug("Updating item = " + String.valueOf(item));
		if (item == null) {
			return null;
		}
		return getEntityManager().merge(item);
	}

	@Override
	public void refresh(final T item) {
		log.debug("Refreshing item = " + String.valueOf(item));
		if (item == null) {
			return;
		}
		getEntityManager().refresh(item);
	}

	@Override
	public void delete(final T item) {
		if (item == null || item.getId() == null) {
			log.warn("Tried to delete a not persisted object: " + item + " of type " + clazz);
			return;
		}
		deleteById(item.getId());
	}

	@Override
	public void deleteById(final Long id) {
		String message = "Deleting " + clazz + " with id = " + id;
		log.debug(message);
		T item = getEntityManager().find(clazz, id);
		if (null == item) {
			throw new EntityNotFoundException(message + " has failed, No entity found with the given Id");
		}
		getEntityManager().remove(item);
	}
}
