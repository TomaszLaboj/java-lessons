package org.example;

public class Matrices2 {
    static int[][] matrix1 = {
            {1, 2},
            {2, 3},
            {3, 6}
    };

    // 3x2 x 2x4
    public static void main(String[] args) {
//        int[][] addedMatrices = addMatrices(matrix1, matrix2);
//        System.out.println("Added matrices:");
//        for (int[] array : addedMatrices){
//            for (int num : array) {
//                System.out.println(num);
//            }
//        }

        int[][] multipliedMatrices = multiplyMatrices(matrix2, matrix3);
        System.out.println("Multiplied matrices:");
        for (int[] array : multipliedMatrices){
            for (int num : array) {
                System.out.println(num);
            }
        }
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
    static int[][] matrix2 = {
            {3, 4},
            {5, 7},
            {0, 10}
    };
    static int[][] matrix3 = {
            {2, 4, 5, 6},
            {1, 2, 2, 3}
    };

    // r*c * r*c                   r c                                         i = 0            i = 1                     i = 2
    //                                                                         0, 0  * 0, 0   +  0,1 * 1,0
    // (0,0 * 0,0) + (0,1 * 1,0) = 0,0    (r,c * r,c) + (r,c+1 * r+1,c)       (r,i *   i,c) +   (r,i * i,c) + (r,c-(c-2) * r-(r-2),c)
    // (0,0 * 0,1) + (0,1 * 1,1) = 0,1    (r,c-1 * r,c) + (r,c * r+1,c)       (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)
    // (0,0 * 0,2) + (0,1 * 1,2) = 0,2    (r,c-2 * r,c) + (r,c-1 * r+1,c)     (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)
    // (0,0 * 0,3) + (0,1 * 1,3) = 0,3    (r,c-3 * r,c) + (r,c-2 * r+1,c)     (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)
    //
    // (1,0 * 0,0) + (1,1 * 1,0) = 1,0    (r,c * r-1,c) + (r,c+1 * r,c)       (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)
    // (1,0 * 0,1) + (1,1 * 1,1) = 1,1    (r,c-1 * r-1,c) + (r,c * r,c)       (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)
    // (1,0 * 0,2) + (1,1 * 1,2) = 1,2    (r,c-2 * r-1,c) + (r,c-1 * r,c)     (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)
    // (1,0 * 0,3) + (1,1 * 1,3) = 1,3    (r,c-3 * r-1,c) + (r,c-2 * r,c)     (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)
    //
    // (2,0 * 0,0) + (2,1 * 1,0) = 2,0    (r,c * r-2,c) + (r,c+1 * r-1,c)     (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)
    // (2,0 * 0,1) + (2,1 * 1,1) = 2,1    (r,c-1 * r-2,c) + (r,c * r-1,c)     (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)
    // (2,0 * 0,2) + (2,1 * 1,2) = 2,2    (r,c-2 * r-2,c) + (r,c-1 * r-1,c)   (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)
    // (2,0 * 0,3) + (2,1 * 1,3) = 2,3    (r,c-3 * r-2,c) + (r,c-2 * r-1,c)   (r,c-c * r-r,c) + (r,c-(c-1) * r-(r-1),c)

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

    static int[][] matrix4 = {
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
    };
    /* rotation
    {1, 2, 3},     {7, 4, 1}
    {4, 5, 6},     {8, 5, 2}
    {7, 8, 9}      {9, 6, 3}
    source => target
    r,c    r, c      r,c    r, c
    0,0 => 0, 2   r,c => c, row length -1 - r    3 - 1 - 0 = 2
    0,1 => 1, 2   r,c => c, row length -1 - r    3 - 1 - 0 = 2
    0,2 => 2, 2   r,c => c, row length -1 - r    3 - 1 - 0 = 2

    1,0 => 0, 1   r,c => c, row length -1 - r    3 - 1 - 0 = 2
    1,1 => 1, 1   r,c => c, row length -1 - r    3 - 1 - 0 = 2
    1,2 => 2, 1   r,c => c, row length -1 - r    3 - 1 - 0 = 2
    target <- source
    r, c   r  c
    0,0 <- 2, 0   r,c <- row length - c, r  (row length - 1) - col
    0,1 <- 1, 0   r,c <- row length - c, r
    0,2 <- 0, 0   r,c <- row length - c, r
    1,0 <- 2, 1   r,c <- row length - c, r
    1,1 <- 1, 1   r,c <- row length - c, r
    1,2 <- 0, 1   r,c <- row length - c, r

          result[col][(matrix.length-1)-row] = matrix[row][col];
          or
          ;
     */
    public static int[][] rotateMatrix(int[][] matrix) {
        int[][] result = new int[matrix[0].length][matrix.length];

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                result[row][col] = matrix[(matrix[0].length - 1) - col][col];
                //    row col
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
