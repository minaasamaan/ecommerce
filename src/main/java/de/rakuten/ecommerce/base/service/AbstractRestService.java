/**
 * 
 */
package de.rakuten.ecommerce.base.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import de.rakuten.ecommerce.base.dto.AbstractDto;
import de.rakuten.ecommerce.base.manager.BusinessEntityManager;
import de.rakuten.ecommerce.base.model.AbstractEntity;
import de.rakuten.ecommerce.base.validation.CrudValidationGroup.Create;
import de.rakuten.ecommerce.base.validation.CrudValidationGroup.Update;

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
	public ResponseEntity<DTO> create(@Validated(value = Create.class) @RequestBody DTO dto) throws URISyntaxException {
		Entity entity = getBusinessManager().create(transform(dto));
		return ResponseEntity.created(new URI(getCrudURL() + entity.getId())).body(transform(entity));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * de.rakuten.ecommerce.base.service.CRUDRestService#read(java.lang.String)
	 */
	@Override
	public ResponseEntity<DTO> read(@NotNull @PathVariable Long id) {
		return ResponseEntity.ok().body(transform(getBusinessManager().read(id)));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.service.CRUDRestService#read()
	 */
	@Override
	public ResponseEntity<List<DTO>> read() {
		return ResponseEntity.ok().body(transform(getBusinessManager().readAll()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.service.CRUDRestService#update(de.rakuten.
	 * ecommerce.base.dto.Dto)
	 */
	@Override
	public ResponseEntity<DTO> update(@Validated(value = Update.class) @RequestBody DTO dto) {
		return ResponseEntity.ok().body(transform(getBusinessManager().update(transform(dto))));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.service.CRUDRestService#delete(de.rakuten.
	 * ecommerce.base.dto.Dto)
	 */
	@Override
	public ResponseEntity<?> delete(@NotNull @PathVariable Long id) {
		getBusinessManager().delete(id);
		return ResponseEntity.noContent().build();
	}

	protected Entity transform(DTO dto) {
		return getMapper().map(dto, getEntityClazz());
	}

	protected DTO transform(Entity entity) {
		return getMapper().map(entity, getDtoClazz());
	}

	@SuppressWarnings("unchecked")
	protected List<DTO> transform(List<Entity> entityList) {
		List<DTO> dtoList = Collections.emptyList();
		return getMapper().map(entityList, dtoList.getClass());
	}
}
