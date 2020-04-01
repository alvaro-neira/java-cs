package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import static com.alvaroneira.utils.ArrayUtils.printMatrix;

/**

 For a given array A of N integers and a sequence S of N integers from the set {−1, 1}, we define val(A, S) as follows:

 val(A, S) = |sum{ A[i]*S[i] for i = 0..N−1 }|

 (Assume that the sum of zero elements equals zero.)

 For a given array A, we are looking for such a sequence S that minimizes val(A,S).

 Write a function:

 class Solution { public int solution(int[] A); }

 that, given an array A of N integers, computes the minimum value of val(A,S) from all possible values of val(A,S) for all possible sequences S of N integers from the set {−1, 1}.

 For example, given array:

 A[0] =  1
 A[1] =  5
 A[2] =  2
 A[3] = -2
 your function should return 0, since for S = [−1, 1, −1, 1], val(A, S) = 0, which is the minimum possible value.

 Write an efficient algorithm for the following assumptions:

 N is an integer within the range [0..20,000];
 each element of array A is an integer within the range [−100..100].

 */
public class MinAbsSum {

    /**
     *
     * https://codility.com/media/train/solution-min-abs-sum.pdf
     *
     * We show how the Codility Challenge codenamed Delta-2011 can be solved. You can still give
     * it a try, but no certificate will be granted. The problem asks you to find the lowest absolute
     * sum of elements of the given array or their negatives.
     * Since we can arbitrarily choose to take the element or its negative, we can simplify the
     * problem and replace each number with its absolute value. Then the problem becomes dividing
     * the numbers into two groups and making the difference between the sums of the two groups
     * as small as possible. It is a classic dynamic programming problem.
     * Assume the sum of absolute values of all the numbers is S. We want to choose some of
     * the numbers (absolute values) to make their sum as large as possible without exceeding S
     * 2
     * .
     * Why? Let P be the sum of the first group, Q be the sum of the other group and P < Q. We
     * have P ¬
     * S
     * 2 ¬ Q and Q + P = S. The larger is P, the smaller is Q and the difference Q − P.
     * Hence, the largest possible P ¬
     * S
     * 2
     * gives the optimal result. Let M be the maximal element
     * in the given array A. We create an array dp of size S.
     * Slow solution O(N2
     * · M)
     * Let dpi equal 1 if it is possible to achieve the sum of i using elements of A, and 0 otherwise.
     * Initially dpi = 0 for all of i (except dp0 = 1). For every successive element in A we update the
     * array taking this element into account. We simply go through all the cells, starting from the
     * top, and if dpi = 1 then we also set dpi+Aj
     * to 1. The direction in which array dp is processed
     * is important, since each element of A can be used only once. After computing the array dp,
     * P is the largest index such that P ¬
     * S
     * 2
     * and dpP = 1.
     *
     * 1: Slow solution.
     *    ------------
     * def slow_min_abs_sum(A):
     *         N = len(A)
     *         M = 0
     *         for i in xrange(N):
     *             A[i] = abs(A[i])
     *             M = max(A[i], M)
     *         S = sum(A)
     *         dp = [0] * (S + 1)
     *         dp[0] = 1
     *         for j in xrange(N):
     *             for i in xrange(S, -1, -1):
     *                 if (dp[i] == 1) and (i + A[j] <= S):
     *                     dp[i + A[j]] = 1
     *         result = S
     *         for i in xrange(S // 2 + 1):
     *         if dp[i] == 1:
     *              result = min(result, S - 2 * i)
     *         return result
     *
     */

    public int solution(int[] A) {
        int N = A.length;
        if (N == 0) {
            return 0;
        }
        int M = 0;
        int S = 0;
        for (int i = 0; i < N; i++) {
            A[i] = Math.abs(A[i]);
            M = Math.max(A[i], M);
            S += A[i];
        }
        ArrayList<Boolean> SUM_EXISTS = new ArrayList<>();
        ArrayList<Boolean> REMAINDER = new ArrayList<>();

        for (int k = 0; k <= S; k++) {
            SUM_EXISTS.add(false);
            REMAINDER.add(false);
        }
        SUM_EXISTS.set(0, true);
        SUM_EXISTS.set(A[0], true);
        REMAINDER.add(S, true);
        REMAINDER.add(S - A[0], true);

        for (int j = 1; j < N; j++) {
            for (int i = 0; i <= S; i++) {
                if (SUM_EXISTS.get(S - i) || REMAINDER.get(i) && (S - i + A[j]) <= S) {
                    REMAINDER.set(i - A[j], true);
                    SUM_EXISTS.set(S - i + A[j], true);
                }
            }
        }
        int result = S;
        for (int i = 0; i < S / 2 + 1; i++) {
            if (SUM_EXISTS.get(i)) {
                result = Math.min(result, S - 2 * i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        MinAbsSum mas = new MinAbsSum();
        Assert.assertEquals(0, mas.solution(new int[]{}));
        Assert.assertEquals(1, mas.solution(new int[]{1}));
        Assert.assertEquals(100, mas.solution(new int[]{100}));
        Assert.assertEquals(5, mas.solution(new int[]{-6,5,-6}));
        Assert.assertEquals(0, mas.solution(new int[]{1, 5, 2, -2}));
        Assert.assertEquals(0, mas.solution(new int[]{100, 100}));
        Assert.assertEquals(100, mas.solution(new int[]{100, 100, -100}));
        Assert.assertEquals(82, mas.solution(new int[]{91, 92, 93, 94, 95, 96, 97}));
        Assert.assertEquals(0, mas.solution(new int[]{91, 92, 93, 94, 95, 96, 97, 100})); //{1, -1, 1, -1, 1, -1, -1, 1}
        Assert.assertEquals(58, mas.solution(new int[]{91, 91, 92, 92, 92, 93, 93, 93, 94, 94, 94, 95, 95, 95, 96, 96, 96, 97, 97, 97, 91}));
    }

    public int solution2(int[] A) {
        // write your code in Java SE 8

        int N = A.length;
        if (N == 0) {
            return 0;
        }
        int maxAbs = Math.abs(A[0]);
        for (int j = 1; j < N; j++) {
            int val = Math.abs(A[j]);
            if(A[j] > maxAbs){
                maxAbs = val;
            }
        }

        //Array of hashes
        HashSet<Integer>[] DP = new HashSet[N];
        sumExists(DP, A, maxAbs);
        int retVal = maxAbs;
        Iterator<Integer> itr = DP[N - 1].iterator();
        while (itr.hasNext()) {
            Integer sum = itr.next();
            if (sum < retVal) {
                retVal = sum;
            }
        }
        return retVal;
    }

    public static void sumExists(HashSet<Integer>[] DP, int[] A, int maxAbs) {
        DP[0] = new HashSet<>();
        DP[0].add(Math.abs(A[0]));
        for (int j = 1; j < A.length; j++) {
            DP[j] = new HashSet<>();
            Iterator<Integer> itr = DP[j - 1].iterator();
            while (itr.hasNext()) {
                Integer previusSum = itr.next();
                int s1 = Math.abs(A[j] + previusSum);
                int s2 = Math.abs(A[j] - previusSum);
                DP[j].add(s1);
                DP[j].add(s2);
            }
        }
    }

    public static void printHashSet(HashSet<Integer> hs){
        Iterator<Integer> itr = hs.iterator();
        System.out.println();
        while (itr.hasNext()) {
            Integer sum = itr.next();
            System.out.println(sum);
        }
    }

    public int solution1(int[] A) {
        // write your code in Java SE 8
        int N = A.length;
        if (N == 0) {
            return 0;
        }
        int DP[][] = new int[(int) Math.pow(2, N - 1)][N];
        int mins[] = new int[N];
        for (int i = 0; i < (int) Math.pow(2, N - 1); i++) {
            DP[i][0] = A[0];
            mins[0] = Math.abs(A[0]);
        }
        for (int j = 1; j < N; j++) {
            DP[0][j] = A[j] + DP[0][j - 1];
            mins[j] = Math.abs(A[j] + DP[0][j - 1]);
        }

        for (int i = 1; i < (int) Math.pow(2, N - 1); i++) {

            for (int j = 1; j < N; j++) {
                int exp = N - j - 1;
                int bool = (i & ((int) Math.pow(2, exp))) >> (exp);
                bool = ((~bool) & 0x01);
                if (bool == 0) {
                    bool = -1;
                }
                int val = A[j] * bool + DP[i][j - 1];
                DP[i][j] = val;
                if (Math.abs(val) < mins[j]) {
                    mins[j] = Math.abs(val);
                }
            }
        }
        return mins[N - 1];
    }
}
