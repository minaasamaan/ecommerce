/**
 * 
 */
package de.rakuten.ecommerce.base.model.listener;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import de.rakuten.ecommerce.base.model.AbstractEntity;
import de.rakuten.ecommerce.security.util.SecurityUtil;

/**
 * @author Mina
 *
 */
public class AuditListener {
	private static final String SYSTEM = "SYSTEM";

	@PrePersist
	private void beforePersist(AbstractEntity entity) {
		Date now = new Date();
		entity.setCreationDate(now);
		entity.setLastModifiedDate(now);
		if (entity.getLastModifiedBy() == null || !entity.getLastModifiedBy().equals(SYSTEM)) {
			entity.setLastModifiedBy(SecurityUtil.getCurrentAthenticatedUser());
		}
	}

	@PreUpdate
	private void beforeUpdate(AbstractEntity entity) {
		entity.setLastModifiedDate(new Date());
		if (entity.getLastModifiedBy() == null || !entity.getLastModifiedBy().equals(SYSTEM)) {
			entity.setLastModifiedBy(SecurityUtil.getCurrentAthenticatedUser());
		}
	}
}
