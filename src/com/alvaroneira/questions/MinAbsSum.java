package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.HashSet;
import java.util.Iterator;

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
     * The time complexity of the above solution is O(N^2  * M), since S = O(N * M).
     *
     * 2: Golden solution O(N*M^2)
     * -------------------
     * Notice that the range of numbers is quite small (maximum 100). Hence, there must be a lot
     * of duplicated numbers. Let counti denote the number of occurrences of the value i. We can
     * improve the previous solution by processing all occurrences of the same value at once. First
     * we calculate values counti
     * . Then we create array dp such that:
     * • dpj = −1 if we cannot get the sum j,
     * • dpj ­ 0 if we can get sum j.
     * Initially, dpj = −1 for all of j (except dp0 = 0). Then we scan through all the values appearing
     * in A; we consider all a such that counta > 0.
     * For every such a we update dp that dpj denotes how many values a remain (maximally)
     * after achieving sum j. Note that if the previous value at dpj ­ 0 then we can set dpj = counta
     * as no value a is needed to obtain the sum j. Otherwise we must obtain sum j − a first and
     * then use a number a to get sum j. In such a situation dpj = dpj−a − 1.
     * Using this algorithm, we can mark all the sum values and choose the best one (closest to
     * half of S).
     *
     * def golden_min_abs_sum(A):
     *     N = len(A)
     *     M = 0
     *     for i in xrange(N):
     *         A[i] = abs(A[i])
     *         M = max(A[i], M)
     *     S = sum(A)
     *     count = [0] * (M + 1)
     *     for i in xrange(N):
     *         count[A[i]] += 1
     *     dp = [-1] * (S + 1)
     *     dp[0] = 0
     *     for a in xrange(1, M + 1):
     *         if count[a] > 0:
     *             for j in xrange(S):
     *                 if dp[j] >= 0:
     *                     dp[j] = count[a]
     *                 elif (j >= a and dp[j - a] > 0):
     *                     dp[j] = dp[j - a] - 1
     *     result = S
     *     for i in xrange(S // 2 + 1):
     *         if dp[i] >= 0:
     *             result = min(result, S - 2 * i)
     *     return result
     *
     * The time complexity of the above solution is O(N*M^2), where M is the maximal element,
     * since S = O(N · M) and there are at most M different values in A.
     */

    public int goldenMinAbsSum(int[] A) {
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

        int[] count = new int[M + 1];
        for (int i = 0; i < N; i++) {
            count[A[i]] += 1;
        }

        int[] dp = new int[S + 1];
        for (int k = 1; k <= S; k++) {
            dp[k] = -1;
        }
        for (int a = 1; a <= M; a++) {
            if (count[a] > 0) {
                for (int j = 0; j < S; j++) {
                    if (dp[j] >= 0) {
                        dp[j] = count[a];
                    } else if (j >= a && dp[j - a] > 0) {
                        dp[j] = dp[j - a] - 1;
                    }
                }
            }
        }

        int result = S;
        for (int i = 0; i <= S / 2; i++) {
            if (dp[i] >= 0) {
                result = Math.min(result, S - 2 * i);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        MinAbsSum mas = new MinAbsSum();
        Assert.assertEquals(0, mas.goldenMinAbsSum(new int[]{}));
        Assert.assertEquals(1, mas.goldenMinAbsSum(new int[]{1}));
        Assert.assertEquals(100, mas.goldenMinAbsSum(new int[]{100}));
        Assert.assertEquals(5, mas.goldenMinAbsSum(new int[]{-6, 5, -6}));
        Assert.assertEquals(0, mas.goldenMinAbsSum(new int[]{1, 5, 2, -2}));
        Assert.assertEquals(0, mas.goldenMinAbsSum(new int[]{100, 100}));
        Assert.assertEquals(100, mas.goldenMinAbsSum(new int[]{100, 100, -100}));
        Assert.assertEquals(82, mas.goldenMinAbsSum(new int[]{91, 92, 93, 94, 95, 96, 97}));
        Assert.assertEquals(0, mas.goldenMinAbsSum(new int[]{91, 92, 93, 94, 95, 96, 97, 100})); //{1, -1, 1, -1, 1, -1, -1, 1}
        Assert.assertEquals(58, mas.goldenMinAbsSum(new int[]{91, 91, 92, 92, 92, 93, 93, 93, 94, 94, 94, 95, 95, 95, 96, 96, 96, 97, 97, 97, 91}));
    }

    public int slowMinAbsSum(int[] A) {
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
        HashSet<Integer> SUM_EXISTS = new HashSet<>();
        HashSet<Integer> REMINDER = new HashSet<>();
        REMINDER.add(S);

        //j = 0
        REMINDER.add(S - A[0]);

        for (int j = 1; j < N; j++) {
            for (int i = 0; i <= S; i++) {
                if (REMINDER.contains(i) && A[j] <= i) {
                    SUM_EXISTS.add(S - i + A[j]);
                    REMINDER.add(i - A[j]);
                }
            }
        }
        int result = S;
        for (Integer i : SUM_EXISTS) {
            if (i <= S / 2) {
                result = Math.min(result, S - 2 * i);
            }
        }
        return result;
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
            if (A[j] > maxAbs) {
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

    public static void printHashSet(HashSet<Integer> hs) {
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
