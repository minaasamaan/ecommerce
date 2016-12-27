/**
 * 
 */
package de.rakuten.ecommerce.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.rakuten.ecommerce.product.model.Product;

/**
 * @author Mina
 *
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
	@Query("select count(id) from Product product where product.productCategory.id=?1")
	Long getAssignedProductsCountToCategory(Long categoryId);
}
