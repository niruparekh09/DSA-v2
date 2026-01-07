import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class ShortestPathInUGWithUnitWeights {
    public static void main(String[] args) {

        // Given input
        int N = 9;
        int M = 10;

        int[][] edges = {
                {0,1}, {0,3}, {3,4}, {4,5}, {5,6},
                {1,2}, {2,6}, {6,7}, {7,8}, {6,8}
        };

        ShortestPathInUGWithUnitWeights solver = new ShortestPathInUGWithUnitWeights();

        int[] result = solver.shortestPath(edges, N, M);

        System.out.println("Shortest distances from node 0:");
        System.out.println(Arrays.toString(result));
    }

    /*
     * Approach: BFS on Unweighted Graph
     * Pattern: Graph + Breadth-First Search
     * Time Complexity: O(N + M) – Every node and edge processed once.
     * Space Complexity: O(N + M) – Adjacency list + BFS queue + dist array.
     */
    public int[] shortestPath(int[][] edges, int N, int M) {

        // Build adjacency list for undirected graph
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) adjList.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            int u = edges[i][0], v = edges[i][1];
            adjList.get(u).add(v);
            adjList.get(v).add(u); // undirected graph
        }

        int[] dist = new int[N];
        Arrays.fill(dist, (int) 1e9); // mark all nodes unvisited with large value

        // BFS from source node 0 (classic shortest path for unit weights)
        bfs(0, adjList, dist);

        // Post-process: unreachable → -1
        for (int i = 0; i < N; i++) {
            if (dist[i] == (int) 1e9) dist[i] = -1;
        }

        return dist;
    }

    /*
     * Approach: BFS Level Expansion
     * Pattern: Queue-Based BFS
     * Why: In unit-weight graphs, BFS guarantees shortest distance discovery in order.
     */
    private void bfs(int src, List<List<Integer>> adjList, int[] dist) {
        Queue<Integer> q = new LinkedList<>();

        dist[src] = 0;      // source distance = 0
        q.add(src);         // start BFS

        while (!q.isEmpty()) {
            int frontNode = q.poll();
            int curDist = dist[frontNode];

            // Explore neighbors and relax edges by 1 unit
            for (int neigh : adjList.get(frontNode)) {
                // If we find a shorter path (first time visit)
                if (dist[neigh] > curDist + 1) {
                    dist[neigh] = curDist + 1;
                    q.add(neigh); // push for further exploration
                }
            }
        }
    }
}
