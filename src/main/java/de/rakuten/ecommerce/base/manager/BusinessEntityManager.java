/**
 * 
 */
package de.rakuten.ecommerce.base.manager;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import de.rakuten.ecommerce.base.model.Entity;
import de.rakuten.ecommerce.base.validation.CrudValidationGroup.Create;
import de.rakuten.ecommerce.base.validation.CrudValidationGroup.Update;

/**
 * @author Mina
 *
 */
public interface BusinessEntityManager<E extends Entity> {

	E create(@Validated(value = Create.class) E entity);

	E read(@NotNull Long id);

	List<E> readAll();

	E update(@Validated(value = Update.class) E entity);

	void delete(@NotNull Long id);
}
