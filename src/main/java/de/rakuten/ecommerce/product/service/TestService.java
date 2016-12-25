/**
 * 
 */
package de.rakuten.ecommerce.product.service;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.rakuten.ecommerce.product.dto.ProductDto;

/**
 * @author Mina
 *
 */
@RestController
@RequestMapping("/test")
public class TestService {

	@RequestMapping(method = POST, produces = { APPLICATION_JSON_VALUE })
	public void doNothing(@RequestBody ProductDto lonlong) {
		System.out.println("TestService.doNothing()");
	}
}
