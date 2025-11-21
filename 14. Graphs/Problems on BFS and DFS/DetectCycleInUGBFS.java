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

    public boolean isCycle(int V, List<Integer>[] adj) {
        boolean[] vis = new boolean[V];

        // For disconnected components of graph
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (detectCycle(i, adj, vis)) return true;
            }
        }

        return false;
    }

    private boolean detectCycle(int src, List<Integer>[] adj, boolean[] vis) {
        Queue<Pair> q = new LinkedList<>();

        q.add(new Pair(src, -1)); // Add the first node of graph to node

        vis[src] = true;

        while (!q.isEmpty()) {
            Pair frontNode = q.remove();
            int cur = frontNode.node; // Current node
            int parent = frontNode.parent;// Parent Node of cur node

            // Add the adj neighbours of cur node to q and check if cyclic
            for (int neighbor : adj[cur]) {
                // If neighbor is not visited add to queue along with cur as parent and mark it as visited
                if (!vis[neighbor]) {
                    q.add(new Pair(neighbor, cur));
                    vis[neighbor] = true;
                }
                // If neighbor is visited and is not parent of cur node then Graph is cyclic
                else if (vis[neighbor] && neighbor != parent) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
    Edges (edge list):
    [
        [0, 1],
        [1, 2],
        [1, 3],
        [2, 1],
        [3, 1]
    ]

    Corresponding adjacency list:
    0 -> [1]
    1 -> [2, 3]
    2 -> [1]
    3 -> [1]
    */
    public boolean isCycle2(int V, int[][] edges) {
        List<Integer>[] adj = new List[V];

        for (int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
        }

        // edges is an edge list, not an adjacency matrix
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];

            adj[u].add(v);
            adj[v].add(u); // <-- Only if the graph is undirected
        }

        boolean[] vis = new boolean[V];

        // For disconnected components of graph
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
