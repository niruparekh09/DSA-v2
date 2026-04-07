import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedArrays {
    /*
     * Approach: Min-Heap (Priority Queue) with Node tracking
     * Pattern: K-way Merge (Arrays)
     * Time Complexity: O(N * K * log K) - Each of the N*K elements is processed once.
     * Space Complexity: O(K) - To maintain the heap of size K (one element from each array).
     */
    public ArrayList<Integer> mergeArrays(int[][] mat) {
        int arrLen = mat[0].length;

        // Min-Heap stores Nodes containing value and coordinates {data, row, col}.
        // This allows us to know exactly which array to pull the next element from.
        PriorityQueue<Node> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> a.data)
        );

        ArrayList<Integer> ans = new ArrayList<>();

        // Step 1: Initialize heap with the first element of every row.
        for (int i = 0; i < mat.length; i++) {
            minHeap.add(new Node(mat[i][0], i, 0));
        }

        // Step 2: Extract the minimum and replace it with the next element from the same row.
        while (!minHeap.isEmpty()) {
            Node min = minHeap.poll();
            int curI = min.i; // Row index
            int curJ = min.j; // Column index

            ans.add(min.data);

            // If there is another element in the current row, add it to the heap.
            if (curJ + 1 < arrLen) {
                Node newNode = new Node(mat[curI][curJ + 1], curI, curJ + 1);
                minHeap.add(newNode);
            }
        }

        return ans;
    }

    /**
     * Helper class to track the position of an element in the 2D matrix.
     */
    private class Node {
        int data;
        int i; // row index
        int j; // column index

        public Node(int data, int i, int j) {
            this.data = data;
            this.i = i;
            this.j = j;
        }
    }
}