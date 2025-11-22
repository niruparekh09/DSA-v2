import java.util.Arrays;

public class FloodFillAlgo {
    // delta row and col to traverse to neighbors
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

        // Return false if pixel is invalid
        if (i < 0 || i >= n) return false;
        if (j < 0 || j >= m) return false;

        // Return true if pixel is valid
        return true;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int n = image.length;
        int m = image[0].length;
        int[][] ans = new int[n][m];

        int iniCol = image[sr][sc]; // To keep the track of the init color.

        // Copying the complete image mat to ans.
        for (int i = 0; i < image.length; i++) {
            ans[i] = Arrays.copyOf(image[i], image[i].length);
        }

        dfs(sr, sc, iniCol, image, ans, color);

        return ans;
    }

    private void dfs(int row, int col, int iniCol, int[][] image, int[][] ans, int newColor) {
        // Color the current node with new color
        ans[row][col] = newColor;

        // Getting the dimensions of image
        int n = image.length;
        int m = image[0].length;

        // Traverse to 4 neighbours of the current node
        for (int k = 0; k < 4; k++) {
            int i = row + dRow[k];
            int j = col + dCol[k];

            // Check if the node coordinates are in boundary
            // Check if the node is equal to iniCol of starting node
            // Check if the node is not already visited (already has newCol)
            if (isValid(i, j, n, m) && image[i][j] == iniCol && ans[i][j] != newColor) {
                dfs(i, j, iniCol, image, ans, newColor);
            }
        }
    }
}