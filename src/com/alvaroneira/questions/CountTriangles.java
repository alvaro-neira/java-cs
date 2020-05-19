package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import com.alvaroneira.utils.NumberUtils;
import org.junit.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import static com.alvaroneira.utils.ArrayUtils.enumerateSubsetsSizeK;
import static com.alvaroneira.utils.NumberUtils.numberOfOnes;

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
    public static final int MAXINT = 1000000000;

    /**
     * https://codesays.com/2014/solution-to-count-triangles-by-codility/
     * O(n^2)
     *
     * @param A
     * @return
     */
    public int solution(int[] A) {
        int n = A.length;
        int result = 0;
        Arrays.sort(A);
        for (int first = 0; first < n - 2; first++) {
            int third = first + 2;
            for (int second = first + 1; second < n - 1; second++) {
                while (third < n && A[first] + A[second] > A[third]) {
                    third += 1;
                }
                result += third - second - 1;
            }
        }
        return result;
    }

    /**
     * https://www.martinkysel.com/codility-counttriangles-solution/
     * O(n^2)
     *
     * @param A
     * @return
     */
    public int solutionKysel(int[] A) {
        Arrays.sort(A);
        int triangle_cnt = 0;

        for (int P_idx = 0; P_idx < A.length - 2; P_idx++) {
            int Q_idx = P_idx + 1;
            int R_idx = P_idx + 2;
            while (R_idx < A.length) {
                if (A[P_idx] + A[Q_idx] > A[R_idx]) {
                    triangle_cnt += R_idx - Q_idx;
                    R_idx += 1;
                } else if (Q_idx < R_idx - 1) {
                    Q_idx += 1;
                } else {
                    R_idx += 1;
                    Q_idx += 1;
                }
            }
        }

        return triangle_cnt;
    }

    public static void main(String[] args) {
        CountTriangles ct = new CountTriangles();
        Assert.assertEquals(0, ct.solution(new int[]{}));
        Assert.assertEquals(0, ct.solution(new int[]{3}));
        Assert.assertEquals(0, ct.solution(new int[]{3, 4}));
        Assert.assertEquals(1, ct.solution(new int[]{3, 4, 5}));
        Assert.assertEquals(1, ct.solution(new int[]{5, 3, 3}));
        Assert.assertEquals(4, ct.solution(new int[]{3, 4, 5, 6}));
        Assert.assertEquals(4, ct.solution(new int[]{10, 2, 5, 1, 8, 12}));
        Assert.assertEquals(525, ct.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20}));
        Assert.assertEquals(1925, ct.solution(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30}));
        Assert.assertEquals(0, ct.solution(new int[]{MAXINT}));
        Assert.assertEquals(1, ct.solution(new int[]{MAXINT, MAXINT, MAXINT}));
        Assert.assertEquals(1, ct.solution(new int[]{1, 1, 1}));
        Assert.assertEquals(0, ct.solution(new int[]{1, 1, 2}));
        Assert.assertEquals(3, ct.solution(new int[]{3, 3, 5, 6}));
    }

    public int solutionOn3LittleBetter(int[] origA) {
        Integer[] A = Arrays.stream(origA).boxed().toArray(Integer[]::new);
        int n = A.length;
        if (n < 3) {
            return 0;
        }
        Arrays.sort(A, Collections.reverseOrder());
        int retVal = 0;
        for (int base = 0; base < n - 2; base++) {
            for (int tail = base + 1; tail < n - 1; tail++) {
                int head = n - 1;
                while (A[base] >= A[tail] + A[head] && head >= tail + 1 && tail >= base + 1) {
                    head--;
                }
                retVal += (head - tail);
            }
        }
        return retVal;
    }

    public int solutionOn3(int[] origA) {
        Integer[] A = Arrays.stream(origA).boxed().toArray(Integer[]::new);
        int n = A.length;
        if (n < 3) {
            return 0;
        }
        Arrays.sort(A, Collections.reverseOrder());
        int retVal = 0;
        for (int i = 0; i < n - 2; i++) {
            for (int tail = i + 1; tail < n - 1; tail++) {
                for (int head = tail + 1; head < n; head++) {
                    if (A[i] < A[tail] + A[head]) {
                        retVal++;
                    }
                }
            }
        }
        return retVal;
    }

    public int solution2(int[] A) {
        int n = A.length;
        if (n < 3) {
            return 0;
        }
        int nAllSubsets = (int) Math.pow(2, n);
        int retVal = 0;
        for (Integer i = 0; i <= nAllSubsets; i++) {
            String str = String.format("%" + n + "s", Integer.toBinaryString(i)).replace(' ', '0');
            if (numberOfOnes(str) != 3) {
                continue;
            }
            int[] triplet = new int[3];
            int z = 0;
            int y = 0;
            while (z < str.length()) {
                int digit = Integer.parseInt(str.substring(z, z + 1));
                if (digit != 0) {
                    triplet[y++] = A[z];
                }
                z++;
            }
            int P = triplet[0];
            int Q = triplet[1];
            int R = triplet[2];
            if (P + Q > R && Q + R > P && R + P > Q) {
                retVal++;
            }
        }
        return retVal;
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

}
