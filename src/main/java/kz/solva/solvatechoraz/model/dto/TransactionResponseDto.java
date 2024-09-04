package kz.solva.solvatechoraz.model.dto;

import co.elastic.clients.elasticsearch._types.mapping.Property;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import kz.solva.solvatechoraz.model.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@FieldNameConstants
public class TransactionResponseDto {
    public static final String TRANSACTION_ELASTIC_INDEX_NAME = "transaction";

    private long id;
    private long accountFrom;
    private long accountTo;
    private double sum;
    private CurrencyShortName accountCurrencyShortName;
    private ZonedDateTime dateTime;
    private ExpenseCategory expenseCategory;
    private boolean limitExceeded;
    private double limitSum;
    private ZonedDateTime limitDateTime;
    private CurrencyShortName limitCurrencyShortName;

    public static Map<String, Property> elasticMapping() {
        Map<String, Property> elasticMapping = new HashMap<>();

        elasticMapping.put(Fields.id, Property.of(property -> property.long_(l -> l.index(true).docValues(true))));
        elasticMapping.put(Fields.accountFrom, Property.of(property -> property.long_(l -> l.index(true).docValues(true))));
        elasticMapping.put(Fields.sum, Property.of(property -> property.double_(d -> d.index(true).docValues(true))));
        elasticMapping.put(Fields.dateTime, Property.of(property -> property.date(d -> d.index(true).docValues(true))));
        elasticMapping.put(Fields.expenseCategory, Property.of(property -> property.keyword(k -> k.index(true).docValues(true))));
        elasticMapping.put(Fields.limitExceeded, Property.of(property -> property.boolean_(b -> b.index(true).docValues(true))));
        elasticMapping.put(Fields.limitSum, Property.of(property -> property.double_(d -> d.index(true).docValues(true).docValues(true))));
        elasticMapping.put(Fields.limitDateTime, Property.of(property -> property.date(d -> d.index(true).docValues(true).docValues(true))));
        elasticMapping.put(Fields.limitCurrencyShortName, Property.of(property -> property.keyword(k -> k.index(true).docValues(true))));

        return elasticMapping;
    }

    public Map<String, Object> toElasticDocument() {
        Map<String, Object> document = new HashMap<>();

        document.put(Fields.id, this.getId());
        document.put(Fields.accountFrom, this.getAccountFrom());
        document.put(Fields.sum, this.getSum());
        document.put(Fields.dateTime, this.getDateTime());
        document.put(Fields.expenseCategory, this.getExpenseCategory());
        document.put(Fields.limitExceeded, this.isLimitExceeded());
        document.put(Fields.limitSum, this.getLimitSum());
        document.put(Fields.limitDateTime, this.getLimitDateTime());
        document.put(Fields.limitCurrencyShortName, this.getLimitCurrencyShortName());

        return document;
    }

}
