package com.marco.tradingjournal.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtils {

    public static double roundTo2(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static boolean isPositive(double value) {
        return value > 0;
    }

    public static boolean isZero(double value) {
        return Double.compare(value, 0.0) == 0;
    }
}
