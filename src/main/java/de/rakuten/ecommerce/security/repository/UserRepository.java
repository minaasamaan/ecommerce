/**
 * 
 */
package de.rakuten.ecommerce.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.rakuten.ecommerce.security.model.User;

/**
 * @author Mina
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
