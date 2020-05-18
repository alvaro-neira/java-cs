package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static com.alvaroneira.utils.NumberUtils.fibonacciBinet;

/**
 *  FibFrog
 * Count the minimum number of jumps required for a frog to get to the other side of a river.
 *
 * Task description
 * The Fibonacci sequence is defined using the following recursive formula:
 *
 *     F(0) = 0
 *     F(1) = 1
 *     F(M) = F(M - 1) + F(M - 2) if M >= 2
 * A small frog wants to get to the other side of a river. The frog is initially located at one bank of the river (position −1) and wants to get to the other bank (position N). The frog can jump over any distance F(K), where F(K) is the K-th Fibonacci number. Luckily, there are many leaves on the river, and the frog can jump between the leaves, but only in the direction of the bank at position N.
 *
 * The leaves on the river are represented in an array A consisting of N integers. Consecutive elements of array A represent consecutive positions from 0 to N − 1 on the river. Array A contains only 0s and/or 1s:
 *
 * 0 represents a position without a leaf;
 * 1 represents a position containing a leaf.
 * The goal is to count the minimum number of jumps in which the frog can get to the other side of the river (from position −1 to position N). The frog can jump between positions −1 and N (the banks of the river) and every position containing a leaf.
 *
 * For example, consider array A such that:
 *
 *     A[0] = 0
 *     A[1] = 0
 *     A[2] = 0
 *     A[3] = 1
 *     A[4] = 1
 *     A[5] = 0
 *     A[6] = 1
 *     A[7] = 0
 *     A[8] = 0
 *     A[9] = 0
 *     A[10] = 0
 * The frog can make three jumps of length F(5) = 5, F(3) = 2 and F(5) = 5.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A consisting of N integers, returns the minimum number of jumps by which the frog can get to the other side of the river. If the frog cannot reach the other side of the river, the function should return −1.
 *
 * For example, given:
 *
 *     A[0] = 0
 *     A[1] = 0
 *     A[2] = 0
 *     A[3] = 1
 *     A[4] = 1
 *     A[5] = 0
 *     A[6] = 1
 *     A[7] = 0
 *     A[8] = 0
 *     A[9] = 0
 *     A[10] = 0
 * the function should return 3, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer that can have one of the following values: 0, 1.
 */

class FibFrog {
    /**
     * https://codesays.com/2014/solution-to-fib-frog-by-codility/
     *
     * To solve this question, we are using the Breadth First Search with pruning. For a game with N intermediate nodes,
     * the count of Fibonacci numbers, to say CF, is proportional to log(N) [Wikipedia]. And with our pruning,
     * for each node, there are CF attempts to jump. So the total number of overall attempts is proportional to
     * N*CF = N*log(N). That is, the worst-case time complexity is O(N*log(N)).
     *
     * def fibonacciDynamic(n):
     *     # Generate and return all the Fibonacci numbers,
     *     # less than or equal to n, in descending order.
     *     # n must be larger than or equal to one.
     *     fib = [0] * (n + 2)
     *     fib[1] = 1
     *     for i in range(2, n + 2):
     *         fib[i] = fib[i - 1] + fib[i - 2]
     *         if fib[i] > n:
     *             return fib[i - 1: 1: -1]
     *         elif fib[i] == n:
     *             return fib[i: 1: -1]
     *
     *
     * def solution(A):
     *     class Status(object):
     *         # Object to store the status of attempts
     *         __slots__ = ('position', 'moves')
     *
     *         def __init__(self, pos, moves):
     *             self.position = pos
     *             self.moves = moves
     *             return
     *
     *     lenA = len(A)  # Array length
     *     fibonacci = fibonacciDynamic(lenA + 1)  # Fibonacci numbers
     *     statusQueue = [Status(-1, 0)]  # Initially we are at position
     *     # -1 with 0 move.
     *     nextTry = 0  # We are not going to delete the tried attemp.
     *     # So we need a pointer to the next attemp.
     *     accessed = [False] * len(A)  # Were we in this position before?
     *     while True:
     *         if nextTry == len(statusQueue):
     *             # There is no unprocessed attemp. And we did not
     *             # find any path yet. So no path exists.
     *             return -1
     *         # Obtain the next attemp's status
     *         currentStatus = statusQueue[nextTry]
     *         nextTry += 1
     *         currentPos = currentStatus.position
     *         currentMoves = currentStatus.moves
     *         # Based upon the current attemp, we are trying any
     *         # possible move.
     *         for length in fibonacci:
     *             if currentPos + length == lenA:
     *                 # Ohhh~ We are at the goal position!
     *                 return currentMoves + 1
     *             elif currentPos + length > lenA or A[currentPos + length] == 0 or accessed[currentPos + length]:
     *                 # Three conditions are moving too far, no
     *                 # leaf available for moving, and being here
     *                 # before respectively.
     *                 # PAY ATTENTION: we are using Breadth First
     *                 # Search. If we were here before, the previous
     *                 # attemp must achieved here with less or same
     *                 # number of moves. With completely same future
     *                 # path, current attemp will never have less
     *                 # moves to goal than previous attemp. So it
     *                 # could be pruned.
     *                 continue
     *             # Enqueue for later attemp.
     *             statusQueue.append(
     *                 Status(currentPos + length, currentMoves + 1))
     *             accessed[currentPos + length] = True
     *
     */

    public static int[] fibonacciDynamic(int n) {
        /**
         * Generate and return all the Fibonacci numbers,
         * less than or equal to n, in descending order.
         * n must be larger than or equal to one.
         */
        int[] fib = new int[n + 2];
        fib[1] = 1;
        for (int i = 2; i < n + 2; i++) {
            fib[i] = fib[i - 1] + fib[i - 2];
            if (fib[i] > n) {
                int[] retVal = new int[i - 1];
                for (int j = 0; j < i - 1; j++) {
                    retVal[j] = fib[i - 1 - j];
                }
                return retVal;
            } else if (fib[i] == n) {
                int[] retVal = new int[i];
                for (int j = 0; j < i; j++) {
                    retVal[j] = fib[i - j];
                }
                return retVal;
            }
        }
        return null;

    }

    /**
     * Object to store the status of attempts
     */
    class Status {
        int position;
        int moves;

        Status(int pos, int moves) {
            this.position = pos;
            this.moves = moves;
        }
    }

    /**
     * Copied from Sheng (above)
     * O(N * log(N))
     * @param A
     * @return
     */
    public int solution(int[] A) {
        int lenA = A.length;// Array length
        int[] fibonacci = fibonacciDynamic(lenA + 1);  // Fibonacci numbers
        ArrayList<Status> statusQueue = new ArrayList<>();
        statusQueue.add(new Status(-1, 0));  // Initially we are at position -1 with 0 move.
        int nextTry = 0;  // We are not going to delete the tried attemp. So we need a pointer to the next attemp.
        boolean[] accessed = new boolean[lenA];  // Were we in this position before?
        for (int i = 0; i < lenA; i++) {
            accessed[i] = false;
        }
        while (true) {
            if (nextTry == statusQueue.size()) {
                //There is no unprocessed attemp.And we did not
                //find any path yet.So no path exists.
                return -1;
            }
            //Obtain the next attemp's status
            Status currentStatus = statusQueue.get(nextTry);
            nextTry += 1;
            int currentPos = currentStatus.position;
            int currentMoves = currentStatus.moves;
            //Based upon the current attemp, we are trying any
            //possible move.
            for (Integer length : fibonacci) {
                if (currentPos + length == lenA) {
                    //Ohhh ~We are at the goal position !
                    return currentMoves + 1;
                } else if (currentPos + length > lenA || A[currentPos + length] == 0 || accessed[currentPos + length]) {
                    /**
                     * Three conditions are moving too far, no leaf available for moving, and being here
                     * before respectively.
                     * PAY ATTENTION:we are using Breadth First Search. If we were here before, the previous
                     * attemp must achieved here with less or same number of moves.With completely same future
                     * path, current attemp will never have less moves to goal than previous attemp. So it
                     * could be pruned.
                     */
                    continue;
                }
                //Enqueue for later attemp.
                statusQueue.add(new Status(currentPos + length, currentMoves + 1));
                accessed[currentPos + length] = true;
            }
        }
    }

    public static int fibonacciBinet(int n) {
        double squareRootOf5 = Math.sqrt(5);
        double phi = (1 + squareRootOf5) / 2;
        int nthTerm = (int) ((Math.pow(phi, n) - Math.pow(-phi, -n)) / squareRootOf5);
        return nthTerm;
    }

    /**
     * O(N * log(N) ** N)
     *
     * @param A
     * @return
     */
    public int solution4(int[] A) {
        if (A.length == 0) {
            return 1;
        }
        int nLeafPositions = A.length + 2;
        int[] fib = new int[nLeafPositions];
        int f = fibonacciBinet(1);
        int i = 2;
        while (f < nLeafPositions) {
            f = fibonacciBinet(i);
            fib[i - 2] = f;
            i++;
        }
        int lastFibIndex = i - 3;
        int[] jumps = new int[nLeafPositions];
        for (int j = 0; j < lastFibIndex; j++) {
            if (fib[j] < nLeafPositions && (fib[j] - 1 == A.length || A[fib[j] - 1] > 0)) {
                jumps[fib[j]] = 1;
            }
        }

        int order = 2;
        while (jumps[nLeafPositions - 1] == 0 && order < nLeafPositions) {
            rec2(jumps, fib, lastFibIndex, order, A);
            order++;
        }
        if (jumps[nLeafPositions - 1] > 0) {
            return order - 1;
        } else {
            return -1;
        }
    }

    public static void rec2(int[] jumps, int[] fib, int lastFibIndex, int order, int[] A) {
        int k = 0;
        int maxFib = fib[lastFibIndex];
        for (int j = 0; j < jumps.length; j++) {
            if (jumps[j] == 0) {
                continue;
            }
            int alreadyFib = j;
            int alreadyOrder = jumps[j];
            if (alreadyOrder == order) {
                continue;
            }
            for (int i = 0; i <= lastFibIndex; i++) {
                k = alreadyFib + fib[i];
                if (k > maxFib) {
                    break;
                }
                if (k < A.length + 2 && (k - 1 == A.length || A[k - 1] > 0) && jumps[k] == 0) {
                    jumps[k] = order;
                }
            }
        }
    }

    public int solution3(int[] A) {
        int nLeafPositions = A.length + 2;
        ArrayList<Integer> fib = new ArrayList<Integer>();
        int f = fibonacciBinet(1);
        for (int i = 2; f < nLeafPositions; i++) {
            f = fibonacciBinet(i);
            fib.add(f);
        }

        TreeSet<Integer> fullPositions = new TreeSet<Integer>(Collections.reverseOrder());
        for (int i = 0; i < A.length; i++) {
            if (A[i] == 1) {
                fullPositions.add(i + 1);
            }
        }
        fullPositions.add(0);
        fullPositions.add(nLeafPositions - 1);
        if (nLeafPositions <= 4) {
            return 1;
        }
        TreeSet<Integer> alreadyUsedPivots = new TreeSet<Integer>();
        Integer pivot = findPivot(fullPositions, 0, nLeafPositions - 1, alreadyUsedPivots);
        if (pivot == -1) {
            return -1;
        }
        int r1 = rec(fib, fullPositions, 0, pivot + 1);
        int r2 = rec(fib, fullPositions, pivot, nLeafPositions);
        int retVal = (r1 > 0 && r2 > 0) ? r1 + r2 : -1;
        if(retVal == 1){
            return 1;
        }
        while (alreadyUsedPivots.size() < fullPositions.size()) {
            alreadyUsedPivots.add(pivot);
            pivot = findPivot(fullPositions, 0, nLeafPositions - 1, alreadyUsedPivots);
            if (pivot == -1) {
                break;
            }
            r1 = rec(fib, fullPositions, 0, pivot + 1);
            r2 = rec(fib, fullPositions, pivot, nLeafPositions);
            int r = (r1 > 0 && r2 > 0) ? r1 + r2 : -1;
            if (retVal <= 0) {
                retVal = r;
            } else if (r > 0 && r < retVal) {
                retVal = r;
            }
            if(retVal == 1){
                return 1;
            }
        }

        if (retVal > 0) {
            return retVal;
        } else {
            return -1;
        }
    }

    public int rec(ArrayList<Integer> fib, TreeSet<Integer> fullPositions, int ini, int end) {
        TreeSet<Integer> alreadyUsedPivots = new TreeSet<Integer>();
        if (end - ini == 1) {
            return 1;
        }
        HashMap<Integer, Integer> jumps = new HashMap<Integer, Integer>();
        for (Integer f : fib) {
            int k = f + ini;
            if (fullPositions.contains(k) && k >= ini && k < end) {
                jumps.put(k, 1);
            }
        }
        if (jumps.containsKey(end - 1)) {
            return jumps.get(end - 1);
        }
        Integer pivot = findPivot(fullPositions, ini, end - 1, alreadyUsedPivots);
        if (pivot == -1) {
            return -1;
        }
        int rec1 = rec(fib, fullPositions, ini, pivot + 1);
        int rec2 = rec(fib, fullPositions, pivot, end);
        while (alreadyUsedPivots.size() < fullPositions.size() && (rec1 < 0 || rec2 < 0)) {
            alreadyUsedPivots.add(pivot);
            pivot = findPivot(fullPositions, ini, end - 1, alreadyUsedPivots);
            if (pivot == -1) {
                return -1;
            }
            rec1 = rec(fib, fullPositions, ini, pivot + 1);
            rec2 = rec(fib, fullPositions, pivot, end);
        }
        return rec1 + rec2;
    }

    public Integer findPivot(TreeSet<Integer> fullPositions, int ini, int end, TreeSet<Integer> alreadyUsedPivots) {
        Iterator<Integer> iterator = fullPositions.iterator();
        while (iterator.hasNext()) {
            Integer pos = iterator.next();
            if (alreadyUsedPivots.contains(pos)) {
                continue;
            }
            if (pos > ini && pos < end) {
                return pos;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        FibFrog ff = new FibFrog();
        Assert.assertEquals(1, ff.solution(new int[]{}));
        Assert.assertEquals(1, ff.solution(new int[]{1}));
        Assert.assertEquals(1, ff.solution(new int[]{1, 1}));
        Assert.assertEquals(2, ff.solution(new int[]{1, 1, 1}));
        Assert.assertEquals(2, ff.solution(new int[]{1, 1, 1, 1, 1}));
        Assert.assertEquals(3, ff.solution(new int[]{0, 1, 0, 1, 0}));
        Assert.assertEquals(1, ff.solution(new int[]{0, 0}));
        Assert.assertEquals(-1, ff.solution(new int[]{0, 0, 0}));
        Assert.assertEquals(-1, ff.solution(new int[]{0, 0, 0, 1, 0}));
        Assert.assertEquals(3, ff.solution(new int[]{0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0}));
        Assert.assertEquals(2, ff.solution(new int[]{1, 1, 0, 0, 0}));
        Assert.assertEquals(2, ff.solution(new int[]{0, 0, 1, 0, 0, 0, 1, 1, 1, 1}));
        Assert.assertEquals(2, ff.solution(new int[]{0, 0, 0, 0, 1}));
        System.out.println("OK");
    }

    public Integer findPivot4(TreeSet<Integer> fullPositions, int ini, int end, TreeSet<Integer> alreadyUsedPivots) {
        int pos1 = ini + (end - ini) / 2;
        int pos2 = pos1 + 1;
        while (pos1 > ini || pos2 < end) {
            if (fullPositions.contains(pos1) && !alreadyUsedPivots.contains(pos1) && pos1 > ini) {
                return pos1;
            } else {
                pos1--;
            }
            if (fullPositions.contains(pos2) && !alreadyUsedPivots.contains(pos2) && pos2 < end) {
                return pos2;
            } else {
                pos2++;
            }
        }
        return -1;
    }


//    public int solution1(int[] A) {
//        int n = A.length;
//        int nLeaves = sum(A, 0, n);
//        ArrayList<Integer> fib = new ArrayList<Integer>();
//        int last = 0;
//        Integer f = fibonacciBinet(last);
//        while (f <= n + 1) {
//            fib.add(f);
//            last++;
//            f = fibonacciBinet(last);
//        }
//        HashMap<Integer, Integer> jumps = new HashMap<Integer, Integer>();
//        int retVal = 1;
//        while (!jumps.containsKey(n + 1)) {
//            retVal++;
//            recn(jumps, fib, retVal - 1, A);
//        }
//        if (jumps.get(n + 1) <= Math.max(nLeaves, 1)) {
//            return retVal - 1;
//        } else {
//            return -1;
//        }
//    }


}
