import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Opposite of 0-1 Matrix (Nearest 1 Distance)
public class DistOfNearestCellHavingOne {

    // Delta Row & Col.
    private int[] dRow = {-1, 0, 1, 0};
    private int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) {
        DistOfNearestCellHavingOne obj = new DistOfNearestCellHavingOne();

        /* Expected Output:
            [
              [1, 0, 0, 1],
              [0, 0, 1, 1],
              [1, 1, 0, 0]
            ]
        */
        int[][] result = obj.nearest(new int[][]{
                {0, 1, 1, 0},
                {1, 1, 0, 0},
                {0, 0, 1, 1}
        });
        System.out.println(Arrays.deepToString(result));
    }

    /*
     * Approach: Multi-Source BFS
     * Pattern: Matrix BFS / Shortest Path
     * Time Complexity: O(N * M) - Visits every cell exactly once.
     * Space Complexity: O(N * M) - Queue and Visited array storage.
     */
    public int[][] nearest(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // Result matrix
        int[][] dist = new int[n][m];
        int[][] vis = new int[n][m];

        Queue<Pair> q = new LinkedList<>();

        // Step 1: Initialization (Multi-Source)
        // Identify all target cells ('1's). Their distance to the nearest '1' is 0.
        // Add them all to the queue to start BFS from all '1's simultaneously.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    q.add(new Pair(i, j, 0));
                    vis[i][j] = 1;
                }
            }
        }

        // Step 2: BFS
        // Propagate outwards layer by layer.
        // Neighbors of '1's get dist 1, neighbors of those get dist 2, etc.
        while (!q.isEmpty()) {
            Pair frontNode = q.remove();
            int x = frontNode.x;
            int y = frontNode.y;
            int step = frontNode.step;

            // Record the shortest distance found for this cell
            if (isValid(n, m, x, y)) dist[x][y] = step;

            // Visit 4 neighbors
            for (int i = 0; i < 4; i++) {
                int r = x + dRow[i];
                int c = y + dCol[i];

                if (isValid(n, m, r, c)) {
                    // If neighbor is unvisited, this is the first (shortest) time we reached it.
                    if (vis[r][c] == 0) {
                        vis[r][c] = 1;
                        q.add(new Pair(r, c, step + 1));
                    }
                }
            }
        }
        return dist;
    }

    private boolean isValid(int n, int m, int x, int y) {
        if (x < 0 || x >= n) return false;
        if (y < 0 || y >= m) return false;
        return true;
    }

    private class Pair {
        int x;
        int y;
        int step; // Distance from the nearest '1'

        public Pair(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}