import java.util.*;

public class NumberOfWaysToArriveAtDestination {
    public int countPaths(int n, int[][] roads) {
        // Convert Roads to AdjList
        List<List<Pair>> adjList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjList.add(new ArrayList<>());
        }

        for (int[] road : roads) {
            int u = road[0];
            int v = road[1];
            int w = road[2];
            adjList.get(u).add(new Pair(v, w));
            adjList.get(v).add(new Pair(u, w));
        }

        //{minTime(distance),node}
        Queue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        long[] dist = new long[n]; // To avoid integer overflow
        int[] ways = new int[n];
        Arrays.fill(dist, Long.MAX_VALUE); // Use infinity since shortest path can exceed 1e9 and must not overflow

        dist[0] = 0;
        ways[0] = 1;

        // Pushing Node 0 to pq
        pq.add(new long[]{dist[0], 0});

        int mod = (int) 1e9 + 7;
        while (!pq.isEmpty()) {
            long[] frontNode = pq.remove();
            long curDist = frontNode[0];
            int curNode = (int) frontNode[1];

            if (curDist > dist[curNode]) continue;

            // Traverse to neigh node
            for (Pair neigh : adjList.get(curNode)) {
                int neighNode = neigh.to;
                int neighDist = neigh.weight;

                // Visiting this node first time with this dist
                if (curDist + neighDist < dist[neighNode]) {
                    dist[neighNode] = curDist + neighDist;
                    pq.add(new long[]{dist[neighNode], neighNode});
                    ways[neighNode] = ways[curNode];
                }

                // This is the second time we are visiting with same dist
                else if (curDist + neighDist == dist[neighNode]) {
                    ways[neighNode] = (ways[neighNode] + ways[curNode]) % mod;
                }
            }
        }

        return ways[n - 1] % mod;
    }

    private class Pair {
        int to;
        int weight;

        public Pair(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}