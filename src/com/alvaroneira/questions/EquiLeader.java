package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.*;

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
     def goldenLeader(A):
     n = len(A)
     size = 0
     for k in xrange(n):
     if (size == 0):
     size += 1
     value = A[k]
     else:
     if (value != A[k]):
     10
     size -= 1
     else:
     size += 1
     candidate = -1
     if (size > 0):
     candidate = value
     leader = -1
     count = 0
     for k in xrange(n):
     if (A[k] == candidate):
     count += 1
     if (count > n // 2):
     leader = candidate
     return leader
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

    public int solutionNSquared(int[] A) {
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

    public int forward(int[] A) {
        Integer n = A.length;
        HashMap<Integer,Integer> counts=new HashMap<Integer, Integer>();
        counts.put(A[0],1);
        Iter zero=new Iter();
        zero.stackSize=1;
        zero.stackTopValue=A[0];
        zero.candidate=A[0];
        zero.leader=A[0];
        Iter[] iterations=new Iter[n];
        iterations[0]=zero;

        for(Integer i=1;i<n;i++){
            iterations[i]=dynamicLeader(A,i, i, iterations[i-1], counts);
        }
        return iterations[n-1].leader;
    }

    public int reverse(int[] A) {
        Integer n = A.length;
        HashMap<Integer,Integer> reverseCounts=new HashMap<Integer, Integer>();
        reverseCounts.put(A[n-1],1);
        Iter last=new Iter();
        last.stackSize=1;
        last.stackTopValue=A[n-1];
        last.candidate=A[n-1];
        last.leader=A[n-1];
        Iter[] iterationsReverse=new Iter[n];
        iterationsReverse[n-1]=last;

        for(Integer i=n-2;i>=0;i--){
            iterationsReverse[i]=dynamicLeader(A,i,n-i, iterationsReverse[i+1], reverseCounts);
        }
        return iterationsReverse[0].leader;
    }
    class Iter{
        Integer stackSize;
        Integer stackTopValue;
        Integer candidate;
        Integer leader;
    }
    public Iter dynamicLeader(int[] A, Integer i, Integer c, Iter oldIter, HashMap<Integer,Integer> counts) {
        Iter newIter=new Iter();
        if (oldIter.stackSize == 0) {
            newIter.stackSize = 1;
            newIter.stackTopValue = A[i];
        } else {
            if (oldIter.stackTopValue != A[i]) {
                newIter.stackSize=oldIter.stackSize-1;
            } else {
                newIter.stackSize=oldIter.stackSize+1;
            }
            newIter.stackTopValue=oldIter.stackTopValue;
        }


        newIter.candidate = -1;
        if (newIter.stackSize > 0) {
            newIter.candidate = newIter.stackTopValue;
        }

        counts.put(A[i],counts.containsKey(A[i]) ? counts.get(A[i])+1:1);

        newIter.leader = -1;

        if ((counts.containsKey(newIter.candidate) ? counts.get(newIter.candidate):0) > c / 2) {
            newIter.leader = newIter.candidate;
        }
        return newIter;
    }
    public int solution(int[] A) {
        Integer n = A.length;
        Integer retVal=0;
        HashMap<Integer,Integer> counts=new HashMap<Integer, Integer>();
        counts.put(A[0],1);
        Iter zero=new Iter();
        zero.stackSize=1;
        zero.stackTopValue=A[0];
        zero.candidate=A[0];
        zero.leader=A[0];
        Iter[] iterations=new Iter[n];
        iterations[0]=zero;

        for(Integer i=1;i<n;i++){
            iterations[i]=dynamicLeader(A,i, i, iterations[i-1], counts);
        }
        HashMap<Integer,Integer> reverseCounts=new HashMap<Integer, Integer>();
        reverseCounts.put(A[n-1],1);
        Iter last=new Iter();
        last.stackSize=1;
        last.stackTopValue=A[n-1];
        last.candidate=A[n-1];
        last.leader=A[n-1];
        Iter[] iterationsReverse=new Iter[n];
        iterationsReverse[n-1]=last;

        for(Integer i=n-2;i>=0;i--){
            iterationsReverse[i]=dynamicLeader(A,i,n-i, iterationsReverse[i+1], reverseCounts);
        }

        for(Integer i=0;i<n-1;i++){
            if(iterations[i].leader!=-1 && iterations[i].leader.intValue()==iterationsReverse[i+1].leader.intValue()){
                retVal++;
            }
        }
        return retVal;
    }
    public static void main(String[] args) {
        EquiLeader el = new EquiLeader();

//        Assert.assertEquals(4, el.forward(new int[]{4}));
//        Assert.assertEquals(-1, el.forward(new int[]{4, 3}));
//        Assert.assertEquals(4, el.forward(new int[]{4, 3, 4}));
//        Assert.assertEquals(4, el.forward(new int[]{4, 3, 4, 4}));
//        Assert.assertEquals(4, el.forward(new int[]{4, 3, 4, 4, 4}));
//        Assert.assertEquals(4, el.forward(new int[]{4, 3, 4, 4, 4, 2}));
//
//        Assert.assertEquals(5, el.forward(new int[]{5}));
//        Assert.assertEquals(-1, el.forward(new int[]{4, 5}));
//        Assert.assertEquals(-1, el.forward(new int[]{3, 4, 5}));
//        Assert.assertEquals(-1, el.forward(new int[]{1, 2, 3, 4, 5}));
//
//        Assert.assertEquals(4, el.forward(new int[]{4, 3, 4, 4, 4, 2}));
//        Assert.assertEquals(6, el.forward(new int[]{6, 8, 4, 6, 8, 6, 6}));
//        Assert.assertEquals(4, el.forward(new int[]{4}));
//        Assert.assertEquals(4, el.forward(new int[]{3, 4, 4, 4, 2}));
//        Assert.assertEquals(4, el.forward(new int[]{4, 3, 4}));
//        Assert.assertEquals(4, el.forward(new int[]{4, 4, 2}));
//
//        Assert.assertEquals(2, el.solution(new int[]{4, 3, 4, 4, 4, 2}));
//
//        Assert.assertEquals(0, el.solution(new int[]{1, 2, 3, 4, 5}));
        Assert.assertEquals(1, el.solution(new int[]{-1000000000, -1000000000}));
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
