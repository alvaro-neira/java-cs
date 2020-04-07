package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 *  MaxNonoverlappingSegments
 * Find a maximal set of non-overlapping segments.
 *
 * Task description:
 * Located on a line are N segments, numbered from 0 to N − 1, whose positions are given in arrays A and B. For each I (0 ≤ I < N) the position of segment I is from A[I] to B[I] (inclusive). The segments are sorted by their ends, which means that B[K] ≤ B[K + 1] for K such that 0 ≤ K < N − 1.
 *
 * Two segments I and J, such that I ≠ J, are overlapping if they share at least one common point.
 * In other words, A[I] ≤ A[J] ≤ B[I] or A[J] ≤ A[I] ≤ B[J].
 *
 * We say that the set of segments is non-overlapping if it contains no two overlapping segments. The goal is to find the size of a non-overlapping set containing the maximal number of segments.
 *
 * For example, consider arrays A, B such that:
 *
 *     A[0] = 1    B[0] = 5
 *     A[1] = 3    B[1] = 6
 *     A[2] = 7    B[2] = 8
 *     A[3] = 9    B[3] = 9
 *     A[4] = 9    B[4] = 10
 * The segments are shown in the figure below.
 *
 *
 *
 * The size of a non-overlapping set containing a maximal number of segments is 3.
 * For example, possible sets are {0, 2, 3}, {0, 2, 4}, {1, 2, 3} or {1, 2, 4}.
 * There is no non-overlapping set with four segments.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A, int[] B); }
 *
 * that, given two arrays A and B consisting of N integers, returns the size of a non-overlapping set containing a
 * maximal number of segments.
 *
 * For example, given arrays A, B shown above, the function should return 3, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..30,000];
 * each element of arrays A, B is an integer within the range [0..1,000,000,000];
 * A[I] ≤ B[I], for each I (0 ≤ I < N);
 * B[K] ≤ B[K + 1], for each K (0 ≤ K < N − 1).
 */

public class MaxNonoverlappingSegments {
    public int solution(int[] A, int[] B) {
        int n = A.length;
        if (n == 0) {
            return 0;
        }
        int retVal = 1;
        for (int i = n - 1; i >= 0; i--) {
            HashSet<Integer> iSet = new HashSet();
            iSet.add(i);
            for (int j = n - 1; j >= 0; j--) {
                if (i == j) {
                    continue;
                }
                iSet.add(j);
                if (!isValid(A, B, iSet)) {
                    iSet.remove(j);
                } else {
                    if (iSet.size() > retVal) {
//                        ArrayUtils.printHashSet(iSet);
                        retVal = iSet.size();
                    }
                }
            }
        }
        return retVal;
    }

    public int bruteForce(int[] A, int[] B) {
        int n = A.length;
        int[] segments = new int[n];
        for (int i = 0; i < n; i++) {
            segments[i] = i;
        }
        int[][] enumeration = ArrayUtils.enumerateSubsets(segments);
        int retVal = 0;
        for (int i = 0; i < enumeration.length; i++) {
            int setLength = enumeration[i] != null ? enumeration[i].length : 0;
            if (setLength > 0 && isValid(A, B, enumeration[i]) && setLength > retVal) {
                retVal = enumeration[i].length;
            }
        }
        return retVal;
    }

    public static boolean isValid(int[] A, int[] B, int[] segments) {
        for (int i = 0; i < segments.length; i++) {
            for (int j = 0; j < segments.length; j++) {
                if (i == j) {
                    continue;
                }
                if (contains(A, B, segments[i], segments[j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isValid(int[] A, int[] B, HashSet<Integer> segments) {
        Iterator<Integer> iterator1 = segments.iterator();
        while (iterator1.hasNext()) {
            Integer i = iterator1.next();
            Iterator<Integer> iterator2 = segments.iterator();
            while (iterator2.hasNext()) {
                Integer j = iterator2.next();
                if (i == j) {
                    continue;
                }
                if (contains(A, B, i, j)) {
                    return false;
                }
            }
        }

        return true;
    }


    public static boolean contains(int[] A, int[] B, int i, int j) {
        return (A[i] <= A[j] && A[j] <= B[i]) || (A[j] <= A[i] && A[i] <= B[j]);
    }

    public static void main(String[] args) {
        MaxNonoverlappingSegments mns = new MaxNonoverlappingSegments();
        Assert.assertEquals(0, mns.bruteForce(new int[]{}, new int[]{}));
        Assert.assertEquals(1, mns.bruteForce(new int[]{3}, new int[]{4}));
        Assert.assertEquals(3, mns.bruteForce(new int[]{1, 3, 7, 9, 9}, new int[]{5, 6, 8, 9, 10}));
        Assert.assertEquals(1, mns.bruteForce(new int[]{11, 11, 11, 11, 11}, new int[]{13, 13, 13, 13, 13}));
        Assert.assertEquals(4, mns.bruteForce(new int[]{9, 11, 11, 11, 11, 11, 12, 14}, new int[]{10, 11, 11, 11, 11, 11, 12, 15}));
        Assert.assertEquals(4, mns.bruteForce(new int[]{11,15,16,19,10}, new int[]{14,15,18,20,21}));
        Assert.assertEquals(4, mns.bruteForce(new int[]{9,9,9,11,15,16,14,19,10}, new int[]{11,11,11,13,15,18,18,20,21}));
//        fancyTest(mns, 11, 2);
    }

    public static void fancyTest(MaxNonoverlappingSegments mns, int n, int max) {
        int[] A = new int[n];
        int[] B = new int[n];
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        for (int i = 0; i < n; i++) {
            int ini = (int) (Math.random() * max);
            int end = (int) (Math.random() * (max - ini)) + ini;
            ArrayList<Integer> segment = new ArrayList();
            segment.add(ini);
            segment.add(end);
            list.add(segment);
        }
        list.sort((p1, p2) -> p1.get(1) - p2.get(1));
        int j = 0;
        for (ArrayList<Integer> pair : list) {
            System.out.println(pair);
            A[j] = pair.get(0);
            B[j++] = pair.get(1);
        }
//        Assert.assertEquals(mns.bruteForce(A,B),mns.solution(A,B));
        System.out.println(mns.bruteForce(A, B));
    }
}
