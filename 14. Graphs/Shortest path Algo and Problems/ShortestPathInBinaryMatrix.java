import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMatrix {
    public static void main(String[] args) {
        ShortestPathInBinaryMatrix solver = new ShortestPathInBinaryMatrix();
        int[][] grid = {
                {0, 0, 0},
                {1, 1, 0},
                {1, 1, 0}
        };
        int result = solver.shortestPathBinaryMatrix(grid);
        System.out.println(result);
    }

    /*
     * Approach: BFS (8-Directional)
     * Pattern: Matrix BFS / Shortest Path
     * Time Complexity: O(N^2) - Each cell is visited at most once.
     * Space Complexity: O(N^2) - For Visited array and Queue.
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // Edge Case: Start or End is blocked
        // Logic: Path must start at (0,0) and end at (n-1,n-1). If either is '1', impossible.
        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) return -1;

        boolean[][] vis = new boolean[n][n];

        // Queue stores {Distance, X, Y}
        Queue<int[]> q = new LinkedList<>();

        // Start BFS from (0,0) with distance 1
        q.add(new int[]{1, 0, 0});
        vis[0][0] = true; // Mark start as visited

        while (!q.isEmpty()) {
            int[] frontNode = q.poll();
            int curDist = frontNode[0];
            int curX = frontNode[1];
            int curY = frontNode[2];

            // Goal Check: If we reached the bottom-right cell
            if (curX == n - 1 && curY == n - 1) return curDist;

            // Traverse all 8 neighbors (Horizontal, Vertical, Diagonal)
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nX = curX + i;
                    int nY = curY + j;

                    // Validity Check: In bounds, is clear path ('0'), and unvisited
                    if (isValid(n, nX, nY) && grid[nX][nY] == 0 && !vis[nX][nY]) {
                        vis[nX][nY] = true;
                        q.add(new int[]{curDist + 1, nX, nY});
                    }
                }
            }
        }

        return -1; // Destination unreachable
    }

    private boolean isValid(int n, int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }
}