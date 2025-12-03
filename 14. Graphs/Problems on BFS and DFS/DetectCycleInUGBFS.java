import java.util.*;

public class DetectCycleInUGBFS {
    public static void main(String[] args) {
        DetectCycleInUGBFS d = new DetectCycleInUGBFS();
        int V = 6;
        List<Integer>[] adj = new List[V];
        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();

        adj[0].addAll(Arrays.asList(1, 3));
        adj[1].addAll(Arrays.asList(0, 2, 4));
        adj[2].addAll(Arrays.asList(1, 5));
        adj[3].addAll(Arrays.asList(0, 4));
        adj[4].addAll(Arrays.asList(1, 3, 5));
        adj[5].addAll(Arrays.asList(2, 4));

        System.out.println(d.isCycle(V, adj));
    }

    /*
     * Approach: BFS Cycle Detection
     * Pattern: Graph Traversal / Breadth-First Search
     * Time Complexity: O(V + 2E) - BFS visits every node and edge once.
     * Space Complexity: O(V) - Queue and Visited array.
     */
    public boolean isCycle(int V, List<Integer>[] adj) {
        boolean[] vis = new boolean[V];

        // Handle Disconnected Components:
        // Check every unvisited node as a potential start of a new component.
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (detectCycle(i, adj, vis)) return true;
            }
        }
        return false;
    }

    private boolean detectCycle(int src, List<Integer>[] adj, boolean[] vis) {
        Queue<Pair> q = new LinkedList<>();

        // Start BFS from source, initializing parent as -1
        q.add(new Pair(src, -1));
        vis[src] = true;

        while (!q.isEmpty()) {
            Pair frontNode = q.remove();
            int cur = frontNode.node;
            int parent = frontNode.parent;

            for (int neighbor : adj[cur]) {
                // Case 1: Unvisited neighbor.
                // Enqueue it and track 'cur' as its parent (where we came from).
                if (!vis[neighbor]) {
                    q.add(new Pair(neighbor, cur));
                    vis[neighbor] = true;
                }
                // Case 2: Visited neighbor.
                // Logic: If we encounter a visited node that is NOT our parent,
                // it means we reached this node via a DIFFERENT path.
                // Two paths to the same node imply a cycle.
                else if (vis[neighbor] && neighbor != parent) {
                    return true;
                }
            }
        }
        return false;
    }

    // Helper method to handle Edge List input instead of Adjacency List
    public boolean isCycle2(int V, int[][] edges) {
        List<Integer>[] adj = new List[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        // Convert Edge List to Adjacency List
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            adj[u].add(v);
            adj[v].add(u); // Undirected Graph property
        }

        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (detectCycle(i, adj, vis)) return true;
            }
        }
        return false;
    }

    private class Pair {
        int node;
        int parent;

        public Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }
}