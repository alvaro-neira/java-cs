package com.alvaroneira;

/**
 * Created by aneira on 3/12/17.
 * https://en.wikipedia.org/wiki/Maximum_subarray_problem
 */
public class MaxAdjacentSubArray {
    public static void main(String[] args) {
        int n = 7;
        int[] arr = new int[n];
        arr[0] = 5;
        arr[1] = -1;
        arr[2] = -2;
        arr[3] = 0;
        arr[4] = 999;
        arr[5] = -999;
        arr[6] = 666;
        System.out.println("max sub array="+maxSubArray(arr));
    }

    public static int maxSubArray(int[] arr) {
        int maxEndingHere=arr[0];
        int maxSoFar=arr[0];
        for(int val:arr){
            maxEndingHere=Math.max(val,maxEndingHere+val);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }
        return maxSoFar;
    }
}
