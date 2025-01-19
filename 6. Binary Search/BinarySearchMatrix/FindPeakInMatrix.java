import java.util.Arrays;

public class FindPeakInMatrix {
    public static void main(String[] args) {
        //mat = [[25,37,23,37,19],[45,19,2,43,26],[18,1,37,44,50]]
        System.out.println(Arrays.toString(findPeakGrid(new int[][]{{25, 37, 23, 37, 19}, {45, 19, 2, 43, 26}, {18, 1, 37, 44, 50}})));
        //System.out.println(Arrays.toString(findPeakGrid(new int[][]{{10, 20, 15}, {21, 30, 14}, {7, 16, 32}})));
    }

    public static int[] findPeakGrid(int[][] mat) {
        int m = mat[0].length; // Finding length of a row (i.e. number of cols)
        int low = 0, high = m-1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int row = getMaxElRow(mat, mid);
            int left = (mid - 1 >= 0) ? mat[row][mid - 1] : -1;
            int right = (mid + 1 < m) ? mat[row][mid + 1] : -1;
            int maxEl = mat[row][mid];
            if (maxEl > left && maxEl > right) return new int[]{row, mid};
            else if (maxEl < left) high = mid - 1;
            else low = mid + 1;
        }
        return new int[]{-1, -1};
    }

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
