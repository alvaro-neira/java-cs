package com.alvaroneira.questions;

import org.junit.Assert;

/**
 *
 */
public class Q2 {
    public int solution(int[] A) {
        // write your code in Java SE 8
        int len = 1;
        int v=A[0];
        for(;v!=-1;){
            len++;
            v=A[v];
        }
        return len;
    }

    public static void main(String[] args) {
        Q2 q = new Q2();
        Assert.assertEquals(4, q.solution(new int[]{1, 4, -1, 3, 2}));
        Assert.assertEquals(3, q.solution(new int[]{5, -101, -102, -1, -103,3}));
    }
}
