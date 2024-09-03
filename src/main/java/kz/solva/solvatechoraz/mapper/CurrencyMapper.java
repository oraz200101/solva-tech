package kz.solva.solvatechoraz.mapper;

import kz.solva.solvatechoraz.model.dto.currency.ExternalCurrencyDto;
import kz.solva.solvatechoraz.model.dto.currency.ExternalCurrencyValueDto;
import kz.solva.solvatechoraz.model.entity.currency.CurrencyEntity;
import kz.solva.solvatechoraz.model.entity.currency.CurrencyValueEntity;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrencyMapper {

    @Mapping(source = "meta.symbol", target = "shortName", qualifiedByName = "getCurrencyShortName")
    CurrencyEntity mapToCurrencyEntity(ExternalCurrencyDto externalCurrencyDto);

    @Mappings({
            @Mapping(source = "open", target = "open", qualifiedByName = "stringToDouble"),
            @Mapping(source = "high", target = "high", qualifiedByName = "stringToDouble"),
            @Mapping(source = "low", target = "low", qualifiedByName = "stringToDouble"),
            @Mapping(source = "close", target = "close", qualifiedByName = "stringToDouble")
    })
    CurrencyValueEntity mapToCurrencyValueEntity(ExternalCurrencyValueDto dto);

    @Named("stringToDouble")
    default double stringToDouble(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }

    @Named("getCurrencyShortName")
    default CurrencyShortName getCurrencyShortName(String currencyName) {
        return CurrencyShortName.getByStringValue(currencyName);
    }

}
