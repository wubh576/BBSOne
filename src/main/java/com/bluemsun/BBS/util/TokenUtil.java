package com.bluemsun.BBS.util;

import java.util.UUID;

public class TokenUtil {

    public static String setToken() {
        String token = String.valueOf(UUID.randomUUID());
        return token;
    }

}
