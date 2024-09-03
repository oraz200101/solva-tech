package kz.solva.solvatechoraz.service;

import kz.solva.solvatechoraz.model.dto.AccountRequestDto;
import kz.solva.solvatechoraz.model.dto.AccountResponseDto;
import kz.solva.solvatechoraz.model.dto.LimitRequestDto;

public interface AccountService {

    void createAccount(AccountRequestDto accountRequestDto);

    AccountResponseDto getByAccountNumber(long accountNumber);

    void changeLimit(long accountNumber, LimitRequestDto limitRequestDto);
}

