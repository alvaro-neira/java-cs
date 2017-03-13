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


    /**
     * Official solution:
     */
    public static int getMaxMatrix(int[][] original) {
        int maxArea = Integer.MIN_VALUE; // Important! Max could be < 0
        int rowCount = original.length;
        int columnCount = original[0].length;
        int[][] matrix = precomputeMatrix(original);
        for (int row1 = 0; row1 < rowCount; row1++) {
            for (int row2 = row1; row2 < rowCount; row2++) {
                for (int col1 = 0; col1 < columnCount; col1++) {
                    for (int col2 = col1; col2 < columnCount; col2++) {
                        maxArea = Math.max(maxArea, computeSum(matrix, row1, row2, col1, col2));
                    }
                }
            }
        }
        return maxArea;
    }


    private static int[][] precomputeMatrix(int[][] matrix) {
        int[][] sumMatrix = new int[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i == 0 && j == 0) {// rstcell
                    sumMatrix[i][j] = matrix[i][j];
                } else if (j == 0) {//cellin rstcolumn
                    sumMatrix[i][j] = sumMatrix[i - 1][j] + matrix[i][j];
                } else if (i == 0) {//cellin rstrow
                    sumMatrix[i][j] = sumMatrix[i][j - 1] + matrix[i][j];
                } else {
                    sumMatrix[i][j] = sumMatrix[i - 1][j] + sumMatrix[i][j - 1] - sumMatrix[i - 1][j - 1] +
                            matrix[i][j];
                }
            }
        }
        return sumMatrix;
    }

    private static int computeSum(int[][] sumMatrix, int i1, int i2, int j1, int j2) {
        if (i1 == 0 && j1 == 0) {//startsatrow0,column0
            return sumMatrix[i2][j2];
        } else if (i1 == 0) {//startatrow0
            return sumMatrix[i2][j2] - sumMatrix[i2][j1 - 1];
        } else if (j1 == 0) {//startatcolumn0
            return sumMatrix[i2][j2] - sumMatrix[i1 - 1][j2];
        } else {
            return
                    sumMatrix[i2][j2] - sumMatrix[i2][j1 - 1]
                            - sumMatrix[i1 - 1][j2] + sumMatrix[i1 - 1][j1 - 1];
        }
    }
}
