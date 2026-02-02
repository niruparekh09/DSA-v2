import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class KruskalAlgorithm {

    /*
     * Approach: Kruskal’s Algorithm (Using Greedy + Disjoint Set)
     * Pattern: Minimum Spanning Tree (Edge-based Greedy)
     * Time Complexity: O(E log E) - Sorting edges dominates.
     * Space Complexity: O(V + E) - Edge list + Disjoint Set arrays.
     */
    public int spanningTree(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj) {

        // 1: Extract all edges from the adjacency list
        // Convert adjacency list representation into simple edge list
        List<Edge> edges = new ArrayList<>();

        // O(V + E)
        for (int i = 0; i < V; i++) {
            for (ArrayList<Integer> it : adj.get(i)) {

                int adjNode = it.get(0);  // Destination node
                int wt = it.get(1);       // Edge weight

                // Create edge {source, destination, weight}
                edges.add(new Edge(i, adjNode, wt));
            }
        }

        // 2: Sort edges in ascending order of weight (Greedy Step)
        // Smallest weight edges are considered first
        Collections.sort(edges);

        // 3: Initialize Disjoint Set (for cycle detection)
        DS ds = new DS(V);

        int minWt = 0;  // Stores total weight of MST

        // 4: Process edges one by one
        for (Edge e : edges) {

            int src = e.src;
            int dest = e.dest;
            int wt = e.wt;

            /*
             * Cycle Check:
             * If src and dest belong to different components,
             * include this edge in MST.
             */
            if (ds.findUPar(src) != ds.findUPar(dest)) {

                ds.unionBySize(src, dest);  // Merge components
                minWt += wt;               // Add weight to MST
            }
        }

        // Return total Minimum Spanning Tree weight
        return minWt;
    }

    /*
     * Disjoint Set (Union-Find) Implementation
     * Used for efficient cycle detection.
     */
    class DS {

        int[] parent;
        int[] rank;
        int[] size;

        public DS(int n) {

            parent = new int[n];
            rank = new int[n];
            size = new int[n];

            // Initially, each node is its own component of size 1
            Arrays.fill(size, 1);

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /*
         * Find Ultimate Parent (Path Compression)
         * Makes future finds faster.
         */
        public int findUPar(int node) {

            if (node != parent[node]) {
                parent[node] = findUPar(parent[node]);
            }

            return parent[node];
        }

        /*
         * Union by Rank
         * Attach smaller height tree under taller tree.
         */
        public void unionByRank(int u, int v) {

            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (rank[ulp_u] < rank[ulp_v]) {
                parent[ulp_u] = ulp_v;
            }
            else if (rank[ulp_v] < rank[ulp_u]) {
                parent[ulp_v] = ulp_u;
            }
            else {
                parent[ulp_v] = ulp_u;
                rank[ulp_u]++;
            }
        }

        /*
         * Union by Size
         * Attach smaller component under larger component.
         */
        public void unionBySize(int u, int v) {

            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (size[ulp_u] < size[ulp_v]) {
                parent[ulp_u] = ulp_v;
                size[ulp_v] += size[ulp_u];
            }
            else {
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
        }
    }
}

/*
 * Edge Class
 * Represents a graph edge with source, destination, and weight.
 * Comparable allows sorting edges by weight.
 */
class Edge implements Comparable<Edge> {

    int src, dest, wt;

    public Edge(int src, int dest, int wt) {
        this.src = src;
        this.dest = dest;
        this.wt = wt;
    }

    // Sort edges in ascending order of weight
    @Override
    public int compareTo(Edge edge) {
        return this.wt - edge.wt;
    }
}