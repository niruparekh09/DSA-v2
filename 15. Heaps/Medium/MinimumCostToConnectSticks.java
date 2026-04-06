import java.util.List;
import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {
    /*
     * Approach: Greedy Algorithm using Min-Heap
     * Pattern: Huffman Coding / Optimal Merging
     * Time Complexity: O(N * log N) - N-1 merges, each merge takes log N to poll/add.
     * Space Complexity: O(N) - To store all sticks in the Priority Queue.
     */
    public int connectSticks(List<Integer> sticks) {
        // No cost if 0 or 1 stick, as no connections can be made.
        if (sticks.size() <= 1) return 0;

        // Min-heap to always pick the two smallest sticks available (Greedy Choice).
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(sticks);

        int totalCost = 0;


        // Continue merging until only one stick remains in the heap.
        while (pq.size() > 1) {
            // Extract the two smallest sticks.
            int first = pq.poll();
            int second = pq.poll();

            // The cost of this connection is the sum of the two sticks.
            int cost = first + second;

            // Accumulate the current connection cost into the total result.
            totalCost += cost;

            // Push the newly formed larger stick back into the heap.
            pq.add(cost);
        }

        return totalCost;
    }
}