/**
 * 
 */
package de.rakuten.ecommerce.unittesting.productcategory.manager;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.BDDCatchException.thenThrown;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;

import de.rakuten.ecommerce.base.manager.exception.EntityNotFound;
import de.rakuten.ecommerce.builder.category.ProductCategoryBuilder;
import de.rakuten.ecommerce.product.repository.ProductRepository;
import de.rakuten.ecommerce.productcategory.manager.ProductCategoryManagerImpl;
import de.rakuten.ecommerce.productcategory.model.ProductCategory;
import de.rakuten.ecommerce.productcategory.repository.ProductCategoryRepository;

/**
 * @author Mina
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryManagerImplTest {

	private static final String NAME = "NAME";
	private static final String DESCRIPTION = "DESCRIPTION";

	private static final String PARENT_NAME = "PARENT_NAME";
	private static final String PARENT_DESCRIPTION = "PARENT_DESCRIPTION";

	private static final String HTTP_STATUS = "httpStatus";

	@InjectMocks
	private ProductCategoryManagerImpl testee;

	@Mock
	private ProductCategoryRepository productCategoryRepository;

	@Mock
	private ProductRepository productRepository;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#create(de.rakuten.ecommerce.base.model.AbstractEntity)}
	 * .
	 */
	@Test
	public final void testCreate_noParent_success() {
		ProductCategory category = new ProductCategoryBuilder().name(NAME).description(DESCRIPTION).build();
		ProductCategory createdCategory = new ProductCategoryBuilder().id(1l).name(NAME).description(DESCRIPTION)
				.build();
		given(productCategoryRepository.saveAndFlush(Mockito.any())).willReturn(createdCategory);
		category = when(testee).create(category);
		then(category.getId()).isEqualTo(1l);
		then(category.getName()).isEqualTo(NAME);
		then(category.getDescription()).isEqualTo(DESCRIPTION);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#create(de.rakuten.ecommerce.base.model.AbstractEntity)}
	 * .
	 */
	@Test
	public final void testCreate_withParent_success() {
		ProductCategory parentCategory = new ProductCategoryBuilder().id(2l).name(PARENT_NAME)
				.description(PARENT_DESCRIPTION).parentId(1l).build();
		ProductCategory newCategory = new ProductCategoryBuilder().name(NAME).description(DESCRIPTION).parentId(2l)
				.build();
		ProductCategory createdCategory = new ProductCategoryBuilder().id(3l).name(NAME).description(DESCRIPTION)
				.parent(parentCategory).build();

		given(productCategoryRepository.findOne(2l)).willReturn(parentCategory);
		given(productCategoryRepository.saveAndFlush(Mockito.any())).willReturn(createdCategory);

		newCategory = when(testee).create(newCategory);
		then(newCategory.getId()).isEqualTo(3l);
		then(newCategory.getName()).isEqualTo(NAME);
		then(newCategory.getDescription()).isEqualTo(DESCRIPTION);
		then(newCategory.getParent()).isEqualTo(parentCategory);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#read(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testRead_found_hasParent() {
		ProductCategory parentCategory = new ProductCategoryBuilder().id(2l).name(PARENT_NAME)
				.description(PARENT_DESCRIPTION).parentId(1l).build();
		ProductCategory returnedCategory = new ProductCategoryBuilder().id(3l).name(NAME).description(DESCRIPTION)
				.parent(parentCategory).build();

		given(productCategoryRepository.findOne(3l)).willReturn(returnedCategory);

		returnedCategory = when(testee).read(3l);
		then(returnedCategory.getId()).isEqualTo(3l);
		then(returnedCategory.getName()).isEqualTo(NAME);
		then(returnedCategory.getDescription()).isEqualTo(DESCRIPTION);
		then(returnedCategory.getParent()).isEqualTo(parentCategory);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#read(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testRead_found_withoutParent() {

		ProductCategory returnedCategory = new ProductCategoryBuilder().id(3l).name(NAME).description(DESCRIPTION)
				.build();

		given(productCategoryRepository.findOne(3l)).willReturn(returnedCategory);

		returnedCategory = when(testee).read(3l);
		then(returnedCategory.getId()).isEqualTo(3l);
		then(returnedCategory.getName()).isEqualTo(NAME);
		then(returnedCategory.getDescription()).isEqualTo(DESCRIPTION);
		then(returnedCategory.getParent()).isNull();
	}

	@Test
	public final void testRead_Notfound() {
		given(productCategoryRepository.findOne(1l)).willReturn(null);
		when(testee).read(1l);
		thenThrown(EntityNotFound.class);
		then((EntityNotFound) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS, HttpStatus.NOT_FOUND);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#readAll()}
	 * .
	 */
	@Test
	public final void testReadAll_withResults() {
		ProductCategory parentCategory = new ProductCategoryBuilder().id(2l).name(PARENT_NAME)
				.description(PARENT_DESCRIPTION).parentId(1l).build();
		ProductCategory returnedCategory = new ProductCategoryBuilder().id(3l).name(NAME).description(DESCRIPTION)
				.parent(parentCategory).build();

		given(productCategoryRepository.findAll()).willReturn(Collections.singletonList(returnedCategory));

		List<ProductCategory> categoryList = when(testee).readAll();
		then(categoryList.get(0).getId()).isEqualTo(3l);
		then(categoryList.get(0).getName()).isEqualTo(NAME);
		then(categoryList.get(0).getDescription()).isEqualTo(DESCRIPTION);
		then(categoryList.get(0).getParent()).isEqualTo(parentCategory);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#readAll()}
	 * .
	 */
	@Test
	public final void testReadAll_noResults() {
		given(productCategoryRepository.findAll()).willReturn(Collections.emptyList());
		when(testee).readAll();
		thenThrown(EntityNotFound.class);
		then((EntityNotFound) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS, HttpStatus.NOT_FOUND);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#update(de.rakuten.ecommerce.base.model.AbstractEntity)}
	 * .
	 */
	@Test
	public final void testUpdateEntity() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#delete(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

}
