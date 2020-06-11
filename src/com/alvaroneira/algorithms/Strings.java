package com.alvaroneira.algorithms;

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
     * Not tested
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int modifiedLevenshtein(String word1, String word2) {
        int[] array_t = new int[word1.length()];
        int[] array_u = new int[word2.length()];
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
            array_u = new int[]{i};
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
}
