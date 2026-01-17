import java.util.*;

public class Bipartite {
    public static void main(String[] args) {
        Bipartite solver = new Bipartite();

        int V1 = 4;
        List<List<Integer>> adj1 = new ArrayList<>();
        for (int i = 0; i < V1; i++) adj1.add(new ArrayList<>());
        adj1.get(0).addAll(Arrays.asList(1, 3));
        adj1.get(1).addAll(Arrays.asList(0, 2));
        adj1.get(2).addAll(Arrays.asList(1, 3));
        adj1.get(3).addAll(Arrays.asList(0, 2));

        int V2 = 3;
        List<List<Integer>> adj2 = new ArrayList<>();
        for (int i = 0; i < V2; i++) adj2.add(new ArrayList<>());
        adj2.get(0).addAll(Arrays.asList(1, 2));
        adj2.get(1).addAll(Arrays.asList(0, 2));
        adj2.get(2).addAll(Arrays.asList(0, 1));

        System.out.println("Graph 1 is bipartite? " + solver.isBipartite(V1, adj1));
        System.out.println("Graph 2 is bipartite? " + solver.isBipartite(V2, adj2));
    }

    /*
     * Approach: Graph Coloring (2-Coloring)
     * Pattern: Graph Traversal (BFS/DFS)
     * Time Complexity: O(V + 2E) - Visits every vertex and edge.
     * Space Complexity: O(V) - Color array and Queue/Recursion stack.
     */
    public boolean isBipartite(int V, List<List<Integer>> adj) {
        int[] color = new int[V];
        Arrays.fill(color, -1); // -1 indicates Unvisited

        // Handle disconnected components
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                // If any component is NOT bipartite, the whole graph isn't.
                // Choose either BFS or DFS:
                // if (!bfs(i, adj, color)) return false;
                if (!dfs(i,adj,color,1)) return false;
            }
        }

        return true;
    }

    // BFS Implementation
    private boolean bfs(int start, List<List<Integer>> adjList, int[] color) {
        Queue<Integer> q = new LinkedList<>();

        q.add(start);
        color[start] = 0; // Start with arbitrary color (0)

        while (!q.isEmpty()) {
            int frontNode = q.remove();

            for (int nei : adjList.get(frontNode)) {
                // Case 1: Unvisited neighbor
                if (color[nei] == -1) {
                    // Key Logic: Assign neighbor the OPPOSITE color (0 -> 1, 1 -> 0)
                    color[nei] = 1 - color[frontNode];
                    q.add(nei);
                }
                // Case 2: Visited neighbor
                // Logic: If neighbor has the SAME color as current node,
                // we have an edge connecting two nodes of the same set. Not Bipartite.
                else if (color[nei] == color[frontNode]) return false;
            }
        }

        return true;
    }

    // DFS Implementation
    private boolean dfs(int curNode, List<List<Integer>> adjList, int[] color, int prevColor) {
        // Key Logic: Color current node opposite to parent's color
        color[curNode] = 1 - prevColor;

        for (int nei : adjList.get(curNode)) {
            // Recurse if unvisited. If sub-call returns false (conflict found), bubble it up.
            if (color[nei] == -1) {
                if (!dfs(nei, adjList, color, color[curNode])) return false; // Fixed: added return false propagation
            }
            // Check for color conflict
            else if (color[nei] == color[curNode]) return false;
        }

        return true;
    }
}