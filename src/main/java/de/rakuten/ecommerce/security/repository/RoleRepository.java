/**
 * 
 */
package de.rakuten.ecommerce.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.rakuten.ecommerce.security.model.Role;

/**
 * @author Mina
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

}
