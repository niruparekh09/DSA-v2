public class Minimum_In_Rotated_Sorted_Array {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2}));
    }

    /*
     * Approach: Binary Search (Identify Sorted Halves)
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Standard binary search splitting.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int findMin(int[] nums) {
        int ans = Integer.MAX_VALUE;
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Case 1: Left half is sorted [low...mid]
            if (nums[low] <= nums[mid]) {

                // Optimization: If the entire range [low...high] is sorted,
                // then nums[low] is definitely the minimum in this range.
                // We can update answer and break immediately (O(1) best case).
                if (nums[low] <= nums[high]) {
                    ans = Math.min(ans, nums[low]);
                    break;
                }

                // Logic: Since Left is sorted, the smallest value *in this half* is nums[low].
                // Update global min, then discard this half to look for the "pivot" in the Right half.
                ans = Math.min(ans, nums[low]);
                low = mid + 1;
            }
            // Case 2: Right half is sorted [mid...high]
            else {
                // Logic: Since Right is sorted, the smallest value *in this half* is nums[mid].
                // Update global min, then discard this half to look for the "pivot" in the Left half.
                ans = Math.min(ans, nums[mid]);
                high = mid - 1;
            }
        }
        return ans;
    }
}