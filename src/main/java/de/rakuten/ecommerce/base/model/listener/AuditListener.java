/**
 * 
 */
package de.rakuten.ecommerce.base.model.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import de.rakuten.ecommerce.base.model.AbstractEntity;
import de.rakuten.ecommerce.base.security.util.SecurityUtil;

/**
 * @author Mina
 *
 */
public class AuditListener {
	@PrePersist
	private void beforePersist(AbstractEntity entity) {
		Date now = new Date();
		entity.setCreationDate(now);
		entity.setLastModifiedDate(now);
		entity.setLastModifiedBy(SecurityUtil.getCurrentAthenticatedUser());
	}

	@PreUpdate
	private void beforeUpdate(AbstractEntity entity) {
		entity.setLastModifiedDate(new Date());
		entity.setLastModifiedBy(SecurityUtil.getCurrentAthenticatedUser());
	}
}
