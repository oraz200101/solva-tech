package kz.solva.solvatechoraz.model.entity.currency;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CurrencyValueEntity {
    private double open;
    private double high;
    private double low;
    private double close;
    private LocalDateTime dateTime;
}
