/**
 * 
 */
package de.rakuten.ecommerce.product.client;

import org.springframework.stereotype.Component;

import de.rakuten.ecommerce.base.client.AbstractRestServicesClient;
import de.rakuten.ecommerce.product.client.exception.CurrencyNotSupported;
import de.rakuten.ecommerce.product.dto.CurrencyDto;

/**
 * @author Mina
 *
 */
@Component
public class FixerRestWSClient extends AbstractRestServicesClient {
	public static final String GET_RATE_URL = "/latest?base={defaultCurrency}&symbols={currencyCode}";

	public Double getRateInDefaultCurrency(String currencyCode) {
		CurrencyDto currencyDto = doGet(GET_RATE_URL, CurrencyDto.class,
				getApplicationConfigurations().getDefaultProductCurrency(), currencyCode);

		if (currencyDto == null || currencyDto.getRates() == null || currencyDto.getRates().isEmpty()) {
			throw new CurrencyNotSupported(currencyCode);
		}
		return currencyDto.getRates().get(currencyCode);
	}

	@Override
	protected StringBuilder getBaseUrl() {
		return getApplicationConfigurations().getCurrencyExchangeRateApiBaseUrl();
	}
}
