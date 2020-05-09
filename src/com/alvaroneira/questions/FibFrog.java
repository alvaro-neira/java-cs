package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.*;

import static com.alvaroneira.utils.HashMapUtils.printHashMap;
import static com.alvaroneira.utils.HashMapUtils.printHashSet;
import static com.alvaroneira.utils.NumberUtils.fibonacciBinet;
import static com.alvaroneira.utils.ArrayUtils.sum;

/**
 *  FibFrog
 * Count the minimum number of jumps required for a frog to get to the other side of a river.
 *
 * Task description
 * The Fibonacci sequence is defined using the following recursive formula:
 *
 *     F(0) = 0
 *     F(1) = 1
 *     F(M) = F(M - 1) + F(M - 2) if M >= 2
 * A small frog wants to get to the other side of a river. The frog is initially located at one bank of the river (position −1) and wants to get to the other bank (position N). The frog can jump over any distance F(K), where F(K) is the K-th Fibonacci number. Luckily, there are many leaves on the river, and the frog can jump between the leaves, but only in the direction of the bank at position N.
 *
 * The leaves on the river are represented in an array A consisting of N integers. Consecutive elements of array A represent consecutive positions from 0 to N − 1 on the river. Array A contains only 0s and/or 1s:
 *
 * 0 represents a position without a leaf;
 * 1 represents a position containing a leaf.
 * The goal is to count the minimum number of jumps in which the frog can get to the other side of the river (from position −1 to position N). The frog can jump between positions −1 and N (the banks of the river) and every position containing a leaf.
 *
 * For example, consider array A such that:
 *
 *     A[0] = 0
 *     A[1] = 0
 *     A[2] = 0
 *     A[3] = 1
 *     A[4] = 1
 *     A[5] = 0
 *     A[6] = 1
 *     A[7] = 0
 *     A[8] = 0
 *     A[9] = 0
 *     A[10] = 0
 * The frog can make three jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A consisting of N integers, returns the minimum number of jumps by which the frog can get to the other side of the river. If the frog cannot reach the other side of the river, the function should return −1.
 *
 * For example, given:
 *
 *     A[0] = 0
 *     A[1] = 0
 *     A[2] = 0
 *     A[3] = 1
 *     A[4] = 1
 *     A[5] = 0
 *     A[6] = 1
 *     A[7] = 0
 *     A[8] = 0
 *     A[9] = 0
 *     A[10] = 0
 * the function should return 3, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer that can have one of the following values: 0, 1.
 */

class FibFrog {
    public static void recn(HashMap<Integer, Integer> jumps, ArrayList<Integer> fib, int order, HashSet<Integer> fullPositions) {
        HashMap<Integer, Integer> jumpsTmp = new HashMap<Integer, Integer>();
        for (int i = 0; i < fib.size(); i++) {
            int o = order;
            Integer k = 0;
            Iterator<Map.Entry<Integer, Integer>> it = jumps.entrySet().iterator();
            while (k < fib.get(fib.size() - 1) && (it.hasNext() || (o == 1 && --o == 0))) {
                Map.Entry<Integer, Integer> pair = order == 1 ? null : it.next();
                Integer alreadyFib = order == 1 ? 0 : pair.getKey();
                k = alreadyFib + fib.get(i);
                if (fullPositions.contains(k) && !jumps.containsKey(k)) {
                    jumpsTmp.put(k, order);
                }
            }
        }
        jumps.putAll(jumpsTmp);
    }

    public int solution(int[] A) {
        int nLeafPositions = A.length + 2;
        HashSet<Integer> fullPositions = new HashSet<Integer>();
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                fullPositions.add(i + 1);
            }
        }
        fullPositions.add(0);
        fullPositions.add(nLeafPositions - 1);
        ArrayList<Integer> fib = new ArrayList<Integer>();
        int f = fibonacciBinet(1);
        for (int i = 2; f < nLeafPositions; i++) {
            f = fibonacciBinet(i);
            fib.add(f);
        }
        HashMap<Integer, Integer> jumps = new HashMap<Integer, Integer>();
        int order = 1;
        while (!jumps.containsKey(nLeafPositions - 1) && order < nLeafPositions) {
            recn(jumps, fib, order, fullPositions);
            order++;
        }
        if (jumps.containsKey(nLeafPositions - 1)) {
            return jumps.get(nLeafPositions - 1);
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        FibFrog ff = new FibFrog();
        Assert.assertEquals(1, ff.solution(new int[]{}));
        Assert.assertEquals(1, ff.solution(new int[]{1}));
        Assert.assertEquals(1, ff.solution(new int[]{1, 1}));
        Assert.assertEquals(2, ff.solution(new int[]{1, 1, 1}));
        Assert.assertEquals(2, ff.solution(new int[]{1, 1, 1, 1, 1}));
        Assert.assertEquals(3, ff.solution(new int[]{0, 1, 0, 1, 0}));
        Assert.assertEquals(1, ff.solution(new int[]{0, 0}));
        Assert.assertEquals(-1, ff.solution(new int[]{0, 0, 0}));
        Assert.assertEquals(-1, ff.solution(new int[]{0, 0, 0, 1, 0}));
        System.out.println("OK");
    }

//    public int solution1(int[] A) {
//        int n = A.length;
//        int nLeaves = sum(A, 0, n);
//        ArrayList<Integer> fib = new ArrayList<Integer>();
//        int last = 0;
//        Integer f = fibonacciBinet(last);
//        while (f <= n + 1) {
//            fib.add(f);
//            last++;
//            f = fibonacciBinet(last);
//        }
//        HashMap<Integer, Integer> jumps = new HashMap<Integer, Integer>();
//        int retVal = 1;
//        while (!jumps.containsKey(n + 1)) {
//            retVal++;
//            recn(jumps, fib, retVal - 1, A);
//        }
//        if (jumps.get(n + 1) <= Math.max(nLeaves, 1)) {
//            return retVal - 1;
//        } else {
//            return -1;
//        }
//    }
}
