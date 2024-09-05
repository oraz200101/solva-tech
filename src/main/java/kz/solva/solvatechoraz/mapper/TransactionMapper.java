package kz.solva.solvatechoraz.mapper;

import kz.solva.solvatechoraz.model.dto.TransactionRequestDto;
import kz.solva.solvatechoraz.model.dto.TransactionResponseDto;
import kz.solva.solvatechoraz.model.entity.LimitEntity;
import kz.solva.solvatechoraz.model.entity.TransactionEntity;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import org.mapstruct.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Mapper(componentModel = "spring", unmappedTargetPolicy = org.mapstruct.ReportingPolicy.IGNORE)
public interface TransactionMapper {

    @Mappings({
            @Mapping(source = "accountFrom", target = "accountFrom.accountNumber", ignore = true),
            @Mapping(source = "accountTo", target = "accountTo.accountNumber", ignore = true),
            @Mapping(source = "sum", target = "sum"),
            @Mapping(source = "expenseCategory", target = "expenseCategory")
    })
    TransactionEntity mapToTransactionEntity(TransactionRequestDto transactionRequestDto);


    @Mappings({
            @Mapping(source = "transactionEntity.id", target = "id"),
            @Mapping(source = "transactionEntity.accountFrom.accountNumber", target = "accountFrom"),
            @Mapping(source = "transactionEntity.accountTo.accountNumber", target = "accountTo"),
            @Mapping(source = "transactionEntity.sum", target = "sum"),
            @Mapping(source = "transactionEntity.currencyShortName", target = "accountCurrencyShortName"),
            @Mapping(source = "transactionEntity.expenseCategory", target = "expenseCategory"),
            @Mapping(source = "transactionEntity.limitExceeded", target = "limitExceeded"),
            @Mapping(source = "transactionEntity.createdAt", target = "dateTime", qualifiedByName = "localDateTimeToZonedDateTime"),
            @Mapping(source = "limitEntity.limitSum", target = "limitSum"),
            @Mapping(source = "limitEntity.createdAt", target = "limitDateTime", qualifiedByName = "localDateTimeToZonedDateTime"),
            @Mapping(source = "limitCurrencyShortName", target = "limitCurrencyShortName"),
    })
    void mapToTransactionResponseDto(
            @MappingTarget TransactionResponseDto dto,
            TransactionEntity transactionEntity,
            LimitEntity limitEntity,
            CurrencyShortName limitCurrencyShortName
    );

    default TransactionResponseDto mapToTransactionResponseDto(
            TransactionEntity transactionEntity,
            LimitEntity limitEntity,
            CurrencyShortName limitCurrencyShortName
    ) {
        TransactionResponseDto dto = new TransactionResponseDto();
        mapToTransactionResponseDto(dto, transactionEntity, limitEntity, limitCurrencyShortName);
        return dto;
    }

    @Named("localDateTimeToZonedDateTime")
    default ZonedDateTime localDateTimeToZonedDateTime(LocalDateTime localDateTime) {
        ZonedDateTime utcZonedDateTime = localDateTime.atZone(ZoneId.of("UTC"));
        return utcZonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Almaty"));
    }
}
