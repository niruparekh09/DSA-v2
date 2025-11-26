import java.util.Arrays;

public class Set_Matrix_Zeroes {
    public static void main(String[] args) {
        int[][] matrix = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        setZeroesOptimal(matrix);
        System.out.println(Arrays.deepToString(matrix));
    }

    /*
     * Approach: Marker Arrays (Better)
     * Pattern: Matrix Traversal / Hashing (Boolean mapping)
     * Time Complexity: O(N * M) - Two passes through the matrix.
     * Space Complexity: O(N + M) - Uses separate arrays to store row/col states.
     */
    private static void setZeroes(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int[] col = new int[m];
        int[] row = new int[n];

        // Pass 1: Mark rows and columns that contain at least one zero
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    row[i] = 1;
                    col[j] = 1;
                }
            }
        }

        // Pass 2: Update matrix based on markers
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (row[i] == 1 || col[j] == 1) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    /*
     * Approach: In-Place (Using first Row/Col as markers)
     * Pattern: Matrix Traversal / Space Optimization
     * Time Complexity: O(N * M) - Linear traversal.
     * Space Complexity: O(1) - No extra space; we use the matrix itself.
     */
    private static void setZeroesOptimal(int[][] matrix) {
        int col0 = 1; // Separate flag for the 0th column, as matrix[0][0] overlaps with Row 0.
        int n = matrix.length;
        int m = matrix[0].length;

        // Step 1: Traverse and mark the first row/col based on internal values
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 0) {
                    // Mark the i-th row (stored in first column)
                    matrix[i][0] = 0;

                    // Mark the j-th column (stored in first row)
                    if (j != 0) matrix[0][j] = 0;
                    else
                        // Handle collision: If it's the 0th column, use the separate col0 variable
                        col0 = 0;
                }
            }
        }

        // Step 2: Process the inner sub-matrix (1,1) to (N,M)
        // Key Logic: We iterate skipping the first row/col to avoid corrupting our "flags" before we read them.
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                // Check markers in first row/col
                if (matrix[0][j] == 0 || matrix[i][0] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }

        // Step 3: Handle the 0th Row
        // Logic: matrix[0][0] stores the status for the entire first row
        if (matrix[0][0] == 0) {
            for (int j = 0; j < m; j++)
                matrix[0][j] = 0;
        }

        // Step 4: Handle the 0th Column
        // Logic: col0 stores the status for the entire first column
        if (col0 == 0) {
            for (int i = 0; i < n; i++) {
                matrix[i][0] = 0;
            }
        }
    }
}