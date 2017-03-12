package com.alvaroneira.questions;

/**
 * Created by aneira on 3/12/17.
 * <p>
 * Q:
 * Write a method to count the number of 2s between 0 and n
 * (In the Book Cracking the Coding Interview)
 * Could not solve it
 */
public class NumberOf2s {
    public static void main(String[] args) {
        System.out.println("begin");
        System.out.println("The answer numberof2s is " + bruteForce(11));
        System.out.println("The answer numberof2s is " + bruteForce(21));
        System.out.println("The answer numberof2s is " + bruteForce(22));
        System.out.println("The answer numberof2s is " + bruteForce(99));
        System.out.println("The answer numberof2s is " + bruteForce(212));
        System.out.println("end");
    }

    public static int bruteForce(int n) {
        int count = 0;
        for (int i = 0; i <= n; i++) {
            String str = String.valueOf(i);
            for (int j = 0; j < str.length(); j++) {
                int num = Integer.parseInt(str.substring(j, j + 1));
                if (num == 2) {
                    System.out.println(i);
                    count++;
                }
            }
        }
        return count;
    }

    public static int withDivideBy10(int n) {
        if (n < 10) {
            return (n >= 2 ? 1 : 0);
        }
        int ndigits = (int) Math.floor(Math.log10(n)) + 1;
        int firstDigit = n / (int) Math.pow(10.0, ndigits - 1);

        return (firstDigit>=2 ? 1:0 + firstDigit + 1) * withDivideBy10(n % (int) Math.pow(10.0, ndigits - 1));
//        withDivideBy10(n);
//        int count = 0;
//        for (int i = n; i > 0; i=i/10) {
//            String str = String.valueOf(i);
//            for (int j = 0; j < str.length(); j++) {
//                int num = Integer.parseInt(str.substring(j, j + 1));
//                if (num == 2) {
//                    System.out.println(i);
//                    count++;
//                }
//            }
//        }
//
//        return count;
    }
}
