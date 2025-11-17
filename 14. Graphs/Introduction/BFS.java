import java.util.*;

class BFS {
    public static void main(String[] args) {
        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // build the graph (adjacency lists)
        adj.get(0).addAll(Arrays.asList(2, 3, 1));
        adj.get(1).add(0);
        adj.get(2).addAll(Arrays.asList(0, 4));
        adj.get(3).add(0);
        adj.get(4).add(2);

        BFS bfs = new BFS();
        List<Integer> bfsOfGraph = bfs.bfsOfGraph(V, adj);

        System.out.println("BFS of Graph: " + bfsOfGraph);
        // Expected output: BFS of Graph: [0, 2, 3, 1, 4]
    }

    // Return BFS traversal for all nodes (handles disconnected graphs)
    public List<Integer> bfsOfGraph(int V, List<List<Integer>> adj) {
        List<Integer> bfs = new ArrayList<>();
        boolean[] visited = new boolean[V]; // mark nodes we've already enqueued/visited

        // Ensure we cover every connected component
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                // BFS from node i will visit its entire component
                bfs(i, adj, visited, bfs);
            }
        }
        return bfs;
    }

    // Standard BFS using a queue
    private void bfs(int node, List<List<Integer>> adjList, boolean[] visited, List<Integer> ans) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(node);         // start from 'node'
        visited[node] = true;    // mark as visited when enqueued (avoid duplicates)

        while (!queue.isEmpty()) {
            int current = queue.poll(); // pop front
            ans.add(current);           // process current node

            // iterate over neighbors and enqueue unvisited ones
            for (int neighbour : adjList.get(current)) {
                if (!visited[neighbour]) {
                    visited[neighbour] = true; // mark immediately to avoid multi-enqueue
                    queue.add(neighbour);
                }
            }
        }
    }
}