import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFS {

    public static void main(String[] args) {

        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();

        // Initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the graph (Undirected Graph)
        adj.get(0).addAll(Arrays.asList(2, 3, 1));
        adj.get(1).add(0);
        adj.get(2).addAll(Arrays.asList(0, 4));
        adj.get(3).add(0);
        adj.get(4).add(2);

        DFS dfs = new DFS();
        List<Integer> dfsOfGraph = dfs.dfsOfGraph(V, adj);

        // Output DFS traversal
        System.out.println("DFS of Graph: " + dfsOfGraph);
    }

    /*
     * Approach: Depth First Search (DFS)
     * Pattern: Graph Traversal (Recursive)
     * Time Complexity: O(V + E) - Each vertex and edge is visited once.
     * Space Complexity: O(V) - Visited array + Recursion stack.
     */
    // Function to return DFS traversal of the whole graph
    public List<Integer> dfsOfGraph(int V, List<List<Integer>> adj) {

        boolean[] vis = new boolean[V];   // Tracks visited nodes
        List<Integer> ans = new ArrayList<>();  // Stores DFS traversal order

        /*
         * In case the graph has multiple connected components,
         * start DFS from every unvisited vertex.
         */
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, vis, ans, adj);    // Start DFS from node i
            }
        }

        return ans;
    }

    /*
     * Recursive DFS Helper Function
     * Visits the current node, then recursively explores its neighbors.
     */
    private void dfs(int node, boolean[] vis, List<Integer> dfsList, List<List<Integer>> adj) {

        // Mark the current node as visited
        vis[node] = true;

        // Add the current node to DFS result
        dfsList.add(node);

        // Explore all adjacent (neighboring) nodes
        for (int neighbor : adj.get(node)) {

            // If the neighbor has not been visited, recurse
            if (!vis[neighbor]) {
                dfs(neighbor, vis, dfsList, adj);
            }
        }
    }
}
