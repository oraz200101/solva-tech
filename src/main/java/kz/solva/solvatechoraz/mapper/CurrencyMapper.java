package kz.solva.solvatechoraz.mapper;

import kz.solva.solvatechoraz.model.dto.currency.ExternalCurrencyDto;
import kz.solva.solvatechoraz.model.dto.currency.ExternalCurrencyValueDto;
import kz.solva.solvatechoraz.model.entity.currency.CurrencyEntity;
import kz.solva.solvatechoraz.model.entity.currency.CurrencyValueEntity;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import kz.solva.solvatechoraz.model.exception.ValidationException;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurrencyMapper {

    @Mappings({
            @Mapping(source = "meta.symbol", target = "shortName", qualifiedByName = "getCurrencyShortName"),
            @Mapping(source = "values", target = "value", qualifiedByName = "mapFirstElementToCurrencyValueEntity")
    })
    CurrencyEntity mapToCurrencyEntity(ExternalCurrencyDto externalCurrencyDto);

    @Named("mapFirstElementToCurrencyValueEntity")
    default CurrencyValueEntity mapFirstElementToCurrencyValueEntity(List<ExternalCurrencyValueDto> dtoList) {
        ExternalCurrencyValueDto firstElement = getFirstElement(dtoList);
        return firstElement != null ? mapToCurrencyValueEntity(firstElement) : null;
    }

    @Named("getFirstElement")
    default ExternalCurrencyValueDto getFirstElement(List<ExternalCurrencyValueDto> dtoList) {
        if (dtoList == null || dtoList.isEmpty()) {
            throw new ValidationException("values from external service is null", "DdD5A3Sz");
        }
        return dtoList.get(0);
    }

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
