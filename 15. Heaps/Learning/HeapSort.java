public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 10, 2, 7};
        int n = arr.length;

        HeapSort sol = new HeapSort();
        sol.heapSort(arr, n);

        // Sorted Array output
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /*
     * Approach: In-place Sorting using Max-Heap
     * Pattern: Comparison-based Sorting
     * Time Complexity: O(N log N) - O(N) to build heap + O(N log N) for extractions.
     * Space Complexity: O(log N) - Due to recursive heapifyDown stack.
     */
    private void heapSort(int[] arr, int n) {
        // Step 1: Transform the raw array into a Max-Heap.
        heapfiy(arr, n);

        int end = n - 1;


        // Step 2: Extract elements one by one from the heap.
        while (end >= 0) {
            /* * Move current root (largest element) to the end of the array.
             * The sorted portion of the array grows from right to left.
             */
            swap(arr, 0, end);

            /* * Restore Max-Heap property for the remaining unsorted portion.
             * The size of the heap effectively shrinks with each iteration.
             */
            heapifyDown(arr, 0, end);
            end--;
        }
    }

    /**
     * Build-Max-Heap: Converts an arbitrary array into a Max-Heap in O(N).
     */
    private void heapfiy(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapifyDown(arr, i, n);
        }
    }

    /**
     * Sift-Down: Standard Max-Heapify to push a small element down the tree.
     */
    private void heapifyDown(int[] arr, int i, int n) {
        int largestInd = i;
        int leftInd = 2 * i + 1;
        int rightInd = 2 * i + 2;

        // Identify the largest value among parent and two children.
        if (leftInd < n && arr[leftInd] > arr[largestInd]) {
            largestInd = leftInd;
        }

        if (rightInd < n && arr[rightInd] > arr[largestInd]) {
            largestInd = rightInd;
        }

        // If the parent is not the largest, swap and recurse down.
        if (largestInd != i) {
            swap(arr, i, largestInd);
            heapifyDown(arr, largestInd, n);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}