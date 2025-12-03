import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DetectCycleInUGDFS {
    public static void main(String[] args) {
        int V = 6;
        List<Integer>[] adj = new List[V];

        for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();

        adj[0].addAll(Arrays.asList(1, 3));
        adj[1].addAll(Arrays.asList(0, 2, 4));
        adj[2].addAll(Arrays.asList(1, 5));
        adj[3].addAll(Arrays.asList(0, 4));
        adj[4].addAll(Arrays.asList(1, 3, 5));
        adj[5].addAll(Arrays.asList(2, 4));

        DetectCycleInUGDFS d = new DetectCycleInUGDFS();

        System.out.println(d.isCycle(V, adj));
    }

    /*
     * Approach: DFS Cycle Detection
     * Pattern: Graph Traversal / Backtracking
     * Time Complexity: O(V + 2E) - DFS visits every node and edge once.
     * Space Complexity: O(V) - Recursion stack depth and visited array.
     */
    public boolean isCycle(int V, List<Integer>[] adj) {
        boolean[] vis = new boolean[V];

        // Handle Disconnected Components:
        // A cycle might exist in a component not connected to node 0.
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                // Start DFS with parent -1 (root of component)
                if (detectCycle(i, adj, vis, -1)) return true;
            }
        }
        return false;
    }

    // DFS Logic: Maintain 'parent' to distinguish between a cycle and simply going back up the edge we came from.
    public boolean detectCycle(int src, List<Integer>[] adj, boolean[] vis, int parent) {
        vis[src] = true;

        for (int neighbor : adj[src]) {
            // Case 1: Unvisited neighbor. Continue DFS.
            if (!vis[neighbor]) {
                // If a cycle is found deep in the recursion, propagate true upwards.
                if (detectCycle(neighbor, adj, vis, src)) return true;
            }
            // Case 2: Visited neighbor.
            // Key Logic: If the visited neighbor is NOT the parent, it means we found a
            // "back edge" to an ancestor or another node in the current traversal path.
            // This confirms a cycle.
            else if (neighbor != parent) {
                return true;
            }
        }
        return false; // No cycle found in this path
    }
}