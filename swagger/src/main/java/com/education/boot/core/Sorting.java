package com.education.boot.core;

public class Sorting {

    public static String of(String unsort) {
        var chars = unsort.toCharArray();
        var n = chars.length;
        while (n > 1) {
            for (var j = 0; j < n - 1; j++) {
                if (Character.valueOf(chars[j]).compareTo(Character.valueOf(chars[j + 1])) > 0) {
                    final var tmp = chars[j + 1];
                    chars[j + 1] = chars[j];
                    chars[j] = tmp;
                }
            }
            n--;
        }
        return chars.toString();
    }
}
