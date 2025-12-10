import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class TopoSortDFS {
    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        TopoSortDFS solver = new TopoSortDFS();
        int[] result = solver.topoSort(V, adj);

        System.out.print("Topological Sort: ");
        for (int x : result) {
            System.out.print(x + " ");
        }
    }

    /*
     * Approach: Topological Sort using DFS
     * Pattern: Graph Traversal / Stack (Reverse Post-Order)
     * Time Complexity: O(V + E) - Visits every node and edge once.
     * Space Complexity: O(V) - Stack + Visited array + Recursion Stack.
     */
    public int[] topoSort(int V, List<List<Integer>> adj) {
        int[] vis = new int[V];
        Stack<Integer> st = new Stack<>();

        // Run DFS on all components
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) dfs(i, adj, vis, st);
        }

        int[] ans = new int[V];
        // Logic: Stack contains nodes in order of completion (children first).
        // Popping gives us the Topological Order (Reverse Post-Order).
        for (int i = 0; i < V; i++) {
            ans[i] = st.pop();
        }

        return ans;
    }

    private void dfs(int curNode, List<List<Integer>> adj, int[] vis, Stack<Integer> st) {
        vis[curNode] = 1;

        for (int neigh : adj.get(curNode)) {
            if (vis[neigh] == 0) dfs(neigh, adj, vis, st);
        }

        // Key Logic: Push node to stack only AFTER visiting all its dependencies (children).
        // This ensures that when a node is popped, all its dependencies have already been processed.
        st.add(curNode);
    }
}