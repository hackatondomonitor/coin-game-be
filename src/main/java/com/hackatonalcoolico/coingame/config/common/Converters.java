package com.hackatonalcoolico.coingame.config.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.Map;
import java.util.Objects;

public class Converters {

    private static final ObjectMapper defaultObjectMapper = new ObjectMapper();

    public static <T> T readValue (ObjectMapper aObjectMapper, String aValue, Class<T> aClass) {
        try {
            return aObjectMapper.readValue(aValue, aClass);
        } catch (Exception e) {
            //throw Throwables.propagate(e);
            throw new RuntimeException(e);
        }
    }

    public static String writeValueAsString (Object value) {

        if (Objects.nonNull(value)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            objectMapper.setDefaultPropertyInclusion(JsonInclude.Value.construct(JsonInclude.Include.ALWAYS, JsonInclude.Include.NON_NULL));
            String result = writeValueAsString(objectMapper, value);
            return result;
        }
        return null;
    }

    public static String writeValueAsString (ObjectMapper objectMapper, Object value) {
        try {
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Map<String, Object> writeValueAsMap(String value)  {
        if (Objects.nonNull(value)) {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.registerModule(new JavaTimeModule());
            objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            try {
                Map<String, Object> result = objectMapper.readValue(value, new TypeReference<Map<String, Object>>() {
                });
                //Map<String, Object> result = readValue(objectMapper,value,new TypeReference<Map<String, Object>>() {})
                return result;
            }  catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}
