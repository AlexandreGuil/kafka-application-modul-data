package com.inti.formation.price.util;

import java.text.DecimalFormat;
import java.util.Random;

public class RandPrice {

    public static String getRandPrice() {
        DecimalFormat from = new DecimalFormat();
        from.setMaximumFractionDigits(3);
        return from.format(Math.abs(Math.random() * 10000)) + "€";
    }
}
