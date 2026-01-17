import java.util.*;

public class CourseScheduleII {

    public static void main(String[] args) {

        CourseScheduleII solver = new CourseScheduleII();

        // Example Input
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 0}, {3, 1}, {3, 2}};

        int[] courseOrder = solver.findOrderBFS(numCourses, prerequisites);

        System.out.println("Output course order: " + Arrays.toString(courseOrder));
        // Expected output: one possible order is [0, 1, 2, 3] or [0, 2, 1, 3]
    }

    /*
     * Approach: Kahn's Algorithm (Topological Sort)
     * Pattern: Topological Sort / DAG
     * Time Complexity: O(V + E) - Process all vertices and edges.
     * Space Complexity: O(V + E) - Adjacency List + Queue + Arrays.
     */
    public int[] findOrderBFS(int N, int[][] prerequisites) {

        // Step 1: Build Graph
        List<List<Integer>> adjList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        // Key Logic: [v, u] means u is a prerequisite for v.
        // Edge direction: u -> v (finish u first, then v).
        for (int[] p : prerequisites) {
            int u = p[1]; // Prerequisite
            int v = p[0]; // Dependent Course
            adjList.get(u).add(v);
        }

        // Step 2: Calculate In-Degrees
        int[] inDegree = new int[N];
        for (int i = 0; i < N; i++) {
            for (int it : adjList.get(i)) inDegree[it]++;
        }

        Queue<Integer> q = new LinkedList<>();

        // Step 3: Initialize Queue with independent nodes (In-degree 0)
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        int[] topo = new int[N];
        int index = 0;

        // Step 4: Process Queue (BFS)
        while (!q.isEmpty()) {
            Integer frontNode = q.remove();

            // Add course to the result order
            topo[index++] = frontNode;

            // Traverse dependent courses
            for (int it : adjList.get(frontNode)) {
                // Resolve dependency
                inDegree[it]--;

                // If course becomes available, add to queue
                if (inDegree[it] == 0) q.add(it);
            }
        }

        // Cycle Detection: If index != N, it means a cycle exists and not all courses can be taken.
        // Return empty array in that case.
        return index == N ? topo : new int[]{};
    }

    /*
     * Approach: Topological Sort via DFS (3-State Coloring)
     * Pattern: Graph Traversal / Cycle Detection
     * Time Complexity: O(V + E) - Visits every node and edge once.
     * Space Complexity: O(V) - Stack, Visited Array, Recursion.
     */
    public int[] findOrderDFS(int V, int[][] prerequisites) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build Graph: u -> v means u must come before v.
        // Input [v, u] means u is prereq for v.
        for (int i = 0; i < prerequisites.length; i++) {
            int u = prerequisites[i][1];
            int v = prerequisites[i][0];
            adj.get(u).add(v);
        }

        // Visited Array (0: Unvisited, 1: Visiting, 2: Processed)
        int[] vis = new int[V];
        Stack<Integer> st = new Stack<>();

        // Iterate over all components
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                // If cycle detected, no valid ordering exists. Return empty array.
                if (hasCycle(i, st, adj, vis)) return new int[]{};
            }
        }

        // Convert Stack (Reverse Post-Order) to Array
        int[] ans = new int[V];
        for (int i = 0; i < V; i++) {
            ans[i] = st.pop();
        }

        return ans;
    }

    /*
     * Helper: Combines Cycle Detection AND Topological Sorting
     * Returns true if a cycle is found.
     */
    private boolean hasCycle(int curNode, Stack<Integer> st, List<List<Integer>> adj, int[] vis) {
        // State 1: Node is currently in the recursion stack (Back Edge) -> Cycle!
        if (vis[curNode] == 1) {
            return true;
        }

        // State 2: Node already fully processed (Safe) -> No cycle here.
        if (vis[curNode] == 2) {
            return false;
        }

        // Mark as Visiting
        vis[curNode] = 1;

        // Traverse neighbors
        for (int neigh : adj.get(curNode)) {
            if (hasCycle(neigh, st, adj, vis)) return true;
        }

        // Mark as Processed (2)
        vis[curNode] = 2;

        // Key Logic: Push to stack AFTER all children are processed.
        // This builds the Topological Sort order.
        st.add(curNode);
        return false;
    }
}
