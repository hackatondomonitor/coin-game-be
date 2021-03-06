package com.hackatonalcoolico.coingame.config.common;

public interface Setter {

    void set(String key, Object value);

    void setIfNull(String key, Object value);

    long increment(String key);

}
