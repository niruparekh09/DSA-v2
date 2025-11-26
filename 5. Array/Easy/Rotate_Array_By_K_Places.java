import java.util.Arrays;

public class Rotate_Array_By_K_Places {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7};
        //leftRotateBrute(arr, 3);
        rightRotate(arr, 3);
        System.out.println(Arrays.toString(arr));
    }

    // Pattern: Reversal Algorithm
    // Time: O(N) - Three pass reversal results in linear time.
    // Space: O(N) - Due to recursion stack in 'reverse'. (Would be O(1) if 'reverse' was iterative).
    private static void leftRotate(int[] arr, int k) {
        // Edge case: No rotation needed for single element or full cycle
        if (arr.length == 1 || arr.length == k)
            return;

        // Optimization: Handle cases where k > arr.length to avoid unnecessary cycles
        if (arr.length < k)
            k = k % arr.length;

        // Logic: Reverse first k elements, reverse remaining elements, then reverse the whole array.
        // Concept: (A^T B^T)^T = BA
        reverse(arr, 0, k - 1);
        reverse(arr, k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    // Pattern: Reversal Algorithm
    // Time: O(N)
    // Space: O(N) (Recursive stack)
    private static void rightRotate(int[] arr, int k) {
        if (arr.length == 1 || arr.length == k)
            return;

        if (arr.length < k)
            k = k % arr.length;

        // Logic: Similar to left rotate, but the split point is based on 'k' from the end.
        // Partition: [0...n-k-1] and [n-k...end]
        reverse(arr, 0, arr.length - k - 1);
        reverse(arr, arr.length - k, arr.length - 1);
        reverse(arr, 0, arr.length - 1);
    }

    // Helper: Recursive Two Pointers Approach
    public static void reverse(int[] arr, int s, int e) {
        // Base case: Pointers cross
        if (s > e) {
            return;
        }
        swap(arr, s, e);
        // Recursive step: Shrink window from both sides
        reverse(arr, s + 1, e - 1);
    }

    public static void swap(int[] arr, int s, int e) {
        int temp = arr[s];
        arr[s] = arr[e];
        arr[e] = temp;
    }

    // Pattern: Auxiliary Array (Brute Force)
    // Time: O(N) - Iterates through array.
    // Space: O(K) - Requires temporary storage for k elements.
    private static void leftRotateBrute(int[] arr, int k) {
        int n = arr.length;
        int[] temp = new int[k];

        // Step 1: Save the first k elements to a temp array to prevent overwriting
        System.arraycopy(arr, 0, temp, 0, k);

        // Step 2: Shift the remaining (n-k) elements to the left
        for (int i = k; i < n; i++) {
            arr[i - k] = arr[i];
        }

        // Step 3: Place the saved elements at the back of the array
        if (n - (n - k) >= 0) System.arraycopy(temp, n - k - (n - k), arr, n - k, n - (n - k));
    }
}