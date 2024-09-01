package kz.solva.solvatechoraz.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum CurrencyInterval {
    ONE_MIN("1min"),
    FIVE_MIN("5min"),
    FIFTEEN_MIN("15min"),
    THIRTY_MIN("30min"),
    FORTY_FIVE_MIN("45min"),
    ONE_HOUR("1hour"),
    ONE_DAY("1day"),
    ONE_WEEK("1week"),
    ONE_MONTH("1month"),
    ;

    private final String externalValue;

    public static CurrencyInterval getByExternalValue(String externalValue) {
        switch (externalValue) {
            case "1min" -> {
                return ONE_MIN;
            }
            case "5min" -> {
                return FIVE_MIN;
            }
            case "15min" -> {
                return FIFTEEN_MIN;
            }
            case "30min" -> {
                return THIRTY_MIN;
            }
            case "45min" -> {
                return FORTY_FIVE_MIN;
            }
            case "1hour" -> {
                return ONE_HOUR;
            }
            case "1day" -> {
                return ONE_DAY;
            }
            case "1week" -> {
                return ONE_WEEK;
            }
            case "1month" -> {
                return ONE_MONTH;
            }
        }

        throw new IllegalArgumentException("Unknown currency interval: " + externalValue);
    }
}
