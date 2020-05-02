package com.alvaroneira.questions;

import org.junit.Assert;

import java.util.*;

/**
 *  NailingPlanks
 * Count the minimum number of nails that allow a series of planks to be nailed.
 *
 * Task description
 * You are given two non-empty arrays A and B consisting of N integers. These arrays represent N planks. More precisely, A[K] is the start and B[K] the end of the K−th plank.
 *
 * Next, you are given a non-empty array C consisting of M integers. This array represents M nails. More precisely, C[I] is the position where you can hammer in the I−th nail.
 *
 * We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].
 *
 * The goal is to find the minimum number of nails that must be used until all the planks are nailed. In other words, you should find a value J such that all planks will be nailed after using only the first J nails. More precisely, for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist a nail C[I] such that I < J and A[K] ≤ C[I] ≤ B[K].
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
 * that, given two non-empty arrays A and B consisting of N integers and a non-empty array C consisting of M integers, returns the minimum number of nails that, used sequentially, allow all the planks to be nailed.
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
public class NailingPlanks {
    public int solution(int[] origA, int[] origB, int[] C) {
        Integer N = origA.length;
        int M = C.length;

        //Sort
        TreeMap<Integer, Integer> planks = new TreeMap<Integer, Integer>();
        for (int i = 0; i < N; i++) {
            planks.put(origB[i], origA[i]);
        }
        int[] A = new int[N];
        int[] B = new int[N];
        int j = 0;
        for (Map.Entry<Integer, Integer> entry : planks.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            A[j] = value;
            B[j] = key;
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

    public int solution3(int[] A, int[] B, int[] C) {
        Integer N = A.length;
        int M = C.length;
        int ini = 0;
        int med = (int) Math.ceil(M / 2);
        int end = M;

        //loop
        while (med < M && med > 0 && ini < med && med < end) {
            if (check3(A, B, C, med)) {
                end = med;
                med = (ini + med) / 2;
            } else {
                ini = med;
                med = med + (int) Math.ceil((end - med) / 2);
            }

        }

        //end
        if (check3(A, B, C, end)) {
            return end;
        } else {
            return -1;
        }

    }

    public int solution2(int[] A, int[] B, int[] C) {
        Integer N = A.length; //N planks
        int M = C.length; //M nails
        boolean[] isNailed = new boolean[N];
        int nNailed = 0;
        for (int i = 0; i < M; i++) {
            for (int plank = 0; plank < N; plank++) {
                if (!isNailed[plank] && A[plank] <= C[i] && C[i] <= B[plank]) {
                    isNailed[plank] = true;
                    nNailed++;
                    if (nNailed == N) {
                        return i + 1;
                    }
                }
            }
        }
        return -1;
    }

    public int solution1(int[] A, int[] B, int[] C) {
        Integer N = A.length;
        int M = C.length;
        int ini = 0;
        int med = (int) Math.ceil(M / 2);
        int end = M;
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (Integer i = 0; i < N; i++) {
            hashSet.add(i);
        }

        //loop
        while (med < M && med > 0 && ini < med && med < end) {
            if (check2(A, B, C, med, hashSet)) {
                end = med;
                med = (ini + med) / 2;
                for (Integer i = 0; i < N; i++) {
                    hashSet.add(i);
                }
            } else {
                ini = med;
                med = med + (int) Math.ceil((end - med) / 2);
            }

        }

        //end
        if (check2(A, B, C, end, hashSet)) {
            return end;
        } else {
            return -1;
        }

    }

    public static boolean check2(int[] A, int[] B, int[] C, int k, HashSet<Integer> hs) {
        for (int j = 0; j < k; j++) {
            for (Iterator<Integer> it = hs.iterator(); it.hasNext(); ) {
                Integer plank = it.next();
                if (A[plank] <= C[j] && C[j] <= B[plank]) {
                    it.remove(); //assume it removes from the hash set

                }
            }
        }

        return hs.isEmpty();

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
    public static boolean check3(int[] A, int[] B, int[] C, int k) {
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


    public static void main(String[] args) {
        NailingPlanks np = new NailingPlanks();
        // Assert.assertTrue(check(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}, 5));
        // Assert.assertTrue(check(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}, 4));
        // Assert.assertFalse(check(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}, 0));
        // Assert.assertFalse(check(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}, 3));
        // Assert.assertFalse(check(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}, 2));
        // Assert.assertFalse(check(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}, 1));
        // Assert.assertFalse(check(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}, 0));
        Assert.assertEquals(-1, np.solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{30}));
        Assert.assertEquals(-1, np.solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{60000, 60000, 60000}));
        Assert.assertEquals(6, np.solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 6, 6, 7, 10, 2}));
        Assert.assertEquals(4, np.solution(new int[]{1, 4, 5, 8}, new int[]{4, 5, 9, 10}, new int[]{4, 6, 7, 10, 2}));
        Assert.assertEquals(7, np.solution(new int[]{2, 5, 6, 9}, new int[]{5, 6, 10, 11}, new int[]{1, 1, 1, 5, 7, 8, 11, 3}));
        Assert.assertEquals(8, np.solution(new int[]{2, 10}, new int[]{4, 12}, new int[]{1, 1, 1, 3, 5, 5, 5, 11, 20, 20, 20}));
        Assert.assertEquals(1, np.solution(new int[]{2, 3, 4}, new int[]{20, 19, 18}, new int[]{9, 9, 9, 9}));
        System.out.println("OK");
    }
}
