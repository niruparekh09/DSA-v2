import java.util.ArrayList;
import java.util.List;

public class NumberOfConnectedComponents {
    public static void main(String[] args) {
        List<List<Integer>> edges = new ArrayList<>();

        edges.add(new ArrayList<>(List.of(0, 1)));
        edges.add(new ArrayList<>(List.of(1, 2)));
        edges.add(new ArrayList<>(List.of(2, 3)));
        edges.add(new ArrayList<>(List.of(4, 5)));

        NumberOfConnectedComponents n = new NumberOfConnectedComponents();

        // Graph has 7 nodes (0-6). Edges only cover 0-5. Node 6 is isolated.
        System.out.println(n.findNumberOfComponent(7, edges));
    }

    /*
     * Approach: Depth First Search (DFS)
     * Pattern: Connected Components / Graph Traversal
     * Time Complexity: O(V + E) - Standard DFS traversal visiting every node and edge once.
     * Space Complexity: O(V + E) - Adjacency list storage and recursion stack.
     */
    public int findNumberOfComponent(int V, List<List<Integer>> edges) {
        // Step 1: Build Adjacency List from Edge List
        List<List<Integer>> adjList = new ArrayList<>();

        // Initialize list for every vertex 0 to V-1
        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        // Populate undirected edges
        for (List<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        int cnt = 0;
        boolean[] vis = new boolean[V];

        // Step 2: Iterate through all vertices to find disconnected parts
        for (int i = 0; i < V; i++) {
            // Key Logic: If a node is not visited, it belongs to a new component.
            if (!vis[i]) {
                cnt++;
                // DFS will visit all nodes in THIS component
                dfs(i, vis, adjList);
            }
        }

        return cnt;
    }

    // Standard DFS to mark all reachable nodes in the current component
    private void dfs(int node, boolean[] vis, List<List<Integer>> adjList) {
        vis[node] = true;

        for (int neighbour : adjList.get(node)) {
            if (!vis[neighbour]) dfs(neighbour, vis, adjList);
        }
    }
}