public class MinToMaxHeap {

    /*
     * Approach: Bottom-Up Heap Construction (Build-Max-Heap)
     * Pattern: Converting a Min-Heap (or any array) into a Max-Heap
     * Time Complexity: O(N) - While it looks like O(N log N), the mathematical
     * sum of heights for all nodes converges to linear time.
     * Space Complexity: O(log N) - Recursive stack depth for maxHeapifyDown.
     */
    public int[] minToMaxHeap(int[] nums) {
        int n = nums.length;

        /* * Start from the last non-leaf node (n/2 - 1) and work upwards.
         * This ensures that every sub-tree is a valid Max-Heap before we
         * process its parent.
         */

        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapifyDown(nums, i);
        }

        return nums;
    }

    /**
     * Sift-Down / Sink-Down (Max-Heap version)
     * Restores the Max-Heap property by moving the parent down until
     * it is larger than both its children.
     */
    private void maxHeapifyDown(int[] nums, int i) {
        int n = nums.length;
        int largestInd = i;

        // Calculate child indices
        int leftInd = i * 2 + 1;
        int rightInd = i * 2 + 2;

        // If left child exists and is greater than the current largest
        if (leftInd < n && nums[leftInd] > nums[largestInd])
            largestInd = leftInd;

        // If right child exists and is even greater than the current largest (parent or left)
        if (rightInd < n && nums[rightInd] > nums[largestInd])
            largestInd = rightInd;

        // If the largest is one of the children, swap and continue sinking down
        if (largestInd != i) {
            // Swap parent with the largest child
            int temp = nums[largestInd];
            nums[largestInd] = nums[i];
            nums[i] = temp;

            // Recurse to fix the impacted sub-tree
            maxHeapifyDown(nums, largestInd);
        }
    }
}