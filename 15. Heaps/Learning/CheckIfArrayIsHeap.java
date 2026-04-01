public class CheckIfArrayIsHeap {

    /*
     * Approach: Level-Order Property Verification
     * Pattern: Heap Property Validation (Min-Heap)
     * Time Complexity: O(N) - We inspect each internal node once.
     * Space Complexity: O(1) - Iterative approach with no extra space.
     */
    public boolean isHeap(int[] nums) {
        int n = nums.length;

        /* * We only need to check internal nodes.
         * Leaf nodes (indices from n/2 to n-1) don't have children
         * to violate the heap property.
         */

        for (int i = n / 2 - 1; i >= 0; i--) {

            // Standard mapping for 0-indexed binary heaps
            int leftChild = 2 * i + 1;
            int rightChild = 2 * i + 2;

            // Min-Heap Property: Parent must be smaller than or equal to Left Child
            if (leftChild < n && nums[leftChild] < nums[i]) {
                return false;
            }

            // Min-Heap Property: Parent must be smaller than or equal to Right Child
            if (rightChild < n && nums[rightChild] < nums[i]) {
                return false;
            }
        }

        // If no violations are found after checking all internal nodes
        return true;
    }
}