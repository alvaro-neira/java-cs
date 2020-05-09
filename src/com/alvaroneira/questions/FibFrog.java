package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.*;

import static com.alvaroneira.utils.HashMapUtils.printHashMap;
import static com.alvaroneira.utils.HashMapUtils.printHashSet;
import static com.alvaroneira.utils.NumberUtils.fibonacciBinet;
import static com.alvaroneira.utils.ArrayUtils.sum;

class FibFrog {
    public static void recn(HashMap<Integer, Integer> jumps, ArrayList<Integer> fib, int order, int[] A) {
        int n = A.length;
        HashMap<Integer, Integer> jumpsTmp = new HashMap<Integer, Integer>();
        for (int i = 0; i < fib.size(); i++) {
            int o = order;
            Iterator<Map.Entry<Integer, Integer>> it = jumps.entrySet().iterator();
            while (it.hasNext() || (o == 1 && --o == 0)) {
                Map.Entry<Integer, Integer> pair = order == 1 ? null : it.next();
                Integer alreadyFib = order == 1 ? 0 : pair.getKey();
                Integer k = alreadyFib + fib.get(i);
                if (!jumps.containsKey(k) && k <= n) {
                    jumpsTmp.put(k, order);
                }
            }
        }
        jumps.putAll(jumpsTmp);
    }

    public int solution(int[] A) {
        int nLeafPositions = A.length + 2;
        int[] leafPositions = new int[nLeafPositions];
        leafPositions[0] = 1;
        leafPositions[nLeafPositions - 1] = 1;
        ArrayList<Integer> emptyPositions = new ArrayList<Integer>();
        for (int i = 0; i < A.length; i++) {
            leafPositions[i + 1] = A[i];
            if (leafPositions[i + 1] == 0) {
                emptyPositions.add(i + 1);
            }
        }
        ArrayList<Integer> fib = new ArrayList<Integer>();
        int f = fibonacciBinet(0);
        for (int i = 1; f <= nLeafPositions; i++) {
            f = fibonacciBinet(i);
            fib.add(f);
        }
        HashMap<Integer, Integer> jumps = new HashMap<Integer, Integer>();
        int order = 1;
        while (!jumps.containsKey(nLeafPositions - 1) && order < nLeafPositions) {
            recn(jumps, fib, order, leafPositions);
            for (Integer emptyPos : emptyPositions) {
                jumps.remove(emptyPos);
            }
            if (jumps.isEmpty()) {
                break;
            }
            order++;
        }
        if (jumps.containsKey(nLeafPositions - 1)) {
            return jumps.get(nLeafPositions - 1);
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
