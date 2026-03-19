public class MakingALargeIsland {

    // Direction vectors to explore 4 neighboring cells
    int[] dRow = {-1, 0, 1, 0};
    int[] dCol = {0, 1, 0, -1};

    /*
     * Utility function to check if the cell is inside grid bounds
     */
    private boolean isValid(int i, int j, int n) {

        if (i < 0 || i >= n) return false;
        if (j < 0 || j >= n) return false;

        return true;
    }

    /*
     * Step 1: Preprocess the grid to form initial islands
     * Connect all adjacent land cells using DSU.
     */
    private void addInitialIslands(DS ds, int[][] grid, int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Skip water cells
                if (grid[i][j] == 0) continue;

                // Check all 4 neighboring cells
                for (int k = 0; k < 4; k++) {

                    int nX = dRow[k] + i;
                    int nY = dCol[k] + j;

                    // If neighbor is valid land, union them
                    if (isValid(nX, nY, n) && grid[nX][nY] == 1) {

                        // Convert 2D coordinates to 1D node index
                        int currentNode = (i * n) + j;
                        int neighNode = (nX * n) + nY;

                        ds.unionBySize(currentNode, neighNode);
                    }
                }
            }
        }
    }

    /*
     * Approach: Disjoint Set (Union-Find) + Grid Transformation
     * Pattern: Connected Components with Optional Merge
     * Time Complexity: O(N^2 * α(N^2))
     * Space Complexity: O(N^2) - DSU arrays.
     *
     * Goal:
     * Convert at most one '0' to '1' and return the largest possible island size.
     */
    public int largestIsland(int[][] grid) {

        int n = grid.length;

        // DSU for all grid cells (flattened to 1D)
        DS ds = new DS(n * n);

        // Step 1: Connect all existing islands
        addInitialIslands(ds, grid, n);

        // Step 2: Try converting each water cell to land
        // (Implementation incomplete in given code)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                // Intended logic:
                // If grid[i][j] == 0,
                // check unique neighboring components
                // and compute possible island size
            }
        }

        // Placeholder return (actual solution should return max island size)
        return -1;
    }

    /*
     * Disjoint Set (Union-Find)
     * Maintains connected components and their sizes.
     */
    class DS {

        int[] rank;
        int[] size;
        int[] parent;

        DS(int n) {

            rank = new int[n + 1];
            parent = new int[n + 1];
            size = new int[n + 1];

            // Initialize each node as its own parent with size 1
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        /*
         * Find Ultimate Parent with Path Compression
         */
        int findUPar(int node) {

            if (parent[node] != node) {
                parent[node] = findUPar(parent[node]);
            }

            return parent[node];
        }

        /*
         * Union by Rank
         * Attach smaller height tree under larger height tree.
         */
        void unionByRank(int u, int v) {

            int ulp_u = findUPar(u);
            int ulp_v = findUPar(v);

            if (ulp_u == ulp_v) return;

            if (rank[ulp_u] < rank[ulp_v]) {
                parent[ulp_u] = ulp_v;
            } else if (rank[ulp_v] < rank[ulp_u]) {
                parent[ulp_v] = ulp_u;
            } else {
                parent[ulp_v] = ulp_u;
                rank[ulp_u]++;
            }
        }

        /*
         * Union by Size
         * Keeps track of island sizes efficiently.
         */
        void unionBySize(int u, int v) {

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