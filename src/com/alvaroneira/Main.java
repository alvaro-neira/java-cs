package com.alvaroneira;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

public class Main {

    public static void main(String[] args) {
        /**
         * PriorityQueue
         */
        Comparator<Integer> maxHeapComparator = new MaxHeapComparator();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10, maxHeapComparator);
        maxHeap.add(1); // O(log n)
        maxHeap.isEmpty(); // O(1)
        maxHeap.peek(); //head of the queue, O(log n), does not remove
        maxHeap.poll(); //head of the queue, O(log n)

        /**
         * HashMap
         */
        HashMap<String, Double> hashMap = new HashMap<>();
        hashMap.put("key1", 1.1); //O(1)
        hashMap.containsKey("key1");//O(1)
        hashMap.get("key1");//O(1)

        /**
         * HashSet
         */
        HashSet<String> hashSet=new HashSet<>();
        hashSet.add("hello");            //O(1)
        hashSet.contains("hello");       //O(1)
        hashSet.size();                  //O(1)
        hashSet.remove("hello");      //O(1)

    }

    private static class MaxHeapComparator implements Comparator<Integer> {
        // Comparator that sorts integers from highest to lowest
        @Override
        public int compare(Integer o1, Integer o2) {
            // TODO Auto-generated method stub
            if (o1 < o2) return 1;
            else if (o1 == o2) return 0;
            else return -1;
        }
    }
}
