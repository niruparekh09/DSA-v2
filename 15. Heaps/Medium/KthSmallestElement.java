import java.util.Collections;
import java.util.PriorityQueue;

public class KthSmallestElement {
    /*
     * Approach: Max-Heap of size K
     * Pattern: Top K / K-th Smallest Element
     * Time Complexity: O(N * log K) - N elements processed, each heap operation takes log K.
     * Space Complexity: O(K) - To store the K smallest elements found so far.
     */
    public int kthSmallestElement(int[] nums, int k) {
        int n = nums.length;

        // Validation for empty array or k out of bounds
        if (n == 0 || n < k) {
            return -1;
        }

        // Max-Heap stores the smallest K elements seen. 
        // The largest of these (kth smallest) stays at the top.
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Step 1: Insert first K elements into the Max-Heap
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }

        // Step 2: For remaining elements, if an element is smaller than heap top,
        // it is a better candidate for the "K smallest" group.
        for (int i = k; i < n; i++) {
            if (maxHeap.peek() > nums[i]) {
                maxHeap.poll(); // Remove current kth smallest
                maxHeap.add(nums[i]); // Add new smaller element
            }
        }

        // The top of the Max-Heap is the largest of the K smallest elements.
        return maxHeap.peek();
    }
}