public class Search_Insert_Position {
    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5));
    }

    /*
     * Approach: Binary Search (Lower Bound)
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Standard binary search traversal.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int searchInsert(int[] nums, int target) {
        int n = nums.length;

        // Default answer is 'n'. If target is greater than all elements,
        // it must be inserted at the very end of the array.
        int ans = n;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Key Logic: We are looking for the smallest index where nums[mid] >= target.
            // This is the definition of "Lower Bound".
            if (nums[mid] >= target) {
                ans = mid;      // Found a potential insert position.
                high = mid - 1; // Try to find an earlier valid position (Left).
            } else {
                low = mid + 1;  // Current element is too small, insert position is to the Right.
            }
        }

        return ans;
    }
}