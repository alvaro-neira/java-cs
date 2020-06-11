package com.alvaroneira.questions;

/**
 * Microsoft Excel numbers cells as 1...26 and after that AA, AB.... AAA, AAB...ZZZ and so on.
 * Given a number, convert it to that format and vice versa.
 */

public class Excel {
    public static final int ASCII_POS = 65;

    public static String changeBaseExcel(int n, int base) {
        // n=n+1;
        int rem = n % base;
        String retVal = "" + number2excel(rem);

        while (n > 0) {
            n = (int) Math.floor(1.0 * n / base);
            if (n == 0) {
                break;
            }
            n = n - 1;
            rem = (n) % base;

            retVal = number2excel(rem) + retVal;

        }
        return retVal;
    }

    public static String number2excel(int n) {
        return Character.toString((char) (n + ASCII_POS));
    }

    public static void main(String[] args) {
        int i;
        for (i = 0; i < 1000; i++) {
            System.out.println(changeBaseExcel(i, 26));
        }
    }
}
