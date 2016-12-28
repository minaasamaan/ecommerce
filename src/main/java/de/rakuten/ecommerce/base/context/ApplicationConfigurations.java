/**
 * 
 */
package de.rakuten.ecommerce.base.context;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Application configurations placeholder
 * 
 * @author Mina
 *
 */
@Component
public class ApplicationConfigurations {

	@Value("${application.api.currency_exchange_rate.base_url}")
	private String apiBaseUrl;

	@Value("${application.ecommerce.product.default_currency}")
	private String defaultProductCurrency;

	@Value("${application.ecommerce.security.create_default_super_user}")
	private Boolean createDefaultSuperUser;

	@Value("${application.ecommerce.security.super_user.name}")
	private String superUserName;

	@Value("${application.ecommerce.security.super_user.password}")
	private String superUserPassword;

	public StringBuilder getCurrencyExchangeRateApiBaseUrl() {
		return new StringBuilder(apiBaseUrl);
	}

	public String getDefaultProductCurrency() {
		return defaultProductCurrency;
	}

	public Boolean isCreateDefaultSuperUser() {
		return createDefaultSuperUser;
	}

	public String getSuperUserName() {
		return superUserName;
	}

	public String getSuperUserPassword() {
		return superUserPassword;
	}
}
