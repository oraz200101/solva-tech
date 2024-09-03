package kz.solva.solvatechoraz.service.impl;

import kz.solva.solvatechoraz.client.CurrencyClient;
import kz.solva.solvatechoraz.mapper.CurrencyMapper;
import kz.solva.solvatechoraz.model.dto.currency.ExternalCurrencyDto;
import kz.solva.solvatechoraz.model.entity.currency.CurrencyEntity;
import kz.solva.solvatechoraz.model.enums.CurrencyDate;
import kz.solva.solvatechoraz.model.enums.CurrencyInterval;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import static kz.solva.solvatechoraz.client.CurrencyClientConstants.CURRENCY_TIME_ZONE;

@Service
@RequiredArgsConstructor
public class CurrencyServiceImpl {

    private final CurrencyClient currencyClient;
    private final RedisTemplate<String, CurrencyEntity> restTemplate;
    private final CurrencyMapper currencyMapper;

    @Scheduled(cron = "0 0 0 * * *", zone = CURRENCY_TIME_ZONE)
    private void refreshCurrencies() {
        ExternalCurrencyDto rub = currencyClient.getCurrency(CurrencyShortName.RUB.getStringValue(), CurrencyInterval.ONE_DAY.getExternalValue(), "", CurrencyDate.TODAY.getExternalValue(), CURRENCY_TIME_ZONE);
        ExternalCurrencyDto kzt = currencyClient.getCurrency(CurrencyShortName.KZT.getStringValue(), CurrencyInterval.ONE_DAY.getExternalValue(), "", CurrencyDate.TODAY.getExternalValue(), CURRENCY_TIME_ZONE);

        restTemplate.opsForValue().set(CurrencyShortName.RUB.getStringValue(), currencyMapper.mapToCurrencyEntity(rub));
        restTemplate.opsForValue().set(CurrencyShortName.KZT.getStringValue(), currencyMapper.mapToCurrencyEntity(kzt));
    }
}
