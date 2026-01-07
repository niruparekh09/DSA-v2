import java.util.*;
import java.util.Queue;

public class PathWithMinEffort {
    // Delta row and col for 4-directional movement
    int[] dRow = {-1, 0, 1, 0};
    int[] dCol = {0, 1, 0, -1};

    public static void main(String[] args) {
        PathWithMinEffort solver = new PathWithMinEffort();
        List<List<Integer>> heights = new ArrayList<>();
        heights.add(Arrays.asList(1, 2, 2));
        heights.add(Arrays.asList(3, 8, 2));
        heights.add(Arrays.asList(5, 3, 5));
        int result = solver.MinimumEffort(heights);
        System.out.println("Output: " + result);
    }

    /*
     * Approach: Dijkstra's Algorithm
     * Pattern: Modified Shortest Path (Minimax)
     * Time Complexity: O(E log V) => O(N*M log(N*M)) - N*M cells, each added to PQ.
     * Space Complexity: O(N*M) - Distance matrix and Priority Queue.
     */
    public int MinimumEffort(List<List<Integer>> heights) {
        int n = heights.size();
        int m = heights.get(0).size(); // Added column size variable for robustness

        int[][] dist = new int[n][m]; // Changed from [n][n] to [n][m]

        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], (int) 1e9);
        }

        // Priority Queue stores {Effort, x, y}, ordered by Effort ASC
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Initialize source
        dist[0][0] = 0;
        pq.add(new int[]{0, 0, 0});

        while (!pq.isEmpty()) {
            int[] frontNode = pq.poll();
            int curDist = frontNode[0]; // Current max absolute difference encountered on this path
            int x = frontNode[1];
            int y = frontNode[2];

            // Goal Check: Since it's Dijkstra, first time we reach target is with minimal effort
            if (x == n - 1 && y == m - 1) {
                return curDist;
            }

            // Traverse 4 neighbors
            for (int i = 0; i < 4; i++) {
                int nX = x + dRow[i];
                int nY = y + dCol[i];

                if (isValid(n, m, nX, nY)) {
                    // Logic: Effort for edge (curr -> neighbor) is the absolute height difference.
                    int edgeWeight = Math.abs(heights.get(nX).get(nY) - heights.get(x).get(y));

                    // Logic: Path effort is the MAX edge weight encountered along the path.
                    // We want to MINIMIZE this maximum value (MiniMax).
                    int newEffort = Math.max(curDist, edgeWeight);

                    // Relaxation: If we found a path to (nX, nY) with lower max effort, update.
                    if (newEffort < dist[nX][nY]) {
                        dist[nX][nY] = newEffort;
                        pq.add(new int[]{newEffort, nX, nY});
                    }
                }
            }
        }
        return 0; // Return 0 for single cell grid case
    }

    private boolean isValid(int n, int m, int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }
}