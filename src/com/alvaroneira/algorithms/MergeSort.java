package com.alvaroneira.algorithms;

import com.alvaroneira.questions.NailingPlanks;

public class MergeSort<T> {
    T t;

    public MergeSort(Class<T> tClass, T t) {
        if (!tClass.isAssignableFrom(t.getClass())) throw new IllegalArgumentException("Must be a " + tClass);
        this.t = t;
    }

    private MergeSort(T t) {
        this.t = t;
    }

    public static <T> MergeSort<T> of(Class<T> tClass, T t) {
        if (!tClass.isAssignableFrom(t.getClass())) throw new IllegalArgumentException("Must be a " + tClass);
        return new MergeSort(t);
    }

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

    void merge(Object arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        Object L[] = new Object[n1];
        Object R[] = new Object[n2];

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

            if (compare(L[i], R[j]) <= 0) {
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

    int compare(Object o1, Object o2) {
        if (t.getClass() == NailingPlanks.Pair.class) {
            return ((NailingPlanks.Pair) o1).end - ((NailingPlanks.Pair) o2).end;
        } else {
            return ((Integer) o1) - ((Integer) o2);
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

            if (compareObjects(L[i], R[j]) <= 0) {
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

    public static int compareObjects(Object o1, Object o2) {
        if (o1 instanceof NailingPlanks.Pair && o2 instanceof NailingPlanks.Pair) {
            return ((NailingPlanks.Pair) o1).end - ((NailingPlanks.Pair) o2).end;
        } else {
            return ((Integer) o1) - ((Integer) o2);
        }
    }
}
