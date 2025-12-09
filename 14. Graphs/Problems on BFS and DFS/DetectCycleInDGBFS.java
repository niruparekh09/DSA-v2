import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class DetectCycleInDGBFS {

    public static void main(String[] args) {
        int V = 6;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        adj.get(0).add(1);
        adj.get(1).add(2);
        adj.get(1).add(5);
        adj.get(2).add(3);
        adj.get(3).add(4);
        adj.get(4).add(1);

        DetectCycleInDGBFS checker = new DetectCycleInDGBFS();
        boolean result = checker.isCyclic(V, adj);

        System.out.println("Cycle detected? " + result);
    }

    /*
     * Approach: Kahn's Algorithm (BFS)
     * Pattern: Topological Sort / Cycle Detection
     * Time Complexity: O(V + E) - Visits every node and edge once.
     * Space Complexity: O(V) - For In-Degree array and Queue.
     */
    public boolean isCyclic(int V, List<List<Integer>> adj) {
        int cnt = 0; // Tracks number of nodes in the Topological Sort

        int[] inDegree = new int[V];

        // Step 1: Calculate In-Degrees for all nodes
        for (int i = 0; i < V; i++) {
            for (int it : adj.get(i)) inDegree[it]++;
        }

        // Queue to facilitate BFS
        Queue<Integer> q = new LinkedList<>();

        // Step 2: Add nodes with 0 In-Degree (Start nodes) to queue
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        int index = 0; // Unused variable

        // Step 3: Process Queue
        while (!q.isEmpty()) {
            int frontNode = q.remove();

            cnt++; // Successfully resolved a node

            // Traverse the neighbours
            for (int neigh : adj.get(frontNode)) {
                // Logic: "Remove" the edge from frontNode -> neigh
                inDegree[neigh]--;

                // If neighbor has no more dependencies, it's ready to be processed
                if (inDegree[neigh] == 0) q.add(neigh);
            }
        }

        // Key Logic: If the graph is a DAG, Topological Sort will contain exactly V nodes.
        // If cnt < V, it means a cycle prevented some nodes from reaching 0 In-Degree.
        return !(cnt == V);
    }
}