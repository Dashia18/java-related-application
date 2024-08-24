package org.dashia18.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import lombok.SneakyThrows;

@Converter
public class ObjectToJsonConverter implements AttributeConverter<Object, String> {

    ObjectMapper mapper = ObjectMapperFactory.createObjectMapper();

    @SneakyThrows
    @Override
    public String convertToDatabaseColumn(Object event) {
        return mapper.writeValueAsString(event);
    }

    @SneakyThrows
    @Override
    public Object convertToEntityAttribute(String eventAsJson) {
        return mapper.readValue(eventAsJson, Object.class);
    }
}