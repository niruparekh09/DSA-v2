import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopoSortKahn {
    public static void main(String[] args) {
        int V = 6;

        // Build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // adj=[ [], [], [3], [1], [0,1], [0,2] ]
        adj.get(2).add(3);
        adj.get(3).add(1);
        adj.get(4).add(0);
        adj.get(4).add(1);
        adj.get(5).add(0);
        adj.get(5).add(2);

        TopoSortKahn solver = new TopoSortKahn();
        int[] result = solver.topoSort(V, adj);

        // Print result
        System.out.print("Topological Sort: ");
        for (int x : result) {
            System.out.print(x + " ");
        }
    }

    /*
     * Approach: Kahn's Algorithm (BFS)
     * Pattern: Topological Sort / Directed Acyclic Graph (DAG)
     * Time Complexity: O(V + E) - Visits every node and edge exactly once.
     * Space Complexity: O(V) - Stores In-Degree array and Queue.
     */
    public int[] topoSort(int V, List<List<Integer>> adj) {
        // Step 1: Find the inDegree of all the nodes
        // Step 2: Add all the nodes into Queue with iD=0
        // Step 3:
        // 3.1: Do BFS -> remove front node and add to ans.
        // 3.2: Traverse its neighbors and mimic, removing the frontNode from Graph.
        // I.E. reduce the inDegree, if inDegree (no incoming connections) of a node = 0
        // then add to Queue and repeat the process.

        // Step 1: Calculate In-Degrees (Count of incoming edges/dependencies)
        int[] inD = new int[V];

        for(int i=0;i<V;i++){
            // Finding neigh of cur node and increasing inDegree
            for(int neigh : adj.get(i)) inD[neigh]++;
        }

        Queue<Integer> q = new LinkedList<>();

        // Step 2: Initialize Queue with independent nodes (In-Degree 0)
        for(int i=0;i<V;i++){
            if(inD[i]==0) q.add(i);
        }

        // Step 3: BFS Processing
        int [] topo = new int[V];
        int idx =0 ;

        // Step 3.1
        while(!q.isEmpty()){
            int frontNode = q.remove();

            // Add to result (Topological Order)
            topo[idx++] = frontNode;

            // Step 3.2: "Remove" edges pointing from frontNode to its neighbors
            for(int neigh : adj.get(frontNode)){
                inD[neigh]--;

                // If neighbor becomes independent (0 dependencies), add to queue
                if(inD[neigh]==0) q.add(neigh);
            }
        }

        return topo;
    }
}