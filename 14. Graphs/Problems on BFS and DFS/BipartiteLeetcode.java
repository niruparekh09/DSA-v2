import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteLeetcode {
    public static void main(String[] args) {
        BipartiteLeetcode solver = new BipartiteLeetcode();

        // Example 1: Bipartite graph
        int[][] graph1 = {
                {1, 3},
                {0, 2},
                {1, 3},
                {0, 2}
        };

        // Example 2: Not bipartite (odd cycle)
        int[][] graph2 = {
                {1, 2},
                {0, 2},
                {0, 1}
        };

        System.out.println("Graph 1 is bipartite? " + solver.isBipartite(graph1));
        System.out.println("Graph 2 is bipartite? " + solver.isBipartite(graph2));
    }

    //int[][] graph → Adjacency List (array-based)
    public boolean isBipartite(int[][] graph) {
        int V = graph.length;
        int[] color = new int[V];
        Arrays.fill(color, -1);

        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!bfs(i, graph, color))
                    return false;
            }
        }
        return true;
    }

    private boolean bfs(int start, int[][] graph, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nei : graph[node]) {
                if (color[nei] == -1) {
                    color[nei] = 1 - color[node];
                    q.add(nei);
                } else if (color[nei] == color[node]) {
                    return false;
                }
            }
        }
        return true;
    }
}