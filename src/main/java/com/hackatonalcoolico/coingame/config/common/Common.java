package com.hackatonalcoolico.coingame.config.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.IOException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class Common {

    private Logger log = LoggerFactory.getLogger(getClass());

    private static final ObjectMapper defaultObjectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

    public static <T> T readValue(String aValue, TypeReference<Map<String, Object>> aClass) throws IOException {
        return (T) defaultObjectMapper.readValue(aValue, aClass);
    }

    public static <T> T readValue(String aValue, Class<T> aClass) throws IOException {
        return (T) defaultObjectMapper.readValue(aValue, aClass);
    }

    public static <T> T readValue(ObjectMapper objectMapper, String aValue, Class<T> aClass) throws IOException {
        return objectMapper.readValue(aValue, aClass);
    }

    public static String writeValueAsString(Object value) {
        try {
            return writeValueAsString(defaultObjectMapper, value);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String writeValueAsString(ObjectMapper aObjectMapper, Object value) {
        try {
            return aObjectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    public static Map<String, Object> flattenMap(Map<String, Object> data, String prefix) {
        try {
            return hashMapper(data, prefix);
        } catch (ParseException e) {
            return Collections.emptyMap();
        }
    }


    private static Map<String, Object> hashMapper(Map<String, Object> lhm1, String prefix) throws ParseException {
        Map<String, Object> mapping = new MapObject();
        for (Map.Entry<String, Object> entry : lhm1.entrySet()) {
            String key = entry.getKey();
            Object value = entry.getValue();

            String newPrefix;
            if (prefix.equals(".")) {
                newPrefix = key;
            } else {
                newPrefix = prefix + "." + key;
            }

            if (value instanceof String) {
                mapping.put(newPrefix, (String) value);
            } else if (value instanceof Map) {
                //Map<String, Object> subMap = (Map<String, Object>) value;
                //hashMapper(subMap, newPrefix);
                mapping.putAll(hashMapper((Map<String, Object>) value, newPrefix));

            } else if (value == null) {
                String val = "null";
                mapping.put(newPrefix, (String) val);
            } else if (value instanceof Object[]) {

                Object[] results = (Object[]) entry.getValue();
                //HashMap<String, Object> subMap = (HashMap<String, Object>) value2;
                int i = 0;
                for (Object object : results) {
                    newPrefix = prefix + "." + key + "." + i + ".";
                    if (object instanceof Map) {
                        mapping.putAll(hashMapper((Map<String, Object>) object, newPrefix));
                    } else if (value == null) {
                        String val = "null";
                        mapping.put(newPrefix, (String) val);
                    } else {
                        mapping.put(newPrefix, object.toString());
                    }
                    i++;
                }
            } else {
                //force everything to be string
                mapping.put(newPrefix, value.toString());
            }
        }
        return mapping;
    }

    /**
     * @param resolver
     * @param <T>
     * @return
     */
    public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    public static MapObject prepareHttpResponse(Object response) {
        if (response instanceof HttpStatusCodeException) {
            HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) response;

            MapObject mapObject = new MapObject();
            mapObject.put("headers", httpStatusCodeException.getResponseHeaders());
            try {
                mapObject.put("body", Common.readValue(httpStatusCodeException.getResponseBodyAsString(), new TypeReference<Map<String, Object>>() {
                }));
            } catch (Exception mapperException) {
                mapObject.put("rawBody", httpStatusCodeException.getResponseBodyAsString());
            }
            mapObject.put("statusCode", httpStatusCodeException.getRawStatusCode());
            return mapObject;
        } else if (response instanceof ResponseEntity) {
            ResponseEntity responseEntity = (ResponseEntity) response;
            MapObject httpResponse = new MapObject();
            httpResponse.put("headers", responseEntity.getHeaders());
            if (responseEntity.getBody() instanceof String) {
                httpResponse.put("rawBody", responseEntity.getBody());
            } else {
                httpResponse.put("body", responseEntity.getBody());
            }
            httpResponse.put("statusCode", responseEntity.getStatusCodeValue());
            return httpResponse;
        }
        return null;
    }


}
