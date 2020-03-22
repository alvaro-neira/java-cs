package com.alvaroneira.questions;

/**
 * Codility Demo Question: Find the smallest positive integer that does not occur in a given sequence.
 *
 * Task description
 *
 * This is a demo task.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given an array A of N integers, returns the smallest positive integer (greater than 0) that does not occur in A.
 *
 * For example, given A = [1, 3, 6, 4, 1, 2], the function should return 5.
 *
 * Given A = [1, 2, 3], the function should return 4.
 *
 * Given A = [−1, −3], the function should return 1.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−1,000,000..1,000,000].
 */

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

import static com.alvaroneira.utils.ArrayUtils.contains;
import static com.alvaroneira.utils.ArrayUtils.java8contains;

public class MissingInteger {
    public int solution(int[] A) {
        return better4(A);
        // write your code in Java SE 8
    }

    public static int better4(int[] A) {
        if (A.length == 0) {
            return 1;
        }
        Integer[] integers = IntStream.of(A).boxed().toArray(Integer[]::new);
        Arrays.sort(integers);
        if (integers[0] > 1) {
            return 1;
        }
        Integer last = integers[0];
        for (Integer integer : integers) {
            if (integer < 1) {
                continue;
            }
            if (last > 0) {
                if (integer - last > 1) {
                    return last + 1;
                }
            } else if (integer != 1) {
                return 1;
            }
            last = integer;
        }
        return Math.max(1, last + 1);
    }

    public static int better3(int[] A){
        if(A.length==0){
            return 1;
        }
        Integer[] integers = IntStream.of( A ).boxed().toArray( Integer[]::new );
        Arrays.sort(integers);
        if(integers[0]>1){
            return 1;
        }
        Integer last=integers[0];
        for(Integer integer : integers){
            if(integer<1){
                continue;
            }
            if(integer-last>1){
                return last>0 ? last+1:1;
            }
            last=integer;
        }
        return Math.max(1,last+1);
    }

    public static int better2(int[] A){
        if(A.length==0){
            return 1;
        }
        Integer[] integers = IntStream.of( A ).boxed().toArray( Integer[]::new );
        Arrays.sort(integers);
        if(integers[0]>1){
            return 1;
        }
        Integer last=integers[0];
        for(Integer i : integers){
            if(i<1){
                continue;
            }
            if(i-last>1){
                return last+1;
            }
            last=i;
        }
        return Math.max(1,last+1);
    }

    public static int better1(int[] A){
        if(A.length==0){
            return 1;
        }
        Integer[] integers = IntStream.of( A ).boxed().toArray( Integer[]::new );
        Arrays.sort(integers);
        Integer last=integers[0];
        for(Integer i : integers){
            if(i<1){
                continue;
            }
            if(i-last>1){
                return last+1;
            }
            last=i;
        }
        return Math.max(1,last+1);
    }
    public static int bruteForce(int[] A){
        int i=1;
        for(;i<=1000000;){
            if(!java8contains(A,i)){
                return i;
            }
            i++;
        }
        return i;
    }


    public static void main(String[] args) {
        MissingInteger mi=new MissingInteger();
        int[] A;
        Assert.assertEquals(mi.solution(new int[]{1, 3, 6, 4, 1, 2}), 5);
        Assert.assertEquals(mi.solution(new int[]{1,2,3}), 4);
        A=new int[2];
        A[0]=-1;A[1]=-2;
        Assert.assertEquals(mi.solution(A), 1);
        Assert.assertEquals(1, mi.solution(new int[]{2}));
        A=new int[2];
        A[0]=-1000000;A[1]=1000000;
        Assert.assertEquals(1, mi.solution(A));
        Assert.assertEquals(6, mi.solution(new int[]{5,4,1,2,3}));
        A=new int[2];
        A[0]=-1000000;A[1]=1;
        Assert.assertEquals(2, mi.solution(A));
        A=new int[2];
        A[0]=-1000000;A[1]=2;
        Assert.assertEquals(1, mi.solution(A));
        A=new int[6];
        A[0]=-1000000;
        A[1]=1;
        A[2]=2;
        A[3]=3;
        A[4]=4;
        A[5]=5;
        Assert.assertEquals(6, mi.solution(A));
        Assert.assertEquals(9, mi.solution(new int[]{2,1,5,4,8,7,10,3,6}));
    }

}
