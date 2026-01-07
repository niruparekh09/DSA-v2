import java.util.Arrays;

public class Quick_Sort {
    public static void main(String[] args) {
        int[] arr = {13, 46, 24, 52, 20, 9};
        quickSort(arr, 0, arr.length - 1);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    /*
     * Approach: Quick Sort
     * Pattern: Divide and Conquer / Sorting
     * Time Complexity: O(N log N) Average, O(N^2) Worst Case (already sorted).
     * Space Complexity: O(log N) - Recursion stack space (O(N) worst case).
     */
    private static void quickSort(int[] arr, int low, int high) {
        // Base Case: Only sort if the range has more than one element
        if (low < high) {
            // Partition array and get the pivot's correct sorted position
            int partitionIndex = partition(arr, low, high);

            // Recursively sort elements before and after partition
            quickSort(arr, low, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, high);
        }
    }

    // Partition Logic (Hoare's Scheme Variation)
    // Goal: Place 'pivot' at correct position such that left < pivot and right > pivot
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // Choosing the first element as pivot
        int i = low;
        int j = high;

        while (i < j) {
            // Find first element from Left that is greater than pivot
            while (arr[i] <= pivot && i <= high - 1) {
                i++;
            }

            // Find first element from Right that is smaller than pivot
            while (arr[j] > pivot && j >= low + 1) {
                j--;
            }

            // Swap them to maintain partition property (Small on left, Large on right)
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Final Step: Place the pivot in its correct sorted position (index j).
        // Since j stopped at an element smaller than pivot, we swap pivot (arr[low]) with arr[j].
        int temp = arr[low];
        arr[low] = arr[j];
        arr[j] = temp;

        return j; // Return the partition index
    }
}