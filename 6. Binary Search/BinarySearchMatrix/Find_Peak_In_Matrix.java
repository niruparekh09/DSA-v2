import java.util.Arrays;

public class Find_Peak_In_Matrix {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(findPeakGrid(new int[][]{{25, 37, 23, 37, 19}, {45, 19, 2, 43, 26}, {18, 1, 37, 44, 50}})));
    }

    /*
     * Approach: Binary Search on Columns
     * Pattern: Modified Binary Search / Divide & Conquer
     * Time Complexity: O(N * log M) - We perform Binary Search on columns (log M) and iterate rows (N) in each step.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int[] findPeakGrid(int[][] mat) {
        int m = mat[0].length;
        int low = 0, high = m - 1;

        // Apply Binary Search horizontally across columns
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Key Strategy: Find the global max in the current column 'mid'.
            // Why? The max element is automatically > its top and bottom neighbors.
            // This reduces the problem to checking only Left and Right neighbors.
            int row = getMaxElRow(mat, mid);

            // Edge Case: Handle out-of-bounds indices (-1 acts as negative infinity)
            int left = (mid - 1 >= 0) ? mat[row][mid - 1] : -1;
            int right = (mid + 1 < m) ? mat[row][mid + 1] : -1;
            int maxEl = mat[row][mid];

            // Case 1: Found Peak (Current is greater than left and right)
            if (maxEl > left && maxEl > right) return new int[]{row, mid};

                // Case 2: Left is greater. A peak must exist in the left half (uphill slope).
            else if (maxEl < left) high = mid - 1;

                // Case 3: Right is greater. A peak must exist in the right half.
            else low = mid + 1;
        }
        return new int[]{-1, -1};
    }

    // Helper: Standard linear scan to find the index of the largest element in a specific column
    public static int getMaxElRow(int[][] mat, int mid) {
        int maxEl = Integer.MIN_VALUE;
        int idx = -1;
        for (int i = 0; i < mat.length; i++) {
            if (mat[i][mid] > maxEl) {
                maxEl = mat[i][mid];
                idx = i;
            }
        }
        return idx;
    }
}