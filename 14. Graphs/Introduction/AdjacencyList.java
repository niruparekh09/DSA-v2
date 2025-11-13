import java.util.ArrayList;
import java.util.Scanner;

public class AdjacencyList {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // -----------------------------
        // Step 1: Take graph input
        // -----------------------------

        // Number of nodes (vertices)
        int n = sc.nextInt();

        // Number of edges
        int m = sc.nextInt();

        // -----------------------------
        // Step 2: Initialize adjacency list
        // -----------------------------
        // We create an ArrayList for each node (1-based indexing)
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>(n + 1);
        // We can also create an AL for storing weight
        ArrayList<ArrayList<Edge>> adjWithWeight = new ArrayList<>(n + 1);

        // Initialize empty lists for all nodes
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        // -----------------------------
        // Step 3: Fill the adjacency list
        // -----------------------------
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(); // starting vertex
            int v = sc.nextInt(); // ending vertex
            //* int w = sc.nextInt(); // Weight of edge

            // Add edge u -> v
            adj.get(u).add(v);
            // With Weight
            //* adjWithWeight.get(u).add(new Edge(v,w));

            // For undirected graph: also add v -> u
            // (Remove this line for a directed graph)
            adj.get(v).add(u);
            //* adjWithWeight.get(v).add(new Edge(u,w));
        }

        sc.close();

        // -----------------------------
        // Step 4: Print adjacency list
        // -----------------------------
        System.out.println("\nAdjacency List Representation:");
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " -> ");
            for (int neighbor : adj.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }

        // -----------------------------
        // Visualization Example for Unweighted graph
        // -----------------------------
        /*
        Example Input:
        5 6
        1 2
        1 3
        2 4
        3 4
        3 5
        4 5

        Nodes = 5, Edges = 6
        Undirected Graph → each edge appears twice

        Adjacency List Representation:
        1 -> 2 3
        2 -> 1 4
        3 -> 1 4 5
        4 -> 2 3 5
        5 -> 3 4

        Interpretation:
        - Node 1 is connected to 2 and 3.
        - Node 3 is connected to 1, 4, and 5.
        - Node 5 is connected to 3 and 4.
        - Each undirected edge appears in both nodes’ lists.
        */

        // -----------------------------
        // Visualization Example for Weighted Graph
        // -----------------------------
        /*
        Example Input:
        5 6
        1 2 4
        1 3 2
        2 4 7
        3 4 1
        3 5 3
        4 5 5

        Nodes = 5, Edges = 6
        Undirected Weighted Graph

        -------------------------------
        Unweighted Adjacency List:
        1 -> 2 3
        2 -> 1 4
        3 -> 1 4 5
        4 -> 2 3 5
        5 -> 3 4

        -------------------------------
        Weighted Adjacency List:
        1 -> (2, w=4) (3, w=2)
        2 -> (1, w=4) (4, w=7)
        3 -> (1, w=2) (4, w=1) (5, w=3)
        4 -> (2, w=7) (3, w=1) (5, w=5)
        5 -> (3, w=3) (4, w=5)

        Interpretation:
        - Node 1 connects to 2 (weight 4) and 3 (weight 2).
        - Node 3 connects to 4 (1) and 5 (3).
        - Edge weights are symmetric for undirected graphs.
        - For directed graphs: only store u → v (remove reverse additions).
        */
    }

    static class Edge {
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
