import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInBinaryMaze {
    // Delta arrays representing 4 directions (Up, Right, Down, Left/Right?)
    // Note: Usually dCol is {0, 1, 0, -1} to cover all 4 directions.
    int[] dRow = {-1, 0, 1, 0};
    int[] dCol = {0, 1, 0, 1};

    public static void main(String[] args) {
        ShortestPathInBinaryMaze solver = new ShortestPathInBinaryMaze();

        // Example 1
        int[][] grid1 = {
                {1, 1, 1, 1},
                {1, 1, 0, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0},
                {1, 0, 0, 1}
        };
        int[] source1 = {0, 1};
        int[] destination1 = {2, 2};

        int result1 = solver.shortestPath(grid1, source1, destination1);
        System.out.println("Output (Example 1): " + result1);

        // Example 2
        int[][] grid2 = {
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 1, 1, 1, 0},
                {1, 0, 1, 0, 1}
        };
        int[] source2 = {0, 0};
        int[] destination2 = {3, 4};

        int result2 = solver.shortestPath(grid2, source2, destination2);
        System.out.println("Output (Example 2): " + result2);
    }

    /*
     * Approach: Breadth-First Search (BFS)
     * Pattern: Shortest Path in Unweighted Graph/Grid using Dijkstra Algo
     * Time Complexity: O(N * M) - In worst case, we visit every cell once.
     * Space Complexity: O(N * M) - Distance array and Queue storage.
     */
    public int shortestPath(int[][] grid, int[] source, int[] destination) {

        // Edge Case: Source is the destination.
        if (source[0] == destination[0] && source[1] == destination[1]) return 0;

        int n = grid.length;
        int m = grid[0].length;

        // Distance array to store min steps to reach each cell.
        // Initialized to Infinity to represent unvisited.
        int[][] dist = new int[n][m];
        for (int i = 0; i < dist.length; i++) {
            Arrays.fill(dist[i], (int) 1e9);
        }

        // Queue stores <Distance, Coordinates(x, y)>
        Queue<Pair> q = new LinkedList<>();

        // Logic: Initialize source distance to 0 and add to queue.
        dist[source[0]][source[1]] = 0;
        q.add(new Pair(0, source[0], source[1]));

        while (!q.isEmpty()) {
            Pair frontNode = q.poll();
            int curDist = frontNode.dist;
            int x = frontNode.x;
            int y = frontNode.y;

            // Logic: Explore all 4 possible neighbors.
            for (int i = 0; i < 4; i++) {
                int nRow = x + dRow[i];
                int nCol = y + dCol[i];

                // Logic: Check bounds, if cell is walkable (1), and if a shorter path is found.
                if (isValid(n, m, nRow, nCol)
                        && grid[nRow][nCol] == 1
                        && dist[nRow][nCol] > (curDist + 1)) {

                    // Update distance to the shorter path found.
                    dist[nRow][nCol] = (curDist + 1);

                    // Optimization: If we reached the destination, return immediately.
                    if (nRow == destination[0] && nCol == destination[1]) return curDist + 1;

                    q.add(new Pair(curDist + 1, nRow, nCol));
                }
            }
        }

        // Return -1 if destination is unreachable.
        return -1;
    }

    private boolean isValid(int n, int m, int x, int y) {
        if (x < 0 || x >= n) return false;
        if (y < 0 || y >= m) return false;
        return true;
    }

    // Helper class to store state in Queue.
    private class Pair {
        int dist;
        int x, y;

        public Pair(int dist, int x, int y) {
            this.dist = dist;
            this.x = x;
            this.y = y;
        }
    }
}