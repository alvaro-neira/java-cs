package com.alvaroneira.questions;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by aneira on 3/13/17.
 * <p>
 * REAL Google Interview
 * <p>
 * Def: an element is popular if it repeats more than 1/4 of the number of elements of the list
 * Create a function to return any popular number in a list or null in case that there is no popular number.
 * The list is SORTED
 * <p>
 * Examples:
 * [ 0 0 1 1 1 2 2 3 4 5 ] -> 1
 * [ 0 0 1 1 1 2 2 2 3 4 5] -> 1 or 2
 * [ 0 1 2 3 4 5 6 7] -> null
 * [ 0 0 1 1 2 2 3 3] -> null
 */

public class PopularNumber {
    public static final int DIV = 8;

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 3, 3, 4, 5, 6, 6, 7, 7, 7};
        assert findPopular(arr1) == null;
        int[] arr5 = {1, 2, 3, 3, 3, 4, 5, 6, 6, 7, 7, 7, 7};
        assert findPopular(arr5) == 7;
        int[] arr2 = {1, 2, 3, 3, 3, 4, 5, 6, 6, 7, 7};
        assert findPopular(arr2) == 3;
        int[] arr3 = {1, 2, 3, 4, 5, 6, 7, 8};
        assert findPopular(arr3) == null;
        int[] arr4 = {1, 1, 2, 2, 3, 3, 4, 4};
        assert findPopular(arr4) == null;
        int[] arr6 = {-1000, -1000, -1000, -1000, -1000, -1000, -1000, -1000};
        assert findPopular(arr6) == -1000;
        int n;
        n = 1000;
        int[] arr7 = new int[n];
        for (int i = 0; i < n; i++) {
            arr7[i] = -1000000;
        }
        assert findPopular(arr7) == -1000000;

        int[] arr8 = new int[n];
        for (int i = 0; i < n; i++) {
            arr8[i] = (int) (Math.random() * 600.0);
        }
        for (int i = 0; i < 250; i++) {
            arr8[i] = 666;
        }
        Arrays.sort(arr8);
        assert findPopular(arr8) == null;
        int[] arr9 = new int[n];
        for (int i = 0; i < n; i++) {
            arr9[i] = (int) (Math.random() * 600.0);
        }
        for (int i = 0; i < 251; i++) {
            arr9[i] = 666;
        }
        Arrays.sort(arr9);
        assert findPopular(arr9) == 666;
    }

    /**
     * Sub problem: find how many x's are there in this sorted array
     */
    public static int howManyInSorted(int[] arr, int x, int ini, int end) {
        if (arr[end] < x) {
            return 0;
        }
        if (arr[ini] > x) {
            return 0;
        }
        if (arr[ini] == x && arr[end] == x) {
            return end + 1 - ini;
        }
        int pivot = ini + (end - ini) / 2;
        int wa1 = howManyInSorted(arr, x, ini, pivot);
        int wa2 = howManyInSorted(arr, x, (int) pivot + 1, end);
        return wa1 + wa2;
    }

    public static Integer findPopular(int[] arr) {
        if (arr.length < DIV) {
            System.err.println("Small arrays still not implemented");
            System.exit(-1);
        }
        int subListLength = arr.length / DIV;
        int reminder = arr.length % DIV;
        HashMap<Integer, Integer> subMaxes = new HashMap<>(DIV);
        HashMap<Integer, Integer> subMins = new HashMap<>(DIV);
        int pos = 0;
        for (int i = 0; i < DIV; i++) {
//            System.out.println("pos="+pos);
            int len = subListLength;
            if (reminder > 0) {
                len++;
                reminder--;
            }
            int subMax = arr[pos + len - 1];
            int subMin = arr[pos];
            int qMax = subMaxes.containsKey(subMax) ? subMaxes.get(subMax) + 1 : 1;
            int qMin = subMins.containsKey(subMin) ? subMins.get(subMin) + 1 : 1;
            if (qMax >= 2) {
//                System.out.println("posible candidate (by max): " + subMax);
                int nOcurrences = howManyInSorted(arr, subMax, 0, arr.length - 1);
                if (nOcurrences * 1.0 > arr.length * 2.0 / DIV) {
                    return subMax;
                }
            }
            if (qMin >= 2) {
//                System.out.println("posible candidate (by min): " + subMin);
                int nOcurrences = howManyInSorted(arr, subMin, 0, arr.length - 1);
                if (nOcurrences * 1.0 > arr.length * 2.0 / DIV) {
                    return subMin;
                }
            }
            subMaxes.put(subMax, qMax);
            subMins.put(subMin, qMin);
            pos += len;
        }
//        System.out.println(subMaxes);
//        System.out.println(subMins);
        return null;
    }
}
