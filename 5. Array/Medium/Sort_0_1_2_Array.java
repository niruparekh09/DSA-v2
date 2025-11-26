import java.util.Arrays;

public class Sort_0_1_2_Array {
    public static void main(String[] args) {
        // sortOne(new int[]{0, 0, 0, 1, 2, 0, 2, 2, 1, 0, 1, 1, 1, 2, 0, 0});
        sortDNF(new int[]{0, 0, 0, 1, 2, 0, 2, 2, 1, 0, 1, 1, 1, 2, 0, 0});
    }

    /*
     * Approach: Dutch National Flag Algorithm (Optimal)
     * Pattern: Three Pointers
     * Time Complexity: O(N) - Single pass; each element is processed at most once.
     * Space Complexity: O(1) - Sorting is done in-place.
     */
    private static void sortDNF(int[] arr) {
        // low: tracks end of 0s region, mid: current element, high: tracks start of 2s region
        int low = 0, mid = 0, high = arr.length - 1;

        while (mid <= high) {
            if (arr[mid] == 0) {
                // Case 0: Swap to the 'low' region.
                // Both pointers move because we know the swapped element at 'low' is processed.
                swap(arr, low, mid);
                low++;
                mid++;
            } else if (arr[mid] == 1) {
                // Case 1: Element is in correct middle region. Just move forward.
                mid++;
            } else {
                // Case 2: Swap to the 'high' region.
                // Key Logic: Only decrement 'high'. Do NOT increment 'mid' because the
                // new value swapped from 'high' hasn't been checked yet.
                swap(arr, mid, high);
                high--;
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /*
     * Approach: Counting Sort (Brute Force/Better)
     * Pattern: Two-Pass Iteration
     * Time Complexity: O(2N) - One pass to count, one pass to overwrite.
     * Space Complexity: O(1) - Constant variables used.
     */
    private static void sortOne(int[] a) {
        int count0 = 0, count1 = 0, count2 = 0;
        int n = a.length;

        // Pass 1: Count frequencies
        for (int j : a) {
            if (j == 0) count0++;
            else if (j == 1) count1++;
            else if (j == 2) count2++;
        }

        // Pass 2: Overwrite array based on counts
        for (int i = 0; i < count0; i++) a[i] = 0;
        for (int i = count0; i < count0 + count1; i++) a[i] = 1;
        for (int i = count0 + count1; i < n; i++) a[i] = 2;

        System.out.println(Arrays.toString(a));
    }
}