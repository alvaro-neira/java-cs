package com.alvaroneira.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.IntStream;

import static com.alvaroneira.utils.NumberUtils.numberOfOnes;

public class HashMapUtils {
    public static void printHashMap(HashMap<Integer,Integer> hm){
        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
        }
    }


    public static void printHashSet(HashSet<Integer> hs) {
        Iterator<Integer> itr = hs.iterator();
        while (itr.hasNext()) {
            Integer sum = itr.next();
            System.out.print(sum + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
    }
}
