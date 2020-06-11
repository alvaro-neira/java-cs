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
//        arrayList.set(2, 31);
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

        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(30, 1);
        hm.put(20, 1);
        hm.put(10, 1);
//        for (Integer key : hm.keySet()) {
//            System.out.println(key);
//        }

        Iterator it = hm.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }

        /**
         * HashSet
         */
        HashSet<String> hashSet = new HashSet<>();
        hashSet.add("hello");            //O(1)
        hashSet.contains("hello");       //O(1)
        hashSet.size();                  //O(1)
        hashSet.remove("hello");      //O(1)
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            //hasSet.remove() can lead to a ConcurrentModificationException
        }

        //To remove use this:
        for (Iterator<String> iter = hashSet.iterator(); it.hasNext(); ) {
            String str = iter.next();
            if (str.compareTo("any condition") == 0) {
                iter.remove();
            }
        }

        /**
         * BinarySearchTree
         */
        TreeNode root = new TreeNode(12);
        root.insertInOrder(10);
        root.height(); //O(log n)

//        int swa = 0;
//        for (int i = 1000; i <= 9999; i++) {
//            if ((i % 10) == 0) {
//                if ((i % 5) == 0 && (i % 7) == 0) {
//                    swa++;
//                }
//            }
//        }
//        System.out.println("swa="+swa);

        int swa = 0;
        for (int i = 0; i <= 1536; i++) {
            String s = ""+i;
            System.out.println(s);
            for(int j=0;j<s.length();j++){
                if(s.charAt(j)=='6'){
                    swa++;
                }
            }
        }
        String uno=new String("a");
        String dos=new String("a");
        if(uno!=dos) {
            System.out.println("swa=" + uno.equals(dos));
        }
        StringBuffer sf;
//        System.out.println(IsCasiPalindromo("abccba"));
//        System.out.println(IsCasiPalindromo("abccbx"));
//        System.out.println(IsCasiPalindromo("abccfg"));
//        System.out.println(NumMasPopular(new Integer[]{34, 31, 34, 77, 82}, 5));
//        System.out.println(NumMasPopular(new Integer[]{77, 101, 102, 101, 102, 525, 88}, 7));
//        System.out.println(NumMasPopular(new Integer[]{66}, 1));
//        System.out.println(NumMasPopular(new Integer[]{14, 14, 2342, 2342, 2342}, 5));
//        System.out.println(NumMasPopular(new Integer[]{}, 0));
    }

    //Java
    public double averageDistance(int x1, int y1, int x2, int y2, int x3, int y3) {
        double retVal = Math.sqrt((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1));
        retVal += Math.sqrt((y3 - y1) * (y3 - y1) + (x3 - x1) * (x3 - x1));
        retVal += Math.sqrt((y3 - y2) * (y3 - y2) + (x3 - x2) * (x3 - x2));
        return retVal / 3;
    }


    public static boolean IsCasiPalindromo(String str) {
        int ini = 0;
        int end = str.length() - 1;
        int limit = 0;
        while (ini <= end) {
            if (str.substring(ini, ini + 1).compareTo(str.substring(end, end + 1)) != 0) {
                limit++;
                if (limit >= 2) {
                    return false;
                }
            }
            ini++;
            end--;
        }
        return true;
    }

    //Java
    public static Integer NumMasPopular(Integer[] arr, Integer n) {
        HashMap<Integer, Integer> freq = new HashMap<Integer, Integer>();
        Integer maxFreq = 0;
        TreeSet<Integer> retVals = new TreeSet<Integer>();
        for (int i = 0; i < n; i++) {
            if (freq.containsKey(arr[i])) {
                Integer f = freq.get(arr[i]) + 1;
                freq.put(arr[i], f);
                if (f > maxFreq) {
                    maxFreq = f;
                    retVals = new TreeSet<Integer>();
                    retVals.add(arr[i]);
                } else if (f == maxFreq) {
                    retVals.add(arr[i]);
                }
            } else {
                freq.put(arr[i], 1);
                retVals = new TreeSet<Integer>();
                retVals.add(arr[i]);
            }
        }
        return retVals.first();
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
