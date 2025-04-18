package org.example;

public class Matrices {
    static int[][] matrix1 = {
            {1, 2},
            {2, 3},
            {3, 6}
    };

    static int[][] matrix2 = {
            {3, 4, 3, 3, 8, 1},
            {3, 4, 3, 3, 7, 11},
            {3, 4, 3, 2, 2, 1},
            {3, 4, 3, 1, 5, 13}
    };
    static int[][] matrix3 = {
            {2, 4, 5, 6},
            {1, 2, 2, 3},
            {1, 1, 1, 2},
            {3, 5, 1, 2},
            {1, 5, 1, 2},
            {5, 5, 7, 2},
    };
// 4*6 * 6 * 4  = 4*4
    static int[][] matrix4 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    static int[][] matrix5 = {
        {1,2,3}
    };
    static int[][] matrix6 = {
            {1},
            {5},
            {2}
    };
    public static void main(String[] args) {
        int[][] addedMatrices = addMatrices(matrix1, matrix2);
        System.out.println("matrix1");
        displayMatrix(matrix1);
        System.out.println("matrix2");
        displayMatrix(matrix2);
        System.out.println("Added matrices:");
        displayMatrix(addedMatrices);

        int[][] multipliedMatrices = multiplyMatrices(matrix5, matrix6);
        System.out.println("matrix5");
        displayMatrix(matrix5);
        System.out.println("matrix6:");
        displayMatrix(matrix6);
        System.out.println("Multiplied matrices:");
        displayMatrix(multipliedMatrices);

        int[][] rotatedMatrix = rotateMatrix(matrix4);
        System.out.println("Original matrix");
        displayMatrix(matrix4);
        System.out.println();
        System.out.println("Rotated matrix");
        displayMatrix(rotatedMatrix);
    }

    // two matrices must have the same number of rows and columns to be added
    public static int[][] addMatrices(int[][] matrix1, int[][] matrix2) {

      int [][] result = new int[matrix1.length][matrix1[0].length];

      for (int row = 0; row < matrix1.length; row++){
          for (int col = 0; col < matrix1[0].length; col++) {
              result[row][col] = matrix1[row][col] + matrix2[row][col];
          }
      }
        return result;
    };

    //the number of columns in the first matrix must be the same as number of rows in the second matrix
    //the product has number of rows from the first matrix and number of columns from the second matrix
    public static int[][] multiplyMatrices(int[][] matrix1, int[][] matrix2) {

        int[][] result = new int[matrix1.length][matrix2[0].length];

        for (int row = 0; row < matrix1.length; row++){
            for (int col = 0; col < matrix2[0].length; col++) {
                for (int i = 0; i < matrix2.length; i++) {
                    result[row][col] = result[row][col] + (matrix1[row][i] * matrix2[i][col]);
                }
            }
        }

        return result;
    }

    public static int[][] rotateMatrix(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                result[row][col] = matrix[(matrix[0].length - 1) - col][row];
            }
        }

        return result;
    }

    public static void displayMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for(int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
