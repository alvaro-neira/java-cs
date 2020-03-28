package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

/**
 *
 */
public class Q1 {
    public int[] solution(int N) {
        // write your code in Java SE 8
        int[] retVal = new int[N];
        boolean even = ((N & 1) == 0);
        int n = N / 2;
        if(!even){
            n = n+1;
            retVal[0] = 0;
        }

        for(int i=0; i < (even ? n : n - 1); i++){
            retVal[2*i+(even ?  0 : 1)]=i+1;
            retVal[2*i+1+(even ?  0 : 1)]=(i+1)*(-1);
        }

        return retVal;
    }

    public static void main(String[] args) {
        Q1 q = new Q1();
        Assert.assertEquals(0, ArrayUtils.java8sum(q.solution(1)));
        Assert.assertEquals(0, ArrayUtils.java8sum(q.solution(2)));
        Assert.assertEquals(0, ArrayUtils.java8sum(q.solution(10000)));
        Assert.assertEquals(0, ArrayUtils.java8sum(q.solution(9999)));
        Assert.assertEquals(0, ArrayUtils.java8sum(q.solution(10000000)));
    }
}
