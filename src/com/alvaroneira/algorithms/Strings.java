package com.alvaroneira.algorithms;

import com.alvaroneira.utils.NumberUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.LinkedList;

public class Strings {
    public final static Integer ASCII_POS = 65;

    /**
     * Microsoft Excel numbers cells as 1...26 and after that AA, AB.... AAA, AAB...ZZZ and so on.
     * Given a number, convert it to that format and vice versa.
     *
     * @param n
     * @param base
     * @return
     */
    public static String changeBaseExcel(Integer n, Integer base) {
        Integer rem = n % base;
        String retVal = "" + number2excel(rem);

        while (n > 0) {
            n = (int) Math.floor(n / base);
            if (n == 0) {
                break;
            }
            n = n - 1;
            rem = (n) % base;

            retVal = number2excel(rem) + retVal;

        }
        return retVal;
    }

    private static String number2excel(Integer n) {
        return number2excel(n, 26);
    }

    private static String number2excel(Integer n, Integer base) {
        return "" + ((char) (n + ASCII_POS + 26 - base));
    }

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
     * TODO: ("kitten", "sitting") throws ArrayIndexOutOfBoundsException
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int levenshtein(String word1, String word2) {
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
                    array_u[j] = NumberUtils.java8min(
                            array_t[j - 1],
                            array_t[j],
                            array_u[j - 1]
                    ) + 1;
                }
            }
            array_t = array_u;
        }
        return array_u[word2.length()];
    }

    /**
     * TODO: ("kitten", "sitting") throws ArrayIndexOutOfBoundsException
     *
     * @param word1
     * @param word2
     * @return
     */
    public static int modifiedLevenshtein(String word1, String word2) {
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

    /**
     * A k-palindrome is a string which transforms into a palindrome on removing at most k characters.
     * <p>
     * Given a string S, and an interger K, print "YES" if S is a k-palindrome; otherwise print "NO".
     * Constraints:
     * S has at most 20,000 characters.
     * 0<=k<=30
     * <p>
     * Sample Test Case#1:
     * Input - abxa 1
     * Output - YES
     * Sample Test Case#2:
     * Input - abdxa 1
     * Output - No
     *
     * @param word
     * @param k
     * @return
     */
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

    public static int numberOfOccurrences(String needle, String haystack) {
        return haystack.length() - haystack.replace(needle, "").length();
    }

    public static Boolean isUnfinished(String s) {
        if (s.length() == 0) {
            return false;
        }
        s = s.trim();
        if (s.startsWith("\"") && s.endsWith("\"")) {
            return false;
        }
        return numberOfOccurrences("\"", s) % 2 != 0;
    }

    /**
     * /**
     * * Have the function minWindowSubstring(strArr) take the array of strings stored in strArr,
     * * which will contain only two strings, the first parameter being the string N and the second parameter being a string
     * * K of some characters, and your goal is to determine the smallest substring of N that contains all the characters in K.
     * * For example: if strArr is ["aaabaaddae", "aed"] then the smallest substring of N that contains the characters a, e,
     * * and d is "dae" located at the end of the string. So for this example your program should return the string dae.
     * <p>
     * Another example: if strArr is ["aabdccdbcacd", "aad"] then the smallest substring of N that contains all of
     * the characters in K is "aabd" which is located at the beginning of the string. Both parameters will be strings
     * ranging in length from 1 to 50 characters and all of K's characters will exist somewhere in the string N.
     * Both strings will only contains lowercase alphabetic characters.
     *
     * @param strArr
     * @return
     */
    public static String minWindowSubstring(String[] strArr) {
        String N = strArr[0];
        String K = strArr[1];
        ArrayList<String> retVals = new ArrayList<String>();

        for (Integer i = 0; i < N.length(); i++) {
            String retVal = "";
            LinkedList<Character> list = new LinkedList<Character>();
            for (Integer n = 0; n < K.length(); n++) {

                list.push(K.charAt(n));
            }
            Character swa = null;
            if (list.removeFirstOccurrence(N.charAt(i))) {
                swa = N.charAt(i);
            }
            ;


            if (swa != null) {
                retVal = N.substring(i, i + 1);
            } else {
                continue;
            }
            for (Integer j = i + 1; j < N.length(); j++) {
                retVal = retVal + N.charAt(j);
                Character swa2 = null;
                if (list.removeFirstOccurrence(N.charAt(j))) {
                    swa2 = N.charAt(j);
                }
                if (swa2 != null) {
                    if (list.isEmpty()) {
                        break;
                    }
                }
            }
            if (list.isEmpty()) {
                retVals.add(retVal);
            }
        }

        String rv = retVals.get(0).toString();
        Integer minLength = rv.length();
        for (String str : retVals) {
            if (str.length() < minLength) {
                rv = str;
                minLength = rv.length();
            }
        }
        return rv;
    }

    /**
     * Given an input string and an alphabet of letters, find the smallest substring in the input that contains each
     * letter of the alphabet at least once.
     * <p>
     * Examples:
     * <p>
     * alphabet -> [ 'a', 'b', 'c' ]
     * input -> 'abbacca'
     * output -> 'bac'
     * <p>
     * alphabet -> [ 'a', 'b', 'c' ]
     * input -> 'abbaacca'
     * output -> 'baac'
     *
     * @param alphabet
     * @param str
     * @return
     */
    public static String smallestSubstring(String alphabet, String str) {

        Integer ini = 0;
        Integer end = alphabet.length();

        ArrayList<String> candidates = new ArrayList<String>();
        while (end <= str.length()) {
            if (doesSatisfy(str, ini, end, alphabet)) {         //O(vertexCount*k)
                while (doesSatisfy(str, ini, end, alphabet)) {  //O(vertexCount*k^2)
                    ini++;
                }
                candidates.add(str.substring(ini - 1, end));
            } else {
                end = end + 1;
            }

        }
        if (candidates.size() == 0) {
            return ""; //not found
        }
        String retVal = candidates.get(0);
        for (Integer i = 1; i < candidates.size(); i++) {               //O(k)
            if (retVal.length() > candidates.get(i).length()) {
                retVal = candidates.get(i);
            }
        }
        return retVal;
    }

    //vertexCount = size of alphabet
    //k = size of input string

    //O(vertexCount*k)
    private static Boolean doesSatisfy(String str, Integer ini, Integer end, String alphabet) {
        for (Integer i = 0; i < alphabet.length(); i++) {
            Integer index = str.indexOf(alphabet.charAt(i), ini); // 2
            if (index == -1 || index >= end || index < ini) {
                return false;
            }
        }
        return true;
    }

    /**
     * Given:
     * * a encoded to 1
     * * b encoded to 2
     * * ....
     * * z encoded to 26
     * *
     * * You can translate a number to a string:
     * *
     * * '123' can be translated to 'abc'
     * * but also can be translated to 'aw','lc' which gives 3 total translations
     * * '12' can be translated to 'ab' and 'l' -> 2 translations
     * *
     * * Write a function to get the number of valid combinations from a number like '123123123'
     *
     * @param number
     * @return
     */
    public static int nTranslations(String number) {
        if (number.length() <= 1) {
            return 1;
        }
        return isValid(number.substring(0, 1)) * nTranslations(number.substring(1, number.length())) +
                nTranslations(number.substring(0, 1)) * isValid(number.substring(1, number.length()));
    }

    private static Integer isValid(String s) {
        if (s.length() == 1) {
            return 1;
        } else if (s.length() == 2) {
            return Integer.parseInt(s) <= 26 ? 1 : 0;
        }
        return 1;
    }

    public static void main(String[] args) {
        Assert.assertEquals("olleh", invertString("hello"));
        Assert.assertEquals("", invertString(""));
        Assert.assertEquals("g", invertString("g"));
        Assert.assertEquals(" ", invertString(" "));

        Assert.assertEquals(0, modifiedLevenshtein("", ""));
        Assert.assertEquals(2, modifiedLevenshtein("yo", ""));
        Assert.assertEquals(2, modifiedLevenshtein("", "yo"));
        Assert.assertEquals(0, modifiedLevenshtein("yo", "yo"));
        Assert.assertEquals(3, modifiedLevenshtein("tier", "tor"));
        Assert.assertEquals(4, modifiedLevenshtein("saturday", "sunday"));
        Assert.assertEquals(2, modifiedLevenshtein("mist", "dist"));
        Assert.assertEquals(2, modifiedLevenshtein("stop", "tops"));
        Assert.assertEquals(2, modifiedLevenshtein("hola", "holo"));
        Assert.assertEquals(9, modifiedLevenshtein("mississippi", "swiss miss"));
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
        Assert.assertEquals(0, levenshtein("", ""));
        Assert.assertEquals(2, levenshtein("yo", ""));
        Assert.assertEquals(2, levenshtein("", "yo"));
        Assert.assertEquals(0, levenshtein("yo", "yo"));
        Assert.assertEquals(2, levenshtein("tier", "tor"));
        Assert.assertEquals(3, levenshtein("saturday", "sunday"));
        Assert.assertEquals(1, levenshtein("mist", "dist"));
        Assert.assertEquals(2, levenshtein("stop", "tops"));
        Assert.assertEquals(1, levenshtein("hola", "holo"));
        Assert.assertEquals(8, levenshtein("mississippi", "swiss miss"));

        Assert.assertFalse(isUnfinished(""));
        Assert.assertFalse(isUnfinished("\"hello\""));
        Assert.assertEquals(2, numberOfOccurrences("\"", "\"hello\""));
        Assert.assertTrue(isUnfinished("\"hello"));
        Assert.assertFalse(isUnfinished("hello"));
        Assert.assertTrue(isUnfinished("hello\""));

        String[] strArr;
        strArr = new String[2];
        strArr[0] = "ahffaksfajeeubsne";
        strArr[1] = "jefaa";
        Assert.assertEquals("aksfaje", minWindowSubstring(strArr));
        strArr[0] = "aaffhkksemckelloe";
        strArr[1] = "fhea";
        Assert.assertEquals("affhkkse", minWindowSubstring(strArr));
        strArr[0] = "aabdccdbcacd";
        strArr[1] = "aad";
        Assert.assertEquals("aabd", minWindowSubstring(strArr));
        Assert.assertEquals("a", minWindowSubstring(new String[]{"aaaaaaaaa", "a"}));
        Assert.assertEquals("fafe", minWindowSubstring(new String[]{"aaffsfsfasfasfasfasfasfacasfafe", "fafe"}));
        Assert.assertEquals("affsf", minWindowSubstring(new String[]{"aaffsfsfasfasfasfasfasfacasfafe", "fafsf"}));
        Assert.assertEquals("vvave", minWindowSubstring(new String[]{"vvavereveaevafefaef", "vvev"}));
        Assert.assertEquals("caae", minWindowSubstring(new String[]{"caae", "cae"}));

        Assert.assertTrue(doesSatisfy("abaa", 0, 4, "ab"));
        Assert.assertFalse(doesSatisfy("abaa", 0, 1, "ab"));
        Assert.assertFalse(doesSatisfy("abaa", 1, 2, "ab"));
        Assert.assertTrue(doesSatisfy("abaa", 1, 3, "ab"));
        Assert.assertEquals("bac", smallestSubstring("abc", "abbacca"));
        Assert.assertEquals("baac", smallestSubstring("abc", "abbaacca"));

        Assert.assertEquals(3, nTranslations("123"));
        Assert.assertEquals(2, nTranslations("12"));
        for (Integer i = 0; i < 1000; i++) {
            System.out.println(changeBaseExcel(i, 26));
        }
//        Assert.assertEquals(3, levenshtein("kitten", "sitting"));
//        Assert.assertEquals(7, modifiedLevenshtein("kitten", "sitting"));
        System.out.println("OK");
    }
}
