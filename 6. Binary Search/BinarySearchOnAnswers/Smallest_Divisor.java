public class Smallest_Divisor {

    public static void main(String[] args) {
        System.out.println(smallestDivisor(new int[]{1, 2, 5, 9}, 6));
    }

    /*
     * Approach: Binary Search on Answer (Minimization)
     * Pattern: Modified Binary Search
     * Time Complexity: O(N * log(MaxVal)) - Binary search over value range [1, Max], iterating array each time.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int smallestDivisor(int[] nums, int threshold) {
        // Edge Case: Minimum possible sum is nums.length (when divisor is very large, every term becomes 1).
        // If threshold is less than array length, it's impossible.
        if (threshold < nums.length) return -1;

        // Search Space: Divisor ranges from 1 to Max Element.
        int low = 1, high = getMax(nums);

        while (low <= high) {
            int mid = low + (high - low) / 2; // 'mid' is the candidate Divisor

            // Logic: If the sum is within the threshold, this divisor is valid.
            // However, we want the *smallest* divisor, so we try to find a smaller one (move left).
            if (sumByD(nums, mid) <= threshold) {
                high = mid - 1;
            }
            // Logic: Sum exceeds threshold. We need to reduce the sum by INCREASING the divisor.
            else {
                low = mid + 1;
            }
        }
        // In minimization problems, 'low' converges to the optimal answer.
        return low;
    }

    // Helper: Predicate Function
    // Calculates sum of results of division (rounded up).
    private static int sumByD(int[] nums, int mid) {
        int ans = 0;
        for (int num : nums) {
            // Key Math: Summing ceil(num / divisor)
            ans += (int) Math.ceil((double) num / (double) mid);
        }
        return ans;
    }

    private static int getMax(int[] nums) {
        int max = Integer.MIN_VALUE;
        for (int el : nums) {
            max = Math.max(el, max);
        }
        return max;
    }
}