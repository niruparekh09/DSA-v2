import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DFS {

    public static void main(String[] args) {

        int V = 5;
        List<List<Integer>> adj = new ArrayList<>();

        // initialize adjacency list
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // build the graph (undirected)
        adj.get(0).addAll(Arrays.asList(2, 3, 1));
        adj.get(1).add(0);
        adj.get(2).addAll(Arrays.asList(0, 4));
        adj.get(3).add(0);
        adj.get(4).add(2);

        DFS dfs = new DFS();
        List<Integer> dfsOfGraph = dfs.dfsOfGraph(V, adj);

        System.out.println("DFS of Graph: " + dfsOfGraph);
    }

    // Function to return DFS traversal of the whole graph
    public List<Integer> dfsOfGraph(int V, List<List<Integer>> adj) {

        boolean[] vis = new boolean[V];   // visited array
        List<Integer> ans = new ArrayList<>();  // stores DFS order

        // In case the graph has multiple components,
        // we start DFS from every unvisited vertex.
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, vis, ans, adj);    // start DFS from node i
            }
        }

        return ans;
    }

    // Recursive DFS function
    private void dfs(int node, boolean[] vis, List<Integer> dfsList, List<List<Integer>> adj) {
        vis[node] = true;        // mark current node as visited
        dfsList.add(node);       // add node to DFS result

        // Explore every neighbor of current node
        for (int neighbor : adj.get(node)) {
            // If neighbor is unvisited, perform DFS on it
            if (!vis[neighbor]) {
                dfs(neighbor, vis, dfsList, adj);
            }
        }
    }
}
