import java.util.PriorityQueue;

public class KthLargetElement {
    /*
     * Approach: Min-Heap of size K
     * Pattern: Top K / K-th Largest Element
     * Time Complexity: O(N * log K) - Each of the N elements is processed once.
     * Space Complexity: O(K) - To maintain the heap of size K.
     */
    public int kthLargestElement(int[] nums, int k) {
        int n = nums.length;

        // Validation for array size relative to K
        if (n == 0 || n < k) {
            return -1;
        }

        // Min-Heap maintains the 'K' largest elements seen so far.
        // The smallest among these (the k-th largest) stays at the root.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Step 1: Initialize heap with the first K elements.
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        // Step 2: For remaining elements, if nums[i] is larger than the root,
        // it belongs in the "top K" set.
        for (int i = k; i < n; i++) {
            if (minHeap.peek() < nums[i]) {
                minHeap.poll(); // Remove the current k-th largest
                minHeap.add(nums[i]); // Insert the new larger candidate
            }
        }

        // The root of the Min-Heap is the k-th largest element of the entire array.
        return minHeap.peek();
    }
}