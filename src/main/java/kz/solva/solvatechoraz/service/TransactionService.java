package kz.solva.solvatechoraz.service;

import kz.solva.solvatechoraz.model.dto.PageRequestDto;
import kz.solva.solvatechoraz.model.dto.TransactionRequestDto;
import kz.solva.solvatechoraz.model.dto.TransactionResponseDto;
import org.springframework.data.domain.Page;

public interface TransactionService {
    TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);

    TransactionResponseDto getTransaction(String transactionId);

    Page<TransactionResponseDto> getAllTransactions(PageRequestDto pageRequestDto, Long accountId);
}
