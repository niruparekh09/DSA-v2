import java.util.ArrayList;
import java.util.List;

public class ConvertAdjMatToAdjList {
    public static void main(String[] args) {
        ConvertAdjMatToAdjList c = new ConvertAdjMatToAdjList();

        System.out.println(c.convertToAdjList((new int[][]{
                {1, 0, 0, 1},
                {0, 1, 1, 0},
                {0, 1, 1, 0},
                {1, 0, 0, 1}
        })));
    }

    public List<List<Integer>> convertToAdjList(int[][] adjMat) {
        // Converting Adjacency Matrix to Adjacency List
        List<List<Integer>> adjList = new ArrayList<>();
        int V = adjMat.length;

        /*
        For a matrix adjMat[i][j]:
        i = current node (row)
        j = potential neighbor (column)
        adjMat[i][j] == 1 means there is an edge from i to j
        */
        for (int i = 0; i < V; i++) {
            List<Integer> neighbours = new ArrayList<>();
            for (int j = 0; j < V; j++) {
                if (adjMat[i][j] == 1 && i != j) {
                    // skip self-loops (i == j)
                    neighbours.add(j);
                }
            }
            adjList.add(neighbours);
        }

        return adjList;
    }
}
