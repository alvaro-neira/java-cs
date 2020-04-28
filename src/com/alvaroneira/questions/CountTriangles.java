package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;

import static com.alvaroneira.utils.ArrayUtils.enumerateSubsetsSizeK;

/**
 * CountTriangles
 * Count the number of triangles that can be built from a given set of edges.
 *
 * Task description
 * An array A consisting of N integers is given. A triplet (P, Q, R) is triangular if it is possible to build a triangle with sides of lengths A[P], A[Q] and A[R]. In other words, triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
 *
 * A[P] + A[Q] > A[R],
 * A[Q] + A[R] > A[P],
 * A[R] + A[P] > A[Q].
 * For example, consider array A such that:
 *
 *   A[0] = 10    A[1] = 2    A[2] = 5
 *   A[3] = 1     A[4] = 8    A[5] = 12
 * There are four triangular triplets that can be constructed from elements of this array, namely (0, 2, 4), (0, 2, 5), (0, 4, 5), and (2, 4, 5).
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A consisting of N integers, returns the number of triangular triplets in this array.
 *
 * For example, given array A such that:
 *
 *   A[0] = 10    A[1] = 2    A[2] = 5
 *   A[3] = 1     A[4] = 8    A[5] = 12
 * the function should return 4, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..1,000];
 * each element of array A is an integer within the range [1..1,000,000,000].
 */

public class CountTriangles {
    public int solution(int[] A) {
        // write your code in Java SE 8
        return bruteForce(A);
    }

    public int bruteForce(int[] A) {
        int n = A.length;
        if (n < 3) {
            return 0;
        }
        int[][] triplets = enumerateSubsetsSizeK(A, 3);

        int retVal = 0;
        for (int i = 0; i < triplets.length; i++) {
            int P = triplets[i][0];
            int Q = triplets[i][1];
            int R = triplets[i][2];
            if (P + Q > R && Q + R > P && R + P > Q) {
                retVal++;
            }
        }
        return retVal;
    }

    public static void main(String[] args) {
        CountTriangles ct = new CountTriangles();
        Assert.assertEquals(0, ct.solution(new int[]{}));
        Assert.assertEquals(0, ct.solution(new int[]{3}));
        Assert.assertEquals(0, ct.solution(new int[]{3, 4}));
        Assert.assertEquals(1, ct.solution(new int[]{3, 4, 5}));
        Assert.assertEquals(4, ct.solution(new int[]{3, 4, 5, 6}));
        Assert.assertEquals(4, ct.solution(new int[]{10, 2, 5, 1, 8, 12}));
        Assert.assertEquals(525, ct.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}));
//        Assert.assertEquals(525, ct.solution(new int[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30}));
    }
}
