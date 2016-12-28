/**
 * 
 */
package de.rakuten.ecommerce.integration.productcategory;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Mina
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@IntegrationTest("server.port:0")
public class ProductCategoryServiceImplTest {

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
	 */
	@Test
	public final void testCreate() {
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
