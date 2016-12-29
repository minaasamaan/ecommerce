/**
 * 
 */
package de.rakuten.ecommerce.unittesting.product.service;

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
import de.rakuten.ecommerce.builder.category.ProductCategoryBuilder;
import de.rakuten.ecommerce.builder.product.ProductBuilder;
import de.rakuten.ecommerce.product.dto.ProductDto;
import de.rakuten.ecommerce.product.exception.MissingMandatoryProductCategory;
import de.rakuten.ecommerce.product.manager.ProductManager;
import de.rakuten.ecommerce.product.model.Product;
import de.rakuten.ecommerce.product.service.ProductServiceImpl;
import de.rakuten.ecommerce.productcategory.model.ProductCategory;

/**
 * @author Mina
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceImplTest {

	private static final String NAME = "NAME";
	private static final String DESCRIPTION = "DESCRIPTION";

	private static final String CATEGORY_NAME = "CATEGORY_NAME";
	private static final String CATEGORY_DESCRIPTION = "CATEGORY_DESCRIPTION";

	private static final String HTTP_STATUS = "httpStatus";

	@InjectMocks
	private ProductServiceImpl testee;

	@Mock
	private ProductManager productManager;

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
	public final void testCreate_noCategory_fail() throws URISyntaxException {
		given(productManager.create(Mockito.any())).willThrow(new MissingMandatoryProductCategory());

		when(testee).create(new ProductDto());
		thenThrown(MissingMandatoryProductCategory.class);
		then((MissingMandatoryProductCategory) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS,
				HttpStatus.PRECONDITION_REQUIRED);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#create(de.rakuten.ecommerce.base.dto.AbstractDto)}
	 * .
	 * 
	 * @throws URISyntaxException
	 */
	@Test
	public final void testCreate_withCategory_success() throws URISyntaxException {
		ProductCategory category = new ProductCategoryBuilder().id(2l).name(CATEGORY_NAME)
				.description(CATEGORY_DESCRIPTION).parentId(3l).build();
		Product product = new ProductBuilder().id(1l).name(NAME).description(DESCRIPTION).category(category).build();
		given(productManager.create(Mockito.any())).willReturn(product);

		ResponseEntity<ProductDto> responseEntity = when(testee).create(new ProductDto());
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		then(responseEntity.getHeaders().get(HttpHeaders.LOCATION))
				.isEqualTo(Collections.singletonList("/api/v1/product/1"));
		then(responseEntity.hasBody()).isTrue();
		then(responseEntity.getBody().getId()).isEqualTo(1l);
		then(responseEntity.getBody().getProductCategory().getId()).isEqualTo(2l);
		then(responseEntity.getBody().getProductCategory().getParentId()).isEqualTo(3l);
		then(responseEntity.getBody().getProductCategory().getName()).isEqualTo(CATEGORY_NAME);
		then(responseEntity.getBody().getProductCategory().getDescription()).isEqualTo(CATEGORY_DESCRIPTION);
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
	public final void testCreate_withInvalidCategory_fail() throws URISyntaxException {
		given(productManager.create(Mockito.any())).willThrow(new EntityNotFound(2l, ProductCategory.class));
		when(testee).create(new ProductDto());
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
		ProductCategory category = new ProductCategoryBuilder().id(2l).name(CATEGORY_NAME)
				.description(CATEGORY_DESCRIPTION).parentId(3l).build();
		Product product = new ProductBuilder().id(1l).name(NAME).description(DESCRIPTION).category(category).build();
		given(productManager.read(1l)).willReturn(product);

		ResponseEntity<ProductDto> responseEntity = when(testee).read(1l);
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		then(responseEntity.hasBody()).isTrue();
		then(responseEntity.getBody().getId()).isEqualTo(1l);
		then(responseEntity.getBody().getProductCategory().getId()).isEqualTo(2l);
		then(responseEntity.getBody().getProductCategory().getParentId()).isEqualTo(3l);
		then(responseEntity.getBody().getProductCategory().getName()).isEqualTo(CATEGORY_NAME);
		then(responseEntity.getBody().getProductCategory().getDescription()).isEqualTo(CATEGORY_DESCRIPTION);
		then(responseEntity.getBody().getName()).isEqualTo(NAME);
		then(responseEntity.getBody().getDescription()).isEqualTo(DESCRIPTION);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#read(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testRead_Notfound() {
		given(productManager.read(1l)).willThrow(new EntityNotFound(1l, Product.class));

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
		ProductCategory category = new ProductCategoryBuilder().id(2l).name(CATEGORY_NAME)
				.description(CATEGORY_DESCRIPTION).parentId(3l).build();
		Product product = new ProductBuilder().id(1l).name(NAME).description(DESCRIPTION).category(category).build();

		given(productManager.readAll()).willReturn(Collections.singletonList(product));

		ResponseEntity<List<ProductDto>> responseEntity = when(testee).read();
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		then(responseEntity.hasBody()).isTrue();
		then(responseEntity.getBody().get(0).getId()).isEqualTo(1l);
		then(responseEntity.getBody().get(0).getProductCategory().getId()).isEqualTo(2l);
		then(responseEntity.getBody().get(0).getProductCategory().getParentId()).isEqualTo(3l);
		then(responseEntity.getBody().get(0).getProductCategory().getName()).isEqualTo(CATEGORY_NAME);
		then(responseEntity.getBody().get(0).getProductCategory().getDescription()).isEqualTo(CATEGORY_DESCRIPTION);
		then(responseEntity.getBody().get(0).getName()).isEqualTo(NAME);
		then(responseEntity.getBody().get(0).getDescription()).isEqualTo(DESCRIPTION);
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#read()}.
	 */
	@Test
	public final void testReadAll_returnNoData() {
		given(productManager.readAll()).willThrow(new EntityNotFound(Product.class));

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
		ProductCategory category = new ProductCategoryBuilder().id(2l).name(CATEGORY_NAME)
				.description(CATEGORY_DESCRIPTION).parentId(3l).build();
		Product product = new ProductBuilder().id(1l).name(NAME).description(DESCRIPTION).category(category).build();
		given(productManager.update(Mockito.any())).willReturn(product);

		ResponseEntity<ProductDto> responseEntity = when(testee).update(new ProductDto());
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
		then(responseEntity.hasBody()).isTrue();
		then(responseEntity.getBody().getId()).isEqualTo(1l);
		then(responseEntity.getBody().getProductCategory().getId()).isEqualTo(2l);
		then(responseEntity.getBody().getProductCategory().getParentId()).isEqualTo(3l);
		then(responseEntity.getBody().getProductCategory().getName()).isEqualTo(CATEGORY_NAME);
		then(responseEntity.getBody().getProductCategory().getDescription()).isEqualTo(CATEGORY_DESCRIPTION);
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
		given(productManager.update(Mockito.any())).willThrow(new EntityNotFound(2l, Product.class));

		when(testee).update(new ProductDto());
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
		doThrow(new EntityNotFound(2l, Product.class)).when(productManager).delete(Mockito.any());
		when(testee).delete(2l);
		thenThrown(EntityNotFound.class);
		then((EntityNotFound) caughtException()).hasFieldOrPropertyWithValue(HTTP_STATUS, HttpStatus.NOT_FOUND);
	}
}
