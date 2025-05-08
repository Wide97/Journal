package com.marco.tradingjournal.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean isValidDate(LocalDate date) {
        return date != null && !date.isAfter(LocalDate.now());
    }

    public static String format(LocalDate date) {
        return date != null ? date.format(FORMATTER) : "";
    }

    public static boolean isSameDay(LocalDate d1, LocalDate d2) {
        return d1 != null && d2 != null && d1.equals(d2);
    }
}
