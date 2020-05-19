package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
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
 *
 * Opitimized Memory
 * def dynamic_coin_changing(C, k):
 *     n = len(C)
 *     dp = [0] + [MAX_INT] * k
 *     for i in xrange(1, n + 1):
 *         for j in xrange(C[i - 1], k + 1):
 *             dp[j] = min(dp[j - C[i - 1]] + 1, dp[j])
 *     return dp
 */
public class CoinChanging {
    public static int MAX_INT = 10000000;

    public int dynamicCoinChangeOptimizedMemory(int[] denominations, int price) {
        int i, j;
        int n = denominations.length;
        int[] DP = new int[price + 1];
        for (j = 1; j <= price; j++) {
            DP[j] = MAX_INT;
        }
        for (i = 1; i <= n; i++) {
            for (j = denominations[i - 1]; j <= price; j++) {
                DP[j] = Math.min(DP[j - denominations[i - 1]] + 1, DP[j]);
            }
        }
        return DP[price];
    }

    /**
     * dynamicCoinChange returns the minimum number of coins required to pay "price"
     */
    public int dynamicCoinChange(int[] denominations, int price) {
        if(price<3){
            return price;
        }
        int n = denominations.length;
        //create two-dimensional array with all zeros
        int[][] DP = new int[n + 1][price + 1];
        int j;
        for (j = 1; j < DP[0].length; j++) {
            DP[0][j] = MAX_INT;
        }
        for (int i = 1; i <= n; i++) {
            for (j = 0; j < denominations[i - 1]; j++) {
                DP[i][j] = DP[i - 1][j];
            }
            for (j = denominations[i - 1]; j <= price; j++) {
                DP[i][j] = Math.min(DP[i][j - denominations[i - 1]] + 1, DP[i - 1][j]);
            }
        }
//        ArrayUtils.printMatrix(DP);
        return DP[n][price];
    }

    public static void main(String[] args) {
        CoinChanging cc = new CoinChanging();
        Assert.assertEquals(2, cc.dynamicCoinChange(new int[]{1, 3, 4}, 6));
        Assert.assertEquals(2, cc.dynamicCoinChange(new int[]{1, 3, 4}, 5));
        Assert.assertEquals(1, cc.dynamicCoinChange(new int[]{1, 3, 4}, 4));
        Assert.assertEquals(1, cc.dynamicCoinChange(new int[]{1, 3, 4}, 3));
        Assert.assertEquals(2, cc.dynamicCoinChange(new int[]{1, 3, 4}, 2));
        Assert.assertEquals(1, cc.dynamicCoinChange(new int[]{1, 3, 4}, 1));
        Assert.assertEquals(0, cc.dynamicCoinChange(new int[]{1, 3, 4}, 0));
    }
}
