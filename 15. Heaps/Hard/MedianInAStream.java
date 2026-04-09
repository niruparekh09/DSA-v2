import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MedianInAStream {
    /*
     * Approach: Two Heaps (Max-Heap and Min-Heap)
     * Pattern: Median Tracking using Signum for balancing
     * Time Complexity: O(N log N) - Each of the N elements involves log N heap operations.
     * Space Complexity: O(N) - To store elements in heaps and the result list.
     */
    public static int[] findMedian(int[] arr, int n) {
        // minHeap stores the larger half; maxHeap stores the smaller half.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        int[] median = new int[1]; // Using array to pass by reference
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int elem = arr[i];
            callMedian(elem, minHeap, maxHeap, median);
            ans.add(median[0]);
        }

        return ans.stream().mapToInt(i -> i).toArray();
    }

    /**
     * core logic: Balancing heaps based on current size differences.
     * Signum cases: 0 (Equal sizes), 1 (maxHeap larger), -1 (minHeap larger).
     */
    private static void callMedian(int elem, PriorityQueue<Integer> minHeap, PriorityQueue<Integer> maxHeap, int[] median) {
        int currMedian = median[0];

        switch (signum(maxHeap.size(), minHeap.size())) {
            // Case 0: Heaps are balanced. Median is whichever heap receives the new element.
            case 0: {
                if (currMedian < elem) {
                    minHeap.add(elem);
                    currMedian = minHeap.peek();
                } else {
                    maxHeap.add(elem);
                    currMedian = maxHeap.peek();
                }
                break;
            }
            // Case 1: maxHeap has one more element than minHeap.
            case 1: {
                if (currMedian < elem) {
                    minHeap.add(elem);
                } else {
                    // Rebalance: move top of maxHeap to minHeap to make space for new element.
                    minHeap.add(maxHeap.poll());
                    maxHeap.add(elem);
                }
                // Both heaps now equal size; median is the average of tops.
                currMedian = (minHeap.peek() + maxHeap.peek()) / 2;
                break;
            }
            // Case -1: minHeap has one more element than maxHeap.
            case -1: {
                if (currMedian < elem) {
                    // Rebalance: move top of minHeap to maxHeap.
                    maxHeap.add((minHeap.poll()));
                    minHeap.add(elem);
                } else {
                    maxHeap.add(elem);
                }
                // Both heaps now equal size; median is the average of tops.
                currMedian = (minHeap.peek() + maxHeap.peek()) / 2;
                break;
            }
        }

        median[0] = currMedian;
    }

    /**
     * Helper to determine heap size relationship.
     */
    private static int signum(int a, int b) {
        return Integer.compare(a, b);
    }
}