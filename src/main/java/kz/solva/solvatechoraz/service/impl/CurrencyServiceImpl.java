package kz.solva.solvatechoraz.service.impl;

import jakarta.annotation.PostConstruct;
import kz.solva.solvatechoraz.client.CurrencyClient;
import kz.solva.solvatechoraz.mapper.CurrencyMapper;
import kz.solva.solvatechoraz.model.dto.currency.ExternalCurrencyDto;
import kz.solva.solvatechoraz.model.entity.currency.CurrencyEntity;
import kz.solva.solvatechoraz.model.enums.CurrencyDate;
import kz.solva.solvatechoraz.model.enums.CurrencyInterval;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import kz.solva.solvatechoraz.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static kz.solva.solvatechoraz.client.CurrencyClientConstants.CURRENCY_OUTPUT_SIZE;
import static kz.solva.solvatechoraz.client.CurrencyClientConstants.CURRENCY_TIME_ZONE;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyClient currencyClient;
    private final RedisTemplate<String, CurrencyEntity> restTemplate;
    private final CurrencyMapper currencyMapper;
    private static final String STATUS_OK = "ok";

    @Value("${spring.twelve.api}")
    private String currencyApi;

    @Scheduled(cron = "0 0 0 * * *", zone = CURRENCY_TIME_ZONE)
    private void refreshCurrencies() {
        saveCurrencies();
    }

    @PostConstruct
    private void saveCurrenciesPostConstruct() {
        saveCurrencies();
    }

    private void saveCurrencies() {
        ExternalCurrencyDto rub = currencyClient.getCurrency(CurrencyShortName.usdToRub(), CurrencyInterval.ONE_DAY.getExternalValue(), currencyApi, CurrencyDate.TODAY.getExternalValue(), CURRENCY_OUTPUT_SIZE, CURRENCY_TIME_ZONE);
        ExternalCurrencyDto kzt = currencyClient.getCurrency(CurrencyShortName.usdToKzt(), CurrencyInterval.ONE_DAY.getExternalValue(), currencyApi, CurrencyDate.TODAY.getExternalValue(), CURRENCY_OUTPUT_SIZE, CURRENCY_TIME_ZONE);

        if (rub.getStatus().equals(STATUS_OK)) {
            restTemplate.opsForValue().set(CurrencyShortName.RUB.getStringValue(), currencyMapper.mapToCurrencyEntity(rub));

        }

        if (kzt.getStatus().equals(STATUS_OK)) {
            restTemplate.opsForValue().set(CurrencyShortName.KZT.getStringValue(), currencyMapper.mapToCurrencyEntity(kzt));
        }
    }

    @Override
    public Optional<CurrencyEntity> findCurrencyByCode(CurrencyShortName code) {
        return Optional.ofNullable(restTemplate.opsForValue().get(code.getStringValue()));
    }

}
