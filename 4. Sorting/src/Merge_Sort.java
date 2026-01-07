import java.util.ArrayList;
import java.util.Arrays;

public class Merge_Sort {
    public static void main(String[] args) {
        int[] arr = {13, 46, 24, 52, 20, 9};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println("Sorted Array: " + Arrays.toString(arr));
    }

    /*
     * Approach: Merge Sort
     * Pattern: Divide and Conquer / Sorting
     * Time Complexity: O(N log N) - Consistent performance (Best, Average, Worst).
     * Space Complexity: O(N) - Required for temporary storage during the merge step.
     */
    private static void mergeSort(int[] arr, int low, int high) {
        // Base Case: Single element or invalid range is already sorted
        if (low >= high) return;

        int mid = (low + high) / 2;

        // Divide: Recursively sort the left and right halves
        mergeSort(arr, low, mid);
        mergeSort(arr, mid + 1, high);

        // Conquer: Merge the two sorted halves
        merge(arr, low, mid, high);
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        ArrayList<Integer> temp = new ArrayList<>();
        int left = low;      // Pointer for left half
        int right = mid + 1; // Pointer for right half

        // Compare elements from both halves and add the smaller one to temp
        while (left <= mid && right <= high) {
            if (arr[left] < arr[right]) {
                temp.add(arr[left]);
                left++;
            } else {
                temp.add(arr[right]);
                right++;
            }
        }

        // Add remaining elements from the left half (if any)
        while (left <= mid) {
            temp.add(arr[left]);
            left++;
        }

        // Add remaining elements from the right half (if any)
        while (right <= high) {
            temp.add(arr[right]);
            right++;
        }

        // Transfer sorted elements from temp back to the original array
        // Key Logic: 'i - low' maps the temp index (0-based) to the correct range in arr
        for (int i = low; i <= high; i++) {
            arr[i] = temp.get(i - low);
        }
    }
}