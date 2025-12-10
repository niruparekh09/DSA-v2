import java.util.ArrayList;
import java.util.List;

public class DetectCycleInDGDFS {
    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(1).add(5);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(4).add(1);

        DetectCycleInDGDFS checker = new DetectCycleInDGDFS();
        boolean result = checker.isCyclic1(V, adj);

        System.out.println("Cycle detected? " + result);
    }

    /*
     * Approach: DFS with Path Tracking (Recursion Stack)
     * Pattern: Graph Traversal / Backtracking
     * Time Complexity: O(V + E) - Visits every node and edge once.
     * Space Complexity: O(V) - Visited arrays and recursion stack.
     */
    public boolean isCyclic1(int N, List<List<Integer>> adj) {
        int[] vis = new int[N];
        int[] pathVis = new int[N]; // Tracks nodes currently active in the recursion stack

        // Handle disconnected components
        for (int i = 0; i < N; i++) {
            if (vis[i] == 0) {
                if (checkCycleDfs1(i, adj, vis, pathVis)) return true;
            }
        }

        return false;
    }

    private boolean checkCycleDfs1(int curNode, List<List<Integer>> adjList, int[] vis, int[] pathVis) {
        vis[curNode] = 1;      // Mark visited globally
        pathVis[curNode] = 1;  // Mark visited in current recursion path

        // Traverse neighbors
        for (int neigh : adjList.get(curNode)) {
            // Case 1: Unvisited neighbor, recurse deeper
            if (vis[neigh] == 0) {
                if (checkCycleDfs1(neigh, adjList, vis, pathVis)) return true;
            }
            // Case 2: Neighbor visited AND is in the current recursion path
            // Logic: This implies a "Back Edge" pointing to an ancestor in the current DFS path -> Cycle detected.
            // (Unlike undirected graphs, we don't check parent here).
            else if (pathVis[neigh] == 1) {
                return true;
            }
        }

        // Backtracking: Remove node from current path as we exit the recursion.
        // This allows this node to be visited again via a different path without triggering a cycle false positive.
        pathVis[curNode] = 0;
        return false;
    }

    /*
     * Approach: DFS with 3-State Coloring
     * Pattern: Graph Traversal / Cycle Detection
     * Time Complexity: O(V + E) - Visits every node and edge once.
     * Space Complexity: O(V) - Recursion stack and a single visited array.
     */
    public boolean isCyclic2(int N, List<List<Integer>> adj) {
        // State 0: Unvisited
        // State 1: Visiting (Currently in recursion stack)
        // State 2: Visited (Processed completely)
        int[] vis = new int[N];

        for (int i = 0; i < N; i++) {
            if (vis[i] == 0) {
                if (checkCycleDfs2(i, adj, vis)) return true;
            }
        }

        return false;
    }

    private boolean checkCycleDfs2(int curNode, List<List<Integer>> adj, int[] vis) {
        // Case 1: Back Edge Detected
        // If we encounter a node marked as '1', it means we are re-visiting an ancestor
        // in the current recursion path -> Cycle.
        if (vis[curNode] == 1) {
            return true;
        }

        // Case 2: Cross Edge or Forward Edge to already processed component
        // Node marked '2' is fully processed and safe. No cycle here.
        if (vis[curNode] == 2) {
            return false;
        }

        // Step 1: Mark as Visiting (Add to recursion stack)
        vis[curNode] = 1;

        // Step 2: Traverse Neighbors
        for (int neigh : adj.get(curNode)) {
            if (checkCycleDfs2(neigh, adj, vis)) return true;
        }

        // Step 3: Backtrack / Mark as Visited
        // We are done with this node and its subtree. Mark as '2' (Safe).
        vis[curNode] = 2;
        return false;
    }
}