import java.util.Scanner;

public class AdjacencyMatrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // -----------------------------
        // Step 1: Take graph input
        // -----------------------------

        System.out.println("Enter Number of Nodes");
        // Number of nodes (vertices)
        int n = sc.nextInt();

        System.out.println("Enter Number of Edges");
        // Number of edges
        int m = sc.nextInt();

        // -----------------------------
        // Step 2: Initialize adjacency matrix
        // -----------------------------
        // Matrix size: (n+1) x (n+1) for 1-based indexing
        int[][] adj = new int[n + 1][n + 1];

        // -----------------------------
        // Step 3: Fill the matrix
        // -----------------------------
        System.out.println("Add node values");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt(); // starting vertex of edge
            int v = sc.nextInt(); // ending vertex of edge
            //* int w = sc.nextInt(); // Weight of edge

            // Mark edge from u to v
            adj[u][v] = 1; // Or if we have a weight we can add `w` here instead of 1

            // For undirected graph: also mark v to u
            // (Remove this line for a directed graph)
            adj[v][u] = 1; // Or w
        }

        sc.close();

        // -----------------------------
        // Step 4: Print adjacency matrix
        // -----------------------------
        System.out.println("\nAdjacency Matrix Representation:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(adj[i][j] + " ");
            }
            System.out.println();
        }

        // -----------------------------
        // Visualization Example
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
        Undirected Graph → edges both ways

        Adjacency Matrix (1-based indexing):

             1  2  3  4  5
          -----------------
        1 |  0  1  1  0  0
        2 |  1  0  0  1  0
        3 |  1  0  0  1  1
        4 |  0  1  1  0  1
        5 |  0  0  1  1  0

        Interpretation:
        - adj[1][2] = 1 → edge between 1 and 2
        - adj[3][5] = 1 → edge between 3 and 5
        - adj[5][1] = 0 → no direct edge between 5 and 1
        */
    }
}
