package kz.solva.solvatechoraz.util;

import kz.solva.solvatechoraz.model.dto.TransactionRequestDto;
import kz.solva.solvatechoraz.model.exception.ValidationException;

public class TransactionValidator {

    public static void validate(TransactionRequestDto transactionRequestDto) {
        if (transactionRequestDto == null) {
            throw new ValidationException("transaction request is null", "4nJDN0Z4");
        }

        if (transactionRequestDto.getAccountFrom() == 0) {
            throw new ValidationException("account from is null", "VXZqFuZcHzy");
        }

        if (transactionRequestDto.getAccountFrom() > 10_000_000_000L) {
            throw new ValidationException("account from number too large", "1gPed2Xl9OU");
        }

        if (transactionRequestDto.getAccountFrom() < 1_000_000_000L) {
            throw new ValidationException("account from number too small", "yd7670YMD0");
        }

        if (transactionRequestDto.getAccountTo() == 0) {
            throw new ValidationException("account to is null", "4E1iQcUg");
        }

        if (transactionRequestDto.getAccountTo() > 10_000_000_000L) {
            throw new ValidationException("account to number too large", "EL4twOR");
        }

        if (transactionRequestDto.getAccountTo() < 1_000_000_000L) {
            throw new ValidationException("account to number too small", "Rw3Y32PN0");
        }

        if (transactionRequestDto.getSum() < 0) {
            throw new ValidationException("transaction sum cannot less than 0", "dlN1Ve9fN");
        }

        if (transactionRequestDto.getExpenseCategory() == null) {
            throw new ValidationException("expense category is null", "On58WEvVq");
        }

        if (transactionRequestDto.getCurrencyShortName() == null) {
            throw new ValidationException("currency short name is null", "KD9QdIf8K7");
        }
    }
}
