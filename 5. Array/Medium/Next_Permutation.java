public class Next_Permutation {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 6, 5, 4};
        nextPermutation(arr);
    }

    /*
     * Approach: Single Pass (3-step Algorithm)
     * Pattern: Two Pointers / Array Manipulation
     * Time Complexity: O(N) - In worst case, we scan the array 3 times (find index, find swap, reverse).
     * Space Complexity: O(1) - Modifications are done in-place (ignoring recursion stack depth).
     */
    public static void nextPermutation(int[] arr) {
        int ind = -1;
        int n = arr.length;

        // Step 1: Find the "break point" (first element from right that is smaller than its neighbor).
        // Logic: We are looking for the first place where the sequence is NOT descending from right-to-left.
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                ind = i;
                break;
            }
        }

        // Edge Case: If no break point exists, the array is fully descending (e.g., 5, 4, 3).
        // The next permutation is the lowest possible order (ascending), so we simply reverse it.
        if (ind == -1) {
            reverse(arr, 0, n - 1);
            return;
        }

        // Step 2: Find the next largest number to swap with arr[ind].
        // Logic: We need the smallest number in the right suffix that is greater than arr[ind]
        // to ensure we get the *next* immediate permutation.
        for (int i = n - 1; i >= ind; i--) {
            if (arr[i] > arr[ind]) {
                swap(arr, i, ind);
                break;
            }
        }

        // Step 3: Reverse the right suffix (from ind + 1 to end).
        // Logic: The suffix is currently in descending order. Reversing it makes it ascending
        // (smallest possible suffix), completing the transformation.
        reverse(arr, ind + 1, n - 1);
    }

    public static void reverse(int[] arr, int s, int e) {
        if (s >= e) {
            return;
        }
        swap(arr, s, e);
        // Recursion here adds O(N) stack space; Iterative approach would be O(1) space.
        reverse(arr, s + 1, e - 1);
    }

    private static void swap(int[] arr, int s, int e) {
        int temp = arr[s];
        arr[s] = arr[e];
        arr[e] = temp;
    }
}