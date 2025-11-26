public class Find_Peak_Element {
    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 1, 3, 4, 5, 6, 4, 1}));
    }

    /*
     * Approach: Binary Search on Slope
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Eliminates half the search space based on local slope.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int findPeakElement(int[] nums) {
        int n = nums.length;

        // Edge Case 1: Single element is always a peak.
        if (n == 1) return 0;

        // Edge Case 2: Check if the first element is a peak (has no left neighbor).
        if (nums[0] > nums[1]) return 0;

        // Edge Case 3: Check if the last element is a peak (has no right neighbor).
        if (nums[n - 1] > nums[n - 2]) return n - 1;

        // Search Space Optimization:
        // Since we handled 0 and n-1, we search from 1 to n-2.
        // This ensures mid-1 and mid+1 always exist (no IndexOutOfBounds).
        int low = 1;
        int high = n - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Condition: Found the peak (greater than both neighbors)
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) return mid;

            // Decision Logic: Identify the slope.
            // If nums[mid] > nums[mid-1], we are on an "Uphill" slope.
            // A peak must exist further up the hill (to the Right).
            if (nums[mid - 1] < nums[mid]) {
                low = mid + 1;
            }
            // Otherwise, we are on a "Downhill" slope (or a valley).
            // A peak must exist previously (to the Left).
            else {
                high = mid - 1;
            }
        }
        return -1;
    }
}