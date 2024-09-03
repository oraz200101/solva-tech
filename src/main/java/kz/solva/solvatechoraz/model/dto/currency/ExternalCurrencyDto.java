package kz.solva.solvatechoraz.model.dto.currency;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ExternalCurrencyDto {
   private ExternalCurrencyMetaDto meta;
   private List<ExternalCurrencyValueDto> values;
   private String status;
}
