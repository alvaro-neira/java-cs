package com.alvaroneira;

import com.alvaroneira.datastructures.TreeNode;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        /**
         * Arrays
         */
//        List aList = Arrays.asList();
//        aList.contains();

        /**
         * ArrayList
         */
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(11);
        arrayList.set(2, 31);
        arrayList.size();
//        arrayList.sort();


        /**
         * LinkedList (it's doubly linked)
         */
        LinkedList<Integer> head = new LinkedList<Integer>();

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
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("hello");            //O(1)
        hashSet.contains("hello");       //O(1)
        hashSet.size();                  //O(1)
        hashSet.remove("hello");      //O(1)
        Iterator<String> iterator = hashSet.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }

        /**
         * BinarySearchTree
         */
        TreeNode root = new TreeNode(12);
        root.insertInOrder(10);
        root.height(); //O(log n)

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
