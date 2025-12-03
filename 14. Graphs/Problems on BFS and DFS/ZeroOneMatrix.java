import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//? 0/1 Matrix (Nearest 0 Distance)
public class ZeroOneMatrix {

    // Delta Row & Col for traversing neighbors
    private int[] dRow = {-1, 0, 1, 0};
    private int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) {
        ZeroOneMatrix z = new ZeroOneMatrix();
        System.out.println(Arrays.deepToString(z.updateMatrix(new int[][]{
                        {0, 0, 0},
                        {0, 1, 0},
                        {1, 1, 1}
                }
        )));
    }

    /*
     * Approach: Multi-Source BFS
     * Pattern: Matrix BFS / Shortest Path
     * Time Complexity: O(N * M) - Each cell is visited exactly once.
     * Space Complexity: O(N * M) - Queue stores up to N*M elements in worst case.
     */
    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // Distance matrix to store result
        int[][] dist = new int[n][m];
        // Visited array (can optimize by using dist array init to -1)
        int[][] vis = new int[n][m];

        Queue<Pair> q = new LinkedList<>();

        // Step 1: Initialize Queue with ALL 0s (Multi-Source).
        // Why? Distance from 0 to itself is 0. We want shortest path from ANY 0.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
                    q.add(new Pair(i, j, 0));
                    vis[i][j] = 1;
                }
            }
        }

        // Step 2: Standard BFS to propagate distance
        while (!q.isEmpty()) {
            Pair frontNode = q.remove();
            int x = frontNode.x;
            int y = frontNode.y;
            int step = frontNode.step;

            // Store the distance for current cell
            if (isValid(n, m, x, y)) dist[x][y] = step;

            // Visit 4 Neighbors
            for (int i = 0; i < 4; i++) {
                int r = x + dRow[i];
                int c = y + dCol[i];

                if (isValid(n, m, r, c)) {
                    // If neighbor is unvisited (meaning it's a '1' we haven't reached from a closer '0' yet)
                    if (vis[r][c] == 0) {
                        vis[r][c] = 1;
                        // Distance to neighbor = Current Distance + 1
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
        int step; // Tracks distance from nearest 0

        public Pair(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}