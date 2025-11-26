public class Search_In_Matrix_Two {
    public static void main(String[] args) {
        System.out.println(
                searchMatrixOptimal(new int[][]{
                        {1, 4, 7, 11, 15},
                        {2, 5, 8, 12, 19},
                        {3, 6, 9, 16, 22},
                        {10, 13, 14, 17, 24},
                        {18, 21, 23, 26, 30}
                }, 5)
        );
    }

    /*
     * Approach: Row-wise Binary Search (Better)
     * Pattern: Binary Search
     * Time Complexity: O(N * log M) - We perform Binary Search on every row individually.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        boolean isPresent = false;
        for (int i = 0; i < matrix.length; i++) {
            // Check every row independently since we cannot guarantee continuity between rows
            isPresent = search(matrix[i], target);
            if (isPresent) return true;
        }
        return isPresent;
    }

    // Helper: Standard Binary Search
    private static boolean search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        if (target > arr[high]) return false; // Optimization: Skip if target exceeds max of row

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }

    /*
     * Approach: Staircase Search / Pointer Elimination (Optimal)
     * Pattern: Two Pointers / Greedy
     * Time Complexity: O(N + M) - In worst case, we traverse one row length and one column length.
     * Space Complexity: O(1) - No extra storage.
     */
    public static boolean searchMatrixOptimal(int[][] matrix, int target) {
        // Start in the Top-Right corner.
        // Why? This position is a "decision pivot":
        // Move Left -> Values Decrease. Move Down -> Values Increase.
        // (Starting at Top-Left fails because Right and Down BOTH increase value).
        int row = 0, col = matrix[0].length - 1;

        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;

                // Logic: Current element is too big.
                // Since the column is sorted, everything below is also too big. Eliminate this Column.
            else if (matrix[row][col] > target) col--;

                // Logic: Current element is too small.
                // Since the row is sorted, everything to the left is also too small. Eliminate this Row.
            else row++;
        }
        return false;
    }
}