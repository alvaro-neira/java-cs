package com.alvaroneira.datastructures;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * PriorityQueue: maintains the given order
 * Only if the elements are equal, it does FIFO
 */
public class PriorityQueueTest {
    public static void main(String[] args) {
        Comparator<String> maxHeapComparator = new MaxHeapComparator();
        PriorityQueue<String> pQueue=new PriorityQueue<>(10, maxHeapComparator);
        pQueue.add("D");
        pQueue.add("C");
        pQueue.add("B");
        pQueue.add("A");
        pQueue.add("Z");

        while(!pQueue.isEmpty()){
            System.out.println(pQueue.poll());
        }
    }

    //Reverse String comparator
    private static class MaxHeapComparator implements Comparator<String> {
        @Override
        public int compare(String s1, String s2) {
            return s2.compareTo(s1);
        }
    }
}
