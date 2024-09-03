package kz.solva.solvatechoraz.service.impl;

import kz.solva.solvatechoraz.mapper.AccountMapper;
import kz.solva.solvatechoraz.model.dto.AccountRequestDto;
import kz.solva.solvatechoraz.model.dto.AccountResponseDto;
import kz.solva.solvatechoraz.model.dto.LimitRequestDto;
import kz.solva.solvatechoraz.model.entity.LimitEntity;
import kz.solva.solvatechoraz.model.exception.NotFoundException;
import kz.solva.solvatechoraz.repository.AccountRepository;
import kz.solva.solvatechoraz.repository.LimitRepository;
import kz.solva.solvatechoraz.service.AccountService;
import kz.solva.solvatechoraz.util.AccountValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final LimitRepository limitRepository;
    private final AccountMapper accountMapper;


    @Override
    @Transactional
    public void createAccount(AccountRequestDto accountRequestDto) {
        AccountValidator.validate(accountRequestDto);
        accountRepository.save(accountMapper.mapToAccountEntity(accountRequestDto));
    }

    @Override
    public AccountResponseDto getByAccountNumber(long accountNumber) {
        return accountRepository.findAccountByNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException("account with number " + accountNumber + " was not found", "nupGLNiI4U"));
    }

    @Override
    @Transactional
    public void changeLimit(long accountNumber, LimitRequestDto limitRequestDto) {
        AccountValidator.validate(limitRequestDto);

        LimitEntity limit = limitRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new NotFoundException("limit with account number " + accountNumber + " was not found", "hEF7jdvAp"));

        limit.setLimitSum(limitRequestDto.getLimitSum());

        limitRepository.save(limit);
    }
}
