package com.loriek.crmloriek.utils;

import java.security.SecureRandom;

public class UUIDGenerator {
    private static final String CHARACTERS =  "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom random = new SecureRandom();
    public static String generateUUID() {
        return generateUUID(5);
    }



    public static String generateUUID(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length ; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        return sb.toString();
    }
}
