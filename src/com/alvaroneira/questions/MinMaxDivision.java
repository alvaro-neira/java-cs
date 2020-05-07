package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import com.alvaroneira.utils.NumberUtils;
import org.junit.Assert;

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
        return bruteForce(K, M, A);
    }

    public int bruteForce(int K, int M, int[] A) {
        int n = A.length;
        if (n == 1) {
            return A[0];
        }
        if (K == 1) {
            return ArrayUtils.sum(A, 0, n);
        }
        int nWalls = Math.min(K - 1, n - 1);
        int retVal = n * M;

        for (int x = 0; x < Math.pow(n, nWalls); x++) {
            Integer[] walls = NumberUtils.changeDecimalBase(x, n, nWalls);
            int max = 0;
            int lastIdx = 0;
            for (int i = 0; i < nWalls; i++) {
                max = Math.max(max, ArrayUtils.sum(A, lastIdx, walls[i]));
                lastIdx = walls[i];
            }
            max = Math.max(max, ArrayUtils.sum(A, lastIdx, n));
            retVal = Math.min(retVal, max);
        }

        return retVal;
    }

    public static void main(String[] args) {
        MinMaxDivision mmd = new MinMaxDivision();
        Assert.assertEquals(6, mmd.solution(3, 5, new int[]{2, 1, 5, 1, 2, 2, 2}));
        Assert.assertEquals(0, mmd.solution(1, 1, new int[]{0}));
        Assert.assertEquals(8, mmd.solution(1, 10, new int[]{5, 3}));
        System.out.println("OK");
    }
}
