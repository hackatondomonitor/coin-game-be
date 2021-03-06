package com.hackatonalcoolico.coingame.config.common;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class MapObject implements Map<String, Object>, Getter, Setter, Serializable {

    private static final long serialVersionUID = 1981170780514105169L;

    private static final ConversionService conversionService = DefaultConversionService.getSharedInstance();

    private final HashMap<String, Object> map;

    public MapObject() {
        map = new HashMap<>();
    }

    public MapObject(Map<String, Object> source) {
        map = new HashMap<>(source);
    }

    @Override
    public void set(String key, Object value) {
        map.put(key,value);
    }

    @Override
    public Object remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ?> variables) {
        map.putAll(variables);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public Set<String> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Object> values() {
        return map.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return map.entrySet();
    }

    @Override
    public void setIfNull(String key, Object value) {
        if(get(key)==null) {
            set(key, value);
        }
    }

    @Override
    public long increment(String key) {
        Long counter = get(key);
        if(counter == null) {
            counter = 0L;
        }
        counter++;
        set(key, counter);
        return counter;
    }

    @Override
    public <T> T get(String key) {
        return (T) map.get(key);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object aKey) {
        return map.containsKey(aKey);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return map.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return map.put(key,value);
    }

    @Override
    public <T> T get(Object key, Class<T> returnType) {
        Object value = get(key);
        if(value == null) {
            return null;
        }
        return conversionService.convert(value, returnType);
    }

    @Override
    public <T> T get(Object aKey, Class<T> returnType, T defaultValue) {
        Object value = get(aKey);
        if(value == null) {
            return defaultValue;
        }
        return conversionService.convert(value, returnType);
    }

    @Override
    public <T> T get(Object aKey, T defaultValue) {
        if(containsKey(aKey)) {
            return (T) get(aKey);
        } else {
            return defaultValue;
        }
    }

    @Override
    public Map<String, Object>  getMap() {
        return Collections.unmodifiableMap(new HashMap<>(map));
    }

    @Override
    public Map<String, Object> getMap(Object key) {
        Map<String, Object> value =  (Map<String, Object>) get(key);
        if(value == null) {
            return null;
        }
        return Collections.unmodifiableMap(value);
    }

    @Override
    public Map<String, Object> getMap(Object aKey, Map<String, Object> objDefault) {
        Map<String, Object> value = getMap(aKey);
        return value!=null?value:objDefault;
    }

    @Override
    public <T> List<T> getList(Object key, Class<T> elementType) {
        List list = get(key, List.class);
        if(list == null) {
            return null;
        }
        List<T> typedList = new ArrayList<>();
        for(Object item : list) {
            if(elementType.equals(Getter.class) || elementType.equals(MapObject.class)) {
                typedList.add((T)new MapObject((Map<String, Object>) item));
            }
            else {
                typedList.add(conversionService.convert(item,elementType));
            }
        }
        return Collections.unmodifiableList(typedList);
    }

    @Override
    public <T> List<T> getList(Object key, Class<T> elementType, List<T> defaultValue) {
        List<T> list = getList(key, elementType);
        return list!=null?list:defaultValue;
    }

    @Override
    public <T> T[] getArray(Object key, Class<T> elementType) {
        Object value = get(key);
        if(value.getClass().isArray() && value.getClass().getComponentType().equals(elementType)) {
            return (T[]) value;
        }
        List<T> list = getList(key, elementType);
        T[] toR = (T[]) Array.newInstance(elementType, list.size());
        for (int i = 0; i < list.size(); i++) {
            toR[i] = list.get(i);
        }
        return toR;
    }

    @Override
    public <T> T getOrDefault(Object key) {
        return (T) map.getOrDefault(key,new Object());
    }

    @Override
    public <T> T getRequired(Object key) {
        if (containsKey(key)) {
            return (T) get(key);
        } else {
            throw new IllegalArgumentException("missing required key: " + key.toString());
        }
    }

    @Override
    public String getRequiredString(Object key) {
        String value = getString(key);
        Assert.notNull(value,"Unknown key: " + key);
        return value;
    }

    @Override
    public String getString(Object key) {
        Object value = get(key);
        return conversionService.convert(value,String.class);
    }

    @Override
    public String getString(Object key, String defaultValue) {
        String value = getString(key);
        return value != null ? value : defaultValue;
    }

    @Override
    public Long getLong(Object key) {
        return get(key,Long.class);
    }

    @Override
    public Long getLong(Object key, long defaultValue) {
        return get(key,Long.class, defaultValue);
    }

    @Override
    public Double getDouble(Object key) {
        return get(key,Double.class);
    }

    @Override
    public Double getDouble(Object key, double defaultValue) {
        return get(key,Double.class,defaultValue);
    }

    @Override
    public Float getFloat(Object key) {
        return get(key,Float.class);
    }

    @Override
    public Float getFloat(Object key, float defaultValue) {
        return get(key,Float.class, defaultValue);
    }

    @Override
    public Integer getInteger(Object key) {
        return get(key,Integer.class);
    }

    @Override
    public Integer getInteger(Object key, int defaultValue) {
        return get(key, Integer.class, defaultValue);
    }

    @Override
    public Boolean getBoolean(Object key) {
        return get(key, Boolean.class);
    }

    @Override
    public boolean getBoolean(Object key, boolean defaultValue) {
        Boolean value = getBoolean(key);
        return value!=null?value:defaultValue;
    }

    @Override
    public Map<String, Object> asMap() {
        return Collections.unmodifiableMap(new HashMap<>(map));
    }

    public static MapObject empty () {
        return new MapObject(Collections.emptyMap());
    }

    public static MapObject of (Map<String,Object> map) {
        return new MapObject(map);
    }

    public String toString() {
        return map.toString();
    }
}
