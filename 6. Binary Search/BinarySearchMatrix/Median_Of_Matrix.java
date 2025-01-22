public class Median_Of_Matrix {
    public static void main(String[] args) {
        //[[1, 3, 5], [2, 6, 9], [3, 6, 9]]
        System.out.println(median(new int[][]{{1, 3, 5}, {2, 6, 9}, {3, 6, 9}}));
    }

    public static int median(int[][] mat) {
        int low = Integer.MAX_VALUE, high = Integer.MIN_VALUE;

        // point low and high to right elements
        int m = mat[0].length;
        int n = mat.length;
        for (int i = 0; i < m; i++) {
            low = Math.min(low, mat[i][0]);
            high = Math.max(high, mat[i][n - 1]);
        }
        int req = (n * m) / 2;
        while (low <= high) {
            int mid = (low + high) / 2;
            int smallEqual = countSmallEqual(mat, m, n, mid);
            if (smallEqual <= req) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

    public static int countSmallEqual(int[][] mat, int m, int n, int mid) {
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            cnt += upperBound(mat[i], mid, n);
        }
        return cnt;
    }

    public static int upperBound(int[] arr, int x, int n) {
        int low = 0, high = n - 1;
        int ans = n;

        while (low <= high) {
            int mid = (low + high) / 2;
            // maybe an answer
            if (arr[mid] > x) {
                ans = mid;
                // look for a smaller index on the left
                high = mid - 1;
            } else {
                low = mid + 1; // look on the right
            }
        }
        return ans;
    }
}
