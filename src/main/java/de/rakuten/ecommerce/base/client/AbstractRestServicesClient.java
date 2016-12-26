/**
 * 
 */
package de.rakuten.ecommerce.base.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestOperations;

import de.rakuten.ecommerce.base.context.ApplicationConfigurations;

/**
 * Parent rest-based client for all external services
 * 
 * @author Mina
 *
 */
public abstract class AbstractRestServicesClient {

	@Autowired
	private RestOperations restTemplate;

	@Autowired
	private ApplicationConfigurations applicationConfigurations;

	protected abstract StringBuilder getBaseUrl();

	/**
	 * Generic implementation for "Get" rest calls.
	 * 
	 * @param serviceUrl
	 * @param clazz
	 *            the type "T" for response parameters
	 * @param uriVariables
	 * @return
	 */
	protected <T> T doGet(String serviceUrl, Class<T> clazz, Object... uriVariables) {
		return getRestTemplate().getForObject(getBaseUrl().append(serviceUrl).toString(), clazz, uriVariables);
	}

	/**
	 * @return the restTemplate
	 */
	private RestOperations getRestTemplate() {
		return restTemplate;
	}

	/**
	 * @return the applicationConfigurations
	 */
	public ApplicationConfigurations getApplicationConfigurations() {
		return applicationConfigurations;
	}
}
