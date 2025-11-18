import java.util.ArrayList;
import java.util.List;


public class NumberOfProvinces {
    public static void main(String[] args) {
        NumberOfProvinces p = new NumberOfProvinces();
       /*
        1 --- 4      2 --- 3
        No of Provinces: 2
        */
        System.out.println("Number of Provinces: " + p.findCircleNum(new int[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1}
        }));
    }

    public int findCircleNum(int[][] isConnected) {
        // Converting Adjacency Matrix to Adjacency List
        List<List<Integer>> adjList = new ArrayList<>();
        int V = isConnected.length;

        for (int i = 0; i < V; i++) {
            List<Integer> neighbours = new ArrayList<>();
            for (int j = 0; j < V; j++) {
                if (isConnected[i][j] == 1 && i != j) {
                    // skip self-loops (i == j)
                    neighbours.add(j);
                }
            }
            adjList.add(neighbours);
        }

        int cnt = 0;
        boolean[] vis = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                cnt++;
                System.out.println("Province no: " + cnt);
                dfs(i, vis, adjList);
            }
        }
        return cnt;
    }

    private void dfs(int node, boolean[] vis, List<List<Integer>> adjList) {
        System.out.println("Visited Node: " + node);
        vis[node] = true;

        // Explore every neighbor of current node
        for (int cur : adjList.get(node)) {
            if (!vis[cur]) {
                dfs(cur, vis, adjList);
            }
        }
    }

    public int findCircleNumMat(int[][] isConnected) {
        int V = isConnected.length;
        boolean[] vis = new boolean[V];
        int cnt = 0;

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                cnt++;
                dfs2(i, isConnected, vis);
            }
        }

        return cnt;
    }

    private void dfs2(int node, int[][] adjMat, boolean[] vis) {
        vis[node] = true;

        // To find the neighbors of the current 'node', we scan its corresponding row in the adjacency matrix.
        // We check every other city 'j' to see if there is a connection.
        for (int j = 0; j < adjMat.length; j++) {
            // This is the crucial check:
            // 1. `adjMat[node][j] == 1`: Is there a direct road/edge from our current 'node' to city/node 'j'?
            // 2. `!vis[j]`: And have we NOT visited city 'j' before?
            if (adjMat[node][j] == 1 && !vis[j]) {
                dfs2(j, adjMat, vis);
            }
        }
    }
}