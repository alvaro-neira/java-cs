package com.alvaroneira.utils;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

/**
 * Created by aneira on 3/18/17.
 */
public class ArrayUtils {
    public static String arr2str(int[] arr) {
        String retVal = "[";
        for (int elem : arr) {
            retVal += elem + ",";
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

    public static int java8max(Integer... args) {
        List<Integer> listOfIntegers = Arrays.asList(args);
        return listOfIntegers
                .stream()
                .mapToInt(v -> v)
                .max().orElseThrow(NoSuchElementException::new);
    }

    public static void printMatrix(int[][] M) {
        int i, j;
        for (i = 0; i < M.length; i++) {
            for (j = 0; j < M[0].length; j++) {
                System.out.print(M[i][j] + ", ");
            }
            System.out.println(" ");
        }
    }
}
