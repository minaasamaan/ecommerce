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

	public StringBuilder getCurrencyExchangeRateApiBaseUrl() {
		return new StringBuilder(apiBaseUrl);
	}

	public String getDefaultProductCurrency() {
		return defaultProductCurrency;
	}

}
