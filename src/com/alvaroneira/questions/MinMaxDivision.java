package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import com.alvaroneira.utils.NumberUtils;
import org.junit.Assert;

import static com.alvaroneira.utils.ArrayUtils.printArr;
import static com.alvaroneira.utils.ArrayUtils.sum;

/**
 *  MinMaxDivision
 * Divide array A into K blocks and minimize the largest sum of any block.
 *
 * Task description
 * You are given integers K, M and a non-empty array A consisting of N integers.
 * Every element of the array is not greater than M.
 *
 * You should divide this array into K blocks of consecutive elements.
 * The size of the block is any integer between 0 and N. Every element of the array should belong to some block.
 *
 * The sum of the block from X to Y equals A[X] + A[X + 1] + ... + A[Y]. The sum of empty block equals 0.
 *
 * The large sum is the maximal sum of any block.
 *
 * For example, you are given integers K = 3, M = 5 and array A such that:
 *
 *   A[0] = 2
 *   A[1] = 1
 *   A[2] = 5
 *   A[3] = 1
 *   A[4] = 2
 *   A[5] = 2
 *   A[6] = 2
 * The array can be divided, for example, into the following blocks:
 *
 * [2, 1, 5, 1, 2, 2, 2], [], [] with a large sum of 15;
 * [2], [1, 5, 1, 2], [2, 2] with a large sum of 9;
 * [2, 1, 5], [], [1, 2, 2, 2] with a large sum of 8;
 * [2, 1], [5, 1], [2, 2, 2] with a large sum of 6.
 * The goal is to minimize the large sum. In the above example, 6 is the minimal large sum.
 *
 * Write a function:
 *
 * class Solution { public int solution(int K, int M, int[] A); }
 *
 * that, given integers K, M and a non-empty array A consisting of N integers, returns the minimal large sum.
 *
 * For example, given K = 3, M = 5 and array A such that:
 *
 *   A[0] = 2
 *   A[1] = 1
 *   A[2] = 5
 *   A[3] = 1
 *   A[4] = 2
 *   A[5] = 2
 *   A[6] = 2
 * the function should return 6, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N and K are integers within the range [1..100,000];
 * M is an integer within the range [0..10,000];
 * each element of array A is an integer within the range [0..M].
 */
public class MinMaxDivision {
    public int solution(int K, int M, int[] A) {
        int n = A.length;
        if (n == 1) {
            return A[0];
        }
        int total = 0;
        int maxValue = A[0];
        for (int i = 0; i < n; i++) {
            total += A[i];
            if (A[i] > maxValue) {
                maxValue = A[i];
            }
        }
        if (K == 1) {
            return total;
        }
        if (K > n) {
            return maxValue;
        }
        int end = total;
        int beg = maxValue;
        int result = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if (check(A, mid, K)) {
                end = mid - 1;
                result = mid;
            } else {
                beg = mid + 1;
            }
        }
        return result;
    }

    boolean check(int[] A, int mid, int K) {
        int s = 0;
        for (int i = 0; i < A.length; i++) {
            if (s + A[i] > mid) {
                if (K == 1) {
                    return false;
                }
                K--;
                s = A[i];
            } else {
                s += A[i];
            }
        }
        return s <= mid;
    }

    public static void main(String[] args) {
        MinMaxDivision mmd = new MinMaxDivision();
        Assert.assertEquals(11, mmd.solution(4, 10, new int[]{3, 4, 5, 6, 7, 8}));
        Assert.assertEquals(6, mmd.solution(3, 5, new int[]{2, 1, 5, 1, 2, 2, 2}));
        Assert.assertEquals(0, mmd.solution(1, 1, new int[]{0}));
        Assert.assertEquals(8, mmd.solution(1, 10, new int[]{5, 3}));
        Assert.assertEquals(5, mmd.solution(3, 5, new int[]{5, 3}));
        Assert.assertEquals(5, mmd.solution(2, 5, new int[]{3, 5}));
        Assert.assertEquals(99, mmd.solution(3, 99, new int[]{0, 0, 99, 0, 0}));
        System.out.println("OK");
    }

    public int solution2(int K, int M, int[] A) {
        int n = A.length;
        if (n == 1) {
            return A[0];
        }
        int total = sum(A, 0, n);
        if (K == 1) {
            return total;
        }
        Integer[] indexes = new Integer[K - 1];
        indexes[0] = search(A, 1, n, 0);
        int retVal = M * n;
        while (indexes[0] > 0) {
            int previousSum = sum(A, 0, indexes[0]);
            for (int i = 0; i < K - 2; i++) {
                indexes[i + 1] = search2(A, indexes[i] + 1, n, previousSum);
            }
            printArr(indexes);
            int sum2 = sum(A, 0, indexes[0]);
            for (int j = 1; j < indexes.length; j++) {
                int sum3 = sum(A, indexes[j - 1], indexes[j]);
                if (sum3 > sum2) {
                    sum2 = sum3;
                }
            }
            sum2 = Math.max(sum2, sum(A, indexes[indexes.length - 1], n));
            System.out.println("max=" + sum2);
            retVal = Math.min(retVal, sum2);
            indexes[0]--;
        }

        return retVal;
    }

    public int search(int[] A, int beg, int end, int prev) {
        int searchFrom = beg - 1;
        int result = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if (check(A, mid, searchFrom, prev)) {
                end = mid - 1;
                result = mid;
            } else {
                beg = mid + 1;
            }
        }
        return result;
    }

    public int search2(int[] A, int beg, int end, int prev) {
        int searchFrom = beg - 1;
        int result = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if (check2(A, mid, searchFrom, prev)) {
                end = mid - 1;
                result = mid;
            } else {
                beg = mid + 1;
            }
        }
        return result;
    }

    public boolean check(int[] A, int mid, int ini, int prev) {
        return sum(A, ini, mid) >= sum(A, mid, A.length);
    }

    public boolean check2(int[] A, int mid, int ini, int prev) {
        return sum(A, ini, mid) >= prev;
    }

    public int bruteForce(int K, int M, int[] A) {
        int n = A.length;
        if (n == 1) {
            return A[0];
        }
        if (K == 1) {
            return sum(A, 0, n);
        }
        int nWalls = Math.min(K - 1, n - 1);
        int retVal = n * M;

        for (int x = 0; x < Math.pow(n, nWalls); x++) {
            Integer[] walls = NumberUtils.changeDecimalBase(x, n, nWalls);
            int max = 0;
            int lastIdx = 0;
            for (int i = 0; i < nWalls; i++) {
                max = Math.max(max, sum(A, lastIdx, walls[i]));
                lastIdx = walls[i];
            }
            max = Math.max(max, sum(A, lastIdx, n));
            retVal = Math.min(retVal, max);
        }

        return retVal;
    }
}
