package kz.solva.solvatechoraz.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import kz.solva.solvatechoraz.mapper.TransactionMapper;
import kz.solva.solvatechoraz.model.dto.*;
import kz.solva.solvatechoraz.model.entity.LimitEntity;
import kz.solva.solvatechoraz.model.entity.TransactionEntity;
import kz.solva.solvatechoraz.model.entity.currency.CurrencyEntity;
import kz.solva.solvatechoraz.model.entity.currency.CurrencyValueEntity;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import kz.solva.solvatechoraz.model.exception.NotFoundException;
import kz.solva.solvatechoraz.model.exception.ValidationException;
import kz.solva.solvatechoraz.repository.AccountRepository;
import kz.solva.solvatechoraz.repository.TransactionRepository;
import kz.solva.solvatechoraz.service.AccountService;
import kz.solva.solvatechoraz.service.CurrencyService;
import kz.solva.solvatechoraz.service.TransactionService;
import kz.solva.solvatechoraz.util.TransactionValidator;
import kz.solva.solvatechoraz.worker.ElasticWorker;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final CurrencyService currencyService;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final ElasticWorker elasticWorker;
    private final ElasticsearchClient elasticsearchClient;

    @Override
    @Transactional
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        TransactionValidator.validate(transactionRequestDto);

        if (!accountRepository.existsAccountByNumber(transactionRequestDto.getAccountFrom())) {
            throw new NotFoundException("account with number " + transactionRequestDto.getAccountFrom() + " was not found", "b5rwDFch");
        }

        AccountResponseDto accountFrom = accountService.getByAccountNumber(transactionRequestDto.getAccountFrom());

        CurrencyEntity currency = currencyService.findCurrencyByCode(transactionRequestDto.getCurrencyShortName())
                .orElseThrow(() -> new NotFoundException("currency with code " + transactionRequestDto.getCurrencyShortName() + " was not found", "zrQcDZ4f9Oc"));

        double limitBalance = calculateLimitBalance(accountFrom, transactionRequestDto, currency.getValue());

        LimitEntity limitEntity = accountService.changeLimit(accountFrom.getAccountNumber(), new LimitRequestDto(transactionRequestDto.getExpenseCategory(), limitBalance));

        TransactionEntity transactionEntity = transactionMapper.mapToTransactionEntity(transactionRequestDto);

        transactionEntity.setLimitExceeded(limitBalance < 0);

        transactionRepository.save(transactionEntity);

        TransactionResponseDto transactionResponseDto = transactionMapper.mapToTransactionResponseDto(transactionEntity, limitEntity, CurrencyShortName.USD);

        elasticWorker.createDocument(TransactionResponseDto.TRANSACTION_ELASTIC_INDEX_NAME, transactionResponseDto.toElasticDocument(), String.valueOf(transactionResponseDto.getId()));

        return transactionResponseDto;
    }

    private double calculateLimitBalance(AccountResponseDto accountFrom, TransactionRequestDto transactionRequest, CurrencyValueEntity currencyValue) {
        switch (transactionRequest.getExpenseCategory()) {
            case PRODUCT -> {
                return accountFrom.getProductLimitSum() - transactionRequest.getSum() / currencyValue.getClose();
            }
            case SERVICE -> {
                return accountFrom.getServiceLimitSum() - transactionRequest.getSum() / currencyValue.getClose();
            }
        }
        throw new ValidationException("unknown expense category", "PrRfRrJuf");
    }

    @Override
    public TransactionResponseTableDto getTransactionsByFilter(@NonNull TransactionFilterRequestDto filterRequest) {

        return null;
    }
}

