package com.frost.temp.sorter.impl;

import java.util.Arrays;

public class StringSorter {

    public static String sortAndToString(String text) {
        char[] characters = text.toCharArray();
        Arrays.sort(characters);
        text = convert(characters);
        return text;
    }

    private static String convert(char[] chars) {
        StringBuilder result = new StringBuilder();

        for (int index = 0; index < chars.length; index++)
            result.append((index + 1) + " - " + chars[index] + '\n');

        return result.toString();
    }
}
