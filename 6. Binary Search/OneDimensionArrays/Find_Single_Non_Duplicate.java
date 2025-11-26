public class Find_Single_Non_Duplicate {
    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
    }

    /*
     * Approach: Binary Search (Index Parity Check)
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Standard binary search halving.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int singleNonDuplicate(int[] nums) {
        int n = nums.length;

        // Edge Cases: Explicitly handle boundaries to prevent IndexOutOfBounds inside the loop.
        // If first or last elements are unique, return them immediately.
        if (n == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];

        // Search Space: Reduced to [1, n-2] since boundaries are checked.
        // This guarantees that mid-1 and mid+1 are always valid indices.
        int low = 1, high = n - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Found Answer: Element is unique if it differs from both neighbors.
            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) return nums[mid];

            // Key Logic: Determine which side the single element lies on.
            // Left Side (Normal): First instance is at Even index, Second at Odd.
            // Right Side (Disrupted): First instance is at Odd index, Second at Even.

            // Check if we are in the "Normal" left half:
            // 1. Mid is Odd and matches Left (mid-1) OR
            // 2. Mid is Even and matches Right (mid+1)
            if ((mid % 2 == 1 && nums[mid] == nums[mid - 1]) ||
                    (mid % 2 == 0 && nums[mid] == nums[mid + 1])) {
                // Pattern holds, so single element is to the Right.
                low = mid + 1;
            } else {
                // Pattern is disrupted, so single element is to the Left.
                high = mid - 1;
            }
        }
        return -1;
    }
}