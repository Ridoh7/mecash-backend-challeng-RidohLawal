package com.mecash.constants;

import java.math.BigDecimal;

/**
 * Application-wide constants used
 * throughout the system.
 */
public class AppConstants {

    private AppConstants() {}

    // Exchange rate between Currency A and Currency B
    public static final BigDecimal EXCHANGE_RATE =
            new BigDecimal("1.3455");

    // Default balance assigned during account creation
    public static final BigDecimal OPENING_BALANCE =
            new BigDecimal("10000.00");
}