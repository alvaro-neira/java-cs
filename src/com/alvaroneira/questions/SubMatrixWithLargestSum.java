package com.alvaroneira.questions;

/**
 * Created by aneira on 3/12/17.
 * <p>
 * Given an NxN matrix of positive and negative integers, write code to find the sub-matrix with the largest possible sum
 */
public class SubMatrixWithLargestSum {
    public static int sumMatrix(int[][] M, int i, int j, int x, int y) {
        if (i < 0 || j < 0 || x < 0 || y < 0 || j < i || y < x) {
            System.err.println("i = [" + i + "], j = [" + j + "], x = [" + x + "], y = [" + y + "]");
            System.exit(-1);
        } else {
            System.out.println("i = [" + i + "], j = [" + j + "], x = [" + x + "], y = [" + y + "]");
        }

        if (j - i == 0 && y - x == 0) {
            return M[i][x];
        }
        int centerMatrix = j > i && y > x ? sumMatrix(M, i, j - 1, x, y - 1) : 0;
        int rightColumn = j > i ? sumMatrix(M, i, j - 1, y, y) : 0;
        int bottomRow = y > x ? sumMatrix(M, j, j, x, y - 1) : 0;
        int corner = M[j][y];
        return centerMatrix + rightColumn + bottomRow + corner;
    }

    public static int maxValueOnly(int[][] M, int i, int j, int x, int y) {
        return 0;
    }

    public static void main(String[] args) {
        int n = 3;
        int[][] M = {{7, 8, 9, -1},
                {-10, -11, -12, -2},
                {13, 14, 15, -3},
                {4, 5, 6, -6}};
        System.out.println(sumMatrix(M, 0, 3, 0, 3));
    }
}
