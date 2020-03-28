package com.alvaroneira.questions;

import com.alvaroneira.utils.ArrayUtils;
import org.junit.Assert;

import static com.alvaroneira.utils.ArrayUtils.printMatrix;

/**
 * A game for one player is played on a board consisting of N consecutive squares, numbered from 0 to N − 1. There is a number written on each square. A non-empty array A of N integers contains the numbers written on the squares. Moreover, some squares can be marked during the game.
 *
 * At the beginning of the game, there is a pebble on square number 0 and this is the only square on the board which is marked. The goal of the game is to move the pebble to square number N − 1.
 *
 * During each turn we throw a six-sided die, with numbers from 1 to 6 on its faces, and consider the number K, which shows on the upper face after the die comes to rest. Then we move the pebble standing on square number I to square number I + K, providing that square number I + K exists. If square number I + K does not exist, we throw the die again until we obtain a valid move. Finally, we mark square number I + K.
 *
 * After the game finishes (when the pebble is standing on square number N − 1), we calculate the result. The result of the game is the sum of the numbers written on all marked squares.
 *
 * For example, given the following array:
 *
 *     A[0] = 1
 *     A[1] = -2
 *     A[2] = 0
 *     A[3] = 9
 *     A[4] = -1
 *     A[5] = -2
 * one possible game could be as follows:
 *
 * the pebble is on square number 0, which is marked;
 * we throw 3; the pebble moves from square number 0 to square number 3; we mark square number 3;
 * we throw 5; the pebble does not move, since there is no square number 8 on the board;
 * we throw 2; the pebble moves to square number 5; we mark this square and the game ends.
 * The marked squares are 0, 3 and 5, so the result of the game is 1 + 9 + (−2) = 8. This is the maximal possible result that can be achieved on this board.
 *
 * Write a function:
 *
 * class Solution { public int solution(int[] A); }
 *
 * that, given a non-empty array A of N integers, returns the maximal result that can be achieved on the board represented by array A.
 *
 * For example, given the array
 *
 *     A[0] = 1
 *     A[1] = -2
 *     A[2] = 0
 *     A[3] = 9
 *     A[4] = -1
 *     A[5] = -2
 * the function should return 8, as explained above.
 *
 * Write an efficient algorithm for the following assumptions:
 *
 * N is an integer within the range [2..100,000];
 * each element of array A is an integer within the range [−10,000..10,000].
 */
public class DiceDynamic {
    public int solution(int[] A) {
        // write your code in Java SE 8

        int j;
        int i;

        int price = A.length;
        int MIN_INT = -10000 * price;
        int[] denominations = new int[]{1, 2, 3, 4, 5, 6};
        int n = denominations.length;

        //Create two-dimensional array
        int[][] DP = new int[n + 1][price + 1];
        //First Row
        for (int tmp1 = 0; tmp1 <= price; tmp1++) {
            DP[0][tmp1] = MIN_INT;
        }
        //First Column
        for(int tmp2 = 0; tmp2 <= n; tmp2++){
            DP[tmp2][0] = MIN_INT;
        }
        //Second Column
        for(int tmp3 = 1; tmp3 <= n; tmp3++){
            DP[tmp3][1] = A[0];
        }

        for (i = 1; i <= n; i++) {
            for (j = 2; j <= price; j++) {
                DP[i][j] = ArrayUtils.java8max(
                        DP[i - 1][j],
                        ArrayUtils.arrayMax(DP[i], j-6, j - i, MIN_INT) + A[j - 1],
                        DP[i][j - 1] + A[j - 1]);
            }
        }

        return DP[n][price];
    }

    public static void main(String[] args) {
        DiceDynamic dd = new DiceDynamic();
        Assert.assertEquals(9, dd.solution(new int[]{9}));
        Assert.assertEquals(19, dd.solution(new int[]{9, 10}));
        Assert.assertEquals(-1, dd.solution(new int[]{9, -10}));
        Assert.assertEquals(-19, dd.solution(new int[]{-9, -10}));
        Assert.assertEquals(9, dd.solution(new int[]{2, 3, 4}));
        Assert.assertEquals(-6, dd.solution(new int[]{-2, -3, -4}));
        Assert.assertEquals(2, dd.solution(new int[]{-2, -3, 4}));
        Assert.assertEquals(-3, dd.solution(new int[]{-2, 3, -4}));
        Assert.assertEquals(14, dd.solution(new int[]{2, 3, 4, 5}));
        Assert.assertEquals(10, dd.solution(new int[]{2, 3, -4, 5}));
        Assert.assertEquals(52, dd.solution(new int[]{2, -3, -4, 50}));
        Assert.assertEquals(62, dd.solution(new int[]{2, -3, -4, 50, 10}));
        Assert.assertEquals(62, dd.solution(new int[]{2, 50, -3, -4, 10}));
        Assert.assertEquals(9, dd.solution(new int[]{1, -2, 0, 9, -1}));
        Assert.assertEquals(8, dd.solution(new int[]{1, -2, 0, 9, -1, -2}));
        Assert.assertEquals(-3, dd.solution(new int[]{-3}));
        Assert.assertEquals(-7, dd.solution(new int[]{-3, -4}));
        Assert.assertEquals(-6, dd.solution(new int[]{-3, -4, -3}));
        Assert.assertEquals(-1, dd.solution(new int[]{-3, -4, -3, 2}));
        Assert.assertEquals(-8, dd.solution(new int[]{-3, -4, -3, 2, -7}));
        Assert.assertEquals(-2, dd.solution(new int[]{-3, -4, -3, 2, -7, -1}));
        Assert.assertEquals(-0, dd.solution(new int[]{0}));
        Assert.assertEquals(-4, dd.solution(new int[]{0, -4}));
        Assert.assertEquals(-5, dd.solution(new int[]{0, -4, -5}));
        Assert.assertEquals(-2, dd.solution(new int[]{0, -4, -5, -2}));
        Assert.assertEquals(-7, dd.solution(new int[]{0, -4, -5, -2, -7}));
        Assert.assertEquals(-9, dd.solution(new int[]{0, -4, -5, -2, -7, -9}));
        Assert.assertEquals(-3, dd.solution(new int[]{0, -4, -5, -2, -7, -9, -3}));
        Assert.assertEquals(-12, dd.solution(new int[]{0, -4, -5, -2, -7, -9, -3, -10}));
    }

    public int solution2(int[] A) {
        // write your code in Java SE 8

        int j;
        int i;

        int price = A.length;
        int MIN_INT=-10000*price;

        int[] denominations = new int[]{1, 2, 3, 4, 5, 6};
        int n = denominations.length;
        //create two-dimensional array
        int[][] DP = new int[n + 1][price + 1];
        for (int tmp1 = 1; tmp1 <= price; tmp1++) {
            DP[0][tmp1] = MIN_INT;
        }

        for (i = 1; i <= n; i++) {
            for (j = 1; j <= price; j++) {
                DP[i][j] = ArrayUtils.java8max(
                        DP[i - 1][j],
                        (j > i ? DP[i][j - i] + A[j - 1] : MIN_INT),
                        DP[i][j - 1] + A[j - 1]);
            }
        }

        return DP[n][price];
    }

    public int solution1(int[] A) {
        // write your code in Java SE 8

        int j;
        int i;

        int price = A.length;
        int MIN_INT=-10000*price;

        int[] denominations = new int[]{1, 2, 3, 4, 5, 6};
        int n = denominations.length;
        //create two-dimensional array
        int[][] DP = new int[n + 1][price + 1];

        for (int tmp1 = 1; tmp1 < DP[0].length; tmp1++) {
            DP[0][tmp1] = MIN_INT;
        }

        for (int tmp2 = 1; tmp2 <= n; tmp2++) {
            DP[tmp2][1] = A[0];
        }

        //Second row
        for (int tmp3 = 2; tmp3 <= price; tmp3++) {
            DP[1][tmp3] = A[tmp3 - 1] + DP[1][tmp3 - 1];
        }

//        for (i = 2; i <= n; i++) {
//            for (j = 2; j <= Math.min(price, 4); j++) {
//                DP[i][j] = ArrayUtils.java8max(
//                        DP[i - 1][j],
//                        DP[1][j] - (j > 2 ? A[j - 2] : 0) - (j > 3 && i >= 3 ? A[j - 3] : 0),
//                        DP[i][j - 1] + A[j - 1]);
//            }
//        }
        for (i = 2; i <= n; i++) {
            for(j=2;j<=price;j++){
                DP[i][j] = ArrayUtils.java8max(
                        DP[i-1][j],
                        DP[1][j] - ArrayUtils.sum(A,Math.max(1,j-i),j-1),
                        DP[i][j-1]+A[j-1]);
            }
        }
        printMatrix(DP);
        return DP[n][price];
    }

    public int solution0(int[] A) {
        // write your code in Java SE 8
        int fullSum=ArrayUtils.java8sum(A);
        int price=A.length;
        int MIN_INT=-10000*price;

        if(price<3){
            return fullSum;
        }
        if(price==3){
            return Math.max(fullSum,fullSum-A[1]);
        }
        if(price==4){
            return ArrayUtils.java8max(fullSum,fullSum-A[1],fullSum-A[2]);
        }
        int[] denominations=new int[]{1,2,3,4,5,6};

        int n = denominations.length;
        //create two-dimensional array
        int[][] DP = new int[n + 1][price + 1];

        for (int swa = 0; swa < 1; swa++) {
            for (int swe = 1; swe < DP[0].length; swe++) {
                DP[swa][swe] = MIN_INT;
            }
        }
        printMatrix(DP);
        int j;
        for (int i = 1; i <= n; i++) {
            for (j = 0; j < denominations[i - 1]; j++) {
                DP[i][j] = DP[i - 1][j];
            }
            for (j = denominations[i - 1]; j <= price; j++) {
//                DP[i][j] = Math.max(DP[i][j - denominations[i - 1]] + 1, DP[i - 1][j]);
                DP[i][j] = Math.max(DP[i][j - denominations[i - 1]] + 1, DP[i - 1][j]);
            }
        }
        printMatrix(DP);
        return DP[n][price];
    }
}
