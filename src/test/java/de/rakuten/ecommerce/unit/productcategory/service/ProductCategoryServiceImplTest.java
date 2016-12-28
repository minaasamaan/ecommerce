/**
 * 
 */
package de.rakuten.ecommerce.unit.productcategory.service;

import static com.googlecode.catchexception.CatchException.caughtException;
import static com.googlecode.catchexception.apis.BDDCatchException.thenThrown;
import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

import java.net.URISyntaxException;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import de.rakuten.ecommerce.base.manager.exception.EntityNotFound;
import de.rakuten.ecommerce.base.tree.exception.CannotDeleteNonLeafNodes;
import de.rakuten.ecommerce.builder.ProductCategoryBuilder;
import de.rakuten.ecommerce.productcategory.dto.ProductCategoryDto;
import de.rakuten.ecommerce.productcategory.manager.ProductCategoryManager;
import de.rakuten.ecommerce.productcategory.manager.exception.CannotDeleteCategoryAssignedToProducts;
import de.rakuten.ecommerce.productcategory.model.ProductCategory;
import de.rakuten.ecommerce.productcategory.service.ProductCategoryServiceImpl;

/**
 * @author Mina
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryServiceImplTest {

	private static final String NAME = "NAME";
	private static final String DESCRIPTION = "DESCRIPTION";
	private static final String HTTP_STATUS = "httpStatus";

	@InjectMocks
	private ProductCategoryServiceImpl testee;

	@Mock
	private ProductCategoryManager productCategoryManager;

	@Spy
	private ModelMapper mapper;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#create(de.rakuten.ecommerce.base.dto.AbstractDto)}
	 * .
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public final void testCreate_noParent_success() throws URISyntaxException {
		ProductCategory category = new ProductCategoryBuilder().id(1l).name(NAME).description(DESCRIPTION).build();
		given(productCategoryManager.create(Mockito.any())).willReturn(category);

		ResponseEntity<ProductCategoryDto> responseEntity = when(testee).create(new ProductCategoryDto());
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		then(responseEntity.getHeaders().get(HttpHeaders.LOCATION))
				.isEqualTo(Collections.singletonList("/api/v1/category/1"));
		then(responseEntity.hasBody()).isTrue();
		then(responseEntity.getBody().getId()).isEqualTo(1l);
		then(responseEntity.getBody().getName()).isEqualTo(NAME);
		then(responseEntity.getBody().getDescription()).isEqualTo(DESCRIPTION);
		then(responseEntity.getBody().getParentId()).isNull();
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#create(de.rakuten.ecommerce.base.dto.AbstractDto)}
	 * .
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public final void testCreate_withParent_success() throws URISyntaxException {
		ProductCategory category = new ProductCategoryBuilder().id(1l).name(NAME).description(DESCRIPTION).parentId(2l)
				.build();
		given(productCategoryManager.create(Mockito.any())).willReturn(category);

		ResponseEntity<ProductCategoryDto> responseEntity = when(testee).create(new ProductCategoryDto());
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		then(responseEntity.getHeaders().get(HttpHeaders.LOCATION))
				.isEqualTo(Collections.singletonList("/api/v1/category/1"));
		then(responseEntity.hasBody()).isTrue();
		then(responseEntity.getBody().getId()).isEqualTo(1l);
		then(responseEntity.getBody().getParentId()).isEqualTo(2l);

		then(responseEntity.getBody().getName()).isEqualTo(NAME);
		then(responseEntity.getBody().getDescription()).isEqualTo(DESCRIPTION);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#create(de.rakuten.ecommerce.base.dto.AbstractDto)}
	 * .
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public final void testCreate_withInvalidParent_fail() throws URISyntaxException {
		given(productCategoryManager.create(Mockito.any())).willThrow(new EntityNotFound(2l, ProductCategory.class));

		when(testee).create(new ProductCategoryDto());
		thenThrown(EntityNotFound.class);
		then((EntityNotFound) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS, HttpStatus.NOT_FOUND);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#read(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testRead_found() {
		ProductCategory category = new ProductCategoryBuilder().id(1l).name(NAME).description(DESCRIPTION).parentId(2l)
				.build();
		given(productCategoryManager.read(1l)).willReturn(category);

		ResponseEntity<ProductCategoryDto> responseEntity = when(testee).read(1l);
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		then(responseEntity.hasBody()).isTrue();
		then(responseEntity.getBody().getId()).isEqualTo(1l);
		then(responseEntity.getBody().getName()).isEqualTo(NAME);
		then(responseEntity.getBody().getDescription()).isEqualTo(DESCRIPTION);
		then(responseEntity.getBody().getParentId()).isEqualTo(2l);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#read(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testRead_Notfound() {
		given(productCategoryManager.read(1l)).willThrow(new EntityNotFound(1l, ProductCategory.class));

		when(testee).read(1l);
		thenThrown(EntityNotFound.class);
		then((EntityNotFound) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS, HttpStatus.NOT_FOUND);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#read()}.
	 * 
	 * @throws Exception
	 */
	@Test
	public final void testReadAll_returnData() throws Exception {
		ProductCategory category = new ProductCategoryBuilder().id(1l).name(NAME).description(DESCRIPTION).parentId(2l)
				.build();
		given(productCategoryManager.readAll()).willReturn(Collections.singletonList(category));

		ResponseEntity<List<ProductCategoryDto>> responseEntity = when(testee).read();
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		then(responseEntity.hasBody()).isTrue();
		then(responseEntity.getBody().get(0).getId()).isEqualTo(1l);
		then(responseEntity.getBody().get(0).getName()).isEqualTo(NAME);
		then(responseEntity.getBody().get(0).getDescription()).isEqualTo(DESCRIPTION);
		then(responseEntity.getBody().get(0).getParentId()).isEqualTo(2l);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#read()}.
	 */
	@Test
	public final void testReadAll_returnNoData() {
		given(productCategoryManager.readAll()).willThrow(new EntityNotFound(ProductCategory.class));

		when(testee).read();
		thenThrown(EntityNotFound.class);
		then((EntityNotFound) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS, HttpStatus.NOT_FOUND);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#update(de.rakuten.ecommerce.base.dto.AbstractDto)}
	 * .
	 */
	@Test
	public final void testUpdate_success() {
		ProductCategory category = new ProductCategoryBuilder().id(1l).name(NAME).description(DESCRIPTION).parentId(2l)
				.build();
		given(productCategoryManager.update(Mockito.any())).willReturn(category);

		ResponseEntity<ProductCategoryDto> responseEntity = when(testee).update(new ProductCategoryDto());
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		then(responseEntity.hasBody()).isTrue();
		then(responseEntity.getBody().getId()).isEqualTo(1l);
		then(responseEntity.getBody().getParentId()).isEqualTo(2l);

		then(responseEntity.getBody().getName()).isEqualTo(NAME);
		then(responseEntity.getBody().getDescription()).isEqualTo(DESCRIPTION);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#update(de.rakuten.ecommerce.base.dto.AbstractDto)}
	 * .
	 */
	@Test
	public final void testUpdate_entityDoesntExist() {
		given(productCategoryManager.update(Mockito.any())).willThrow(new EntityNotFound(2l, ProductCategory.class));

		when(testee).update(new ProductCategoryDto());
		thenThrown(EntityNotFound.class);
		then((EntityNotFound) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS, HttpStatus.NOT_FOUND);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#delete(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testDelete_success() {
		ResponseEntity<?> responseEntity = when(testee).delete(1l);
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
		then(responseEntity.hasBody()).isFalse();
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#delete(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testDelete_fail_entityDoesntExist() {
		doThrow(new EntityNotFound(2l, ProductCategory.class)).when(productCategoryManager).delete(Mockito.any());
		when(testee).delete(2l);
		thenThrown(EntityNotFound.class);
		then((EntityNotFound) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS, HttpStatus.NOT_FOUND);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#delete(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testDelete_fail_nonLeafNode() {
		doThrow(new CannotDeleteNonLeafNodes(2l)).when(productCategoryManager).delete(Mockito.any());
		when(testee).delete(2l);
		thenThrown(CannotDeleteNonLeafNodes.class);
		then((CannotDeleteNonLeafNodes) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS,
				HttpStatus.CONFLICT);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#delete(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testDelete_fail_categoryAssignedToProducts() {
		doThrow(new CannotDeleteCategoryAssignedToProducts(2l)).when(productCategoryManager).delete(Mockito.any());
		when(testee).delete(2l);
		thenThrown(CannotDeleteCategoryAssignedToProducts.class);
		then((CannotDeleteCategoryAssignedToProducts) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS,
				HttpStatus.CONFLICT);
	}
}
