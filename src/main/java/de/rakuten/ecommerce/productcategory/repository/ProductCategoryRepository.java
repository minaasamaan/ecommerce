/**
 * 
 */
package de.rakuten.ecommerce.productcategory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.rakuten.ecommerce.productcategory.model.ProductCategory;

/**
 * @author Mina
 *
 */
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

}
