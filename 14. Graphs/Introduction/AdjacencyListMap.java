import java.util.*;

public class AdjacencyListMap {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Graph g = new Graph();

        System.out.println("Enter number of nodes");
        int n = sc.nextInt();

        System.out.println("Enter number of edges");
        int m = sc.nextInt(); // Number of connections

        System.out.println("Is Graph Directed (true/false)");
        boolean isDirected = sc.nextBoolean();

        System.out.println("Enter the nodes that connected to each other (u<->v for undirected and u->v for directed)");
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            g.addEdge(u, v, isDirected);
        }

        System.out.println("Graph");
        g.printAdjList();
    }

    private static class Graph {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        void addEdge(int u, int v, boolean isDirected) {
            /*List<Integer> to = adj.getOrDefault(u, new ArrayList<>());
            to.add(v);
            adj.put(u, to);

            if(!isDirected){
                List<Integer> back = adj.getOrDefault(v, new ArrayList<>());
                back.add(u);
                adj.put(v,back);
            }*/

            adj.computeIfAbsent(u, k -> new ArrayList<>()).add(v);

            if (!isDirected) {
                adj.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
            }
        }

        void printAdjList() {
            for (Map.Entry<Integer, List<Integer>> entry : adj.entrySet()) {
                int node = entry.getKey();
                List<Integer> neighbors = entry.getValue();

                System.out.println(node + " -> " + neighbors);
            }
        }
    }
}
