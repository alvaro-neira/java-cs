package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.*;

import static com.alvaroneira.utils.NumberUtils.fibonacciBinet;
import static com.alvaroneira.utils.ArrayUtils.sum;

class FibFrog {
    public int solution(int[] A) {
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
        last--;
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

    public static void recn(HashMap<Integer, Integer> jumps, ArrayList<Integer> fib, int order, int[] A) {
        Iterator it = jumps.entrySet().iterator();
        int n = A.length;
        HashMap<Integer, Integer> jumpsTmp = new HashMap<Integer, Integer>();
        if (order == 1) {
            for (int i = 0; i < fib.size(); i++) {
                jumpsTmp.put(fib.get(i), 1);
            }
        }
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            Integer alreadyFib = (Integer) pair.getKey();
            for (int i = 0; i < fib.size(); i++) {
                Integer k = alreadyFib + fib.get(i);
                if (!jumps.containsKey(k) && k <= n + 1) {
                    jumpsTmp.put(k, order);
                }
            }
        }
        jumps.putAll(jumpsTmp);
    }

    public static void main(String[] args) {
        FibFrog ff = new FibFrog();
        Assert.assertEquals(1, ff.solution(new int[]{}));
        Assert.assertEquals(1, ff.solution(new int[]{0}));
        Assert.assertEquals(1, ff.solution(new int[]{1}));
        Assert.assertEquals(1, ff.solution(new int[]{1, 1}));
        Assert.assertEquals(3, ff.solution(new int[]{0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0}));
        Assert.assertEquals(-1, ff.solution(new int[]{0, 0, 0}));
        Assert.assertEquals(2, ff.solution(new int[]{1, 1, 1, 1, 1}));
        Assert.assertEquals(3, ff.solution(new int[]{0, 1, 0, 1, 0}));
        System.out.println("OK");
    }
}
