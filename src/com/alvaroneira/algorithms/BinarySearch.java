package com.alvaroneira.algorithms;

public class BinarySearch {
    public int solution(int[] A) {
        int n = A.length;
        int beg = 1;
        int end = n;
        int result = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if (check()) {
                end = mid - 1;
                result = mid;
            } else {
                beg = mid + 1;
            }
        }
        return result;
    }

    public static boolean check() {
        //Actual check here
        return true;
    }
}
