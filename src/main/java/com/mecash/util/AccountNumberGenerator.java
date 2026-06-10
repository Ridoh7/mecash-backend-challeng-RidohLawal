package com.mecash.util;

import java.util.Random;

public class AccountNumberGenerator {

    private static final Random RANDOM = new Random();

    public static String generate() {

        long number =
                1000000000L +
                        Math.abs(RANDOM.nextLong() % 9000000000L);

        return String.valueOf(number);
    }
}