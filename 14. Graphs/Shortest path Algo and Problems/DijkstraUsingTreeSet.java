import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class DijkstraUsingTreeSet {

    public static void main(String[] args) {
        int numNodes = 3;
        ArrayList<ArrayList<ArrayList<Integer>>> adjList = new ArrayList<>();
        for (int i = 0; i < numNodes; i++) {
            adjList.add(new ArrayList<>());
        }

        // Add edges: {Node u -> {Node v, Weight w}}
        adjList.get(0).add(new ArrayList<>(Arrays.asList(1, 1)));
        adjList.get(0).add(new ArrayList<>(Arrays.asList(2, 6)));
        adjList.get(1).add(new ArrayList<>(Arrays.asList(2, 3)));
        adjList.get(1).add(new ArrayList<>(Arrays.asList(0, 1)));
        adjList.get(2).add(new ArrayList<>(Arrays.asList(1, 3)));
        adjList.get(2).add(new ArrayList<>(Arrays.asList(0, 6)));

        int sourceNode = 2;

        DijkstraUsingTreeSet d = new DijkstraUsingTreeSet();
        int[] shortestDistances = d.dijkstra(numNodes, adjList, sourceNode);

        System.out.println(Arrays.toString(shortestDistances));
    }

    /*
     * Approach: Dijkstra's Algorithm (TreeSet)
     * Pattern: Shortest Path in Weighted Graph
     * Time Complexity: O(E * log(V)) - Similar to PQ, but allows removing outdated entries.
     * Space Complexity: O(V + E) - Adjacency List + Distance Array + TreeSet.
     */
    public int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {
        // TreeSet acts as a Priority Queue but allows REMOVING specific elements efficiently (O(log N)).
        // Comparator: Sort primarily by distance, secondarily by node index (to handle unique pairs).
        // <dist,node>
        TreeSet<int[]> set = new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9);

        // Initialize Source
        dist[S] = 0;
        set.add(new int[]{dist[S], S});

        while(!set.isEmpty()){
            // Extract min distance node (Greedy Step)
            int[] cur = set.pollFirst();
            int curDis = cur[0];
            int node = cur[1];

            // Traverse neighbors
            for(List<Integer> neigh : adj.get(node)){
                Integer adjN = neigh.get(0);
                Integer edgW = neigh.get(1);

                // Relaxation Step
                if(curDis + edgW < dist[adjN]){
                    // Key Difference vs PQ: We can remove the old {dist, node} pair to prevent
                    // processing redundant/stale entries later.
                    // This keeps the Set size strictly bounded by V (at most one entry per node).
                    int[] remVal = {dist[adjN], adjN};
                    set.remove(remVal);

                    // Update distance and insert new pair
                    dist[adjN] = curDis + edgW;
                    set.add(new int[]{dist[adjN], adjN});
                }
            }
        }

        return dist;
    }
}