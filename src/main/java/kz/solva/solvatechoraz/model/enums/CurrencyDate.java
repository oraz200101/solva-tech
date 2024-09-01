package kz.solva.solvatechoraz.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CurrencyDate {
    TODAY("today"),
    YESTERDAY("yesterday"),;

    private final String externalValue;
}
