import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseScheduleI {

    public static void main(String[] args) {

        CourseScheduleI solver = new CourseScheduleI();

        // Example 1
        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};

        boolean result1 = solver.canFinishBFS(numCourses1, prerequisites1);
        System.out.println("Example 1 Output: " + result1);
        // Expected: true

        // Example 2
        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};

        boolean result2 = solver.canFinishBFS(numCourses2, prerequisites2);
        System.out.println("Example 2 Output: " + result2);
        // Expected: false
    }

    /*
     * Approach: Kahn's Algorithm (BFS)
     * Pattern: Topological Sort / Cycle Detection
     * Time Complexity: O(V + E) - Building the graph and traversing nodes/edges.
     * Space Complexity: O(V + E) - Adjacency list, in-degree array, and queue.
     */
    // Kahn's algorithm is used because in-degree reduction cleanly detects cycles:
    // if a cycle exists, no node in it ever reaches in-degree 0, so we process < N nodes.
    public boolean canFinishBFS(int N, int[][] prerequisites) {

        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        // Step 1: Build Graph (Convert Edge List to Adjacency List)
        // Edge u -> v means 'u' is a prerequisite for 'v' (or vice versa depending on problem statement,
        // here [0,1] usually means 1 is prereq for 0, so 1 -> 0.
        // The logic below implies u points to v. Direction consistency matters for Topo Sort.
        for (int[] p : prerequisites) {
            int u = p[0];
            int v = p[1];
            adjList.get(u).add(v);
        }

        // Step 2: Calculate In-Degrees (Number of prerequisites)
        int[] inDegree = new int[N];
        for (int i = 0; i < N; i++) {
            for (int it : adjList.get(i)) inDegree[it]++;
        }

        Queue<Integer> q = new LinkedList<>();

        // Step 3: Initialize Queue with courses having NO prerequisites (In-degree 0)
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        int cnt = 0;

        // Step 4: Process Queue (resolve dependencies)
        while (!q.isEmpty()) {
            Integer frontNode = q.remove();

            cnt++; // Successfully finished one course

            // Traverse dependent courses
            for (int it : adjList.get(frontNode)) {
                // "Remove" the dependency
                inDegree[it]--;

                // If all prerequisites for 'it' are met, add to queue
                if (inDegree[it] == 0) q.add(it);
            }
        }

        // Key Logic: If we finished exactly N courses, it's a valid schedule (DAG).
        // If cnt < N, there was a cycle (deadlock), so some courses could never be started.
        return cnt == N;
    }

    /*
     * Approach: Cycle Detection via 3-Color DFS
     * Pattern: Graph Traversal / Backtracking
     * Time Complexity: O(V + E) - Visits each node and edge exactly once.
     * Space Complexity: O(V) - Adjacency list, visited array, and recursion stack.
     */
    public boolean canFinishDFS(int V, int[][] prerequisites) {
        // Step 1: Build Graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Add Directed Edges: u -> v (u is prerequisite for v, or vice-versa)
        // Consistency with cycle detection logic is key. A cycle means deadlock.
        for (int i = 0; i < prerequisites.length; i++) {
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];
            adj.get(u).add(v);
        }

        // Visited Array (0: Unvisited, 1: Visiting, 2: Visited)
        int[] vis = new int[V];

        // Step 2: Iterate all components
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                // If cycle is found, we CANNOT finish all courses.
                if (hasCycle(i, adj, vis)) return false;
            }
        }

        return true;
    }

    /*
     * Helper: Detects cycle using state tracking
     * State 0: Unvisited
     * State 1: Currently in recursion stack (Back Edge target) -> Cycle!
     * State 2: Fully processed (Safe)
     */
    private boolean hasCycle(int curNode, List<List<Integer>> adj, int[] vis) {
        // Case 1: Back Edge found (Node is currently being visited higher up the stack)
        if (vis[curNode] == 1) {
            return true;
        }

        // Case 2: Cross/Forward Edge (Node already processed successfully)
        if (vis[curNode] == 2) {
            return false;
        }

        // Mark as Visiting (1)
        vis[curNode] = 1;

        // Traverse Neighbors
        for (int neigh : adj.get(curNode)) {
            if (hasCycle(neigh, adj, vis)) return true;
        }

        // Mark as Visited/Safe (2) - Backtrack step
        vis[curNode] = 2;
        return false;
    }
}