package kz.solva.solvatechoraz.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {

    @Override
    public void serialize(ZonedDateTime zonedDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {
            String str = zonedDateTime.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
            jsonGenerator.writeString(str);
        } catch (DateTimeParseException e) {
            jsonGenerator.writeString("");
        }
    }
}
