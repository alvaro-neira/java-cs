package com.alvaroneira.algorithms;

import java.util.ArrayList;

public class MergeSort<T extends Comparable<T>> {
    T t;

    public MergeSort(Class<T> tClass, T t) {
        if (!tClass.isAssignableFrom(t.getClass())) throw new IllegalArgumentException("Must be a " + tClass);
        this.t = t;
    }

    private MergeSort(T t) {
        this.t = t;
    }

//    public static <T> MergeSort<T> of(Class<T> tClass, T t) {
//        if (!tClass.isAssignableFrom(t.getClass())) throw new IllegalArgumentException("Must be a " + tClass);
//        return new MergeSort(t);
//    }

    public void sort(T arr[]) {
        sort(arr, 0, arr.length - 1);
    }

    public void sort(T arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    public void sort(ArrayList<T> arr) {
        int l = 0;
        int r = arr.size() - 1;
        sort(arr, l, r);
    }

    public void sort(ArrayList<T> arr, int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = (l + r) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Find sizes of two subarrays to be merged
            int n1 = m - l + 1;
            int n2 = r - m;

            /* Create temp arrays */
            ArrayList<T> L = new ArrayList<T>(n1);
            ArrayList<T> R = new ArrayList<T>(n2);

            /*Copy data to temp arrays*/
            for (int i = 0; i < n1; ++i) {
                L.add(arr.get(l + i));
            }
            for (int j = 0; j < n2; ++j) {
                R.add(arr.get(m + 1 + j));
            }

            mergeSortMerge(arr, L, R, n1, n2, l);
        }
    }


    void merge(Comparable arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        Comparable L[] = new Comparable[n1];
        Comparable R[] = new Comparable[n2];

        /*Copy data to temp arrays*/
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public void mergeSortMerge(T[] result, T[] L, T[] R) {
        int n1 = L.length;
        int n2 = R.length;
        mergeSortMerge(result, L, R, n1, n2);
    }

    public void mergeSortMerge(T[] result, T[] L, T[] R, int n1, int n2) {
        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarry array
        int k = 0;
        while (i < n1 && j < n2) {
            if (L[i].compareTo(R[j]) <= 0) {
                result[k] = L[i];
                i++;
            } else {
                result[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            result[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            result[k] = R[j];
            j++;
            k++;
        }
    }

    public void mergeSortMerge(ArrayList<T> result, ArrayList<T> L, ArrayList<T> R, int n1, int n2) {
        mergeSortMerge(result, L, R, n1, n2, 0);
    }

    public void mergeSortMerge(ArrayList<T> result, ArrayList<T> L, ArrayList<T> R, int n1, int n2, int l) {
        // Initial index of merged subarry array
        int k = l;
        int i = 0, j = 0;
        while (i < n1 && j < n2) {
            if (L.get(i).compareTo(R.get(j)) <= 0) {
                result.set(k, L.get(i));
                i++;
            } else {
                result.set(k, R.get(j));
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            if(result.size()<=k){
                result.add(L.get(i));
            } else {
                result.set(k, L.get(i));
            }
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            if(result.size()<=k){
                result.add(R.get(j));
            } else {
                result.set(k, R.get(j));
            }
            j++;
            k++;
        }
    }
}
