package com.hackatonalcoolico.coingame.config.common;

import java.util.List;
import java.util.Map;

public interface Getter {

    <T> T get(Object key);

    <T> T get(String key);

    <T> T get(Object key, Class<T> returnType);

    <T> T get (Object aKey, Class<T> returnType, T defaultValue);

    <T> T get (Object aKey, T defaultValue);

    boolean containsKey(Object key);

    Map<String, Object> getMap();

    Map<String, Object> getMap(Object key);

    Map<String, Object> getMap(Object key, Map<String, Object> objDefault);

    <T> List<T> getList(Object key, Class<T> elementType);

    <T> List<T> getList(Object key, Class<T> elementType, List<T> defaultValue);

    <T> T[] getArray(Object aKey, Class<T> elementType);

    <T> T getOrDefault(Object key);

    <T> T getRequired (Object key);

    String getRequiredString (Object key);

    String getString (Object key);

    String getString (Object key, String defaultValue);

    Long getLong (Object key);

    Long getLong (Object key, long defaultValue);

    Double getDouble (Object key);

    Double getDouble (Object key, double defaultValue);

    Float getFloat (Object key);

    Float getFloat (Object key, float defaultValue);

    Integer getInteger (Object key);

    Integer getInteger (Object key, int defaultValue);

    Boolean getBoolean (Object key);

    boolean getBoolean (Object key, boolean defaultValue);

    Map<String, Object> asMap ();

}
