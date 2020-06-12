package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MatrixSortedByRows {
    public static void main(String[] args) {
        Integer[][] matrix = new Integer[3][];
        matrix[0] = new Integer[]{20, 40, 80};
        matrix[1] = new Integer[]{5, 60, 90};
        matrix[2] = new Integer[]{45, 50, 55};
        printSorted(matrix);
    }

    private static class MinHeapComparator implements Comparator<Record> {
        @Override
        public int compare(Record r1, Record r2) {
            return r1.key - r2.key;
        }
    }

    public static void printSorted(Integer[][] matrix) {
        int[] currentIndexes = new int[matrix.length];
        Integer N = matrix[0].length;
        Comparator<Record> minHeapComparator = new MinHeapComparator();
        PriorityQueue<Record> heap = new PriorityQueue<>(N, minHeapComparator);
        for (Integer j = 0; j < matrix.length; j++) {
            heap.add(new Record(matrix[j][currentIndexes[j]], j));
        }
        Integer printed = 0;
        StringBuilder retVal = new StringBuilder();
        while (printed < matrix.length * N) {
            Record popped = heap.poll();
            retVal.append(popped.key + ",");
            printed++;
            if (currentIndexes[popped.row] < (N - 1)) {
                heap.add(new Record(matrix[popped.row][++(currentIndexes[popped.row])], popped.row));
            }
        }
        System.out.println("retVal=" + retVal);
    }

    private static class Record {
        Integer key;
        Integer row;

        public Record(Integer key, Integer row) {
            this.key = key;
            this.row = row;
        }
    }
}
