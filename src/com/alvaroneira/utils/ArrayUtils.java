package com.alvaroneira.utils;

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
}
