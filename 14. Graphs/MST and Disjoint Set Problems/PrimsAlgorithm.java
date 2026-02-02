import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class PrimsAlgorithm {

    /*
     * Approach: Prim's Algorithm (Greedy + Min-Heap)
     * Pattern: Minimum Spanning Tree (Vertex-based Greedy)
     * Time Complexity: O(E log V) - Each edge insertion/removal from PQ takes log V.
     * Space Complexity: O(V + E) - Visited array + Priority Queue.
     */
    public int spanningTree(int V, List<List<List<Integer>>> adj) {

        // Min-Heap storing {weight, node}
        // Always selects the minimum weight edge leading to a new node
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Tracks whether a node is already included in MST
        boolean[] vis = new boolean[V];

        // Start from node 0 with weight 0
        pq.add(new int[]{0, 0});

        int mstWt = 0; // Stores total weight of Minimum Spanning Tree

        while (!pq.isEmpty()) {

            // Extract node with smallest edge weight
            int[] topNode = pq.remove();

            int wt = topNode[0];   // Weight of edge
            int cur = topNode[1];  // Current node

            // If already included in MST, skip
            if (vis[cur]) continue;

            // Include this node in MST
            mstWt += wt;
            vis[cur] = true;

            // Traverse all adjacent nodes
            for (List<Integer> it : adj.get(cur)) {

                int adjNode = it.get(0);  // Adjacent node
                int edgeWt = it.get(1);   // Edge weight

                // If adjacent node is not yet included in MST,
                // push it into priority queue
                if (!vis[adjNode]) {
                    pq.add(new int[]{edgeWt, adjNode});
                }
            }
        }

        // Return total weight of MST
        return mstWt;
    }
}