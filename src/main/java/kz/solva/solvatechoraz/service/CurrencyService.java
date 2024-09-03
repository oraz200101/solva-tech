package kz.solva.solvatechoraz.service;

import kz.solva.solvatechoraz.model.entity.currency.CurrencyEntity;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;

import java.util.Optional;

public interface CurrencyService {

    Optional<CurrencyEntity> findCurrencyByCode(CurrencyShortName code);

}
