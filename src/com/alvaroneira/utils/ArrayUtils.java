package com.alvaroneira.utils;

import java.util.*;
import java.util.stream.IntStream;

import static com.alvaroneira.utils.NumberUtils.numberOfOnes;

/**
 * Created by aneira on 3/18/17.
 */
public class ArrayUtils {
    public static <T> String arr2str(T[] arr) {
        String retVal = "[";
        for (T elem : arr) {
            if (elem instanceof Boolean) {
                retVal += ((Boolean) elem ? 1 : 0) + ",";
            } else {
                retVal += elem + ",";
            }
        }
        return retVal + "]";
    }

    public static boolean contains(int[] A, int n) {
        for (int i = 0; i < A.length; i++) {
            if (A[i] == n) {
                return true;
            }
        }
        return false;
    }

    public static int sum(int[] A, int ini, int end) {
        if (ini < 0) {
            ini = 0;
        }
        if (end > A.length) {
            end = A.length;
        }
        int retVal = 0;
        for (int i = ini; i < end; i++) {
            retVal += A[i];
        }
        return retVal;
    }

    public static int arrayMax(int[] A, int ini, int end, int defaultValue) {
        if (ini < 0) {
            ini = 0;
        }
        if (end > A.length) {
            end = A.length;
        }
        if (end < ini) {
            return defaultValue;
        }
        int retVal = A[ini];
        for (int i = ini + 1; i < end; i++) {
            if (A[i] > retVal) {
                retVal = A[i];
            }
        }
        return retVal;
    }

    public static boolean java8contains(int[] A, int n) {
        return IntStream.of(A).anyMatch(x -> x == n);
    }

    public static int java8sum(int[] A) {
        return IntStream.of(A).reduce(0, (a, b) -> a + b);
    }

    public static <T> void printMatrix(T[][] M) {
        int i, j;
        for (i = 0; i < M.length; i++) {
            for (j = 0; j < M[0].length; j++) {
                System.out.print(M[i][j] + ", ");
            }
            System.out.println(" ");
        }
    }

    public static void printArr(int[] arr) {
        printArr(arr, 0, arr.length);
    }

    public static void printArr(int[] arr, int ini, int end) {
        String retVal = "[";
        for (int i = 0; i < ini; i++) {
            retVal += "_,";
        }
        for (int i = ini; i < end; i++) {
            retVal += arr[i] + ",";
        }
        for (int i = end; i < arr.length; i++) {
            retVal += "_,";
        }
        retVal = retVal.substring(0, retVal.length() - 1);
        System.out.println(retVal + "]");
    }

    /**
     * @param arr
     * @param <T>
     */
    public static <T> void printArr(T[] arr) {
        int ini = 0;
        int end = arr.length;
        if (end == 0) {
            System.out.println("[]");
            return;
        }
        String retVal = "[";
        for (int i = ini; i < end; i++) {
            retVal += arr[i] + ",";
        }
        retVal = retVal.substring(0, retVal.length() - 1);
        System.out.println(retVal + "]");
    }

    /**
     * Lists 2^n - 1 subsets(skips empty set)
     *
     * @param A
     * @return
     */
    public static int[][] enumerateSubsets(int[] A) {
        int n = A.length;
        int size = (int) Math.pow(2, n);
        int[][] retVal = new int[size][];
        if (n == 0) {
            return retVal;
        }
        for (Integer i = 1; i <= size; i++) {
            String str = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(' ', '0');
            int j = 0;
            int k = 0;
            retVal[i - 1] = new int[numberOfOnes(str)];
            while (j < str.length()) {
                int digit = Integer.parseInt(str.substring(j, j + 1));
                if (digit != 0) {
                    retVal[i - 1][k++] = A[j];
                }
                j++;
            }
        }
        return retVal;
    }

    public static int[][] enumerateSubsetsSizeK(int[] A, int k) {
        int n = A.length;
        int nAllSubsets = (int) Math.pow(2, n);
        int size = (int) NumberUtils.binomial(n, k);
        int[][] retVal = new int[size][];
        if (n == 0) {
            return retVal;
        }
        int j = 0;
        for (Integer i = 0; i <= nAllSubsets; i++) {
            String str = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(' ', '0');
            if (numberOfOnes(str) != k) {
                continue;
            }
            retVal[j] = new int[k];
            int z = 0;
            int y = 0;
            while (z < str.length()) {
                int digit = Integer.parseInt(str.substring(z, z + 1));
                if (digit != 0) {
                    retVal[j][y++] = A[z];
                }
                z++;
            }
            j++;
        }
        return retVal;
    }

    public static void main(String[] args) {
    }
}
