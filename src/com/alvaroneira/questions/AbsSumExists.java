package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

/**

 For example, given array:

 A[0] =  1
 A[1] =  5
 A[2] =  2
 A[3] = -2

 sumExists(0) = 1 because there is [1,-1,1,-1] or [-1, 1, -1, 1]

 */
public class AbsSumExists {

    public static int sumExists(int[] A, int sum) {
        HashSet<Integer>[] DP = new HashSet[A.length];
        DP[0] = new HashSet<>();
        DP[0].add(Math.abs(A[0]));
        for (int j = 1; j < A.length; j++) {
            DP[j] = new HashSet<>();
            Iterator<Integer> itr = DP[j - 1].iterator();
            while (itr.hasNext()) {
                Integer previusSum = itr.next();
                int s1 = Math.abs(A[j] + previusSum);
                int s2 = Math.abs(A[j] - previusSum);
                DP[j].add(s1);
                DP[j].add(s2);
            }
        }
        return DP[A.length - 1].contains(sum) ? 1 : 0;
    }

    public static int sumExists1(int[] A, int sum, int maxAbs) {
        int N = A.length;

        int DP[][] = new int[N*maxAbs+1][N];

        //First Column
        DP[Math.abs(A[0])][0] = 1;
        for (int j = 1; j < N; j++) {
            for (int i = 0; i <= N*maxAbs; i++) {
                if(DP[i][j-1] == 0){
                    continue;
                }
                int s1=Math.abs(A[j]+DP[i][j-1]*i);
                int s2=Math.abs(A[j]-DP[i][j-1]*i);
                if(s1<=N*maxAbs){
                    DP[s1][j]=1;
                }
                if(s2<=N*maxAbs){
                    DP[s2][j]=1;
                }
            }
        }
//        ArrayUtils.printMatrix(DP);
        return DP[sum][N-1];
    }

    public static void main(String[] args) {
        AbsSumExists ase = new AbsSumExists();
        Assert.assertEquals(0, ase.sumExists(new int[]{1},2));
        Assert.assertEquals(1, ase.sumExists(new int[]{100},100));
        Assert.assertEquals(1, ase.sumExists(new int[]{100, -100, 100},100));
        Assert.assertEquals(1, ase.sumExists(new int[]{1, 5, 2, -2},0));
        Assert.assertEquals(1, ase.sumExists(new int[]{1, 1, 1, 2, 2, 5, 5, 5},0));
        Assert.assertEquals(1, ase.sumExists(new int[]{1, 5, 2, -2, 6},6));
        Assert.assertEquals(1, ase.sumExists(new int[]{91, 92, 93, 94, 95, 96},3));
        Assert.assertEquals(1, ase.sumExists(new int[]{91},91));
        Assert.assertEquals(1, ase.sumExists(new int[]{91, 92},183));
        Assert.assertEquals(1, ase.sumExists(new int[]{91, 92},183));
        Assert.assertEquals(1, ase.sumExists(new int[]{91, 92, 93, 94, 95},91));
        Assert.assertEquals(1, ase.sumExists(new int[]{91, 92, 93, 94, 95, 96},179));
        Assert.assertEquals(1, ase.sumExists(new int[]{91, 92, 93, 94, 95, 96, 97},82));
        Assert.assertEquals(1, ase.sumExists(new int[]{91, 92, 93, 94, 95, 96, 97, 100},182));
        Assert.assertEquals(1, ase.sumExists(new int[]{91, 92, 93, 94, 95, 96, 97, 100},0)); //{1, -1, 1, -1, 1, -1, -1, 1}
        Assert.assertEquals(1, ase.sumExists(new int[]{91, 92, 93, 94, 95, 96, 97},100));
        Assert.assertEquals(1, ase.sumExists(new int[]{91, 92, 93, 94, 95, 96},3));

    }
}
