package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;

/**
 * Tasks Details
 * Easy
 * 1. CountDistinctSlices
 * Count the number of distinct slices (containing only unique numbers).
 *
 * Task description
 * An integer M and a non-empty array A consisting of N non-negative integers are given.
 * All integers in array A are less than or equal to M.
 *
 * A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A.
 * The slice consists of the elements A[P], A[P + 1], ..., A[Q].
 * A distinct slice is a slice consisting of only unique numbers.
 * That is, no individual number occurs more than once in the slice.
 *
 * For example, consider integer M = 6 and array A such that:
 *
 *     A[0] = 3
 *     A[1] = 4
 *     A[2] = 5
 *     A[3] = 5
 *     A[4] = 2
 * There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
 *
 * The goal is to calculate the number of distinct slices.
 *
 * Write a function:
 *
 * class Solution { public int solution(int M, int[] A); }
 *
 * that, given an integer M and a non-empty array A consisting of N integers, returns the number of distinct slices.
 *
 * If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.
 *
 * For example, given integer M = 6 and array A such that:
 *
 *     A[0] = 3
 *     A[1] = 4
 *     A[2] = 5
 *     A[3] = 5
 *     A[4] = 2
 * the function should return 9, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * M is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [0..M].
 */
public class CountDistinctSlices {
    public static int MAX = 1000000000;

    public int solution(int M, int[] A) {
        int N = A.length;
        long count = 0;
        int tail = 0;
        while (tail < N) {
            HashMap<Integer, Integer> hMap = new HashMap();
            hMap.put(A[tail], tail);
            int head = tail + 1;
            while (head < N && !hMap.containsKey(A[head])) {
                hMap.put(A[head], head);
                head++;
            }
            long diff = head - tail;
            count += diff * (diff + 1) / 2;
            if (head < N && head - tail > 1 && hMap.containsKey(A[head])) {
                diff = head - hMap.get(A[head]) - 1;
                head = hMap.get(A[head]) + 1;
                count -= (diff) * (diff + 1) / 2;
            }
            if (count > MAX) {
                return MAX;
            }
            tail = head;
        }
        return (int) count;
    }

    public int largeTest2(int N, int M) {
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = i;
        }
        return solution(M, A);
    }

    public static void main(String[] args) {
        CountDistinctSlices cds = new CountDistinctSlices();
        Assert.assertEquals(5, cds.solution(11, new int[]{2, 3, 2}));
        Assert.assertEquals(19, cds.solution(11, new int[]{9, 2, 2, 2, 2, 1, 2, 13, 13, 13, 13, 13, 9}));
        Assert.assertEquals(6, cds.solution(11, new int[]{2, 1, 2, 2}));
        Assert.assertEquals(7, cds.solution(11, new int[]{2, 1, 2, 1}));
        Assert.assertEquals(12, cds.solution(11, new int[]{2, 1, 0, 2, 1}));
        Assert.assertEquals(11, cds.solution(11, new int[]{2, 1, 0, 1, 2}));
        Assert.assertEquals(12, cds.solution(11, new int[]{2, 1, 0, 2, 1}));
        Assert.assertEquals(21, cds.solution(11, new int[]{5, 1, 2, 3, 4, 5, 5}));
        Assert.assertEquals(24, cds.solution(11, new int[]{3, 2, 1, 0, 1, 2, 3, 4}));
        Assert.assertEquals(1, cds.solution(11, new int[]{3}));
        Assert.assertEquals(3, cds.solution(11, new int[]{2, 3}));
        Assert.assertEquals(4, cds.solution(11, new int[]{2, 3, 3}));
        Assert.assertEquals(4, cds.solution(11, new int[]{2, 2, 3}));
        Assert.assertEquals(15, cds.solution(11, new int[]{1, 2, 3, 4, 5}));
        Assert.assertEquals(5, cds.solution(11, new int[]{13, 13, 13, 13, 13}));
        Assert.assertEquals(9, cds.solution(11, new int[]{3, 4, 5, 5, 2}));
        Assert.assertEquals(7, cds.solution(11, new int[]{2, 13, 13, 13, 13, 13}));
        Assert.assertEquals(7, cds.solution(11, new int[]{13, 13, 13, 13, 13, 2}));
        Assert.assertEquals(9, cds.solution(11, new int[]{2, 13, 13, 13, 13, 13, 2}));
        Assert.assertEquals(10, cds.solution(11, new int[]{2, 2, 2, 2, 13, 13, 13, 13, 13}));
        Assert.assertEquals(15, cds.solution(11, new int[]{2, 2, 2, 2, 1, 1, 1, 1, 13, 13, 13, 13, 13}));
        Assert.assertEquals(8, cds.solution(11, new int[]{11, 11, 22, 22, 33, 33}));
        Assert.assertEquals(13, cds.solution(11, new int[]{2, 2, 2, 2, 1, 13, 13, 13, 13, 13}));
        Assert.assertEquals(15, cds.solution(11, new int[]{2, 2, 2, 2, 1, 13, 13, 13, 13, 13, 9}));
        Assert.assertEquals(17, cds.solution(11, new int[]{9, 2, 2, 2, 2, 1, 13, 13, 13, 13, 13, 9}));
        Assert.assertEquals(15, cds.solution(11, new int[]{1, 2, 3, 4, 5}));
        Assert.assertEquals(19, cds.solution(11, new int[]{9, 2, 2, 2, 2, 1, 2, 13, 13, 13, 13, 13, 9}));
        Assert.assertEquals(12, cds.solution(11, new int[]{2, 1, 0, 0, 1, 2}));
        Assert.assertEquals(16, cds.solution(11, new int[]{1, 2, 3, 4, 5, 5}));
        Assert.assertEquals(MAX, cds.largeTest2(100000, 100000));
    }

    /**
     * 40% performance
     *
     * @param M
     * @param A
     * @return
     */
    public int solutionOn2(int M, int[] A) {
        int N = A.length;
        int count = 0;
        int tailPos = 0;
        int headPos = 1;
        HashSet<Integer> hSet = new HashSet();
        while (tailPos < N) {
            hSet.add(A[headPos - 1]);
            ArrayUtils.printArr(A, tailPos, headPos);
            count++;
            while (headPos < N && !hSet.contains(A[headPos])) {
                headPos++;
                hSet.add(A[headPos - 1]);
                ArrayUtils.printArr(A, tailPos, headPos);
                count++;
            }
            hSet = new HashSet();
            tailPos++;
            headPos = tailPos + 1;
        }
        return count;
    }
}
