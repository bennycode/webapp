package com.welovecoding.data.base;

import java.text.Normalizer;
import java.util.Random;

public class Slugify {

    private static final int RANDOM_DIGITS = 6;

    public static String slugify(String stringToSugify) {
        if (isNull(stringToSugify)) {
            return null;
        }
        stringToSugify = stringToSugify.trim();

        if (stringToSugify.isEmpty()) {
            return "";
        }
        // Replace German umlauts
        stringToSugify = stringToSugify.replaceAll("[Ü]", "Ue").replaceAll("[ü]", "ue");
        stringToSugify = stringToSugify.replaceAll("[Ä]", "Ae").replaceAll("[ä]", "ae");
        stringToSugify = stringToSugify.replaceAll("[Ö]", "Oe").replaceAll("[ö]", "oe");
        stringToSugify = stringToSugify.replaceAll("ß", "ss");
        // Replace special characters
        stringToSugify = stringToSugify.replaceAll("\\+", "plus").replaceAll("\\#", " sharp").replaceAll("-", " ");

        stringToSugify = stringToSugify.trim();

        stringToSugify = stringToSugify.replace("ß", "ss");

        stringToSugify = Normalizer.normalize(stringToSugify, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "")
                .replaceAll("[^a-zA-Z0-9 ]", "");

        stringToSugify = stringToSugify.trim();

        stringToSugify = removeDuplicateWhiteSpaces(stringToSugify);
        stringToSugify = stringToSugify.replace(" ", "-").replace("plus", "+");
        return stringToSugify + "-" + randomNumber();

    }

    private static String removeDuplicateWhiteSpaces(String input) {
        return input.replaceAll("\\s+", " ");
    }

    private static boolean isNull(String string) {
        return string == null;
    }

    static int randomNumber() {
        Random rnd = new Random();
        StringBuilder randomString = new StringBuilder();
        for (int i = 0; i < RANDOM_DIGITS; i++) {
            int rand = rnd.nextInt(9);
            if (i == 0 && rand == 0) {
                rand++;
            }
            randomString.append(rand);
        }
        Integer random = Integer.parseInt(randomString.toString());
        return random;
    }

}
