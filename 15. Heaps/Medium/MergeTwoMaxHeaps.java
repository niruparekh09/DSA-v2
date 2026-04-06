public class MergeTwoMaxHeaps {
    /*
     * Approach: Concatenation and Re-heapify (Build-Max-Heap)
     * Pattern: Merging two Heaps using Array representation
     * Time Complexity: O(N + M) - Linear time to copy and build heap.
     * Space Complexity: O(N + M) - For the result array.
     */
    public int[] mergeHeaps(int[] a, int[] b, int n, int m) {
        int[] ans = new int[n + m];

        // Step 1: Concatenate both heap arrays into a single array.
        for (int i = 0; i < a.length; i++) {
            ans[i] = a[i];
        }

        for (int i = 0; i < b.length; i++) {
            ans[a.length + i] = b[i];
        }

        int size = n + m;

        /* * Step 2: Transform the concatenated array into a Max-Heap.
         * Start from the last non-leaf node (size/2 - 1) and move upwards.
         * Note: i = size * 2 - 1 in original code is safe but size/2 - 1 is the standard start.
         */
        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(ans, i, size);
        }

        return ans;
    }

    /**
     * Sift-Down: Standard Max-Heapify to restore heap property.
     */
    private void heapify(int[] arr, int ind, int n) {
        int largestInd = ind;
        int leftInd = 2 * ind + 1;
        int rightInd = 2 * ind + 2;

        // Compare parent with left child
        if (leftInd < n && arr[leftInd] > arr[largestInd]) {
            largestInd = leftInd;
        }

        // Compare current largest with right child
        if (rightInd < n && arr[rightInd] > arr[largestInd]) {
            largestInd = rightInd;
        }

        // If the max-heap property is violated, swap and recurse down
        if (largestInd != ind) {
            int temp = arr[ind];
            arr[ind] = arr[largestInd];
            arr[largestInd] = temp;

            heapify(arr, largestInd, n);
        }
    }
}