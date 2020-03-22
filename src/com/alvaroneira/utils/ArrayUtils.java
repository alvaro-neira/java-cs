package com.alvaroneira.utils;

import java.util.stream.IntStream;

/**
 * Created by aneira on 3/18/17.
 */
public class ArrayUtils {
    public static String arr2str(int[] arr){
        String retVal="[";
        for(int elem: arr){
            retVal+=elem+",";
        }
        return retVal+"]";
    }

    public static boolean contains(int[] A, int n){
        for(int i=0;i<A.length;i++){
            if(A[i]==n){
                return true;
            }
        }
        return false;
    }

    public static boolean java8contains(int[] A, int n){
        return IntStream.of(A).anyMatch(x -> x == n);
    }
}
