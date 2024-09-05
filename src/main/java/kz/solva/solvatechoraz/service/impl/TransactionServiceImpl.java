package kz.solva.solvatechoraz.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldSort;
import co.elastic.clients.elasticsearch._types.SortOptions;
import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import kz.solva.solvatechoraz.mapper.TransactionMapper;
import kz.solva.solvatechoraz.model.dto.*;
import kz.solva.solvatechoraz.model.entity.AccountEntity;
import kz.solva.solvatechoraz.model.entity.LimitEntity;
import kz.solva.solvatechoraz.model.entity.TransactionEntity;
import kz.solva.solvatechoraz.model.entity.currency.CurrencyEntity;
import kz.solva.solvatechoraz.model.entity.currency.CurrencyValueEntity;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import kz.solva.solvatechoraz.model.enums.SortType;
import kz.solva.solvatechoraz.model.exception.NotFoundException;
import kz.solva.solvatechoraz.model.exception.ValidationException;
import kz.solva.solvatechoraz.repository.AccountRepository;
import kz.solva.solvatechoraz.repository.LimitRepository;
import kz.solva.solvatechoraz.repository.TransactionRepository;
import kz.solva.solvatechoraz.service.AccountService;
import kz.solva.solvatechoraz.service.CurrencyService;
import kz.solva.solvatechoraz.service.TransactionService;
import kz.solva.solvatechoraz.util.TransactionValidator;
import kz.solva.solvatechoraz.worker.ElasticWorker;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final CurrencyService currencyService;
    private final AccountService accountService;
    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;
    private final TransactionRepository transactionRepository;
    private final LimitRepository limitRepository;
    private final ElasticWorker elasticWorker;
    private final ElasticsearchClient elasticsearchClient;

    @Override
    @Transactional
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        TransactionValidator.validate(transactionRequestDto);

        if (!accountRepository.existsAccountByNumber(transactionRequestDto.getAccountFrom())) {
            throw new NotFoundException("account with number " + transactionRequestDto.getAccountFrom() + " was not found", "b5rwDFch");
        }

        AccountEntity account = accountRepository.findByAccountNumber(transactionRequestDto.getAccountFrom())
                .orElseThrow(() -> new NotFoundException("account with number " + transactionRequestDto.getAccountFrom() + " was not found", "Cn5v1izAC"));

        CurrencyEntity currency = currencyService.findCurrencyByCode(transactionRequestDto.getCurrencyShortName())
                .orElseThrow(() -> new NotFoundException("currency with code " + transactionRequestDto.getCurrencyShortName() + " was not found", "zrQcDZ4f9Oc"));

        LimitEntity limitEntity = limitRepository.findByAccountNumberAndExpenseCategory(transactionRequestDto.getAccountFrom(), transactionRequestDto.getExpenseCategory())
                .orElseThrow(() -> new NotFoundException("limit with account number " + transactionRequestDto.getAccountFrom() + "was not found", "uYu1GH6e2lR"));

        double limitBalance = calculateLimitBalance(limitEntity, transactionRequestDto, currency.getValue());

        limitEntity = accountService.changeLimit(transactionRequestDto.getAccountFrom(), new LimitRequestDto(transactionRequestDto.getExpenseCategory(), limitBalance));

        TransactionEntity transactionEntity = transactionMapper.mapToTransactionEntity(transactionRequestDto);
        transactionEntity.setAccountFrom(account);
        transactionEntity.setAccountTo(null);
        transactionEntity.setLimitExceeded(limitBalance < 0);

        transactionEntity = transactionRepository.save(transactionEntity);

        TransactionResponseDto transactionResponseDto = transactionMapper.mapToTransactionResponseDto(transactionEntity, limitEntity, CurrencyShortName.USD);

        elasticWorker.createDocument(TransactionResponseDto.TRANSACTION_ELASTIC_INDEX_NAME, transactionResponseDto.toElasticDocument(), String.valueOf(transactionResponseDto.getId()));

        return transactionResponseDto;
    }

    private double calculateLimitBalance(LimitEntity limitEntity, TransactionRequestDto transactionRequest, CurrencyValueEntity currencyValue) {
        return limitEntity.getLimitSum() - transactionRequest.getSum() / currencyValue.getClose();
    }

    @Override
    @SneakyThrows
    public TransactionResponseTableDto getTransactionsByFilter(@NonNull TransactionFilterRequestDto filterRequest) {
        List<SortOptions> sortOptionsList = filterRequest.getSortFilter().stream()
                .map(filter -> new SortOptions.Builder()
                        .field(f -> f.field(filter.getSortField())
                                .order(filter.getSortType() == SortType.ASC ? SortOrder.Asc : SortOrder.Desc))
                        .build())
                .toList();

        SearchResponse<TransactionResponseDto> searchResponse = elasticsearchClient.search(s -> s
                .index(TransactionResponseDto.TRANSACTION_ELASTIC_INDEX_NAME)
                .query(q -> q
                        .match(m -> m
                                .field(filterRequest.getSearchFilter().getSearchField())
                                .query(filterRequest.getSearchFilter().getSearchValue())
                                .field(TransactionResponseDto.Fields.accountFrom)
                                .query(filterRequest.getAccountNumber()))
                )
                .sort(sortOptionsList)
                .trackTotalHits(t -> t.enabled(true))
                .from(filterRequest.getPage().getPage() * filterRequest.getPage().getSize()), TransactionResponseDto.class);

        List<TransactionResponseDto> responseDtoList = searchResponse.hits().hits()
                .stream()
                .map(Hit::source)
                .toList();

        long totalElements = searchResponse.hits().total() != null ? searchResponse.hits().total().value() : 0;

        long totalPages = (totalElements + filterRequest.getPage().getSize() - 1) / filterRequest.getPage().getSize();

        return TransactionResponseTableDto.of(responseDtoList, totalPages, totalElements);
    }

}

