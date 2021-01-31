package com.alvaroneira.algorithms;

import org.junit.Assert;

public class Strings {
    public static boolean isPalindrome(String str) {
        int ini = 0;
        int end = str.length() - 1;
        while (ini <= end) {
            if (str.charAt(ini) != str.charAt(end)) {
                return false;
            }
            ini++;
            end--;
        }
        return true;
    }

    /**
     * @param word1
     * @param word2
     * @return
     */
    public static Integer modifiedLevenshtein(String word1, String word2) {
        int[] array_t = new int[word1.length() + 1];
        int[] array_u = new int[word2.length() + 1];
        int i;
        int j;
        if (word1.length() == 0) {
            return word2.length();
        }
        if (word2.length() == 0) {
            return word1.length();
        }
        for (j = 0; j <= word2.length(); j++) {
            array_t[j] = j;
        }
        for (i = 1; i <= word1.length(); i++) {
            array_u = new int[word2.length() + 1];
            array_u[0] = i;
            for (j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    array_u[j] = array_t[j - 1];
                } else {
                    array_u[j] = Math.min(
                            // array_t[j - 1], //substitution out
                            array_t[j],
                            array_u[j - 1]
                    ) + 1;
                }
            }
            array_t = array_u;
        }
        return array_u[word2.length()];
    }

    public static String invertString(String s) {
        int half = (int) Math.ceil(s.length() / 2.0);
        char[] strChars = s.toCharArray();
        for (Integer i = 0; i < half; i++) {
            swap(strChars, i, s.length() - i - 1);
        }
        return String.valueOf(strChars);
    }

    public static void swap(char[] strChars, Integer i, Integer j) {
        char tmp = strChars[i];
        strChars[i] = strChars[j];
        strChars[j] = tmp;
    }

    public static Boolean isKPalindrome(String word, Integer k) {
        return modifiedLevenshtein(word, invertString(word)) <= 2 * k;
    }

    /**
     * TODO: further testing
     *
     * @param str
     * @return
     */
    public static Boolean isAlphaNumericPalindrome(String str) {
        Integer ini = 0;
        Integer end = str.length() - 1;
        while (ini <= end) {
            String iniStr = str.substring(ini, ini + 1).toLowerCase();
            while (!iniStr.matches("[a-z0-9]") && ini < str.length() - 1) {
                ini++;
                iniStr = str.substring(ini, ini + 1).toLowerCase();
            }
            String endStr = str.substring(end, end + 1).toLowerCase();
            while (!endStr.matches("[a-z0-9]") && end > 0) {
                end--;
                endStr = str.substring(end, end + 1).toLowerCase();
            }
            /**
             * This "if" was commented. Check this
             */
//            if (ini > end) {
//                return false;
//            }
            if (!iniStr.equals(endStr)) {
                return false;
            }
            ini++;
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        Assert.assertEquals("olleh", invertString("hello"));
        Assert.assertEquals("", invertString(""));
        Assert.assertEquals("g", invertString("g"));
        Assert.assertEquals(" ", invertString(" "));

        Assert.assertTrue(isKPalindrome("a", 0));
        Assert.assertTrue(isKPalindrome("aba", 0));
        Assert.assertTrue(isKPalindrome("abxa", 1));
        Assert.assertFalse(isKPalindrome("abdxa", 1));
        Assert.assertTrue(isKPalindrome("abdxa", 2));
        Assert.assertTrue(isAlphaNumericPalindrome("aba"));
        Assert.assertTrue(isAlphaNumericPalindrome("aba12321aba"));
        Assert.assertTrue(isAlphaNumericPalindrome("aba1232?1aba"));
        Assert.assertFalse(isAlphaNumericPalindrome("aba123?1aba"));
        Assert.assertTrue(isAlphaNumericPalindrome("a?"));
        Assert.assertTrue(isAlphaNumericPalindrome("a"));
        Assert.assertTrue(isAlphaNumericPalindrome(""));
        Assert.assertTrue(isAlphaNumericPalindrome("?"));
        Assert.assertTrue(isAlphaNumericPalindrome("a?!"));
        Assert.assertTrue(isAlphaNumericPalindrome("a?a"));
        Assert.assertTrue(isAlphaNumericPalindrome("?!a&^"));
        Assert.assertTrue(isAlphaNumericPalindrome("?!a&^a"));
        Assert.assertFalse(isAlphaNumericPalindrome("1?!a&^a"));
        System.out.println("OK");
    }
}
