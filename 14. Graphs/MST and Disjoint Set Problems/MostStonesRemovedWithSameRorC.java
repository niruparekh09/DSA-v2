import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MostStonesRemovedWithSameRorC {

    /*
     * Approach: Disjoint Set (Union-Find) with Row-Column Mapping
     * Pattern: Connected Components in Bipartite Graph (Rows ↔ Columns)
     * Time Complexity: O(N * α(N)) - Union-Find operations for each stone.
     * Space Complexity: O(R + C) - DSU arrays for rows and columns.
     *
     * Key Idea:
     * - Treat each row and each column as separate nodes.
     * - A stone connects its row node to its column node.
     * - Stones in the same connected component can be removed except one.
     * - Stones removable = Total stones - Number of connected components.
     */
    public int removeStones(int[][] stones) {

        int maxRow = 0;
        int maxCol = 0;

        // Determine maximum row and column indices
        // This helps in sizing the DSU structure
        for (int[] it : stones) {
            maxRow = Math.max(it[0], maxRow);
            maxCol = Math.max(it[1], maxCol);
        }

        /*
         * Create DSU large enough to hold:
         * - All row indices
         * - All column indices (shifted to avoid collision with rows)
         */
        DS ds = new DS(maxRow + maxCol + 2);

        // Tracks only the nodes that actually appear (used for component counting)
        Map<Integer, Integer> stoneNodes = new HashMap<>();

        // Union row node with column node for each stone
        for (int[] it : stones) {

            int row = it[0];
            int col = it[1] + maxRow + 1; // Shift column index to avoid overlap

            ds.unionBySize(row, col);

            // Record used nodes for later component counting
            stoneNodes.put(row, 1);
            stoneNodes.put(col, 1);
        }

        // Count number of connected components
        int components = 0;

        for (int key : stoneNodes.keySet()) {
            if (ds.findUPar(key) == key) components++;
        }

        /*
         * In each connected component, we must keep one stone.
         * So removable stones = Total stones - Components
         */
        return stones.length - components;
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

            if (parent[node] != node) {
                parent[node] = findUPar(parent[node]);
            }
            return parent[node];
        }

        /*
         * Union by Rank
         * Attaches smaller height tree under larger height tree.
         */
        public void unionByRank(int u, int v) {

            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (rank[ulp_u] < rank[ulp_v]) {
                parent[ulp_u] = ulp_v;
            } else if (rank[ulp_u] > rank[ulp_v]) {
                parent[ulp_v] = ulp_u;
            } else {
                parent[ulp_v] = ulp_u;
                rank[ulp_u]++;
            }
        }

        /*
         * Union by Size
         * Attaches smaller component under larger component.
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