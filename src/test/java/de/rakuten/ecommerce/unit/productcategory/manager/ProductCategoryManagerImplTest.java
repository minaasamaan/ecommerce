/**
 * 
 */
package de.rakuten.ecommerce.unit.productcategory.manager;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import de.rakuten.ecommerce.base.context.ApplicationConfigurations;
import de.rakuten.ecommerce.productcategory.manager.ProductCategoryManagerImpl;

/**
 * @author Mina
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductCategoryManagerImplTest {

	@InjectMocks
	private ProductCategoryManagerImpl testee;

	@Mock
	private ApplicationConfigurations applicationConfigurations;

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
	public final void testCreate() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#read(java.lang.Long)}
	 * .
	 */
	@Test
	public final void testRead() {
		fail("Not yet implemented"); // TODO
	}

	/**
	 * Test method for
	 * {@link de.rakuten.ecommerce.base.manager.AbstractBusinessEntityManager#readAll()}
	 * .
	 */
	@Test
	public final void testReadAll() {
		fail("Not yet implemented"); // TODO
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
