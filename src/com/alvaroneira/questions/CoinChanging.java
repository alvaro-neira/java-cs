package com.alvaroneira.questions;

import org.junit.Assert;

/**
 * "Dynamic Programming"
 *
 * Python solution for the "Coin Changing Problem":
 * def dynamic_coin_changing(C, k):
 *     n = len(C)
 *     # create two-dimensional array with all zeros
 *     dp = [[0] * (k + 1) for i in xrange(n + 1)]
 *     dp[0] = [0] + [MAX_INT] * k
 *     for i in xrange(1, n + 1):
 *         for j in xrange(C[i - 1]):
 *             dp[i][j] = dp[i - 1][j]
 *         for j in xrange(C[i - 1], k + 1):
 *             dp[i][j] = min(dp[i][j - C[i - 1]] + 1, dp[i - 1][j])
 *     return dp[n]
 */
public class CoinChanging {
    public static int MAX_INT=1000;
    public int dynamicCoinChange(int[] denominations, int price){
        int n=denominations.length;
        //create two-dimensional array with all zeros
        int[][] dp = new int[n+1][price+1];
        int j;
        for(j=1;j<dp[0].length;j++){
            dp[0][j]=MAX_INT;
        }
        for(int i=1;i<=n;i++){
            for(j=0;j<denominations[i-1];j++){
                dp[i][j] = dp[i - 1][j];
            }
            for(j=denominations[i-1];j<=price;j++){
                dp[i][j] = Math.min(dp[i][j - denominations[i - 1]] + 1, dp[i - 1][j]);
            }
        }
        return dp[n][price];
    }
    public static void main(String[] args) {
        CoinChanging cc = new CoinChanging();
        Assert.assertEquals(2,cc.dynamicCoinChange(new int[]{1, 3, 4},6));
    }
}
