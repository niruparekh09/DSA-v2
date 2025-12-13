import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// https://leetcode.com/problems/shortest-path-visiting-all-nodes/description/
public class ShortestPathVisitingAllNodes {
    public int shortestPathLength(int[][] graph) {
        int N = graph.length;
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            List<Integer> list = new ArrayList<>();
            for (int neigh : graph[i]) {
                list.add(neigh);
            }
            adj.add(list);
        }

        int[] ans = new int[1];
        ans[0] = Integer.MAX_VALUE;
        int[] vis = new int[N];

        // Do DFS for every elem and find out the shortest path every every node is vis

        for(int i=0; i<N; i++){
            dfs(i,adj,ans,vis);
            Arrays.fill(vis,0); // reset the vis array
        }

        return ans[0];
    }

    private void dfs(int curNode, List<List<Integer>> adj, int[] ans, int[] vis) {

    }
}