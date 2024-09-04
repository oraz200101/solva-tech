package kz.solva.solvatechoraz.service;

import kz.solva.solvatechoraz.model.dto.AccountRequestDto;
import kz.solva.solvatechoraz.model.dto.AccountResponseDto;
import kz.solva.solvatechoraz.model.dto.LimitRequestDto;
import kz.solva.solvatechoraz.model.entity.LimitEntity;

public interface AccountService {

    void createAccount(AccountRequestDto accountRequestDto);

    AccountResponseDto getByAccountNumber(long accountNumber);

    LimitEntity changeLimit(long accountNumber, LimitRequestDto limitRequestDto);
}

