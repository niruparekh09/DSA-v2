import java.util.ArrayList;
import java.util.Arrays;

public class BellmanFord {
    public static void main(String[] args) {
        int V = 6, S = 0;
        ArrayList<ArrayList<Integer>> edges = new ArrayList<>();

        // Add edges: {u, v, weight}
        edges.add(new ArrayList<>(Arrays.asList(3, 2, 6)));
        edges.add(new ArrayList<>(Arrays.asList(5, 3, 1)));
        edges.add(new ArrayList<>(Arrays.asList(0, 1, 5)));
        edges.add(new ArrayList<>(Arrays.asList(1, 5, -3)));
        edges.add(new ArrayList<>(Arrays.asList(1, 2, -2)));
        edges.add(new ArrayList<>(Arrays.asList(3, 4, -2)));
        edges.add(new ArrayList<>(Arrays.asList(2, 4, 3)));

        BellmanFord sol = new BellmanFord();
        int[] ans = sol.bellman_ford(V, edges, S);

        // Output logic
        if (ans.length == 1 && ans[0] == -1)
            System.out.println("The graph contains negative cycle.");
        else {
            System.out.print("The shortest distance from source is: ");
            for (int i = 0; i < V; i++) {
                System.out.print(ans[i] + " ");
            }
        }
    }

    /*
     * Approach: Bellman-Ford Algorithm
     * Pattern: Shortest Path in Graphs with Negative Weights
     * Time Complexity: O(V * E) - Where V is vertices and E is edges.
     * Space Complexity: O(V) - To store the distance array.
     */
    public int[] bellman_ford(int V, ArrayList<ArrayList<Integer>> edges, int S) {
        int[] dist = new int[V];

        // Initialize distances with a large value (Infinity)
        Arrays.fill(dist, (int) 1e9);

        // Distance of source node to itself is always 0
        dist[S] = 0;

        /* * Relaxation Step:
         * We relax all edges (V-1) times. The shortest path in a graph with V nodes
         * can have at most V-1 edges.
         */
        for (int i = 0; i < V - 1; i++) {

            for (ArrayList<Integer> it : edges) {
                int u = it.get(0); // Source node
                int v = it.get(1); // Destination node
                int wt = it.get(2); // Edge weight

                // If the source node 'u' has been reached and a shorter path to 'v'
                // is found through 'u', update the distance.
                if (dist[u] != 1e9 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        /*
         * Negative Cycle Detection:
         * We perform one final relaxation (the V-th iteration).
         * If any distance can still be reduced, it indicates a negative weight cycle.
         */

        for (ArrayList<Integer> it : edges) {
            int u = it.get(0);
            int v = it.get(1);
            int wt = it.get(2);

            if (dist[u] != 1e9 && dist[u] + wt < dist[v]) {
                // If weight reduces even after V-1 iterations, return {-1} to signal cycle.
                return new int[]{-1};
            }
        }

        return dist;
    }
}