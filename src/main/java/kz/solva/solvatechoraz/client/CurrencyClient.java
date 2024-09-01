package kz.solva.solvatechoraz.client;

import kz.solva.solvatechoraz.model.dto.currency.ExternalCurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestParam;

import static kz.solva.solvatechoraz.client.CurrencyClientConstants.CURRENCY_BASE_URL;

@FeignClient(value = "currency-api", url = CURRENCY_BASE_URL)
public interface CurrencyClient {
    ExternalCurrencyDto getCurrency(@RequestParam(name = "symbol") String symbol,
                                    @RequestParam(name = "interval") String interval,
                                    @RequestParam(name = "apikey") String apiKey,
                                    @RequestParam(name = "date") String date,
                                    @RequestParam(name = "timezone") String timeZone);
}
