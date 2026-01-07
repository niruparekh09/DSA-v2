import java.util.*;

public class CheapestFlight {
    /*
     * Approach: Dijkstra's Algorithm (Modified with Stops) / BFS
     * Pattern: Shortest Path in Weighted Graph
     * Time Complexity: O(E) - Since we use a Queue (not PQ) and limit stops, it behaves like BFS.
     * Space Complexity: O(V + E) - Adjacency list and distance array.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dest, int k) {
        // Step 1: Build Adjacency List
        List<List<Pair>> adjList = new ArrayList<>();

        if (src == dest) return 0;

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] flight : flights) {
            adjList.get(flight[0]).add(new Pair(flight[1], flight[2]));
        }

        // Distance array to store min cost to reach each node
        int[] dist = new int[n];
        Arrays.fill(dist, (int) 1e9);

        // Queue stores {stops, node, cost}
        // Logic: Use Queue instead of PriorityQueue.
        // Why? We prioritize 'stops' (Level Order Traversal) over 'cost'.
        // Using PQ might pick a cheaper path that exceeds 'k' stops, ignoring valid paths.
        Queue<int[]> q = new LinkedList<>();

        q.add(new int[]{0, src, 0});
        dist[src] = 0; // Initialize source distance (though dist[src] is rarely read back in this logic)

        while (!q.isEmpty()) {
            int[] frontNode = q.remove();

            int curStops = frontNode[0];
            int curNode = frontNode[1];
            int curCost = frontNode[2]; // Use 'cost' instead of 'dist' for clarity

            // Optimization: If stops exceed K, we cannot extend this path further.
            if (curStops > k) continue;

            // Traverse neighbors
            for (Pair neigh : adjList.get(curNode)) {
                int neighNode = neigh.to;
                int edgeCost = neigh.weight;

                // Relaxation Step
                // If we found a cheaper way to reach neighbor within allowed stops
                if (dist[neighNode] > curCost + edgeCost) {
                    dist[neighNode] = curCost + edgeCost;
                    // Add to queue with incremented stop count
                    q.add(new int[]{curStops + 1, neighNode, dist[neighNode]});
                }
            }
        }

        if (dist[dest] == (int) 1e9) return -1;

        return dist[dest];
    }

    private class Pair {
        int to;
        int weight;

        public Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}