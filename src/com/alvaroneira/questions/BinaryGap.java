package com.alvaroneira.questions;

/**
 * Find longest sequence of zeros in binary representation of an integer
 * <p>
 * Task description
 * A binary gap within a positive integer N is any maximal sequence of consecutive zeros that is surrounded by ones at both ends in the binary representation of N.
 * <p>
 * For example, number 9 has binary representation 1001 and contains a binary gap of length 2. The number 529 has binary representation 1000010001 and contains two binary gaps: one of length 4 and one of length 3. The number 20 has binary representation 10100 and contains one binary gap of length 1. The number 15 has binary representation 1111 and has no binary gaps. The number 32 has binary representation 100000 and has no binary gaps.
 * <p>
 * Write a function:
 * <p>
 * class Solution { public int solution(int N); }
 * <p>
 * that, given a positive integer N, returns the length of its longest binary gap. The function should return 0 if N doesn't contain a binary gap.
 * <p>
 * For example, given N = 1041 the function should return 5, because N has binary representation 10000010001 and so its longest binary gap is of length 5. Given N = 32 the function should return 0, because N has binary representation '100000' and thus no binary gaps.
 * <p>
 * Write an efficient algorithm for the following assumptions:
 * <p>
 * N is an integer within the range [1..2,147,483,647].
 */

import org.junit.Assert;

public class BinaryGap {
    public int solution(int N) {
        // write your code in Java SE 8
        System.out.println(N + ":" + Integer.toBinaryString(N));
        return better1(N);
    }

    public int better1(int N) {
        String str = Integer.toBinaryString(N);
        int zeroCount = 0;
        int retVal = 0;
        int cpy = N;
        boolean insideGap = false;
        int last = 0;
        String swa = Integer.toBinaryString(cpy);
        for (int i = 0; i < str.length(); i++) {
            swa = Integer.toBinaryString(cpy);
            if ((cpy & 1) == 1) {
                if (last == 0 && insideGap) {
                    insideGap = false;
                } else {
                    insideGap = true;
                }
            } else {
                int cpy2 = cpy;
                String str2 = Integer.toBinaryString(cpy);
                if (zeroCount > retVal) {
                    retVal = zeroCount;
                }
                zeroCount = 0;
                last = -1;
                for (int j = 0; insideGap && j < str2.length(); j++) {
                    if ((cpy2 & 1) == 1) {
                        if (zeroCount > retVal) {
                            retVal = zeroCount;
                        }
                        zeroCount = 0;

                    } else if (insideGap) {
                        zeroCount++;
                    }
                    cpy2 = cpy2 >> 1;
                }

            }
            last = (cpy & 1);
            cpy = cpy >> 1;
        }

        return retVal;
    }

    public int bruteForce(int N) {
        String str = Integer.toBinaryString(N);
        int zeroCount = 0;
        int retVal = 0;
        for (int i = 0; i < str.length(); i++) {
            String ch = str.substring(i, i + 1);

            if (ch.compareTo("1") == 0) {

            } else {
                for (int j = i; j < str.length(); j++) {
                    String ch2 = str.substring(j, j + 1);
                    if (ch2.compareTo("1") == 0) {
                        // System.out.println("zeroCount="+zeroCount);
                        if (zeroCount > retVal) {
                            retVal = zeroCount;
                        }
                        zeroCount = 0;

                    } else {
                        zeroCount++;
                    }
                }

            }
        }

        return retVal;
    }

    public static void main(String[] args) {
        BinaryGap bg = new BinaryGap();
        Assert.assertEquals(2, bg.solution(9));
        Assert.assertEquals(5, bg.solution(1041));
        Assert.assertEquals(1, bg.solution(11));
        Assert.assertEquals(0, bg.solution(32));
        Assert.assertEquals(2, bg.solution(328));
    }
}
