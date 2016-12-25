/**
 * 
 */
package de.rakuten.ecommerce.base.service;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mina
 *
 */
// TODO handle API version
public interface CRUDRestService<DTO> {

	@RequestMapping(method = POST, consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
	ResponseEntity<DTO> create(DTO dto);

	@RequestMapping(path = "{id}", method = GET, produces = { APPLICATION_JSON_VALUE })
	ResponseEntity<DTO> read(Long id);

	@RequestMapping(method = PUT, consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
	ResponseEntity<DTO> update(DTO dto);

	@RequestMapping(method = DELETE, consumes = { APPLICATION_JSON_VALUE })
	ResponseEntity<?> delete(DTO dto);
}
