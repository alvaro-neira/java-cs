package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

public class CountDistinctSlices {
//    public static int MAX = 1000000000;

    public int solution(int M, int[] A) {
        int N = A.length;
        int count = 0;
        int tailPos = 0;
        int headPos = 1;
        while (tailPos < N) {
//            ArrayUtils.printArr(A, tailPos, headPos);
            count++;
            while (headPos < N && A[headPos] != A[headPos - 1] && !slowSearch(A, tailPos, headPos)) {
                headPos++;
//                ArrayUtils.printArr(A, tailPos, headPos);
                count++;
            }
            tailPos++;
            headPos = tailPos + 1;
        }
        return count;
    }

    public static boolean slowSearch(int[] A, int tailPos, int headPos) {
        for (int i = tailPos; i < headPos; i++) {
            if (A[headPos] == A[i]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        CountDistinctSlices cds = new CountDistinctSlices();
        Assert.assertEquals(1, cds.solution(11, new int[]{3}));
        Assert.assertEquals(9, cds.solution(11, new int[]{3, 4, 5, 5, 2}));
        Assert.assertEquals(5, cds.solution(11, new int[]{13, 13, 13, 13, 13}));
        Assert.assertEquals(7, cds.solution(11, new int[]{2, 13, 13, 13, 13, 13}));
        Assert.assertEquals(7, cds.solution(11, new int[]{13, 13, 13, 13, 13, 2}));
        Assert.assertEquals(9, cds.solution(11, new int[]{2, 13, 13, 13, 13, 13, 2}));
        Assert.assertEquals(10, cds.solution(11, new int[]{2, 2, 2, 2, 13, 13, 13, 13, 13}));
        Assert.assertEquals(15, cds.solution(11, new int[]{2, 2, 2, 2, 1, 1, 1, 1, 13, 13, 13, 13, 13}));
        Assert.assertEquals(8, cds.solution(11, new int[]{11, 11, 22, 22, 33, 33}));
        Assert.assertEquals(13, cds.solution(11, new int[]{2, 2, 2, 2, 1, 13, 13, 13, 13, 13}));
        Assert.assertEquals(15, cds.solution(11, new int[]{2, 2, 2, 2, 1, 13, 13, 13, 13, 13, 9}));
        Assert.assertEquals(17, cds.solution(11, new int[]{9, 2, 2, 2, 2, 1, 13, 13, 13, 13, 13, 9}));
        Assert.assertEquals(15, cds.solution(11, new int[]{1, 2, 3, 4, 5}));
        Assert.assertEquals(19, cds.solution(11, new int[]{9, 2, 2, 2, 2, 1, 2, 13, 13, 13, 13, 13, 9}));
        Assert.assertEquals(5, cds.solution(11, new int[]{2, 1, 2}));
        Assert.assertEquals(6, cds.solution(11, new int[]{2, 1, 2, 2}));
        Assert.assertEquals(7, cds.solution(11, new int[]{2, 1, 2, 1}));
        Assert.assertEquals(12, cds.solution(11, new int[]{2, 1, 0, 2, 1}));
        Assert.assertEquals(11, cds.solution(11, new int[]{2, 1, 0, 1, 2}));
        Assert.assertEquals(12, cds.solution(11, new int[]{2, 1, 0, 0, 1, 2}));
        Assert.assertEquals(12, cds.solution(11, new int[]{2, 1, 0, 2, 1}));
        Assert.assertEquals(16, cds.solution(11, new int[]{1, 2, 3, 4, 5, 5}));
        Assert.assertEquals(21, cds.solution(11, new int[]{5, 1, 2, 3, 4, 5, 5}));
        Assert.assertEquals(24, cds.solution(11, new int[]{3, 2, 1, 0, 1, 2, 3, 4}));
    }
}
