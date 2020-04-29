package com.alvaroneira.utils;

import com.google.common.math.IntMath;

public class NumberUtils {
    public static long binomial(int n, int k) {
        return IntMath.binomial(n, k);
//        long retVal = 1;
//        k = Math.max(k, n - k);
//        for (int i = n; i > k; i--) {
//            retVal *= i;
//        }
//        return retVal / factorial(n - k);
    }

//    public static long factorial(int n) {
//        if (n <= 1) {
//            return 1;
//        }
//        return n * factorial(n - 1);
//    }

    public static int numberOfOnes(String binaryString) {
        int retVal = 0;
        for (int i = 0; i < binaryString.length(); i++) {
            int digit = Integer.parseInt(binaryString.substring(i, i + 1));
            if (digit != 0) {
                retVal++;
            }
        }
        return retVal;
    }

    public static void main(String[] args) {
//        System.out.println(factorial(1));
//        System.out.println(factorial(2));
//        System.out.println(factorial(3));
//        System.out.println(factorial(4));
//        System.out.println(factorial(5));
        System.out.println();
        System.out.println(binomial(6, 3));
        System.out.println(binomial(4, 3));
        System.out.println(binomial(4, 0));
        System.out.println(binomial(4, 2));
        System.out.println(binomial(4, 4));
    }
}
