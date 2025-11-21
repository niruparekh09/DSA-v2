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

    public boolean isCycle(int V, List<Integer>[] adj) {
        boolean[] vis = new boolean[V];

        // Exploring all the components of the graph
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (detectCycle(i, adj, vis, -1)) return true;
            }
        }
        return false;
    }

    public boolean detectCycle(int src, List<Integer>[] adj, boolean[] vis, int parent) {
        vis[src] = true;

        for (int neighbor : adj[src]) {
            if (!vis[neighbor]) {
                // Recurse: if a cycle is found deeper, bubble up true
                if (detectCycle(neighbor, adj, vis, src)) return true;
            } else if (neighbor != parent) {
                // visited neighbor which is not parent -> cycle
                return true;
            }
        }
        return false; // no cycle found in any neighbor
    }
}
