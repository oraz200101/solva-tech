package kz.solva.solvatechoraz.model.dto.currency;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ExternalCurrencyValueDto {
    private String datetime;
    private String open;
    private String high;
    private String low;
    private String close;
}
