/**
 * 
 */
package de.rakuten.ecommerce.security.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager;
import de.rakuten.ecommerce.security.model.Role;
import de.rakuten.ecommerce.security.repository.RoleRepository;

/**
 * @author Mina
 *
 */
@Service
public class RoleManager extends AbstractBusinessEntityManager<Role> {

	@Autowired
	private RoleRepository roleRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#
	 * getEntityRepository()
	 */
	@Override
	public JpaRepository<Role, Long> getEntityRepository() {
		return roleRepository;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#
	 * getEntityClass()
	 */
	@Override
	protected Class<Role> getEntityClass() {
		return Role.class;
	}

}
