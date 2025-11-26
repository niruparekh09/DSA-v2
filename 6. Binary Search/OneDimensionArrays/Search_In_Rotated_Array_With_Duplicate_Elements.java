public class Search_In_Rotated_Array_With_Duplicate_Elements {
    public static void main(String[] args) {
        System.out.println(search(new int[]{1, 1}, 1));
    }

    /*
     * Approach: Binary Search (Shrinking Window for Duplicates)
     * Pattern: Modified Binary Search
     * Time Complexity: Average O(log N), Worst Case O(N) (e.g., all elements are same).
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid; // Found target (return true/index)

            // Edge Case: Ambiguity due to duplicates.
            // If low, mid, and high are equal (e.g., [3, 1, 2, 3, 3, 3, 3]), we cannot identify
            // which side is sorted.
            // Logic: Since nums[mid] != target, nums[low] and nums[high] are also not target.
            // We safely shrink the search space from both ends.
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low = low + 1;
                high = high - 1;
                continue;
            }

            // Standard Rotated Array Logic from here onwards:

            // Case 1: Left half is sorted
            if (nums[low] <= nums[mid]) {
                // Check if target lies within this sorted range
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid - 1; // Eliminate Right
                } else {
                    low = mid + 1;  // Target is in the unsorted Right half
                }
            }
            // Case 2: Right half is sorted
            else {
                // Check if target lies within this sorted range
                if (nums[mid] <= target && target <= nums[high]) {
                    low = mid + 1;  // Eliminate Left
                } else {
                    high = mid - 1; // Target is in the unsorted Left half
                }
            }
        }
        return -1;
    }
}