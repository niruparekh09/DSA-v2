public class ArrayToHeap {
    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 10, 2, 7};
        int n = arr.length;

        ArrayToHeap sol = new ArrayToHeap();
        sol.buildMaxHeap(arr, n);

        for (int num : arr) {
            System.out.print(num + " ");
        }

        sol.buildMinHeap(arr, n);

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    /*
     * Approach: Bottom-Up Heap Construction (Floyd's Algorithm)
     * Pattern: In-place Array to Heap Conversion
     * Time Complexity: O(N) - Mathematical optimization (sum of heights).
     * Space Complexity: O(log N) - Recursive stack depth.
     */
    public int[] buildMaxHeap(int[] arr, int n) {
        // Start from the last non-leaf node (n/2 - 1) up to the root.
        for (int i = n / 2 - 1; i >= 0; i--) {
            maxHeapify(arr, i, n);
        }
        return arr;
    }

    /**
     * Sift-Down for Max-Heap: Ensures parent is greater than children.
     */
    public void maxHeapify(int[] arr, int ind, int n) {
        int largestInd = ind;
        int leftInd = 2 * ind + 1;
        int rightInd = 2 * ind + 2;

        // Compare parent with left and right children to find the maximum
        if (leftInd < n && arr[leftInd] > arr[largestInd]) {
            largestInd = leftInd;
        }

        if (rightInd < n && arr[rightInd] > arr[largestInd]) {
            largestInd = rightInd;
        }

        // If a child is larger, swap and continue sinking down
        if (largestInd != ind) {
            swap(arr, ind, largestInd);
            maxHeapify(arr, largestInd, n);
        }
    }

    /*
     * Pattern: Min-Heap Property Maintenance
     * Logic: Identical to buildMaxHeap but uses min-comparison.
     */
    public int[] buildMinHeap(int[] arr, int n) {
        for (int i = n / 2 - 1; i >= 0; i--) {
            minHeapify(arr, i, n);
        }
        return arr;
    }

    /**
     * Sift-Down for Min-Heap: Ensures parent is smaller than children.
     */
    public void minHeapify(int[] arr, int ind, int n) {
        int smallestInd = ind;
        int leftInd = 2 * ind + 1;
        int rightInd = 2 * ind + 2;

        // Identify the smallest value among parent and children
        if (leftInd < n && arr[leftInd] < arr[smallestInd]) {
            smallestInd = leftInd;
        }

        if (rightInd < n && arr[rightInd] < arr[smallestInd]) {
            smallestInd = rightInd;
        }

        // Swap if property is violated and recurse
        if (smallestInd != ind) {
            swap(arr, ind, smallestInd);
            minHeapify(arr, smallestInd, n);
        }
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}