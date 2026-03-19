import java.util.Arrays;

public class NumberOfOpsToConnectNetwork {

    /*
     * Approach: Disjoint Set (Union-Find)
     * Pattern: Connected Components / Network Connectivity
     * Time Complexity: O(N + E * α(N))
     *   - Union-Find operations are nearly constant time.
     * Space Complexity: O(N) - Parent, Rank, Size arrays.
     */
    public int makeConnected(int n, int[][] connections) {

        int noOfEdges = connections.length;

        /*
         * Minimum edges required to connect n nodes = n - 1
         * If total edges are less than n - 1,
         * it is impossible to connect all components.
         */
        if (noOfEdges < n - 1) return -1;

        // Initialize Disjoint Set
        DS ds = new DS(n);

        // Union all given connections
        for (int i = 0; i < noOfEdges; i++) {
            ds.unionBySize(connections[i][0], connections[i][1]);
        }

        /*
         * Count number of connected components.
         * A node is a root if parent[node] == node.
         */
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (ds.findUPar(i) == i) count++;
        }

        /*
         * To connect 'count' components,
         * we need (count - 1) extra edges.
         */
        return count - 1;
    }

    /*
     * Disjoint Set (Union-Find)
     * Supports Path Compression + Union by Rank/Size
     */
    class DS {

        int[] parent, rank, size;

        public DS(int n) {

            parent = new int[n];
            rank = new int[n];
            size = new int[n];

            // Initially each node is its own component of size 1
            Arrays.fill(size, 1);

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /*
         * Find Ultimate Parent with Path Compression
         */
        public int findUPar(int node) {

            if (node != parent[node]) {
                parent[node] = findUPar(parent[node]);
            }

            return parent[node];
        }

        /*
         * Union by Rank
         * Attach smaller height tree under larger height tree.
         */
        public void unionByRank(int u, int v) {

            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (rank[ulp_u] < rank[ulp_v]) {
                parent[ulp_u] = ulp_v;
            } else if (rank[ulp_v] < rank[ulp_u]) {
                parent[ulp_v] = ulp_u;
            } else {
                parent[ulp_u] = ulp_v;
                rank[ulp_v]++;
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
            } else {
                parent[ulp_v] = ulp_u;
                size[ulp_u] += size[ulp_v];
            }
        }
    }
}