package com.alvaroneira.algorithms;

import org.junit.Assert;

public class Numbers {
    public static int lcd(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static int gcd(int a, int b) {
        return gcdBinary(a, b, 1);
    }

    private static int gcdBinary(int N, int M, int res) {
        if (N == M) {
            return res * N;
        } else if ((N % 2 == 0) && (M % 2 == 0)) {
            return gcdBinary(N / 2, M / 2, 2 * res);
        } else if (N % 2 == 0) {
            return gcdBinary(N / 2, M, res);
        } else if (M % 2 == 0) {
            return gcdBinary(N, M / 2, res);
        } else if (N > M) {
            return gcdBinary(N - M, M, res);
        } else {
            return gcdBinary(N, M - N, res);
        }
    }

    private static int gcdDivision(int N, int M) {
        if (N % M == 0) {
            return M;
        }
        return gcdDivision(M, N % M);
    }

    private static int gcdSubstraction(int N, int M) {
        if (N == M) {
            return N;
        }
        if (N > M) {
            return gcdSubstraction(N - M, M);
        } else {
            return gcdSubstraction(N, M - N);
        }
    }

    /**
     * O(sqrt(N))
     *
     * @param N: tested until 2147483647 (2^31-1)
     * @return
     */
    public static int countDivisors(int N) {
        long i = 1;
        int result = 0;
        while (i * i < N) {
            if (N % i == 0) {
                result += 2;
            }
            i += 1;
        }
        if (i * i == N) {
            result += 1;
        }
        return result;
    }

    /**
     * The primality test of n can be performed in an analogous way to counting the divisors. If we
     * find a number between 2 and n − 1 that divides n then n is a composite number. Otherwise,
     * n is a prime number.
     * We assume that 1 is neither a prime nor a composite number, so the above algorithm works
     * only for n >= 2 (it will return 1 as prime).
     *
     * @param n
     * @return
     */
    public static boolean isPrime(long n) {
        long i = 2;
        while (i * i <= n) {
            if (n % i == 0) {
                return false;
            }
            i += 1;
        }
        return true;
    }

    public static void main(String[] args) {
//        Assert.assertEquals(22, solution(30));
        Assert.assertEquals(true, isPrime(1));
        System.out.println("OK");
    }
}
