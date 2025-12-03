import java.util.ArrayList;
import java.util.List;


public class NumberOfProvinces {
    public static void main(String[] args) {
        NumberOfProvinces p = new NumberOfProvinces();
       /*
        1 --- 4      2 --- 3
        No of Provinces: 2
        */
        System.out.println("Number of Provinces: " + p.findCircleNum(new int[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1}
        }));
    }

    /*
     * Approach: Connected Components using Adjacency List
     * Pattern: Graph Traversal / DFS
     * Time Complexity: O(V + 2E) - Standard DFS complexity + O(V^2) for conversion.
     * Space Complexity: O(V + 2E) - Storing the adjacency list.
     */
    public int findCircleNum(int[][] isConnected) {
        // Step 1: Convert Adjacency Matrix to Adjacency List
        // This is useful if the graph is sparse, though O(V^2) to iterate matrix is unavoidable here.
        List<List<Integer>> adjList = new ArrayList<>();
        int V = isConnected.length;

        for (int i = 0; i < V; i++) {
            List<Integer> neighbours = new ArrayList<>();
            for (int j = 0; j < V; j++) {
                // If edge exists and not a self-loop
                if (isConnected[i][j] == 1 && i != j) {
                    neighbours.add(j);
                }
            }
            adjList.add(neighbours);
        }

        int cnt = 0;
        boolean[] vis = new boolean[V];

        // Step 2: Count Connected Components
        for (int i = 0; i < V; i++) {
            // Logic: If a node is unvisited, it starts a NEW province (component).
            if (!vis[i]) {
                cnt++;
                System.out.println("Province no: " + cnt);
                // DFS marks all cities connected to this one as visited.
                dfs(i, vis, adjList);
            }
        }
        return cnt;
    }

    private void dfs(int node, boolean[] vis, List<List<Integer>> adjList) {
        System.out.println("Visited Node: " + node);
        vis[node] = true;

        // Standard DFS traversal using Adjacency List
        for (int cur : adjList.get(node)) {
            if (!vis[cur]) {
                dfs(cur, vis, adjList);
            }
        }
    }

    /*
     * Approach: Connected Components using Adjacency Matrix (Space Optimized)
     * Pattern: Graph Traversal / DFS
     * Time Complexity: O(V^2) - We iterate the entire row for every node in the worst case.
     * Space Complexity: O(V) - Only for visited array and recursion stack. No extra List stored.
     */
    public int findCircleNumMat(int[][] isConnected) {
        int V = isConnected.length;
        boolean[] vis = new boolean[V];
        int cnt = 0;

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                cnt++;
                dfs2(i, isConnected, vis);
            }
        }

        return cnt;
    }

    private void dfs2(int node, int[][] adjMat, boolean[] vis) {
        vis[node] = true;

        // Logic: Iterate through the row 'node' to find connected neighbors 'j'.
        for (int j = 0; j < adjMat.length; j++) {
            // Check: Connection exists (1) AND neighbor 'j' is unvisited.
            // Note: Self-loop check (i!=j) is implicitly handled by vis[j] (since vis[node] is set true above).
            if (adjMat[node][j] == 1 && !vis[j]) {
                dfs2(j, adjMat, vis);
            }
        }
    }
}