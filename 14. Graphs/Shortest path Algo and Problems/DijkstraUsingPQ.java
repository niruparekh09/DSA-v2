import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraUsingPQ {

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

        DijkstraUsingPQ d = new DijkstraUsingPQ();
        int[] shortestDistances = d.dijkstra(numNodes, adjList, sourceNode);

        System.out.println(Arrays.toString(shortestDistances));
    }

    /*
     * Approach: Dijkstra's Algorithm (Priority Queue)
     * Pattern: Shortest Path in Weighted Graph
     * Time Complexity: O(E * log(V)) - E updates, each update takes log(V) in PQ.
     * Space Complexity: O(V + E) - Adjacency List + Distance Array + Priority Queue.
     */
    public int[] dijkstra(int V, ArrayList<ArrayList<ArrayList<Integer>>> adj, int S) {

        // Min-Heap (Priority Queue) stores {distance, node}.
        // Ordered by distance ASC to always process the closest node first (Greedy).
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> x.distance - y.distance);

        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9); // Infinity

        // Initialize Source
        dist[S] = 0;
        pq.add(new Pair(dist[S], S));

        while (!pq.isEmpty()) {
            Pair frontPair = pq.poll();
            int frontNode = frontPair.node;
            int frontNodeDist = frontPair.distance;

            // Traverse neighbors
            for (List<Integer> neigh : adj.get(frontNode)) {
                Integer adjNode = neigh.get(0);
                Integer edgeW = neigh.get(1);

                // Relaxation Step:
                // If a shorter path to 'adjNode' is found through 'frontNode', update distance and push to PQ.
                if (edgeW + frontNodeDist < dist[adjNode]) {
                    dist[adjNode] = edgeW + frontNodeDist;
                    pq.add(new Pair(dist[adjNode], adjNode));
                }
            }
        }

        return dist;
    }

    private class Pair {
        int distance;
        int node;

        public Pair(int distance, int node) {
            this.distance = distance;
            this.node = node;
        }
    }
}