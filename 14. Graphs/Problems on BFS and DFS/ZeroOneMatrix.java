import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//? 0/1 Matrix
public class ZeroOneMatrix {

    // Delta Row & Col.
    private int[] dRow = {-1, 0, 1, 0};
    private int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) {
        ZeroOneMatrix z = new ZeroOneMatrix();

        /*** Expected Output:
           [
           [0, 0, 0],
           [0, 1, 0],
           [1, 2, 1]
           ]
        */
        System.out.println(Arrays.deepToString(z.updateMatrix(new int[][]{
                        {0, 0, 0},
                        {0, 1, 0},
                        {1, 1, 1}
                }
        )));
    }

    public int[][] updateMatrix(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        // Extra DS to not tamper with Mat
        int[][] dist = new int[n][m];
        int[][] vis = new int[n][m]; // 0 - Unvisited | 1 - Visited

        Queue<Pair> q = new LinkedList<>();

        // Iterate through mat to find the zeros and their coordinates
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (mat[i][j] == 0) {
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

            // Add the frontNode to dist mat
            if (isValid(n, m, x, y)) dist[x][y] = step;

            // Visits frontNode's neighbors
            for (int i = 0; i < 4; i++) {
                // Coordinate for up, right, down and left (according to )
                int r = x + dRow[i];
                int c = y + dCol[i];

                if (isValid(n, m, r, c)) {
                    // For unvisited neighbor add to queue along with step+1
                    // (As it'll be non-zero elem i.e. 1 as all zeros have been vis)
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
