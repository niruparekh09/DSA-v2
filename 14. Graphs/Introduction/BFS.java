import java.util.*;

class BFS {

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

        BFS bfs = new BFS();
        List<Integer> bfsOfGraph = bfs.bfsOfGraph(V, adj);

        // Output BFS traversal
        System.out.println("BFS of Graph: " + bfsOfGraph);
        // Expected output: BFS of Graph: [0, 2, 3, 1, 4]
    }

    /*
     * Approach: Breadth First Search (BFS)
     * Pattern: Graph Traversal (Level Order using Queue)
     * Time Complexity: O(V + E) - Each vertex and edge is processed once.
     * Space Complexity: O(V) - Visited array + Queue.
     */
    // Return BFS traversal for all nodes (handles disconnected graphs)
    public List<Integer> bfsOfGraph(int V, List<List<Integer>> adj) {

        List<Integer> bfs = new ArrayList<>(); // Stores BFS traversal order
        boolean[] visited = new boolean[V];    // Tracks visited nodes

        /*
         * In case the graph has multiple connected components,
         * perform BFS starting from every unvisited vertex.
         */
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                bfs(i, adj, visited, bfs); // BFS for the current component
            }
        }

        return bfs;
    }

    /*
     * Helper Method: Standard BFS using Queue
     * Traverses all nodes reachable from the given start node.
     */
    private void bfs(int node, List<List<Integer>> adjList,
                     boolean[] visited, List<Integer> ans) {

        Queue<Integer> queue = new LinkedList<>();

        // Start BFS from the given node
        queue.add(node);
        visited[node] = true; // Mark visited at enqueue time to avoid duplicates

        while (!queue.isEmpty()) {

            // Remove front element of queue
            int current = queue.poll();

            // Add node to BFS traversal result
            ans.add(current);

            // Traverse all adjacent (neighbor) nodes
            for (int neighbour : adjList.get(current)) {

                // If neighbor is unvisited, mark and enqueue it
                if (!visited[neighbour]) {
                    visited[neighbour] = true;
                    queue.add(neighbour);
                }
            }
        }
    }
}