package com.alvaroneira.utils;

import com.google.common.math.IntMath;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

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

    public static int java8max(Integer... args) {
        List<Integer> listOfIntegers = Arrays.asList(args);
        return listOfIntegers
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
    }

    public static int java8min(Integer... args) {
        List<Integer> listOfIntegers = Arrays.asList(args);
        return listOfIntegers
                .stream()
                .mapToInt(v -> v)
                .min().orElseThrow(NoSuchElementException::new);
    }

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

    /**
     * @param n
     * @param b
     * @param arraySize
     * @return
     */
    public static Integer[] changeDecimalBase(int n, int b, int arraySize) {
        Integer[] retVal = new Integer[arraySize];
        int rem = n % b;
        retVal[arraySize - 1] = rem;
        for (int i = arraySize - 2; i >= 0; i--) {
            n = n / b;
            rem = n % b;
            retVal[i] = rem;
        }
        return retVal;
    }
//
//    function binary2decimal(str){
//        var len=str.length;
//        var i;
//        var retVal=0;
//        for(i=len-1;i>=0;i--){
//            var digit=parseInt(str.substring(i,i+1),0);
//            retVal+=Math.pow(2,len-i-1)*digit;
//        }
//        return retVal;
//    }

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
        System.out.println();
        for (int i = 1; i < 100; i++) {
            ArrayUtils.printArr(changeDecimalBase(i, 16, 3));
        }
    }
}
