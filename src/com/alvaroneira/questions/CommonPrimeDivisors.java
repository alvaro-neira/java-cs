package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.HashMap;

import static com.alvaroneira.algorithms.Numbers.isPrime;

public class CommonPrimeDivisors {
    public int solution(int[] A, int[] B) {
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
//        printHashMap(hm);
        int retVal = 0;
        for (int i = 0; i < Z; i++) {
//            System.out.println("Ka=" + A[i]);
//            System.out.println("Kb=" + B[i]);
            if (equals(hm.get((Long) Integer.valueOf(A[i]).longValue()), hm.get((Long) Integer.valueOf(B[i]).longValue()))) {
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

    public static void main(String[] args) {
        CommonPrimeDivisors cpm = new CommonPrimeDivisors();
        Assert.assertEquals(1, cpm.solution(new int[]{15, 10, 9}, new int[]{75, 30, 5}));
    }
}
