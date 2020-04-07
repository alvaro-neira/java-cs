package com.alvaroneira.utils;

import java.util.*;
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

    /**
     * NOT optimal
     * For N elements, you should get 2^N subsets, but this produces repetitions,
     * which are then avoided with a HashSet
     *
     * @param set
     * @param output
     */
    public static void enumerateSubsets(HashSet<Integer> set, HashSet<String> output) {
        if (set.size() == 0) {
            return;
        }
        Iterator<Integer> iterator1 = set.iterator();
        String str = "";
        while (iterator1.hasNext()) {
            Integer elem = iterator1.next();
            str += elem + ",";
        }
        output.add(str);
        Iterator<Integer> iterator2 = set.iterator();
        while (iterator2.hasNext()) {
            Integer elem = iterator2.next();
            HashSet<Integer> set2 = (HashSet<Integer>) set.clone();
            set2.remove(elem);
            enumerateSubsets(set2, output);
        }
    }

    public static int[][] enumerateSubsets(int[] A) {
        int n = A.length;
        int[][] retVal = new int[(int) Math.pow(2, n) - 1][];
        HashSet<Integer> set = new HashSet();
        for (int i = 0; i < A.length; i++) {
            set.add(A[i]);
        }

        HashSet<String> output = new HashSet();
        enumerateSubsets(set, output);
        Iterator<String> iterator = output.iterator();
        int j = 0;
        while (iterator.hasNext()) {
            retVal[j++] = Arrays.asList(iterator.next().split(","))
                    .stream()
                    .map(String::trim)
                    .mapToInt(Integer::parseInt).toArray();
        }
        return retVal;
    }

    public static void printHashSet(HashSet<Integer> hs) {
        Iterator<Integer> itr = hs.iterator();
        System.out.println();
        while (itr.hasNext()) {
            Integer sum = itr.next();
            System.out.println(sum);
        }
    }

    public static void main(String[] args) {
        int[][] subsets = enumerateSubsets(new int[]{0, 1, 2, 3, 4});
        for (int i = 0; i < subsets.length; i++) {
            System.out.println(arr2str(subsets[i]));
        }
    }
}
