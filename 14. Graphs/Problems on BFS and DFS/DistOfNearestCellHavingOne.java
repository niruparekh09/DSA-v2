import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

// Opposite of 0-1 Matrix
public class DistOfNearestCellHavingOne {

    // Delta Row & Col.
    private int[] dRow = {-1, 0, 1, 0};
    private int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) {
        DistOfNearestCellHavingOne obj = new DistOfNearestCellHavingOne();

        /*** Expected Output:
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

    public int[][] nearest(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        // Extra DS to not tamper with Mat
        int[][] dist = new int[n][m];
        int[][] vis = new int[n][m]; // 0 - Unvisited | 1 - Visited

        Queue<Pair> q = new LinkedList<>();

        // Iterate through grid to find the ones and their coordinates
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) { // The change is only here w.r.t ZeroOneMatrix
                    // Adding coordinates and step = 0 to Queue
                    q.add(new Pair(i, j, 0));
                    vis[i][j] = 1; // Visited
                }
            }
        }

        // BFS
        while (!q.isEmpty()) {
            Pair frontNode = q.remove();
            int x = frontNode.x;
            int y = frontNode.y;
            int step = frontNode.step;

            // Add the frontNode to dist grid
            if (isValid(n, m, x, y)) dist[x][y] = step;

            // Visits frontNode's neighbors
            for (int i = 0; i < 4; i++) {
                // Coordinate for up, right, down and left (according to )
                int r = x + dRow[i];
                int c = y + dCol[i];

                if (isValid(n, m, r, c)) {
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
        int step; // For min distance from nearest 0

        public Pair(int x, int y, int step) {
            this.x = x;
            this.y = y;
            this.step = step;
        }
    }
}
