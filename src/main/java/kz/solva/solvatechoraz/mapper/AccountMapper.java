package kz.solva.solvatechoraz.mapper;

import kz.solva.solvatechoraz.model.dto.AccountRequestDto;
import kz.solva.solvatechoraz.model.dto.LimitRequestDto;
import kz.solva.solvatechoraz.model.entity.AccountEntity;
import kz.solva.solvatechoraz.model.entity.LimitEntity;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AccountMapper {

    AccountEntity mapToAccountEntity(AccountRequestDto accountRequestDto);

    @BeforeMapping
    default void setDefaultLimitCategoryName(AccountRequestDto accountRequestDto) {
        accountRequestDto.setDefaultLimitCategoryNames();
    }

    LimitEntity mapToLimitEntity(LimitRequestDto limitRequestDto);
}
