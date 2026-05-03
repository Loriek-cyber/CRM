package com.loriek.crmloriek.utils;

import java.security.SecureRandom;

public class UUIDGenerator {
    private static final String CHARACTERS =  "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    public static String generateUUID() {
        StringBuilder sb = new StringBuilder(5);
        for (int i = 0; i < 5 ; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
