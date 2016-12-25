/**
 * 
 */
package de.rakuten.ecommerce.base.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import de.rakuten.ecommerce.base.dto.AbstractDto;
import de.rakuten.ecommerce.base.manager.BusinessEntityManager;
import de.rakuten.ecommerce.base.model.AbstractEntity;

/**
 * @author Mina
 *
 */
public abstract class AbstractRestService<DTO extends AbstractDto, Entity extends AbstractEntity>
		implements CRUDRestService<DTO> {

	@Autowired
	private ModelMapper mapper;

	public abstract BusinessEntityManager<Entity> getBusinessManager();

	public abstract Class<DTO> getDtoClazz();

	public abstract Class<Entity> getEntityClazz();

	protected abstract String getCrudURL();

	/**
	 * @return the mapper
	 */
	public ModelMapper getMapper() {
		return mapper;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.service.CRUDRestService#create(de.rakuten.
	 * ecommerce.base.dto.Dto)
	 */
	@Override
	public ResponseEntity<DTO> create(@RequestBody DTO dto) {
		Entity entity = getBusinessManager().create(transform(dto));
		try {
			return ResponseEntity.created(new URI(getCrudURL() + entity.getId())).body(transform(entity));
		} catch (URISyntaxException e) {
			// TODO handle properly
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rakuten.ecommerce.base.service.CRUDRestService#read(java.lang.String)
	 */
	@Override
	public ResponseEntity<DTO> read(@PathVariable Long id) {
		return ResponseEntity.ok().body(transform(getBusinessManager().read(id)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.service.CRUDRestService#update(de.rakuten.
	 * ecommerce.base.dto.Dto)
	 */
	@Override
	public ResponseEntity<DTO> update(@RequestBody DTO dto) {
		return ResponseEntity.ok().body(transform(getBusinessManager().update(transform(dto))));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.service.CRUDRestService#delete(de.rakuten.
	 * ecommerce.base.dto.Dto)
	 */
	@Override
	public ResponseEntity<?> delete(@RequestBody DTO dto) {
		getBusinessManager().delete(transform(dto));
		return ResponseEntity.noContent().build();
	}

	protected Entity transform(DTO dto) {
		return getMapper().map(dto, getEntityClazz());
	}

	protected DTO transform(Entity entity) {
		return getMapper().map(entity, getDtoClazz());
	}
}
