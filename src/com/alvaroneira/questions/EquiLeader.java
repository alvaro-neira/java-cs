package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Tasks Details
 * Easy
 *
 * Task description
 * A non-empty array A consisting of N integers is given.
 *
 * The leader of this array is the value that occurs in more than half of the elements of A.
 *
 * An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
 *
 * For example, given array A such that:
 *
 *     A[0] = 4
 *     A[1] = 3
 *     A[2] = 4
 *     A[3] = 4
 *     A[4] = 4
 *     A[5] = 2
 * we can find two equi leaders:
 *
 * 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
 * 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
 * The goal is to count the number of equi leaders.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given a non-empty array A consisting of N integers, returns the number of equi leaders.
 *
 * For example, given:
 *
 *     A[0] = 4
 *     A[1] = 3
 *     A[2] = 4
 *     A[3] = 4
 *     A[4] = 4
 *     A[5] = 2
 * the function should return 2, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 */
public class EquiLeader {
    /**
     * def goldenLeader(A):
     * n = len(A)
     * size = 0
     * for k in xrange(n):
     * if (size == 0):
     * size += 1
     * value = A[k]
     * else:
     * if (value != A[k]):
     * 10
     * size -= 1
     * else:
     * size += 1
     * candidate = -1
     * if (size > 0):
     * candidate = value
     * leader = -1
     * count = 0
     * for k in xrange(n):
     * if (A[k] == candidate):
     * count += 1
     * if (count > n // 2):
     * leader = candidate
     * return leader
     */
    public int goldenLeader(Integer[] A) {
        Integer n = A.length;
        Integer size = 0;
        Integer value = null;
        for (Integer k = 0; k < n; k++) {
            if (size == 0) {
                size += 1;
                value = A[k];
            } else {
                if (value != A[k]) {
                    size -= 1;
                } else {
                    size += 1;
                }
            }
        }
        Integer candidate = -1;
        if (size > 0) {
            candidate = value;
        }
        Integer leader = -1;
        Integer count = 0;
        for (Integer k = 0; k < n; k++) {
            if (A[k] == candidate) {
                count += 1;
            }
        }
        if (count > n / 2) {
            leader = candidate;
        }
        return leader;
    }

    public int customLeader(Integer[] A) {
        Integer n = A.length;

        /**
         Let’s create an empty stack onto which
         we will be pushing consecutive elements
         */
        Integer stackSize = 0;
        Integer stackTopValue = null;
        for (Integer k = 0; k < n; k++) {
            if (stackSize == 0) {
                stackSize = 1;
                stackTopValue = A[k];
            } else {
                /**
                 After each such operation we check whether the two
                 elements at the top of the stack are different. If they are, we remove them from the stack.
                 This is equivalent to removing a pair of different elements from the sequence
                 */
                if (stackTopValue != A[k]) {
                    stackSize--;
                } else {
                    stackSize++;
                }
            }
        }

        /**
         After removing all pairs of different
         elements, we end up with a sequence containing all the same values. This value is not necessarily the leader; it is only a candidate for the leader. Finally, we should iterate through all
         the elements and count the occurrences of the candidate; if it is greater than n
         2 then we have
         found the leader; otherwise the sequence does not contain a leader.
         */
        Integer candidate = -1;
        if (stackSize > 0) {
            candidate = stackTopValue;
        }
        Integer leader = -1;
        Integer count = 0;
        for (Integer k = 0; k < n; k++) {
            if (A[k] == candidate) {
                count += 1;
            }
        }
        if (count > n / 2) {
            leader = candidate;
        }
        return leader;
    }

    public int getLeaderN(int[] A, Integer ini, Integer end) {
        if (ini >= end) {
            System.err.println("ini>=end (" + ini + "," + end + ")");
            System.exit(-1);
        }
        Integer size = 0;
        Integer value = null;
        for (Integer k = ini; k < end; k++) {
            if (size == 0) {
                size += 1;
                value = A[k];
            } else {
                if (value != A[k]) {
                    size -= 1;
                } else {
                    size += 1;
                }
            }
        }
        Integer candidate = -1;
        if (size > 0) {
            candidate = value;
        }
        Integer leader = -1;
        Integer count = 0;
        for (Integer k = ini; k < end; k++) {
            if (A[k] == candidate) {
                count += 1;
            }
        }
        if (count > (end - ini) / 2) {
            leader = candidate;
        }
        return leader;
    }

    public int solution(int[] A) {
        Integer retVal = 0;
        Integer n = A.length;
        for (Integer i = 0; i < n - 1; i++) {
            Integer getLeader0 = getLeaderN(A, 0, i + 1);
            if (getLeader0 != -1 && getLeader0 == getLeaderN(A, i + 1, n)) {
                retVal++;
            }
        }
        return retVal;
    }

    public static void main(String[] args) {
        EquiLeader el = new EquiLeader();

        Assert.assertEquals(4, el.customLeader(new Integer[]{4, 3, 4, 4, 4, 2}));
        Assert.assertEquals(6, el.customLeader(new Integer[]{6, 8, 4, 6, 8, 6, 6}));
        Assert.assertEquals(4, el.customLeader(new Integer[]{4}));
        Assert.assertEquals(4, el.customLeader(new Integer[]{3, 4, 4, 4, 2}));
        Assert.assertEquals(4, el.customLeader(new Integer[]{4, 3, 4}));
        Assert.assertEquals(4, el.customLeader(new Integer[]{4, 4, 2}));


        Assert.assertEquals(4, el.getLeaderNLogN(new int[]{4, 3, 4, 4, 4, 2}, 0, 6));
        Assert.assertEquals(6, el.getLeaderNLogN(new int[]{6, 8, 4, 6, 8, 6, 6}, 0, 7));
        Assert.assertEquals(4, el.getLeaderNLogN(new int[]{4}, 0, 1));
        Assert.assertEquals(4, el.getLeaderNLogN(new int[]{3, 4, 4, 4, 2}, 0, 5));
        Assert.assertEquals(4, el.getLeaderNLogN(new int[]{4, 3, 4}, 0, 3));
        Assert.assertEquals(4, el.getLeaderNLogN(new int[]{4, 4, 2}, 0, 3));
        Assert.assertEquals(4, el.getLeaderNLogN(new int[]{4, 3, 4, 4, 4, 2}, 0, 1));
        Assert.assertEquals(4, el.getLeaderNLogN(new int[]{4, 3, 4, 4, 4, 2}, 0, 3));
        Assert.assertEquals(4, el.getLeaderNLogN(new int[]{4, 3, 4, 4, 4, 2}, 3, 6));

        Assert.assertEquals(4, el.getLeaderNLogN(new int[]{4, 3, 4, 4, 4, 2}, 1, 6));

        Assert.assertEquals(2, el.solution(new int[]{4, 3, 4, 4, 4, 2}));

        Assert.assertEquals(0, el.solution(new int[]{1, 2, 3, 4, 5}));
        System.out.println("OK");
    }

    /**
     * def fastLeader(A):
     * n = len(A)
     * leader = -1
     * A.sort()
     * candidate = A[n // 2]
     * count = 0
     * for i in xrange(n):
     * if (A[i] == candidate):
     * count += 1
     * if (count > n // 2):
     * leader = candidate
     * return leader
     */
    public int fastLeader(Integer[] A) {
        Integer n = A.length;
        Integer leader = -1;
        Arrays.sort(A);
        Integer candidate = A[n / 2];
        Integer count = 0;
        for (Integer i = 0; i < n; i++) {
            if (A[i] == candidate) {
                count += 1;
            }
        }
        if (count > n / 2) {
            leader = candidate;
        }
        return leader;
    }

    public int getLeaderNLogN(int[] A, Integer ini, Integer end) {

        Integer leader = -1;
        Arrays.sort(A, ini, end);
        Integer candidate = A[ini + (end - ini) / 2];
        Integer count = 0;
        for (Integer i = ini; i < end; i++) {
            if (A[i] == candidate) {
                count += 1;
            }
        }
        if (count > (end - ini) / 2) {
            leader = candidate;
        }
        return leader;
    }
}
