import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ShortestPathInDAG {
    public static void main(String[] args) {
        ShortestPathInDAG sp = new ShortestPathInDAG();

        int N = 4;
        int M = 2;
        int[][] edges = {
                {0, 1, 2},
                {0, 2, 1}
        };

        int[] result = sp.shortestPath(N, M, edges);

        for (int x : result) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    /*
     * Approach: Topological Sort + Edge Relaxation
     * Pattern: DAG + DP on Topo Order
     * Time Complexity: O(N + M) – Topo sort + single pass relaxation.
     * Space Complexity: O(N + M) – Adjacency list + recursion + stack.
     */
    public int[] shortestPath(int N, int M, int[][] edges) {

        // Build adjacency list: each edge u -> v with weight w
        List<List<Pair>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) adjList.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            adjList.get(u).add(new Pair(v, w));
        }

        // Step 1: Topological Sort (valid ordering for DAG the shortest path)
        Stack<Integer> st = new Stack<>();
        int[] vis = new int[N];

        for (int i = 0; i < N; i++) {
            if (vis[i] == 0) topoSort(i, adjList, vis, st);
        }

        // Step 2: Relax edges in topological order
        int[] dist = new int[N];
        Arrays.fill(dist, (int) 1e9);

        dist[0] = 0; // source = 0 (as per problem statement)

        // Pop in topo order → ensures we only relax once dependencies are processed
        while (!st.isEmpty()) {
            int topNode = st.pop();

            // Relax outgoing edges from this node
            for (Pair neigh : adjList.get(topNode)) {
                int nxt = neigh.to;
                int wt = neigh.weight;

                if (dist[topNode] + wt < dist[nxt]) {
                    dist[nxt] = dist[topNode] + wt;
                }
            }
        }

        // Mark unreachable nodes
        for (int i = 0; i < N; i++) {
            if (dist[i] == (int) 1e9) dist[i] = -1;
        }

        return dist;
    }

    /*
     * Approach: DFS-based Topological Sort
     * Pattern: DFS + Post-order push
     * Why: DAG shortest path requires nodes processed in dependency order.
     */
    private void topoSort(int curNode, List<List<Pair>> adjList, int[] vis, Stack<Integer> st) {
        vis[curNode] = 1;

        // DFS all outgoing edges
        for (Pair neigh : adjList.get(curNode)) {
            int nxt = neigh.to;
            if (vis[nxt] == 0) topoSort(nxt, adjList, vis, st);
        }

        // Post-order: push after exploring all neighbors
        st.add(curNode);
    }

    class Pair {
        int to, weight;

        public Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
