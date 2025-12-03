import java.util.LinkedList;
import java.util.Queue;

public class NumberOfIslands {
    public static void main(String[] args) {
        char[][] grid = {
                {'1', '0', '0', '0', '1'},
                {'0', '1', '0', '1', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '1', '0', '1', '0'}
        };

        NumberOfIslands main = new NumberOfIslands();
        int result = main.numIslands(grid);
        System.out.println("Number of islands: " + result);
    }

    /*
     * Approach: Connected Components (BFS)
     * Pattern: Matrix Traversal / Breadth-First Search
     * Time Complexity: O(N * M) - We visit each cell at most once.
     * Space Complexity: O(N * M) - For the visited array and Queue in worst case.
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] vis = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // Key Logic: If we find a piece of land ('1') that hasn't been visited yet,
                // it is a starting point of a new island.
                if (grid[i][j] == '1' && !vis[i][j]) {
                    count++;
                    // Trigger BFS to visit and mark the ENTIRE connected component (island).
                    bfs(i, j, grid, vis);
                }
            }
        }

        return count;
    }

    // BFS implementation checking all 8 directions (Horizontal, Vertical, Diagonal)
    private void bfs(int curI, int curJ, char[][] grid, boolean[][] vis) {
        int n = grid.length;
        int m = grid[0].length;
        vis[curI][curJ] = true;

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{curI, curJ});

        while (!q.isEmpty()) {
            int[] curNode = q.remove();
            int i = curNode[0];
            int j = curNode[1];

            // Traverse neighbors using nested loops for -1, 0, 1 offsets
            // This covers 8 directions (and self, effectively handled by visited check)
            for (int k = -1; k <= 1; k++) {
                for (int l = -1; l <= 1; l++) {
                    int nI = i + k;
                    int nJ = j + l;

                    // Validation: Check bounds, check if it's land, and check if unvisited
                    if (isValid(n, m, nI, nJ)
                            && grid[nI][nJ] == '1'
                            && !vis[nI][nJ]) {
                        // Mark as visited immediately upon adding to queue to prevent cycles/redundancy
                        q.add(new int[]{nI, nJ});
                        vis[nI][nJ] = true;
                    }
                }
            }
        }
    }

    // BFS implementation checking standard 4 directions (Up, Right, Down, Left)
    private void bfs2(int curI, int curJ, char[][] grid, boolean[][] vis) {
        int n = grid.length;
        int m = grid[0].length;
        vis[curI][curJ] = true;

        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{curI, curJ});

        // Direction arrays for 4 cardinal movements
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            int[] curNode = q.remove();
            int i = curNode[0];
            int j = curNode[1];

            // Traverse using direction arrays
            for (int k = 0; k < 4; k++) {
                int nI = i + dRow[k];
                int nJ = j + dCol[k];

                if (isValid(n, m, nI, nJ)
                        && grid[nI][nJ] == '1'
                        && !vis[nI][nJ]) {
                    // Mark as visited and enqueue
                    q.add(new int[]{nI, nJ});
                    vis[nI][nJ] = true;
                }
            }
        }
    }

    // Helper to check boundary conditions
    private boolean isValid(int n, int m, int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}