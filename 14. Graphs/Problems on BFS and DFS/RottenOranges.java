import java.util.LinkedList;
import java.util.Queue;

public class RottenOranges {
    public static void main(String[] args) {
        RottenOranges o = new RottenOranges();

        System.out.println(o.orangesRotting(new int[][]{
                {2, 1, 1},
                {1, 1, 0},
                {0, 1, 1}
        }));
    }

    /*
     * Approach: Multi-Source BFS (Level-order Traversal)
     * Pattern: Matrix BFS
     * Time Complexity: O(N * M) - Each cell is visited at most once.
     * Space Complexity: O(N * M) - For Queue and Visited array.
     */
    public int orangesRotting(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();

        int[][] vis = new int[n][m]; // Replicate grid here, so we don't need to modify the grid
        int freshCnt = 0;

        // --- Step 1: Initialization ---
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    // Start BFS with initially rotten oranges at time 0
                    q.add(new Pair(i, j));
                    vis[i][j] = 2;
                } else if (grid[i][j] == 1) {
                    freshCnt++;
                    vis[i][j] = 1;
                }
            }
        }

        if (freshCnt == 0) return 0;

        int maxTime = 0; // This now tracks the number of levels/minutes passed

        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};

        int rottenCnt = 0;

        // --- Step 2: Core Level-Based BFS Traversal ---
        while (!q.isEmpty()) {

            // Level-Based Logic Start: Capture the size of the current level
            int size = q.size();

            // Flag to check if any new oranges were rotted in this minute
            boolean rottedInThisLevel = false;

            // Process ALL oranges that became rotten in the PREVIOUS minute
            for (int k = 0; k < size; k++) {
                Pair current = q.remove();
                int row = current.row;
                int col = current.col;

                // Traverse all 4 neighbors
                for (int i = 0; i < 4; i++) {
                    int nRow = row + dRow[i];
                    int nCol = col + dCol[i];

                    // Check boundaries and if it's a fresh orange
                    if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m
                            && vis[nRow][nCol] == 1) {

                        // 1. Mark the neighbor as rotten/visited
                        vis[nRow][nCol] = 2;

                        // 2. Enqueue the newly rotten orange (time is not needed here)
                        // Note: Time will be maxTime + 1, but we handle it outside the inner loop.
                        q.add(new Pair(nRow, nCol));

                        // 3. Update the successful rot count
                        rottenCnt++;
                        rottedInThisLevel = true;
                    }
                }
            }

            // Check if any fresh oranges were rotted (i.e., spread occurred) in this minute.
            // If the queue is now empty, the last minute was the final successful spread.
            // Note: If you stop storing 'time' in the Pair object, maxTime directly represents the elapsed minutes (the level count).
            if (rottedInThisLevel) {
                maxTime++;
            }
        }

        // --- Step 3: Final Result Check ---
        if (rottenCnt != freshCnt) return -1;

        return maxTime;
    }

    /*
     * Approach: Space Optimized BFS
     * Logic: Same as above, but modifies the input 'grid' directly instead of using 'vis' array.
     * Space Complexity: O(1) auxiliary (excluding Queue).
     */
    public int orangesRotting2(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<Pair> q = new LinkedList<>();
        int freshCnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Pair(i, j));
                } else if (grid[i][j] == 1) {
                    freshCnt++;
                }
            }
        }

        if (freshCnt == 0)
            return 0;

        int maxTime = 0;
        int[] dRow = {-1, 0, 1, 0};
        int[] dCol = {0, 1, 0, -1};
        int rottenCnt = 0;

        while (!q.isEmpty()) {
            int size = q.size();
            boolean rottedInThisLevel = false;

            for (int k = 0; k < size; k++) {
                Pair current = q.remove();
                int row = current.row;
                int col = current.col;

                // Check 4 neighbors
                for (int i = 0; i < 4; i++) {
                    int nRow = row + dRow[i];
                    int nCol = col + dCol[i];

                    if (nRow >= 0 && nRow < n && nCol >= 0 && nCol < m
                            && grid[nRow][nCol] == 1) {
                        grid[nRow][nCol] = 2;
                        q.add(new Pair(nRow, nCol));
                        rottenCnt++;
                        rottedInThisLevel = true;
                    }
                }
            }

            if (rottedInThisLevel)
                maxTime++;
        }

        if (rottenCnt != freshCnt)
            return -1;

        return maxTime;
    }

    // Custom class to store coordinate and time, allowing for node-based time tracking
    private class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}