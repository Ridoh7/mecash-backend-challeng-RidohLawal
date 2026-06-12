package com.mecash.util;

import java.util.Random;

/**
 * Utility class responsible for generating
 * account numbers for newly created accounts.
 *
 * The generated account number is a random
 * 10-digit numeric value.
 */
public class AccountNumberGenerator {

    private static final Random RANDOM = new Random();

    /**
     * Generates a random 10-digit account number.
     *
     * Note:
     * This method relies on randomness and does not guarantee uniqueness by itself. Uniqueness is
     * enforced at the database level through the unique constraint on the account_number column.
     *
     * @return generated account number as a String
     */
    public static String generate() {

        long number = 1000000000L + Math.abs(RANDOM.nextLong() % 9000000000L);
        return String.valueOf(number);
    }
}