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
        // This list stores neighbors as (v, w) pairs: adjList[u] = List of {v, w}
        List<List<Pair>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) adjList.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            int u = edges[i][0], v = edges[i][1], w = edges[i][2];
            adjList.get(u).add(new Pair(v, w));
        }

        // Step 1: Topological Sort (valid ordering for DAG the shortest path)
        // We need an order where predecessors (u) are processed before successors (v).
        Stack<Integer> st = new Stack<>();
        int[] vis = new int[N]; // 0: unvisited, 1: visited

        // Ensure all components are covered for the topological order
        for (int i = 0; i < N; i++) {
            if (vis[i] == 0) topoSort(i, adjList, vis, st);
        }
        //

        // Step 2: Relax edges in topological order
        int[] dist = new int[N];
        // Initialize distances to infinity (a large number)
        Arrays.fill(dist, (int) 1e9);

        dist[0] = 0; // source = 0 (as per problem statement)

        // Pop in topo order → ensures we only relax once dependencies are processed
        // The source node (0) must be processed early due to the topo sort property.
        while (!st.isEmpty()) {
            int topNode = st.pop();

            // Check if the current node is reachable (dist is not infinity)
            if (dist[topNode] == (int) 1e9) continue;

            // Relax outgoing edges from this node
            for (Pair neigh : adjList.get(topNode)) {
                int nxt = neigh.to;
                int wt = neigh.weight;

                // Relaxation condition: Check if path through topNode is shorter
                if (dist[topNode] + wt < dist[nxt]) {
                    // Update the shortest distance to the neighbor
                    dist[nxt] = dist[topNode] + wt;
                }
            }
        }

        // Mark unreachable nodes
        for (int i = 0; i < N; i++) {
            // If distance is still infinity, the node is unreachable from source 0
            if (dist[i] == (int) 1e9) dist[i] = -1;
        }

        return dist;
    }

    /*
     * Approach: DFS-based Topological Sort
     * Pattern: DFS + Post-order push
     * Why: Pushing a node *after* visiting all its neighbors guarantees that all
     * predecessors appear before the current node when the stack is popped.
     */
    private void topoSort(int curNode, List<List<Pair>> adjList, int[] vis, Stack<Integer> st) {
        vis[curNode] = 1;

        // DFS all outgoing edges to ensure they are pushed onto the stack first
        for (Pair neigh : adjList.get(curNode)) {
            int nxt = neigh.to;
            if (vis[nxt] == 0) topoSort(nxt, adjList, vis, st);
        }

        // Post-order: push after exploring all neighbors (i.e., when recursion returns)
        st.add(curNode);
    }

    private class Pair {
        int to, weight; // 'to' is the neighbor node, 'weight' is the edge weight

        public Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
