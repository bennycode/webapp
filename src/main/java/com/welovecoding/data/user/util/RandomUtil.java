package com.welovecoding.data.user.util;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Utility class for generating random Strings.
 */
public final class RandomUtil {

    private static SecureRandom random = new SecureRandom();

    private RandomUtil() {
    }

    /**
     * Generates a password.
     *
     * @return the generated password
     */
    public static String generatePassword() {
        return new BigInteger(130, random).toString(32);
    }

    /**
     * Generates an activation key.
     *
     * @return the generated activation key
     */
    public static String generateActivationKey() {
        return new BigInteger(130, random).toString(32);
    }

    /**
     * Generates a reset key.
     *
     * @return the generated reset key
     */
    public static String generateResetKey() {
        return new BigInteger(130, random).toString(32);
    }
}
