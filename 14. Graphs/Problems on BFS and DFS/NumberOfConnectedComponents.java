import java.util.ArrayList;
import java.util.List;

public class NumberOfConnectedComponents {
    public static void main(String[] args) {
        List<List<Integer>> edges = new ArrayList<>();

        edges.add(new ArrayList<>(List.of(0, 1)));
        edges.add(new ArrayList<>(List.of(1, 2)));
        edges.add(new ArrayList<>(List.of(2, 3)));
        edges.add(new ArrayList<>(List.of(4, 5)));

        NumberOfConnectedComponents n = new NumberOfConnectedComponents();

        System.out.println(n.findNumberOfComponent(7, edges));
    }

    public int findNumberOfComponent(int V, List<List<Integer>> edges) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjList.add(new ArrayList<>());
        }

        for (List<Integer> e : edges) {
            int u = e.get(0);
            int v = e.get(1);

            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        int cnt = 0;
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                cnt++;
                dfs(i, vis, adjList);
            }
        }

        return cnt;
    }

    private void dfs(int node, boolean[] vis, List<List<Integer>> adjList) {
        vis[node] = true;

        for (int neighbour : adjList.get(node)) {
            if (!vis[neighbour]) dfs(neighbour, vis, adjList);
        }
    }
}
