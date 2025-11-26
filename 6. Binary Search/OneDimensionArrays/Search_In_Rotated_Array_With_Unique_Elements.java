public class Search_In_Rotated_Array_With_Unique_Elements {
    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
    }

    /*
     * Approach: Binary Search (Identify Sorted Halves)
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Guaranteed logarithmic search as elements are unique.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int search(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) return mid;

            // Key Strategy: One half of the array is ALWAYS sorted.
            // We determine which half, then check if the target lies within it.

            // Case 1: Left half [low...mid] is sorted
            if (arr[low] <= arr[mid]) {
                // Logic: Check if target lies strictly within this sorted range
                if (arr[low] <= target && target <= arr[mid]) {
                    high = mid - 1; // Target is here, eliminate Right
                } else {
                    low = mid + 1;  // Target is in the unsorted Right half
                }
            }
            // Case 2: Right half [mid...high] is sorted
            else {
                // Logic: Check if target lies strictly within this sorted range
                if (arr[mid] <= target && target <= arr[high]) {
                    low = mid + 1;  // Target is here, eliminate Left
                } else {
                    high = mid - 1; // Target is in the unsorted Left half
                }
            }
        }
        return -1;
    }
}