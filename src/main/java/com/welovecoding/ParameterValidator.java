package com.welovecoding;

import java.util.List;

public class ParameterValidator {

    public static void notNull(Object value, RuntimeException exception) {
        if (value == null) {
            throw exception;
        }
    }

    public static void isNull(Object value, RuntimeException exception) {
        if (value != null) {
            throw exception;
        }
    }

    public static void notEmpty(Object value, RuntimeException exception) {
        if (value instanceof List && ((List) value).isEmpty()) {
            throw exception;
        } else if (value instanceof String && ((String) value).isEmpty()) {
            throw exception;
        }
    }

}
