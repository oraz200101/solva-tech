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
            case "USD/KZT" -> {
                return KZT;
            }
            case "USD/RUB" -> {
                return RUB;
            }
            default -> throw new IllegalArgumentException("Unknown currency short name: " + stringValue);
        }
    }

    public static String usdToKzt() {
        return "USD/KZT";
    }

    public static String usdToRub() {
        return "USD/RUB";
    }
}
