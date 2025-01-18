public class SearchInMatrixTwo {
    public static void main(String[] args) {
        System.out.println(
                searchMatrixOptimal(new int[][]{
                        {1, 4, 7, 11, 15}, {2, 5, 8, 12, 19}, {3, 6, 9, 16, 22}, {10, 13, 14, 17, 24}, {18, 21, 23, 26, 30}
                }, 5)
        );
    }

    // Better [O(nlog(m))]
    public static boolean searchMatrix(int[][] matrix, int target) {
        boolean isPresent = false;
        for (int i = 0; i < matrix.length; i++) {
            isPresent = search(matrix[i], target);
            if (isPresent)
                return true;
        }
        return isPresent;
    }

    private static boolean search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        if (target > arr[high])
            return false;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target)
                return true;
            else if (arr[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return false;
    }

    // Optimal [O(m+n)]
    public static boolean searchMatrixOptimal(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) col--;
            else row++;
        }
        return false;
    }
}
