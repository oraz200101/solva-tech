package kz.solva.solvatechoraz.model.dto;

import co.elastic.clients.elasticsearch._types.mapping.Property;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import kz.solva.solvatechoraz.model.enums.ExpenseCategory;
import kz.solva.solvatechoraz.util.ZonedDateTimeDeserializer;
import kz.solva.solvatechoraz.util.ZonedDateTimeSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponseDto {
    public static final String TRANSACTION_ELASTIC_INDEX_NAME = "transaction";
    private static final String ZONED_DATE_TIME_FORMAT_ELASTIC = "strict_date_optional_time||epoch_millis";

    private long id;
    private long accountFrom;
    private long accountTo;
    private double sum;
    private CurrencyShortName accountCurrencyShortName;
    private ExpenseCategory expenseCategory;
    private boolean limitExceeded;
    private double limitSum;
    private CurrencyShortName limitCurrencyShortName;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime limitDateTime;

    @JsonSerialize(using = ZonedDateTimeSerializer.class)
    @JsonDeserialize(using = ZonedDateTimeDeserializer.class)
    private ZonedDateTime dateTime;

    public static Map<String, Property> elasticMapping() {
        Map<String, Property> elasticMapping = new HashMap<>();

        elasticMapping.put(Fields.id, Property.of(property -> property.long_(l -> l.index(true).docValues(true))));
        elasticMapping.put(Fields.sum, Property.of(property -> property.double_(d -> d.index(true).docValues(true))));
        elasticMapping.put(Fields.accountFrom, Property.of(property -> property.long_(l -> l.index(true).docValues(true))));
        elasticMapping.put(Fields.limitExceeded, Property.of(property -> property.boolean_(b -> b.index(true).docValues(true))));
        elasticMapping.put(Fields.expenseCategory, Property.of(property -> property.keyword(k -> k.index(true).docValues(true))));
        elasticMapping.put(Fields.limitCurrencyShortName, Property.of(property -> property.keyword(k -> k.index(true).docValues(true))));
        elasticMapping.put(Fields.limitSum, Property.of(property -> property.double_(d -> d.index(true).docValues(true).docValues(true))));
        elasticMapping.put(Fields.dateTime, Property.of(property -> property.date(d -> d.index(true).docValues(true).format(ZONED_DATE_TIME_FORMAT_ELASTIC))));
        elasticMapping.put(Fields.limitDateTime, Property.of(property -> property.date(d -> d.index(true).docValues(true).docValues(true).format(ZONED_DATE_TIME_FORMAT_ELASTIC))));

        return elasticMapping;
    }

    public Map<String, Object> toElasticDocument() {
        Map<String, Object> document = new HashMap<>();

        document.put(Fields.id, this.getId());
        document.put(Fields.sum, this.getSum());
        document.put(Fields.limitSum, this.getLimitSum());
        document.put(Fields.accountFrom, this.getAccountFrom());
        document.put(Fields.limitExceeded, this.isLimitExceeded());
        document.put(Fields.expenseCategory, this.getExpenseCategory());
        document.put(Fields.limitCurrencyShortName, this.getLimitCurrencyShortName());
        document.put(Fields.dateTime, this.getDateTime().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        document.put(Fields.limitDateTime, this.getLimitDateTime().format(DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        return document;
    }

}
