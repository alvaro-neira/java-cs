package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import com.alvaroneira.utils.NumberUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

import static com.alvaroneira.utils.ArrayUtils.printArr;

/**
 *  MinAbsSumOfTwo
 * Find the minimal absolute value of a sum of two elements.
 *
 * Task description
 * Let A be a non-empty array consisting of N integers.
 *
 * The abs sum of two for a pair of indices (P, Q) is the absolute value |A[P] + A[Q]|, for 0 ≤ P ≤ Q < N.
 *
 * For example, the following array A:
 *
 *   A[0] =  1
 *   A[1] =  4
 *   A[2] = -3
 * has pairs of indices (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2).
 * The abs sum of two for the pair (0, 0) is A[0] + A[0] = |1 + 1| = 2.
 * The abs sum of two for the pair (0, 1) is A[0] + A[1] = |1 + 4| = 5.
 * The abs sum of two for the pair (0, 2) is A[0] + A[2] = |1 + (−3)| = 2.
 * The abs sum of two for the pair (1, 1) is A[1] + A[1] = |4 + 4| = 8.
 * The abs sum of two for the pair (1, 2) is A[1] + A[2] = |4 + (−3)| = 1.
 * The abs sum of two for the pair (2, 2) is A[2] + A[2] = |(−3) + (−3)| = 6.
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given a non-empty array A consisting of N integers, returns the minimal abs sum of two for any pair of indices in this array.
 *
 * For example, given the following array A:
 *
 *   A[0] =  1
 *   A[1] =  4
 *   A[2] = -3
 * the function should return 1, as explained above.
 *
 * Given array A:
 *
 *   A[0] = -8
 *   A[1] =  4
 *   A[2] =  5
 *   A[3] =-10
 *   A[4] =  3
 * the function should return |(−8) + 5| = 3.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 */
public class MinAbsSumOfTwo {
    public static final int MAXINT = 1000000000;

    public int solution(int[] A) {
        int n = A.length;
        Arrays.sort(A);
        int headPos = n - 1;
        int tailPos = 0;
        int minVal = Math.abs(A[tailPos] + A[headPos]);
        int currVal = minVal;
        while (tailPos + 1 < headPos) {
            int shrinkTail = Math.abs(A[tailPos + 1] + A[headPos]);
            int shrinkHead = Math.abs(A[tailPos] + A[headPos - 1]);
            if (shrinkTail < shrinkHead) {
                currVal = shrinkTail;
                tailPos++;
            } else {
                currVal = shrinkHead;
                headPos--;
            }
            if (currVal < minVal) {
                minVal = currVal;
            }
        }

        minVal = NumberUtils.java8min(currVal, minVal,
                Math.abs(A[headPos] + A[headPos]),
                Math.abs(A[headPos] + A[tailPos]),
                Math.abs(A[tailPos] + A[tailPos]));

        return minVal;
    }

    public int bruteForce(int[] A) {
        int n = A.length;
        int retVal = Math.abs(A[0] + A[0]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int newVal = Math.abs(A[i] + A[j]);
                if (newVal < retVal) {
                    retVal = newVal;
                }
            }
        }
        return retVal;
    }

    public static void main(String[] args) {
        MinAbsSumOfTwo masot = new MinAbsSumOfTwo();
        Assert.assertEquals(6, masot.solution(new int[]{3}));
        Assert.assertEquals(1, masot.solution(new int[]{-2, 3}));
        Assert.assertEquals(1, masot.solution(new int[]{1, 4, -3}));
        Assert.assertEquals(3, masot.solution(new int[]{-8, 4, 5, -10, 3}));
        Assert.assertEquals(2000000000, masot.solution(new int[]{1000000000, 1000000000}));
        Assert.assertEquals(0, masot.solution(new int[]{-1000000000, 1000000000}));
        Assert.assertEquals(2000000000, masot.solution(new int[]{1000000000}));
        Assert.assertEquals(13, masot.solution(new int[]{140, -420, 908, 288, -227, -321, 449, -275, -243, 25}));
//        fancyTest(masot, 10);
        System.out.println("OK");
    }

    public static void fancyTest(MinAbsSumOfTwo masot, int n) {
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = (int) (-MAXINT + Math.random() * MAXINT * 2);
        }
        printArr(A);
        Assert.assertEquals(masot.bruteForce(A), masot.solution(A));
    }
}
