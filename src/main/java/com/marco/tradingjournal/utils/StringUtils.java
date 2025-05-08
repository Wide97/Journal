package com.marco.tradingjournal.utils;

public class StringUtils {

    public static boolean isNullOrBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static String capitalize(String str) {
        if (isNullOrBlank(str)) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
}

