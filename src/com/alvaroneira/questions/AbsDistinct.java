package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.ArrayDeque;

import static java.lang.Math.abs;

/**
 *Tasks Details
 * Easy
 * 1. AbsDistinct
 * Compute number of distinct absolute values of sorted array elements.
 * Task Score
 * 21%
 * Correctness
 * 27%
 * Performance
 * 0%
 * Task description
 * A non-empty array A consisting of N numbers is given. The array is sorted in non-decreasing order. The absolute distinct count of this array is the number of distinct absolute values among the elements of the array.
 *
 * For example, consider array A such that:
 *
 *   A[0] = -5
 *   A[1] = -3
 *   A[2] = -1
 *   A[3] =  0
 *   A[4] =  3
 *   A[5] =  6
 * The absolute distinct count of this array is 5, because there are 5 distinct absolute values among the elements of this array, namely 0, 1, 3, 5 and 6.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given a non-empty array A consisting of N numbers, returns absolute distinct count of array A.
 *
 * For example, given array A such that:
 *
 *   A[0] = -5
 *   A[1] = -3
 *   A[2] = -1
 *   A[3] =  0
 *   A[4] =  3
 *   A[5] =  6
 * the function should return 5, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647];
 * array A is sorted in non-decreasing order.
 */

public class AbsDistinct {
    public int solution(int[] A) {
        // write your code in Java SE 8
        int N = A.length;
        int headPosition = N - 1;
        int tailPosition = 0;

        long currMax = abs(A[headPosition]);
        if (currMax < abs(A[tailPosition])) {
            currMax = abs(A[tailPosition]);
            tailPosition++;
        } else {
            headPosition--;
        }
        int count = 1;
        while (true) {
            if (headPosition - tailPosition < 0) {
                break;
            }
            while (headPosition >= tailPosition && abs(A[tailPosition]) == currMax) {
                tailPosition++;
            }
            while (headPosition >= tailPosition && abs(A[headPosition]) == currMax) {
                headPosition--;
            }
            if (headPosition - tailPosition < 0) {
                break;
            }
            if (abs(A[tailPosition]) > abs(A[headPosition])) {
                currMax = abs(A[tailPosition]);
                tailPosition++;
            } else {
                currMax = abs(A[headPosition]);
                headPosition--;
            }
            count++;
        }
        return count;
    }

    public static long abs(long val) {
        if (val < 0) {
            return (-1) * val;
        }
        return val;
    }

    public static void main(String[] args) {
        AbsDistinct ad = new AbsDistinct();
        Assert.assertEquals(5, ad.solution(new int[]{-5, -3, -1, 0, 3, 6}));
        Assert.assertEquals(1, ad.solution(new int[]{100}));
        Assert.assertEquals(1, ad.solution(new int[]{100, 100}));
        Assert.assertEquals(1, ad.solution(new int[]{-2147483648}));
        Assert.assertEquals(2, ad.solution(new int[]{-2147483648, -10}));
        Assert.assertEquals(3, ad.solution(new int[]{-2147483648, -1, 0, 1}));
        Assert.assertEquals(3, ad.solution(new int[]{-2, -1, 0, 1}));
    }
}
