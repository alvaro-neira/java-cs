package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.*;

import static com.alvaroneira.utils.HashMapUtils.printHashMap;
import static com.alvaroneira.utils.HashMapUtils.printHashSet;
import static com.alvaroneira.utils.NumberUtils.fibonacciBinet;
import static com.alvaroneira.utils.ArrayUtils.sum;

class FibFrog {
    public static void recn(HashMap<Integer, Integer> jumps, ArrayList<Integer> fib, int order, int[] A) {
        Iterator<Map.Entry<Integer, Integer>> it = jumps.entrySet().iterator();
        int n = A.length;
        HashMap<Integer, Integer> jumpsTmp = new HashMap<Integer, Integer>();
        if (order == 1) {
            for (int i = 0; i < fib.size(); i++) {
                if (fib.get(i) <= n) {
                    jumpsTmp.put(fib.get(i), 1);
                }
            }
        }
        while (it.hasNext()) {
            Map.Entry<Integer, Integer> pair = it.next();
            Integer alreadyFib = order == 1 ? 0 : pair.getKey();
            for (int i = 0; i < fib.size(); i++) {
                Integer k = alreadyFib + fib.get(i);
                if (!jumps.containsKey(k) && k <= n) {
                    jumpsTmp.put(k, order);
                }
            }
        }
        jumps.putAll(jumpsTmp);
    }

    public int solution(int[] A) {
        int nLeaves = A.length + 2;
        int[] leaves = new int[nLeaves];
        leaves[0] = 1;
        leaves[nLeaves - 1] = 1;
        int L = 2;
        for (int i = 0; i < A.length; i++) {
            leaves[i + 1] = A[i];
            if (leaves[i + 1] == 1) {
                L++;
            }
        }
        ArrayList<Integer> fib = new ArrayList<Integer>();
        int f = fibonacciBinet(0);
        for (int i = 1; f <= nLeaves; i++) {
            f = fibonacciBinet(i);
            fib.add(f);
        }
        HashMap<Integer, Integer> jumps = new HashMap<Integer, Integer>();
        if (L == 2) {
            recn(jumps, fib, 1, leaves);
        } else {
            int order = 1;
            while (!jumps.containsKey(nLeaves - 1)) {
                recn(jumps, fib, order, leaves);
                for (int i = 0; i < nLeaves; i++) {
                    if (leaves[i] == 0) {
                        jumps.remove(i);
                    }
                }
                if (jumps.isEmpty()) {
                    break;
                }
                order++;
            }
        }
        if (jumps.containsKey(nLeaves - 1)) {
            return jumps.get(nLeaves - 1);
        } else {
            return -1;
        }

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
        System.out.println("OK");
    }

    public int solution1(int[] A) {
        int n = A.length;
        int nLeaves = sum(A, 0, n);
        ArrayList<Integer> fib = new ArrayList<Integer>();
        int last = 0;
        Integer f = fibonacciBinet(last);
        while (f <= n + 1) {
            fib.add(f);
            last++;
            f = fibonacciBinet(last);
        }
        HashMap<Integer, Integer> jumps = new HashMap<Integer, Integer>();
        int retVal = 1;
        while (!jumps.containsKey(n + 1)) {
            retVal++;
            recn(jumps, fib, retVal - 1, A);
        }
        if (jumps.get(n + 1) <= Math.max(nLeaves, 1)) {
            return retVal - 1;
        } else {
            return -1;
        }
    }
}
