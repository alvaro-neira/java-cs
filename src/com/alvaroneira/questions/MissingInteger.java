package com.alvaroneira.questions;

/**
 * Codility Demo Question
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
import org.junit.Assert;
public class MissingInteger {
    public static int solution(int[] A) {
        System.out.println("start");
        return bruteForce(A);
        // write your code in Java SE 8
    }
    public static int bruteForce(int[] A){
        //List<Integer> aList = Arrays.asList(A);
        int i=1;
        for(;i<=1000000;){
            System.out.println(i);
            if(!contains(A,i)){
                return i;
            }
            i++;
        }
        return i;
    }

    public static boolean contains(int[] A, int n){
        for(int i=0;i<A.length;i++){
            if(A[i]==n){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        Assert.assertEquals(solution(new int[]{1, 3, 6, 4, 1, 2}), 5);
        Assert.assertEquals(solution(new int[]{1,2,3}), 4);
        int[] A=new int[2];
        A[0]=-1;A[1]=-2;
        Assert.assertEquals(solution(A), 1);
    }

}
