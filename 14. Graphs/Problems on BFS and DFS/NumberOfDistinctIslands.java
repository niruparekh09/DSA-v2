import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class NumberOfDistinctIslands {
    // Direction vectors for Up, Right, Down, Left
    int[] dRow = {-1, 0, 1, 0};
    int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) {
        int[][] grid = {
                {1, 1, 0, 1, 1},
                {1, 0, 0, 0, 0},
                {0, 0, 0, 0, 1},
                {1, 1, 0, 1, 1}
        };

        NumberOfDistinctIslands n = new NumberOfDistinctIslands();

        System.out.println(n.countDistinctIslands(grid));
    }

    /*
     * Approach: DFS with Relative Coordinates
     * Pattern: Matrix Traversal / Hashing (Shape Identification)
     * Time Complexity: O(N * M) - Visits each cell once.
     * Space Complexity: O(N * M) - For visited array and recursion stack/hash set storage.
     */
    public int countDistinctIslands(int[][] grid) {

        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];

        // Set stores unique "shapes". We use a List of Strings as the signature for each island.
        Set<ArrayList<String>> st = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Found a new unvisited island part
                if (grid[i][j] == 1 && !vis[i][j]) {
                    vis[i][j] = true;
                    ArrayList<String> path = new ArrayList<>();

                    // Start DFS. Pass (i, j) as the 'base' coordinates to calculate relative positions.
                    dfs(i, j, path, grid, vis, i, j);

                    // Add the unique shape signature to the set
                    st.add(path);
                }
            }
        }

        return st.size();
    }

    private void dfs
            (int curI, int curJ, ArrayList<String> path, int[][] grid, boolean[][] vis, int baseI, int baseJ) {
        // Key Logic: Store coordinates relative to the Base (Start) node.
        // This ensures that identical shapes at different locations produce the same path list
        // (e.g., an island at (0,0) and identical one at (2,3) will both store "0 0", "0 1"...).
        path.add((curI - baseI) + " " + (curJ - baseJ));

        int n = grid.length;
        int m = grid[0].length;

        // Traverse all 4 neighbors
        for (int i = 0; i < 4; i++) {
            int nI = curI + dRow[i];
            int nJ = curJ + dCol[i];

            if (isValid(n, m, nI, nJ) && grid[nI][nJ] == 1 && !vis[nI][nJ]) {
                vis[nI][nJ] = true;
                dfs(nI, nJ, path, grid, vis, baseI, baseJ);
            }
        }
    }

    private boolean isValid(int n, int m, int x, int y) {

        if (x < 0 || x >= n) return false;
        if (y < 0 || y >= m) return false;

        return true;
    }
}