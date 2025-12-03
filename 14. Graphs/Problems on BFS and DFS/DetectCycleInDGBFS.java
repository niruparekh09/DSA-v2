import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DetectCycleInDGBFS {

    /*
     * Approach: Cycle Detection in Directed Graph (BFS attempt)
     * Pattern: Graph Traversal
     * Time Complexity: O(V + E)
     * Space Complexity: O(V) - For visited arrays and Queue.
     */
    public boolean isCyclic(int N, List<List<Integer>> adj) {
        int[] vis = new int[N];
        int[] pathVis = new int[N]; // Used to track nodes in the current traversal path

        // Handle disconnected components
        for (int i = 0; i < N; i++) {
            if (vis[i] == 0) {
                if (checkCycleBfs(i, adj, vis, pathVis)) return true;
            }
        }

        return false;
    }

    private boolean checkCycleBfs(int startNode, List<List<Integer>> adjList, int[] vis, int[] pathVis) {
        Queue<Pair> q = new LinkedList<>();

        // Start BFS from the source
        q.add(new Pair(startNode, -1));

        while (!q.isEmpty()) {
            //* Implementation details missing in provided snippet.
            //* Typically, requires checking indegrees (Kahn's Algo) for Directed Graphs,
            //* rather than just parent pointers used in Undirected Graphs.
            //* So will do the impl. after learning about it.
        }

        return false;
    }

    class Pair {
        int node;
        int parent;

        public Pair(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }
    }
}