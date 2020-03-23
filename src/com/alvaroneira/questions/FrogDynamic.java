package com.alvaroneira.questions;

import org.junit.Assert;

/**
 * "Dynamic Programming"
 *
 * Problem: A small frog wants to get from position 0 to k (1 ¬ k ¬ 10 000). The frog can
 * jump over any one of n fixed distances s0, s1, . . . , sn−1 (1 ¬ si ¬ k). The goal is to count the
 * number of different ways in which the frog can jump to position k. To avoid overflow, it is
 * sufficient to return the result modulo q, where q is a given number.
 * We assume that two patterns of jumps are different if, in one pattern, the frog visits
 * a position which is not visited in the other pattern.
 *
 * Python solution for the Frog Problem
 * def frog(S, k, q):
 *     n = len(S)
 *     dp = [1] + [0] * k
 *     for j in xrange(1, k + 1):
 *         for i in xrange(n):
 *             if S[i] <= j:
 *                  dp[j] = (dp[j] + dp[j - S[i]]) % q;
 *     return dp[k]
 */
public class FrogDynamic {
    public static int MAX_INT=10000000;

    public int frog(int[] fixedDistances, int targetPosition, int q){
        int n=fixedDistances.length;
        int[] dp=new int[targetPosition+1];
        dp[0]=1;
        for(int j=1;j<=targetPosition;j++){
            for(int i=0;i<n;i++){
                if(fixedDistances[i] <= j){
                    dp[j] = (dp[j] + dp[j - fixedDistances[i]]) % q;
                }
            }
        }
        return dp[targetPosition];
    }
    public static void main(String[] args) {
        FrogDynamic fd = new FrogDynamic();
        Assert.assertEquals(1,fd.frog(new int[]{3},6, MAX_INT));
    }
}
