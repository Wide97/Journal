package com.marco.tradingjournal.utils;

public class CurrencyUtils {

    public static String getSymbol(String code) {
        return switch (code.toUpperCase()) {
            case "EUR" -> "€";
            case "USD" -> "$";
            case "GBP" -> "£";
            case "JPY" -> "¥";
            case "AUD" -> "A$";
            case "CAD" -> "C$";
            case "CHF" -> "CHF";
            case "NZD" -> "NZ$";
            default -> "";
        };
    }

    public static boolean isSupported(String code) {
        return getSymbol(code).length() > 0;
    }
}
