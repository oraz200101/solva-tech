package kz.solva.solvatechoraz.service;

import kz.solva.solvatechoraz.model.dto.TransactionFilterRequestDto;
import kz.solva.solvatechoraz.model.dto.TransactionRequestDto;
import kz.solva.solvatechoraz.model.dto.TransactionResponseDto;
import kz.solva.solvatechoraz.model.dto.TransactionResponseTableDto;
import lombok.NonNull;

public interface TransactionService {
    TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);

    TransactionResponseTableDto getTransactionsByFilter(@NonNull TransactionFilterRequestDto filterRequest);
}
