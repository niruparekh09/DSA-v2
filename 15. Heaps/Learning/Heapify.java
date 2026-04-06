public class Heapify {

    public static void main(String[] args) {
        // Example array representing a min-heap
        int[] nums = {1, 4, 5, 5, 7, 6};
        int ind = 5, val = 2;

        // Print input array
        System.out.print("Input array: ");
        for (int it : nums) {
            System.out.print(it + " ");
        }

        // Create an object of the Solution class
        Heapify sol = new Heapify();

        // Function call to heapify the array
        sol.heapify(nums, ind, val);

        // Print modified array
        System.out.print("\nModified array after heapifying: ");
        for (int it : nums) {
            System.out.print(it + " ");
        }
        System.out.println();
    }

    /*
     * Approach: Manual Element Update and Re-Heapify
     * Pattern: Binary Heap Maintenance (Min-Heap)
     * Time Complexity: O(log N) - Height of the tree determines the number of swaps.
     * Space Complexity: O(log N) - Recursive stack space.
     */
    public void heapify(int[] nums, int ind, int val) {
        // If new value is smaller than current, it might need to move UP towards the root.
        if (nums[ind] > val) {
            nums[ind] = val;
            heapifyUp(nums, ind);
        }
        // If new value is larger, it might need to move DOWN towards the leaves.
        else {
            nums[ind] = val;
            heapifyDown(nums, ind);
        }
    }

    /**
     * Sift-Up / Bubble-Up
     * Used when a node's value is decreased (in a Min-Heap).
     * Compares the node with its parent and swaps if the node is smaller.
     */
    public void heapifyUp(int[] arr, int ind) {
        int parent_ind = (ind - 1) / 2;

        // Base case: root reached or heap property satisfied (child >= parent)
        if (ind > 0 && arr[ind] < arr[parent_ind]) {
            // Swap child with parent
            int temp = arr[ind];
            arr[ind] = arr[parent_ind];
            arr[parent_ind] = temp;

            // Recurse upwards
            heapifyUp(arr, parent_ind);
        }
    }

    /**
     * Sift-Down / Sink-Down
     * Used when a node's value is increased (in a Min-Heap).
     * Compares the node with its children and swaps with the smallest child.
     */
    public void heapifyDown(int[] arr, int ind) {
        int n = arr.length;
        int smallest_Ind = ind;

        // Calculate child indices in a 0-indexed array representation
        int left_child = ind * 2 + 1;
        int right_child = ind * 2 + 2;

        // Check if left child exists and is smaller than the current smallest
        if (left_child < n && arr[smallest_Ind] > arr[left_child]) {
            smallest_Ind = left_child;
        }

        // Check if right child exists and is smaller than the current smallest (node or left)
        if (right_child < n && arr[smallest_Ind] > arr[right_child]) {
            smallest_Ind = right_child;
        }

        // If the smallest is one of the children, swap and continue sinking down
        if (smallest_Ind != ind) {
            // Swap current node with the smaller child
            int temp = arr[smallest_Ind];
            arr[smallest_Ind] = arr[ind];
            arr[ind] = temp;

            // Recurse downwards to ensure sub-tree heap property
            heapifyDown(arr, smallest_Ind);
        }
    }

    /*
     * Approach: Manual Element Update and Re-Heapify (Max-Heap)
     * Pattern: Binary Heap Maintenance (Max-Heap Property)
     * Time Complexity: O(log N) - Traversal depth is limited by the tree height.
     * Space Complexity: O(log N) - Recursive stack space.
     */
    public void heapifyMaxHeap(int[] nums, int ind, int val) {
        // If new value is larger, it may violate the parent's property and needs to move UP.
        if (nums[ind] < val) {
            nums[ind] = val;
            heapifyUpMax(nums, ind);
        }
        // If new value is smaller, it may be smaller than its children and needs to move DOWN.
        else {
            nums[ind] = val;
            heapifyDownMax(nums, ind);
        }
    }

    /**
     * Sift-Up / Bubble-Up (Max-Heap)
     * Restores property by swapping a node with its parent if the node is larger.
     */

    public void heapifyUpMax(int[] arr, int ind) {
        int parent_ind = (ind - 1) / 2;

        // Check if current node is larger than its parent
        if (ind > 0 && arr[ind] > arr[parent_ind]) {
            int temp = arr[ind];
            arr[ind] = arr[parent_ind];
            arr[parent_ind] = temp;

            // Recurse towards the root
            heapifyUpMax(arr, parent_ind);
        }
    }

    /**
     * Sift-Down / Sink-Down (Max-Heap)
     * Restores property by swapping a node with its largest child.
     */

    public void heapifyDownMax(int[] arr, int ind) {
        int n = arr.length;
        int largest_Ind = ind;

        int left_child = ind * 2 + 1;
        int right_child = ind * 2 + 2;

        // Compare parent with left child to find the largest
        if (left_child < n && arr[largest_Ind] < arr[left_child]) {
            largest_Ind = left_child;
        }

        // Compare current largest with right child
        if (right_child < n && arr[largest_Ind] < arr[right_child]) {
            largest_Ind = right_child;
        }

        // If a child is larger, swap and continue sinking to maintain Max-Heap property
        if (largest_Ind != ind) {
            int temp = arr[largest_Ind];
            arr[largest_Ind] = arr[ind];
            arr[ind] = temp;

            heapifyDownMax(arr, largest_Ind);
        }
    }
}