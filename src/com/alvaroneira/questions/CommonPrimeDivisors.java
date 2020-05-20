package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.*;

import static com.alvaroneira.algorithms.Numbers.isPrime;
import static com.alvaroneira.utils.ArrayUtils.printArr;
import static com.alvaroneira.utils.HashMapUtils.printHashMap;

public class CommonPrimeDivisors {
    public int solution(int[] A, int[] B) {
        int Z = A.length;
        int N = 1;
        HashMap<Long, Boolean[]> hm = new HashMap();
        for (int i = 0; i < Z; i++) {
            if (A[i] > N) {
                N = A[i];
            }
            if (B[i] > N) {
                N = B[i];
            }
        }
        HashMap<Long, Long> primes = new HashMap();
        Long primeCandidate = 1L;
        Long pos = 0L;
        while (primeCandidate <= N) {

            primeCandidate++;
            if (isPrime(primeCandidate)) {
                primes.put(primeCandidate, pos);
                pos++;
            }
        }

        for (int i = 0; i < Z; i++) {
            Long Ai = Integer.valueOf(A[i]).longValue();
            Long Bi = Integer.valueOf(B[i]).longValue();
            func(hm, primes, Ai);
            func(hm, primes, Bi);
        }
        int retVal = 0;
        for (int i = 0; i < Z; i++) {
            if (equals(hm.get(Integer.valueOf(A[i]).longValue()), hm.get(Integer.valueOf(B[i]).longValue()))) {
                retVal++;
            }
        }
        return retVal;
    }

    public static void func(HashMap<Long, Boolean[]> hm, HashMap<Long, Long> primes, Long N) {
        if (!hm.containsKey(N)) {
            Boolean[] representation = new Boolean[primes.size()];
            Long i = 1L;
            while (i * i < N) {
                if (N % i == 0) {
                    if (primes.containsKey(i)) {
                        representation[primes.get(i).intValue()] = true;
                    }
                    Long d2 = N / i;
                    if (primes.containsKey(d2)) {
                        representation[primes.get(d2).intValue()] = true;
                    }
                }
                i += 1;
            }
            if (i * i == N) {
                if (primes.containsKey(i)) {
                    representation[primes.get(i).intValue()] = true;
                }
            }
            hm.put(N, representation);
        }
    }

    private static boolean equals(Boolean[] a, Boolean[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        CommonPrimeDivisors cpm = new CommonPrimeDivisors();
        Assert.assertEquals(1, cpm.solution(new int[]{15, 10, 9}, new int[]{75, 30, 5}));
        Assert.assertEquals(2, cpm.solution(new int[]{7, 17, 5, 3}, new int[]{7, 11, 5, 2}));
        Assert.assertEquals(2, cpm.solution(new int[]{3, 9, 20, 11}, new int[]{9, 81, 5, 13}));
        Assert.assertEquals(2, cpm.solution(new int[]{6059, 551}, new int[]{442307, 303601}));
        Assert.assertEquals(0, cpm.solution(new int[]{96}, new int[]{95}));
        Assert.assertEquals(1, cpm.solution(new int[]{96}, new int[]{6}));
        Assert.assertEquals(1, cpm.solution(new int[]{96}, new int[]{72}));
        Assert.assertEquals(1, cpm.solution(new int[]{999}, new int[]{111}));
        Assert.assertEquals(0, cpm.solution(new int[]{999}, new int[]{888}));
        Assert.assertEquals(0, cpm.solution(new int[]{999}, new int[]{823}));
        Assert.assertEquals(0, cpm.solution(new int[]{100101}, new int[]{100100}));
        Assert.assertEquals(1, cpm.solution(new int[]{2002004}, new int[]{1001002}));
        Assert.assertEquals(0, cpm.solution(new int[]{1234567}, new int[]{9721}));
        Assert.assertEquals(1, cpm.solution(new int[]{12345}, new int[]{10159935}));
    }

    public static void fancyTest(CommonPrimeDivisors cpm, int n) {
        int a = (int) (Math.random() * n) + 1;
        int b = (int) (Math.random() * n) + 1;
        System.out.println("a=" + a + ", b=" + b);
        Assert.assertEquals(cpm.solution1(new int[]{a}, new int[]{b}), cpm.solution(new int[]{a}, new int[]{b}));
    }

    /**
     * Correctness %100, performance 0%, O(Z * (max(A) + max(B)))
     *
     * @param A
     * @param B
     * @return
     */
    public int solution1(int[] A, int[] B) {
        int Z = A.length;
        int N = 1;
        HashMap<Long, boolean[]> hm = new HashMap();
        for (int i = 0; i < Z; i++) {
            if (A[i] > N) {
                N = A[i];
            }
            if (B[i] > N) {
                N = B[i];
            }
        }
        ArrayList<Long> primes = new ArrayList();
        long primeCandidate = 1;
        while (primeCandidate <= N) {
            primeCandidate++;
            if (isPrime(primeCandidate)) {
                primes.add(primeCandidate);
            }
        }
        for (int i = 0; i < Z; i++) {
            if (!hm.containsKey(A[i])) {
                boolean[] representation = new boolean[primes.size()];
                for (int j = 0; j < primes.size(); j++) {
                    representation[j] = isDivisor(A[i], primes.get(j));
                }
                hm.put(Integer.valueOf(A[i]).longValue(), representation);
            }
            if (!hm.containsKey(B[i])) {
                boolean[] representation = new boolean[primes.size()];
                for (int j = 0; j < primes.size(); j++) {
                    representation[j] = isDivisor(B[i], primes.get(j));
                }
                hm.put(Integer.valueOf(B[i]).longValue(), representation);
            }
        }
        int retVal = 0;
        for (int i = 0; i < Z; i++) {
            if (equals(hm.get(Integer.valueOf(A[i]).longValue()), hm.get(Integer.valueOf(B[i]).longValue()))) {
                retVal++;
            }
        }
        return retVal;
    }

    private static boolean equals(boolean[] a, boolean[] b) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDivisor(int N, Long div) {
        long i = 1;
        while (i * i < N) {
            if (N % i == 0 && (div == i || N / i == div)) {
                return true;
            }
            i += 1;
        }
        if (i * i == N && div == i) {
            return true;
        }
        return false;
    }
}
