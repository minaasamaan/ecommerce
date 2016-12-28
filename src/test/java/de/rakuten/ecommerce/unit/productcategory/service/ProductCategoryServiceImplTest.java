/**
 * 
 */
package de.rakuten.ecommerce.unit.productcategory.service;

import static com.googlecode.catchexception.apis.BDDCatchException.when;
import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import de.rakuten.ecommerce.builder.ProductCategoryBuilder;
import de.rakuten.ecommerce.builder.ProductCategoryDtoBuilder;
import de.rakuten.ecommerce.productcategory.dto.ProductCategoryDto;
import de.rakuten.ecommerce.productcategory.manager.ProductCategoryManager;
import de.rakuten.ecommerce.productcategory.model.ProductCategory;
import de.rakuten.ecommerce.productcategory.service.ProductCategoryServiceImpl;

/**
 * @author Mina
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryServiceImplTest {

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
		ProductCategoryDto dto = new ProductCategoryDtoBuilder().name("name").description("description").build();
		ProductCategory category = new ProductCategoryBuilder().id(1l).name("name").description("description").build();
		given(productCategoryManager.create(Mockito.any())).willReturn(category);

		ResponseEntity<ProductCategoryDto> responseEntity = when(testee).create(dto);
		then(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		then(responseEntity.hasBody()).isTrue();
		then(responseEntity.getBody().getId()).isEqualTo(1l);
		then(responseEntity.getBody().getName()).isEqualTo("name");
		then(responseEntity.getBody().getDescription()).isEqualTo("description");
		then(responseEntity.getBody().getParentId()).isNull();
	}

	@Test
	public final void testCreate_withParent_success() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#read(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testReadLong() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#read()}.
	 */
	@Test
	public final void testRead() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#update(de.rakuten.ecommerce.base.dto.AbstractDto)}
	 * .
	 */
	@Test
	public final void testUpdate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.service.AbstractRestService#delete(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testDelete() {
		fail("Not yet implemented"); // TODO
	}

}
