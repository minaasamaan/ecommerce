/**
 * 
 */
package de.rakuten.ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.rakuten.ecommerce.product.model.Product;

/**
 * @author Mina
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
