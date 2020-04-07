package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.ArrayDeque;
import java.util.HashSet;

/**
 * https://codility.com/media/train/14-GreedyAlgorithms.pdf
 *
 * Problem: There are n > 0 canoeists weighing respectively 1 � w0 � w1 � . . . � wn−1 � 109.
 * The goal is to seat them in the minimum number of double canoes whose displacement (the
 * maximum load) equals k. You may assume that wi � k.
 */

public class GreedyCanoeists {
    /**
     * Solution A O(n): The task can be solved by using a greedy algorithm. The heaviest canoeist
     * is called fatso. Other canoeists who can be seated with fatso in the canoe are called skinny.
     * All the other remaining canoeists are also called fatsos.
     * The idea is that, for the heaviest fatso, we should find the heaviest skinny who can be
     * seated with him. So, we seat together the heaviest fatso and the heaviest skinny. Let us note
     * that the thinner the heaviest fatso is, the fatter skinny can be. Thus, the division between
     * fatso and skinny will change over time — as the heaviest fatso gets closer to the pool of
     * skinnies.
     * Canoeist in O(n) solution.
     * def greedyCanoeistA(W, k):
     *     N = len(W)
     *     skinny = deque()
     *     fatso = deque()
     *     for i in xrange(N - 1):
     *         if W[i] + W[-1] <= k:
     *             skinny.append(W[i])
     *         else:
     *             fatso.append(W[i])
     *     fatso.append(W[-1])
     *     canoes = 0
     *     while (skinny or fatso):
     *         if len(skinny) > 0:
     *             skinny.pop()
     *         fatso.pop()
     *         canoes += 1
     *         if (not fatso and skinny):
     *             fatso.append(skinny.pop())
     *         while (len(fatso) > 1 and fatso[-1] + fatso[0] <= k):
     *             skinny.append(fatso.popleft())
     *     return canoes
     */

    /**
     * Not fully tested
     * @param W
     * @param k
     * @return
     */
    public int greedyCanoeistA(int[] W, int k) {
        int N = W.length;
        ArrayDeque<Integer> skinny = new ArrayDeque();
        ArrayDeque<Integer> fatso = new ArrayDeque();
        for (int i = 0; i < N - 1; i++) {
            if (W[i] + W[N - 1] <= k) {
                skinny.addLast(W[i]);
            } else {
                fatso.addLast(W[i]);
            }
        }
        fatso.addLast(W[N - 1]);
        int canoes = 0;
        while (!skinny.isEmpty() || !fatso.isEmpty()) {
            if (skinny.size() > 0) {
                skinny.pop();
            }
            fatso.pop();
            canoes += 1;
            if (fatso.isEmpty() && !skinny.isEmpty()) {
                fatso.addLast(skinny.pop());
            }
            while (fatso.size() > 1 && fatso.getFirst() + fatso.getLast() <= k) {
                skinny.addLast(fatso.getFirst());
                fatso.removeFirst();
            }
        }
        return canoes;
    }

    /**
     * The solution for the first canoe is optimal, so the problem can be reduced to seat the
     * remaining canoeists in the minimum number of canoes.
     * The total time complexity of this solution is O(n). The outer while loop performs O(n) steps
     * since in each step one or two canoeists are seated in a canoe. The inner while loop in each
     * step changes a fatso into a skinny. As at the beginning there are O(n) fatsos and with each
     * step at the outer while loop only one skinny become a fatso, the overall total number of steps
     * of the inner while loop has to be O(n).
     * Solution B O(n): The fattest canoeist is seated with the thinnest, as long as their weight is
     * less than or equal to k. If not, the fattest canoeist is seated alone in the canoe.
     *
     * Canoeist in O(n) solution.
     * def greedyCanoeistB(W, k):
     *         canoes = 0
     *         j = 0
     *         i = len(W) - 1
     *         while (i >= j):
     *             if W[i] + W[j] <= k:
     *                 j += 1;
     *             canoes += 1;
     *             i -= 1
     *         return canoes
     *
     * The time complexity is O(n), because with each step of the loop, at least one canoeist is
     * seated.
     */

    public int greedyCanoeistB(int[] weights, int canoeLimit) {
        int nCanoes = 0;
        int j = 0;
        int i = weights.length - 1;
        while (i >= j) {
            if (weights[i] + weights[j] <= canoeLimit) {
                j += 1;
            }
            nCanoes += 1;
            i -= 1;
        }
        return nCanoes;
    }

    public static void main(String[] args) {
        GreedyCanoeists gc = new GreedyCanoeists();
        Assert.assertEquals(1,gc.greedyCanoeistA(new int[]{100}, 200));
        Assert.assertEquals(2,gc.greedyCanoeistA(new int[]{100,50,50,50}, 200));
    }
}
