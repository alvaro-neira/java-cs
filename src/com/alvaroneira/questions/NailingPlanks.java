package com.alvaroneira.questions;

import com.alvaroneira.algorithms.MergeSort;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Arrays;

import static com.alvaroneira.utils.ArrayUtils.printArr;

/**
 *  NailingPlanks
 * Count the minimum number of nails that allow a series of planks to be nailed.
 *
 * Task description
 * You are given two non-empty arrays A and B consisting of N integers. These arrays represent N planks.
 * More precisely, A[K] is the start and B[K] the end of the K−th plank.
 *
 * Next, you are given a non-empty array C consisting of M integers. This array represents M nails. More precisely,
 * C[I] is the position where you can hammer in the I−th nail.
 *
 * We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].
 *
 * The goal is to find the minimum number of nails that must be used until all the planks are nailed. In other words,
 * you should find a value J such that all planks will be nailed after using only the first J nails. More precisely,
 * for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist a nail C[I] such that
 * I < J and A[K] ≤ C[I] ≤ B[K].
 *
 * For example, given arrays A, B such that:
 *
 *     A[0] = 1    B[0] = 4
 *     A[1] = 4    B[1] = 5
 *     A[2] = 5    B[2] = 9
 *     A[3] = 8    B[3] = 10
 * four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].
 *
 * Given array C such that:
 *
 *     C[0] = 4
 *     C[1] = 6
 *     C[2] = 7
 *     C[3] = 10
 *     C[4] = 2
 * if we use the following nails:
 *
 * 0, then planks [1, 4] and [4, 5] will both be nailed.
 * 0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
 * 0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
 * 0, 1, 2, 3, then all the planks will be nailed.
 * Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A, int[] B, int[] C); }
 *
 * that, given two non-empty arrays A and B consisting of N integers and a non-empty array C consisting of M integers,
 * returns the minimum number of nails that, used sequentially, allow all the planks to be nailed.
 *
 * If it is not possible to nail all the planks, the function should return −1.
 *
 * For example, given arrays A, B, C such that:
 *
 *     A[0] = 1    B[0] = 4
 *     A[1] = 4    B[1] = 5
 *     A[2] = 5    B[2] = 9
 *     A[3] = 8    B[3] = 10
 *
 *     C[0] = 4
 *     C[1] = 6
 *     C[2] = 7
 *     C[3] = 10
 *     C[4] = 2
 * the function should return 4, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N and M are integers within the range [1..30,000];
 * each element of arrays A, B, C is an integer within the range [1..2*M];
 * A[K] ≤ B[K].
 */

class NailingPlanks {
    public class Pair implements Comparable<Pair> {
        public int ini;
        public int end;

        public Pair(int ini, int end) {
            this.ini = ini;
            this.end = end;
        }

        public int compareTo(Pair other) {
            return this.end - other.end;
        }
    }

    public class Nail implements Comparable<Nail> {
        public int order;
        public int position;

        public Nail(int order, int position) {
            this.order = order;
            this.position = position;
        }

        public int compareTo(Nail other) {
            return this.position - other.position;
        }
    }

    /**
     * This is the optimal O((N + M) * log(M))
     * @param A
     * @param B
     * @param C
     * @return
     */
    public int solution(int[] A, int[] B, int[] C) {
        int n = A.length;
        int m = C.length;
        ArrayList<Pair> planks = new ArrayList<Pair>(n);
        ArrayList<Nail> nails = new ArrayList<Nail>(m);
        for (int i = 0; i < n; i++) {
            planks.add(new Pair(A[i], B[i]));
        }
        for (int j = 0; j < m; j++) {
            nails.add(new Nail(j, C[j]));
        }
        planks.sort(Pair::compareTo);
        nails.sort(Nail::compareTo);

        int beg = 1;
        int end = m;
        int result = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if (check9(nails, planks, mid)) {
                end = mid - 1;
                result = mid;
            } else {
                beg = mid + 1;
            }
        }
        return result;
    }

    public boolean check9(ArrayList<Nail> nails, ArrayList<Pair> planks, int mid) {
        int lastNail = 0;
        for (Pair plank : planks) {
            boolean nailed = false;
            for (int j = lastNail; j < nails.size(); j++) {
                Nail nail = nails.get(j);
                if (nail.order < mid && plank.ini <= nail.position && nail.position <= plank.end) {
                    nailed = true;
                    lastNail = j;
                    break;
                }
            }
            if (!nailed) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NailingPlanks np = new NailingPlanks();
        Assert.assertEquals(-1, np.solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{30}));
        Assert.assertEquals(-1, np.solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{60000, 60000, 60000}));
        Assert.assertEquals(6, np.solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 6, 6, 7, 10, 2}));
        Assert.assertEquals(4, np.solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}));
        Assert.assertEquals(7, np.solution(new int[]{2, 5, 6, 9}, new int[]{5, 6, 10, 11}, new int[]{1, 1, 1, 5, 7, 8, 11, 3}));
        Assert.assertEquals(8, np.solution(new int[]{2, 10}, new int[]{4, 12}, new int[]{1, 1, 1, 3, 5, 5, 5, 11, 20, 20, 20}));
        Assert.assertEquals(1, np.solution(new int[]{2, 3, 4}, new int[]{20, 19, 18}, new int[]{9, 9, 9, 9}));
        Assert.assertEquals(4, np.solution(new int[]{8, 5, 4, 1}, new int[]{10, 9, 5, 4}, new int[]{4, 6, 7, 10, 2}));
        Assert.assertEquals(4, np.solution(new int[]{8, 5, 4, 1}, new int[]{10, 9, 5, 4}, new int[]{4, 6, 7, 10, 2, 2}));
        Assert.assertEquals(8, np.solution(new int[]{1, 9, 9, 5, 1, 1, 5, 1, 9, 9}, new int[]{8, 9, 9, 9, 9, 6, 6, 9, 9, 9}, new int[]{1, 10, 7, 1, 10, 6, 6, 9, 4, 7}));
        Assert.assertEquals(4, np.solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}));
        Assert.assertEquals(6, np.solution(new int[]{2, 3, 4, 6}, new int[]{9, 7, 7, 6}, new int[]{10, 12, 1, 7, 3, 6, 13}));
        System.out.println("OK");
    }
    public int solution8(int[] A, int[] B, int[] C) {
        int n = A.length;
        int m = C.length;
        int beg = 1;
        int end = m;
        int result = -1;
        int mid = 0;
        Integer[] nailsA = new Integer[0];
        ArrayList<Pair> planks = new ArrayList<Pair>();
        planks.sort(Pair::compareTo);
        MergeSort<Integer> integerSort = new MergeSort(Integer.class, new Integer(10));
        MergeSort<Pair> pairSort = new MergeSort(Pair.class, new Pair(1, 2));
        pairSort.sort(planks);
        while (beg <= end) {
            int oldMid = mid;
            mid = (beg + end) / 2;
            int nailsBsize = mid - oldMid;
            if (nailsBsize > 0) {
                Integer[] nailsB = new Integer[nailsBsize];
                for (int i = 0; i < nailsBsize; i++) {
                    nailsB[i] = C[oldMid + i];
                }
                integerSort.sort(nailsB);
                Integer[] sortResult = new Integer[nailsA.length + nailsB.length];
                integerSort.mergeSortMerge(sortResult, nailsA, nailsB);
                nailsA = sortResult;
            } else {
                nailsA = new Integer[mid];
                for (int i = 0; i < mid; i++) {
                    nailsA[i] = C[i];
                }
                integerSort.sort(nailsA);
            }
            if (check8(nailsA, planks)) {
                end = mid - 1;
                result = mid;
            } else {
                beg = mid + 1;
            }
        }
        return result;
    }

    public boolean check8(Integer[] nailsA, ArrayList<Pair> planksA) {
        int lastNail = 0;
        for(Pair plank:planksA){
            boolean nailed = false;
            for (int j = lastNail; j < nailsA.length; j++) {
                if (plank.ini <= nailsA[j] && nailsA[j] <= plank.end) {
                    nailed = true;
                    lastNail = j;
                    break;
                }
            }
            if (!nailed) {
                return false;
            }
        }
        return true;
    }

    public boolean check9(Integer[] nails, ArrayList<Pair> planks) {
        int lastNail = 0;
        for(Pair plank:planks){
            boolean nailed = false;
            for (int j = lastNail; j < nails.length; j++) {
                if (plank.ini <= nails[j] && nails[j] <= plank.end) {
                    nailed = true;
                    lastNail = j;
                    break;
                }
            }
            if (!nailed) {
                return false;
            }
        }
        return true;
    }

//    public int solution7(int[] A, int[] B, int[] C) {
//        int n = A.length;
//        int m = C.length;
//        int beg = 1;
//        int end = m;
//        int result = -1;
//        int mid = 0;
//        Integer[] nailsA = new Integer[0];
//        Pair[] planksA;
//        MergeSort<Integer> integerSort = MergeSort.of(Integer.class, 1);
//        MergeSort<Pair> pairSort = MergeSort.of(Pair.class, new Pair(1, 2));
//        while (beg <= end) {
//            int oldMid = mid;
//            mid = (beg + end) / 2;
//            int nailsBsize = mid - oldMid;
//            if (nailsBsize > 0) {
//                Integer[] nailsB = new Integer[nailsBsize];
//                for (int i = 0; i < nailsBsize; i++) {
//                    nailsB[i] = C[oldMid + i];
//                }
//                integerSort.sort(nailsB);
//                nailsA = MergeSort.mergeSortMerge(nailsA, nailsB);
//            } else {
//                nailsA = new Integer[mid];
//                for (int i = 0; i < mid; i++) {
//                    nailsA[i] = C[i];
//                }
//                integerSort.sort(nailsA);
//            }
//            planksA = new Pair[n];
//            Pair[] planksB = new Pair[n];
//            int nPlanksA = 0;
//            int nPlanksB = 0;
//            for (int i = 0; i < n; i++) {
//                if (A[i] <= nailsA[mid - 1]) {
//                    planksA[nPlanksA++] = new Pair(A[i], B[i]);
//                } else {
//                    planksB[nPlanksB++] = new Pair(A[i], B[i]);
//                }
//            }
//            pairSort.sort(planksA, 0, nPlanksA - 1);
//            if (check7(nailsA, planksA, nPlanksA, nPlanksB)) {
//                end = mid - 1;
//                result = mid;
//            } else {
//                beg = mid + 1;
//            }
//        }
//        return result;
//    }

    /**
     * O((N + M) * log(M))
     *
     * @param origA
     * @param origB
     * @param C
     * @return
     */
    public int solution4(int[] origA, int[] origB, int[] C) {
        Integer N = origA.length;
        int M = C.length;

        //Sort by end
        ArrayList<ArrayList<Integer>> list = new ArrayList();
        for (int i = 0; i < N; i++) {
            int ini = origA[i];
            int end = origB[i];
            ArrayList<Integer> segment = new ArrayList();
            segment.add(ini);
            segment.add(end);
            list.add(segment);
        }
        list.sort((p1, p2) -> p1.get(1) - p2.get(1));
        int[] A = new int[N];
        int[] B = new int[N];
        int j = 0;
        for (ArrayList<Integer> seg : list) {
            A[j] = seg.get(0);
            B[j] = seg.get(1);
            j++;
        }

        int ini = 0;
        int med = (int) Math.ceil(M / 2);
        int end = M;

        //loop
        while (med < M && med > 0 && ini < med && med < end) {
            int[] nails = new int[med];
            System.arraycopy(C, 0, nails, 0, med);
            if (check(A, B, nails)) {
                end = med;
                med = (ini + med) / 2;
            } else {
                ini = med;
                med = med + (int) Math.ceil((end - med) / 2);
            }

        }

        //end
        int[] nails = new int[end];
        System.arraycopy(C, 0, nails, 0, end);
        if (check(A, B, nails)) {
            return end;
        } else {
            return -1;
        }

    }

    public static boolean check(int[] A, int[] B, int[] nails) {
        Arrays.sort(nails);
        int lastNail = 0;
        for (int i = 0; i < A.length; i++) {
            boolean nailed = false;
            for (int j = lastNail; j < nails.length; j++) {
                if (A[i] <= nails[j] && nails[j] <= B[i]) {
                    nailed = true;
                    lastNail = j;
                    break;
                }
            }
            if (!nailed) {
                return false;
            }
        }
        return true; //never reach this?
    }

    /**
     * O((N+M)*N)
     *
     * @param A
     * @param B
     * @param C
     * @return
     */
    public int solution5(int[] A, int[] B, int[] C) {
        int m = C.length;
        int beg = 1;
        int end = m;
        int result = -1;
        while (beg <= end) {
            int mid = (beg + end) / 2;
            if (check5(A, B, C, mid)) {
                end = mid - 1;
                result = mid;
            } else {
                beg = mid + 1;
            }
        }
        return result;
    }

    public boolean check5(int[] A, int[] B, int[] C, int mid) {
        int n = A.length;
        int[] nails = new int[mid];
        System.arraycopy(C, 0, nails, 0, mid);
        Arrays.sort(nails);
        ArrayList<Pair> planksIn = new ArrayList<Pair>();
        ArrayList<Pair> planksOut = new ArrayList<Pair>();
        for (int i = 0; i < n; i++) {
            if (A[i] <= nails[mid - 1]) {
                planksIn.add(new Pair(A[i], B[i]));
            } else {
                return false;
            }
        }
        planksIn.sort((p1, p2) -> p1.end - p2.end);
        int lastNail = 0;
        for (Pair plank : planksIn) {
            boolean nailed = false;
            for (int j = lastNail; j < nails.length; j++) {
                if (plank.ini <= nails[j] && nails[j] <= plank.end) {
                    nailed = true;
                    lastNail = j;
                    break;
                }
            }
            if (!nailed) {
                return false;
            }
        }
        return true;
    }

    public static final int MAXNAILPOS = 10;

    public static void fancyTest(NailingPlanks np, int n, int m) {
        int[] A = new int[n];
        int[] B = new int[n];
        int[] C = new int[m];
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < n; i++) {
            int ini = (int) (Math.random() * MAXNAILPOS) + 1;
            int end = (int) (Math.random() * (MAXNAILPOS - ini)) + ini;
            ArrayList<Integer> segment = new ArrayList<Integer>();
            segment.add(ini);
            segment.add(end);
            list.add(segment);
        }
        int j = 0;
        for (ArrayList<Integer> pair : list) {
            A[j] = pair.get(0);
            B[j++] = pair.get(1);
        }
        for (int i = 0; i < m; i++) {
            int nailPos = (int) (Math.random() * MAXNAILPOS) + 1;
            C[i] = nailPos;
        }
        printArr(A);
        printArr(B);
        printArr(C);
        Assert.assertEquals(np.solution5(A, B, C), np.solution4(A, B, C));
//        Assert.assertNotEquals(-1,np.solution(A, B, C));
    }

//    public int solution6(int[] A, int[] B, int[] C) {
//        int m = C.length;
//        int beg = 1;
//        int end = m;
//        int result = -1;
//        MergeSort<Integer> integerSort = MergeSort.of(Integer.class, 1);
//        MergeSort<Pair> pairSort = MergeSort.of(Pair.class, new Pair(1, 2));
//        while (beg <= end) {
//            int mid = (beg + end) / 2;
//            if (check6(A, B, C, mid, integerSort, pairSort)) {
//                end = mid - 1;
//                result = mid;
//            } else {
//                beg = mid + 1;
//            }
//        }
//        return result;
//    }

    public boolean check6(int[] A, int[] B, int[] C, int mid, MergeSort<Integer> integerSort, MergeSort<Pair> pairSort) {
        int n = A.length;
        Integer[] nails = new Integer[mid];
        for (int i = 0; i < mid; i++) {
            nails[i] = C[i];
        }
        integerSort.sort(nails);
        Pair[] planksIn = new Pair[n];
        int nPlanksIn = 0;
        for (int i = 0; i < n; i++) {
            if (A[i] <= nails[mid - 1]) {
                planksIn[nPlanksIn++] = new Pair(A[i], B[i]);
            } else {
                return false;
            }
        }
        pairSort.sort(planksIn);
        int lastNail = 0;
        for (int i = 0; i < nPlanksIn; i++) {
            Pair plank = planksIn[i];
            boolean nailed = false;
            for (int j = lastNail; j < nails.length; j++) {
                if (plank.ini <= nails[j] && nails[j] <= plank.end) {
                    nailed = true;
                    lastNail = j;
                    break;
                }
            }
            if (!nailed) {
                return false;
            }
        }
        return true;
    }

    public int solution3(int[] A, int[] B, int[] C) {
        int M = C.length;
        int ini = 0;
        int med = (int) Math.ceil(M / 2);
        int end = M;

        //loop
        while (med < M && med > 0 && ini < med && med < end) {
            if (checkNtimesM(A, B, C, med)) {
                end = med;
                med = (ini + med) / 2;
            } else {
                ini = med;
                med = med + (int) Math.ceil((end - med) / 2);
            }

        }

        //end
        if (checkNtimesM(A, B, C, end)) {
            return end;
        } else {
            return -1;
        }

    }

    /**
     * N*M check
     *
     * @param A
     * @param B
     * @param C
     * @param k
     * @return
     */
    public static boolean checkNtimesM(int[] A, int[] B, int[] C, int k) {
        Integer N = A.length; //N planks
        int M = C.length; //M nails
        for (int i = 0; i < N; i++) {
            boolean nailed = false;
            for (int j = 0; j < k; j++) {
                if (A[i] <= C[j] && C[j] <= B[i]) {
                    nailed = true;
                    break;
                }
            }
            if (!nailed) {
                return false;
            }
        }
        return true;
    }
}
