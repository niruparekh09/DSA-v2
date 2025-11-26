public class Search_In_Matrix_One {
    public static void main(String[] args) {
        System.out.println(searchMatrixOptimal(new int[][]{
                {1, 3, 5, 7},
                {10, 11, 16, 20},
                {23, 30, 34, 60}
        }, 3));
    }

    /*
     * Approach: Row Filtering + Binary Search (Better)
     * Pattern: Binary Search / Divide & Conquer
     * Time Complexity: O(N + log M) - O(N) to find the correct row, O(log M) to search inside it.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        // Iterate through rows to identify the specific row where the target range exists
        for (int i = 0; i < row; i++) {
            // Key Logic: Check if target lies between the First and Last element of the row.
            if (matrix[i][0] <= target && matrix[i][col - 1] >= target) return search(matrix[i], target);
        }
        return false;
    }

    // Helper: Standard Binary Search implementation
    private static boolean search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        if (target > arr[high]) return false; // Optimization: Fail fast if target > max

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }

    /*
     * Approach: Imaginary 1D Array (Optimal)
     * Pattern: Binary Search / Matrix Math
     * Time Complexity: O(log(N * M)) - Performs Binary Search on the total number of elements.
     * Space Complexity: O(1) - No actual 1D array is created; we map indices on the fly.
     */
    public static boolean searchMatrixOptimal(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        // Treat the 2D matrix as a single flattened 1D sorted array
        // Range becomes 0 to (TotalElements - 1)
        int low = 0, high = n * m - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Key Logic: Coordinate Mapping
            // Convert 1D index 'mid' back to 2D coordinates (row, col)
            // Row is Quotient, Col is Remainder when divided by column count 'm'.
            int row = mid / m, col = mid % m;

            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }
}