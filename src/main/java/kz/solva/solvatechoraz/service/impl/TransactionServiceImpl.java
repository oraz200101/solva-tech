package kz.solva.solvatechoraz.service.impl;

import kz.solva.solvatechoraz.model.dto.PageRequestDto;
import kz.solva.solvatechoraz.model.dto.TransactionRequestDto;
import kz.solva.solvatechoraz.model.dto.TransactionResponseDto;
import kz.solva.solvatechoraz.service.AccountService;
import kz.solva.solvatechoraz.service.CurrencyService;
import kz.solva.solvatechoraz.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final CurrencyService currencyService;
    private final AccountService accountService;

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        return null;
    }

    @Override
    public TransactionResponseDto getTransaction(String transactionId) {
        return null;
    }

    @Override
    public Page<TransactionResponseDto> getAllTransactions(PageRequestDto pageRequestDto, Long accountId) {
        return null;
    }
}

