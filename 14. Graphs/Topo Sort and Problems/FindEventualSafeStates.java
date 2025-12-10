import java.util.*;

public class FindEventualSafeStates {

    public static void main(String[] args) {
        FindEventualSafeStates solver = new FindEventualSafeStates();

        int[][] graph = {
                {1, 2},
                {2, 3},
                {5},
                {0},
                {5},
                {},
                {}
        };

        List<Integer> result = solver.eventualSafeNodesDFS(graph);
        System.out.println(result);   // Expected: [2, 4, 5, 6]
    }

    /*
     * Approach: DFS with Cycle Detection
     * Pattern: Graph Traversal / Backtracking
     * Time Complexity: O(V + E) - Visits every node and edge once.
     * Space Complexity: O(V) - Visited arrays and safeNode array.
     */
    public List<Integer> eventualSafeNodesDFS(int[][] adj) {
        List<Integer> ans = new ArrayList<>();
        int N = adj.length;

        int[] vis = new int[N];
        int[] pathVis = new int[N]; // Tracks nodes in current recursion path
        int[] safeNode = new int[N]; // 1 = Safe, 0 = Unsafe/Unprocessed

        for (int i = 0; i < N; i++) {
            if (vis[i] == 0) dfs(i, vis, pathVis, safeNode, adj);
        }

        // Collect all nodes marked as Safe
        for (int i = 0; i < N; i++) {
            if (safeNode[i] == 1) ans.add(i);
        }
        return ans;
    }

    private boolean dfs(int curNode, int[] vis, int[] pathVis, int[] safeNode, int[][] adj) {
        // Base case: If already visited in current path -> Cycle detected!
        // (Though the logic below handles this via recursion, explicit check is safe)
        if (vis[curNode] == 1 && pathVis[curNode] == 1) return true;

        vis[curNode] = 1;
        pathVis[curNode] = 1;

        // Traverse neighbors
        for (int neigh : adj[curNode]) {
            // Case 1: Unvisited neighbor leads to a cycle
            if (vis[neigh] == 0) {
                if (dfs(neigh, vis, pathVis, safeNode, adj)) return true;
            }
            // Case 2: Visited neighbor is part of current path -> Cycle
            else if (pathVis[neigh] == 1) {
                return true;
            }
        }

        // If no cycle found downstream:
        pathVis[curNode] = 0; // Remove from path
        safeNode[curNode] = 1; // Mark as Safe Node
        return false;
    }

    /*
     * Approach: BFS (Kahn's Algorithm on Reversed Graph)
     * Pattern: Topological Sort
     * Time Complexity: O(V + E) + O(NlogN) - Reversing graph + BFS + Sorting.
     * Space Complexity: O(V + E) - Reversed Adjacency List + Queue.
     */
    public List<Integer> eventualSafeNodeBFS(int[][] adj) {
        // 1. Reverse the direction of edges for all nodes
        //    (Safe nodes are those that lead to terminal nodes. In reverse, terminal nodes have In-Degree 0).
        // 2. Do BFS Traversal (Topo Sort).
        // 3. The Topo Sort will give all safe nodes (Terminal -> Safe).
        //    Sort the result and return it.
        int V = adj.length;

        List<List<Integer>> adjRev = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjRev.add(new ArrayList<>());
        }

        // Step 1: Reverse Edges
        for (int i = 0; i < V; i++) {
            // Original: i -> it. Reversed: it -> i
            for (int it : adj[i]) {
                adjRev.get(it).add(i);
            }
        }

        // Step 2: Kahn's Algo on Reversed Graph
        List<Integer> ans = bfs(adjRev);

        // Sort result for output format
        Collections.sort(ans); // Changed from reverse to sort for typical output requirements
        return ans;
    }

    private List<Integer> bfs(List<List<Integer>> adjRev) {
        // Note: Using 'topo' list instead of Stack, as order doesn't strictly matter for "set of safe nodes"
        int V = adjRev.size();
        int[] inD = new int[V];

        // Calculate In-Degrees for Reversed Graph
        for (int i = 0; i < V; i++) {
            for (int neigh : adjRev.get(i)) inD[neigh]++;
        }

        Queue<Integer> q = new LinkedList<>();

        // Add Terminal Nodes (0 In-Degree in Reversed Graph)
        for (int i = 0; i < V; i++) {
            if (inD[i] == 0) q.add(i);
        }

        List<Integer> safeNodes = new ArrayList<>();

        while (!q.isEmpty()) {
            Integer frontNode = q.remove();
            safeNodes.add(frontNode);

            for (int neigh : adjRev.get(frontNode)) {
                inD[neigh]--;
                if (inD[neigh] == 0) q.add(neigh);
            }
        }

        return safeNodes;
    }
}