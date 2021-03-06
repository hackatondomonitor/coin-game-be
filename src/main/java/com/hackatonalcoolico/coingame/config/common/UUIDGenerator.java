package com.hackatonalcoolico.coingame.config.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UUIDGenerator {


    public static String generate() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}

