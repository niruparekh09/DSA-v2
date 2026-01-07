import java.util.*;

public class NetworkDelayTime {
    /*
     * Approach: Dijkstra's Algorithm
     * Pattern: Shortest Path in Weighted Graph
     * Time Complexity: O(E log V) - Priority Queue operations dominate.
     * Space Complexity: O(V + E) - Adjacency List + Distance Array + Priority Queue.
     */
    public int networkDelayTime(int[][] times, int n, int k) {
        // Step 1: Build Adjacency List
        List<List<Pair>> adjList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        // Convert 1-based input to 0-based indexing
        for (int[] time : times) {
            int u = time[0] - 1;
            int v = time[1] - 1;
            int w = time[2];
            adjList.get(u).add(new Pair(v, w));
        }

        // Step 2: Dijkstra's Algorithm
        // Min-Heap stores {distance, node}, ordered by distance ASC
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int[] dist = new int[n];
        Arrays.fill(dist, (int) 1e9);

        // Initialize source (adjust k to 0-based)
        k = k - 1;
        dist[k] = 0;
        pq.add(new int[]{0, k});

        while (!pq.isEmpty()) {
            int[] frontNode = pq.remove();
            int curDist = frontNode[0];
            int curNode = frontNode[1];

            // Optimization: If popped distance is greater than known shortest distance, skip (stale entry)
            if (curDist > dist[curNode]) continue;

            // Traverse neighbors
            for (Pair neigh : adjList.get(curNode)) {
                int neighNode = neigh.to;
                int edgeWeight = neigh.weight;

                // Relaxation Step
                if (dist[neighNode] > curDist + edgeWeight) {
                    dist[neighNode] = curDist + edgeWeight;
                    pq.add(new int[]{dist[neighNode], neighNode});
                }
            }
        }

        // Step 3: Result Calculation
        // The network delay time is the maximum time it takes for signal to reach ANY node.
        // It's equivalent to finding the "farthest" node in the shortest path tree.
        int maxDist = 0;
        for (int d : dist) {
            // If any node is unreachable, signal fails
            if (d == (int) 1e9) return -1;
            maxDist = Math.max(maxDist, d);
        }
        return maxDist;
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