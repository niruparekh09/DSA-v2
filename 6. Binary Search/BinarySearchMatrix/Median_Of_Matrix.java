public class Median_Of_Matrix {
    public static void main(String[] args) {
        System.out.println(median(new int[][]{{1, 3, 5}, {2, 6, 9}, {3, 6, 9}}));
    }

    /*
     * Approach: Binary Search on Answer (Value Range)
     * Pattern: Modified Binary Search
     * Time Complexity: O(32 * N * log(M)) - Outer loop searches value range (integer bit depth), inner loop does BS on rows.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int median(int[][] mat) {
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;

        int m = mat[0].length; // Columns
        int n = mat.length;    // Rows

        // Step 1: Define the Search Space (Min to Max possible values in matrix).
        // Since rows are sorted, Min is in first column, Max is in last column.
        // Note: Loop usually iterates 'n' (rows), user code iterates 'm' (logic implies Square matrix or N >= M).
        for (int i = 0; i < n; i++) {
            low = Math.min(low, mat[i][0]);
            high = Math.max(high, mat[i][m - 1]);
        }

        // The median index we are looking for
        int req = (n * m) / 2;

        // Step 2: Binary Search on values [low, high]
        while (low <= high) {
            int mid = (low + high) / 2;

            // Count how many numbers in the matrix are <= mid
            int smallEqual = countSmallEqual(mat, n, m, mid);

            // Logic: If count <= req, 'mid' is too small to be the median.
            // We need more numbers, so we search the right half (larger values).
            if (smallEqual <= req) low = mid + 1;
            else high = mid - 1;
        }
        return low; // 'low' converges to the median value
    }

    // Helper: Iterates through all rows to sum up the count of elements <= mid
    public static int countSmallEqual(int[][] mat, int n, int m, int mid) {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            // Use Binary Search (upperBound) since rows are sorted
            cnt += upperBound(mat[i], mid, m);
        }
        return cnt;
    }

    // Helper: Finds count of elements <= x in a sorted array (Upper Bound)
    public static int upperBound(int[] arr, int x, int m) {
        int low = 0, high = m - 1;
        int ans = m;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Smallest index where element > x
            if (arr[mid] > x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}