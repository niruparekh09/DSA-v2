import java.util.Arrays;

public class Rotate_Matrix_By_90_Degree {
    public static void main(String[] args) {
        int[][] matrix = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}};
        rotate(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    /*
     * Approach: Transpose & Reverse
     * Pattern: Matrix Traversal / Math
     * Time Complexity: O(N^2) - We visit every cell in the N*N matrix twice.
     * Space Complexity: O(1) - Modified in-place without allocating a new matrix.
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;

        // Step 1: Transpose the matrix (Rows become Columns)
        // Logic: Swap elements across the main diagonal (matrix[i][j] <-> matrix[j][i]).
        for (int i = 0; i < n - 1; i++) {
            // Key Detail: j starts at i + 1. We only traverse the upper triangle
            // to avoid swapping the same elements back (double swap).
            for (int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 2: Reverse each row
        // Logic: Mathematically, Transpose + Horizontal Reflection = +90 deg Rotation.
        for (int i = 0; i < n; i++) {
            reverse(matrix[i]);
        }
    }

    // Helper: Standard Two-Pointer Reversal
    public static void reverse(int[] arr) {
        int s = 0;
        int e = arr.length - 1;
        while (s <= e) {
            int temp = arr[s];
            arr[s] = arr[e];
            arr[e] = temp;
            s++;
            e--;
        }
    }
}