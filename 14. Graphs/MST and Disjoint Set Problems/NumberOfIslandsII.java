import java.util.ArrayList;
import java.util.List;

public class NumberOfIslandsII {

    // Direction vectors for exploring 4 neighbors (Up, Right, Down, Left)
    int[] dRow = {-1, 0, 1, 0};
    int[] dCol = {0, 1, 0, -1};

    /*
     * Approach: Disjoint Set (Union-Find) + Dynamic Connectivity
     * Pattern: Incremental Graph / Dynamic Islands
     * Time Complexity: O(Q * α(N*M))
     *   - Q operations, each union/find is nearly constant time.
     * Space Complexity: O(N*M) - DSU arrays + visited grid.
     *
     * Key Idea:
     * - Treat each cell as a node in DSU.
     * - As land is added, connect it with existing adjacent land.
     * - Track number of connected components (islands) dynamically.
     */
    public List<Integer> numOfIslands(int n, int m, int[][] A) {

        int[][] vis = new int[n][m]; // Tracks whether a cell is land

        // DSU for n*m grid cells
        DS ds = new DS(n * m);

        int islandCount = 0; // Current number of islands
        List<Integer> ans = new ArrayList<>();

        // Process each land addition query
        for (int i = 0; i < A.length; i++) {

            int x = A[i][0];
            int y = A[i][1];

            // If this cell was previously water, convert to land
            if (vis[x][y] == 0) {
                vis[x][y] = 1;
                islandCount++;
            }

            // Check all 4 neighbors
            for (int k = 0; k < 4; k++) {

                int nX = x + dRow[k];
                int nY = y + dCol[k];

                // If neighbor is valid and already land
                if (isValid(nX, nY, n, m) && vis[nX][nY] == 1) {

                    // Convert 2D grid coordinates to 1D node index
                    int currentNode = (x * m) + y;
                    int neighbourNode = (nX * m) + nY;

                    /*
                     * If both cells belong to different components,
                     * merge them and reduce island count.
                     */
                    if (ds.findUPar(currentNode) != ds.findUPar(neighbourNode)) {
                        ds.unionByRank(currentNode, neighbourNode);
                        islandCount--;
                    }
                }
            }

            // Store island count after this operation
            ans.add(islandCount);
        }

        return ans;
    }

    /*
     * Utility function to check whether a cell is inside grid bounds
     */
    private boolean isValid(int i, int j, int n, int m) {

        if (i < 0 || i >= n) return false;
        if (j < 0 || j >= m) return false;

        return true;
    }

    /*
     * Disjoint Set (Union-Find)
     * Used to efficiently track connected land components.
     */
    class DS {

        int[] rank;
        int[] size;
        int[] parent;

        DS(int n) {

            rank = new int[n + 1];
            parent = new int[n + 1];
            size = new int[n + 1];

            // Initially each node is its own component
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
         * Attach smaller height tree under larger height tree
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
         * Attach smaller component under larger component
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