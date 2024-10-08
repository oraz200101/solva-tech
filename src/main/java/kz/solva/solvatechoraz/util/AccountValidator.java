package kz.solva.solvatechoraz.util;

import kz.solva.solvatechoraz.model.dto.AccountRequestDto;
import kz.solva.solvatechoraz.model.dto.LimitRequestDto;
import kz.solva.solvatechoraz.model.exception.ValidationException;

public class AccountValidator {

    public static void validate(AccountRequestDto accountRequestDto) {
        if (accountRequestDto == null) {
            throw new ValidationException("Account request is null", "OdHixUy");
        }

        if (accountRequestDto.getAccountNumber() == 0) {
            throw new ValidationException("Account number is null", "uBFfOpVM4r");
        }

        if (accountRequestDto.getAccountNumber() > 10_000_000_000L) {
            throw new ValidationException("Account number too large", "6tw4Gv6nVv");
        }

        if (accountRequestDto.getAccountNumber() < 1_000_000_000L) {
            throw new ValidationException("Account number too small", "OPAzBVjO");
        }

        validate(accountRequestDto.getProductLimit());
        validate(accountRequestDto.getServiceLimit());
    }

    public static void validate(LimitRequestDto limitRequestDto) {
        if (limitRequestDto == null) {
            throw new ValidationException("Limit is null", "IBOUcH0S");
        }
    }
}
