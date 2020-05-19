package com.alvaroneira.algorithms;

public class GcdAndLcd {

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
}
