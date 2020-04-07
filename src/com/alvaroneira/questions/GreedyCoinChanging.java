package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * https://codility.com/media/train/14-GreedyAlgorithms.pdf
 *
 * The Coin Changing problem
 * For a given set of denominations, you are asked to find the minimum number of coins with
 * which a given amount of money can be paid. That problem can be approached by a greedy
 * algorithm that always selects the largest denomination not exceeding the remaining amount
 * of money to be paid. As long as the remaining amount is greater than zero, the process is
 * repeated.
 * A correct algorithm should always return the minimum number of coins. It turns out
 * that the greedy algorithm is correct for only some denomination selections, but not for all.
 * For example, for coins of values 1, 2 and 5 the algorithm returns the optimal number of
 * coins for each amount of money, but for coins of values 1, 3 and 4 the algorithm may return
 * a suboptimal result. An amount of 6 will be paid with three coins: 4, 1 and 1 by using the
 * greedy algorithm. The optimal number of coins is actually only two: 3 and 3.
 * Consider n denominations 0 < m0 � m1 � . . . � mn−1 and the amount k to be paid.
 *
 *  The greedy algorithm for finding change:
 * def greedyCoinChanging(M, k):
 *      n = len(M)
 *      result = []
 *      for i in xrange(n - 1, -1, -1):
 *         result += [(M[i], k // M[i])]
 *         k %= M[i]
 *      return result
 *
 * The function returns the list of pairs: denomination, number of coins. The time complexity
 * of the above algorithm is O(n) as the number of coins is added once for every denomination.
 */

public class GreedyCoinChanging {
    public HashSet<int[]> greedyCoinChanging(int[] M, int k) {
        int n = M.length;
        HashSet<int[]> result = new HashSet();
        for (int i = n - 1; i >= 0; i--) {
            result.add(new int[]{M[i], k / M[i]}); //{<denomination>, <number of coins>}
            k %= M[i];
        }
        System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        GreedyCoinChanging gcc = new GreedyCoinChanging();
        gcc.greedyCoinChanging(new int[]{1, 2, 5}, 6); //Optimal
        gcc.greedyCoinChanging(new int[]{1, 3, 4}, 6); //Not Optimal

    }
}
