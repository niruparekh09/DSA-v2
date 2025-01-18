public class SearchInMatrix {
    public static void main(String[] args) {
        System.out.println(searchMatrixOptimal(new int[][]{{1, 3, 5, 7}, {10, 11, 16, 20}, {23, 30, 34, 60}}, 3));
    }

    // Better
    public static boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            if (matrix[i][0] <= target && matrix[i][col - 1] >= target)
                return search(matrix[i], target);
        }
        return false;
    }

    private static boolean search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        if (target > arr[high]) return false;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return true;
            else if (arr[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }

    // Optimized Solution
    public static boolean searchMatrixOptimal(int[][] matrix, int target) {
        int n = matrix.length;
        int m = matrix[0].length;

        int low = 0, high = n * m - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int row = mid / m, col = mid % m;
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] < target) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }

}
