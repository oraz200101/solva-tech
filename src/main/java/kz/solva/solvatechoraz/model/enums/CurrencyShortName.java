package kz.solva.solvatechoraz.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrencyShortName {
    KZT("KZT"),
    USD("USD"),
    RUB("RUB");

    private final String stringValue;

    public static CurrencyShortName getByStringValue(String stringValue) {
        switch (stringValue) {
            case "KZT" -> {
                return KZT;
            }
            case "USD" -> {
                return USD;
            }
            case "RUB" -> {
                return RUB;
            }
            default -> throw new IllegalArgumentException("Unknown currency short name: " + stringValue);
        }
    }
}
