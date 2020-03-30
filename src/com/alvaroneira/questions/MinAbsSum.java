package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

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
    public int solution(int[] A) {
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

    public static void main(String[] args) {
        MinAbsSum mas = new MinAbsSum();
        Assert.assertEquals(0, mas.solution(new int[]{}));
        Assert.assertEquals(1, mas.solution(new int[]{1}));
        Assert.assertEquals(0, mas.solution(new int[]{1, 5, 2, -2}));
        Assert.assertEquals(100, mas.solution(new int[]{100}));
        Assert.assertEquals(0, mas.solution(new int[]{100, 100}));
        Assert.assertEquals(100, mas.solution(new int[]{100, 100, -100}));
        Assert.assertEquals(82, mas.solution(new int[]{91, 92, 93, 94, 95, 96, 97}));
        Assert.assertEquals(0, mas.solution(new int[]{91, 92, 93, 94, 95, 96, 97, 100})); //{1, -1, 1, -1, 1, -1, -1, 1}
        Assert.assertEquals(58, mas.solution(new int[]{91, 91, 91, 92, 92, 92, 93, 93, 93, 94, 94, 94, 95, 95, 95, 96, 96, 96, 97, 97, 97}));
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
