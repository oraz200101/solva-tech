package kz.solva.solvatechoraz.model.entity.currency;

import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CurrencyEntity implements Serializable {
    private CurrencyShortName shortName;
    private CurrencyValueEntity value;
}
