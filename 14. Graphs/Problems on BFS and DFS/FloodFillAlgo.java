import java.util.Arrays;

public class FloodFillAlgo {
    // Delta row and col to traverse neighbor pixels (Up, Right, Down, Left)
    private int[] dRow = {-1, 0, 1, 0};
    private int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) {
        FloodFillAlgo f = new FloodFillAlgo();
        System.out.println(Arrays.deepToString(f.floodFill(new int[][]{
                {1, 1, 1},
                {2, 2, 0},
                {2, 2, 2}
        }, 2, 0, 3)));
    }

    /* Helper Function to check if a pixel is within boundaries */
    private boolean isValid(int i, int j,
                            int n, int m) {
        if (i < 0 || i >= n) return false;
        if (j < 0 || j >= m) return false;
        return true;
    }

    /*
     * Approach: DFS (Recursion)
     * Pattern: Matrix Traversal / Flood Fill
     * Time Complexity: O(N * M) - In worst case, we change color of all pixels.
     * Space Complexity: O(N * M) - Recursion stack + Answer Matrix.
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;

        // Create a separate result matrix to preserve the original input
        int[][] ans = new int[n][m];

        int iniCol = image[sr][sc]; // The target color we are replacing

        // Copy the complete image data to ans matrix
        for (int i = 0; i < image.length; i++) {
            ans[i] = Arrays.copyOf(image[i], image[i].length);
        }

        // Edge Case: If new color is same as old color, no changes needed (prevents infinite recursion)
        // Logic handled implicitly by 'ans[i][j] != newColor' check in DFS below,
        // or could return 'ans' immediately here.
        dfs(sr, sc, iniCol, image, ans, color);

        return ans;
    }

    private void dfs(int row, int col, int iniCol, int[][] image, int[][] ans, int newColor) {
        // Apply the new color
        ans[row][col] = newColor;

        int n = image.length;
        int m = image[0].length;

        // Traverse all 4 neighbors
        for (int k = 0; k < 4; k++) {
            int i = row + dRow[k];
            int j = col + dCol[k];

            // Validation Logic:
            // 1. Within bounds
            // 2. Pixel matches the Original Color (part of the connected component)
            // 3. Pixel hasn't been colored yet (Avoids infinite loops if newColor != iniCol)
            if (isValid(i, j, n, m) && image[i][j] == iniCol && ans[i][j] != newColor) {
                dfs(i, j, iniCol, image, ans, newColor);
            }
        }
    }
}