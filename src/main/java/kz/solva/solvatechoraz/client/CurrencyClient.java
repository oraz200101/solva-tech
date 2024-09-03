package kz.solva.solvatechoraz.client;

import kz.solva.solvatechoraz.model.dto.currency.ExternalCurrencyDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static kz.solva.solvatechoraz.client.CurrencyClientConstants.CURRENCY_BASE_URL;

@FeignClient(value = "currency-api", url = CURRENCY_BASE_URL)
public interface CurrencyClient {

    @GetMapping
    ExternalCurrencyDto getCurrency(@RequestParam(name = "symbol") String symbol,
                                    @RequestParam(name = "interval") String interval,
                                    @RequestParam(name = "apikey") String apiKey,
                                    @RequestParam(name = "dateTime") String date,
                                    @RequestParam(name = "outputsize") String outputSize,
                                    @RequestParam(name = "timezone") String timeZone);
}
