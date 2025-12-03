public class NumberOfEnclaves {

    // Delta Row & Col for 4-direction traversal
    private int[] dRow = {-1, 0, 1, 0};
    private int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) {
        NumberOfEnclaves n = new NumberOfEnclaves();
        System.out.println(n.numEnclaves(new int[][]{
                {0, 0, 0, 0},
                {1, 0, 1, 0},
                {0, 1, 1, 0},
                {0, 0, 0, 0}
        }));
    }

    /*
     * Approach: Boundary DFS / Flood Fill
     * Pattern: Matrix Traversal
     * Time Complexity: O(N * M) - Each cell is visited at most twice.
     * Space Complexity: O(N * M) - Visited array and recursion stack.
     */
    public int numEnclaves(int[][] grid) {
        int ans = 0;
        int n = grid.length;
        int m = grid[0].length;

        int[][] vis = new int[n][m];

        // Strategy: "Reverse Thinking"
        // Enclaves are islands NOT connected to the boundary.
        // So, we flood-fill from the boundary to find all 'safe' land (connected to edge).
        // Any land remaining unvisited after this process is an enclave.

        // Step 1: Traverse Boundaries (First/Last Row & Col)
        for (int j = 0; j < m; j++) {
            // 1st row
            if (vis[0][j] == 0 && grid[0][j] == 1) {
                dfs(0, j, vis, grid);
            }
            // Last row
            if (vis[n - 1][j] == 0 && grid[n - 1][j] == 1) {
                dfs(n - 1, j, vis, grid);
            }
        }

        for (int i = 0; i < n; i++) {
            // 1st col
            if (vis[i][0] == 0 && grid[i][0] == 1) {
                dfs(i, 0, vis, grid);
            }
            // Last Col
            if (vis[i][m - 1] == 0 && grid[i][m - 1] == 1) {
                dfs(i, m - 1, vis, grid);
            }
        }

        // Step 2: Count Unvisited Land
        // Any '1' that wasn't reached from the boundary is an Enclave.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == 0 && grid[i][j] == 1) ans++;
            }
        }

        return ans;
    }

    private void dfs(int row, int col, int[][] vis, int[][] grid) {
        vis[row][col] = 1; // Mark as reachable from boundary
        int n = grid.length;
        int m = grid[0].length;

        // Traverse 4 neighbors
        for (int i = 0; i < 4; i++) {
            int nRow = row + dRow[i];
            int nCol = col + dCol[i];

            // Validation: Within bounds, not visited, and is land
            if (isValid(n, m, nRow, nCol) && vis[nRow][nCol] == 0 && grid[nRow][nCol] == 1) {
                dfs(nRow, nCol, vis, grid);
            }
        }
    }

    private boolean isValid(int n, int m, int x, int y) {
        if (x < 0 || x >= n) return false;
        if (y < 0 || y >= m) return false;
        return true;
    }
}