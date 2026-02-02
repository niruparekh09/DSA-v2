import java.util.Arrays;

public class DisjointSet {

    private final int[] rank;    // Tracks tree height (used in Union by Rank)
    private final int[] size;    // Tracks component size (used in Union by Size)
    private final int[] parent;  // Stores parent of each node

    /*
     * Approach: Disjoint Set (Union-Find Data Structure)
     * Pattern: Dynamic Connectivity / Connected Components
     * Time Complexity:
     *   - findUPar(): O(α(N)) (Inverse Ackermann, nearly constant)
     *   - unionByRank()/unionBySize(): O(α(N))
     * Space Complexity: O(N) - Parent, Rank, and Size arrays
     */
    // Constructor: Initialize DSU
    public DisjointSet(int n) {

        rank = new int[n];
        size = new int[n];
        parent = new int[n];

        // Initially, every node is its own component of size 1
        Arrays.fill(size, 1);

        // Every node is its own parent (Self root)
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /*
     * Find Ultimate Parent (with Path Compression)
     * Compresses path by directly attaching node to root.
     */
    public int findUPar(int node) {

        // Base Case: If node is its own parent, it's the root
        if (node == parent[node]) return node;

        // Recursively find root and apply Path Compression
        return parent[node] = findUPar(parent[node]);
    }

    /*
     * Check if two nodes belong to the same component
     */
    public boolean find(int u, int v) {

        // If ultimate parents are same → same set
        return (findUPar(u) == findUPar(v));
    }

    /*
     * Union by Rank
     * Attach shorter tree under taller tree
     * Keeps tree height minimal
     */
    public void unionByRank(int u, int v) {

        // Step 1: Find ultimate parents
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);

        // Step 2: If already in same component, no need to merge
        if (ulp_u == ulp_v) return;

        // Step 3: Attach smaller height tree under larger height tree
        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v;
        } else if (rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u;
        }
        // If both have same height, choose one as root and increase rank
        else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }

    /*
     * Union by Size
     * Attach smaller component under larger component
     * Keeps tree shallow and balanced
     */
    public void unionBySize(int u, int v) {

        // Step 1: Find ultimate parents
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);

        // Step 2: If already connected, do nothing
        if (ulp_u == ulp_v) return;

        // Step 3: Attach smaller component to larger one
        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }

    public static void main(String[] args) {
        // Disjoint Data structure
        DisjointSet ds = new DisjointSet(7);
        ds.unionByRank(1, 2); // Adding edge between 1 and 2
        ds.unionByRank(2, 3); // Adding edge between 2 and 3
        ds.unionByRank(4, 5); // Adding edge between 4 and 5
        ds.unionByRank(6, 7); // Adding edge between 6 and 7
        ds.unionByRank(5, 6); // Adding edge between 5 and 6

        /* Checking if node 3 and node 7
        are in the same component */
        if (ds.find(3, 7))
            System.out.println("They belong to the same components.");
        else
            System.out.println("They do not belong to the same components.");

        ds.unionByRank(3, 7); // Adding edge between 3 and 7

        /* Checking again if node 3 and node 7
        are in the same component */
        if (ds.find(3, 7))
            System.out.println("They belong to the same components.");
        else
            System.out.println("They do not belong to the same components.");
    }
}