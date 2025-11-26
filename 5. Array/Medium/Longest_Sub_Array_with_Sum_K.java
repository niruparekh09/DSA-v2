import java.util.HashMap;
import java.util.Map;

public class Longest_Sub_Array_with_Sum_K {
    public static void main(String[] args) {
        System.out.println(lenOfLongestSubArray(new int[]{1, 2, 3, 1, 1, 1, 1, 4, 2, 3}, 3));
    }

    /*
     * Approach: Hashing (Handles negatives and zeros)
     * Pattern: Prefix Sum
     * Time Complexity: O(N) - Single pass, map operations are amortized O(1).
     * Space Complexity: O(N) - Worst case, we store all unique prefix sums.
     */
    public static int lenOfLongestSubArrayOne(int[] a, int k) {
        int n = a.length;
        Map<Long, Integer> preSumMap = new HashMap<>();
        long sum = 0;
        int maxLen = 0;

        for (int i = 0; i < n; i++) {
            sum += a[i];

            // Case 1: The subarray starts from index 0 and sums to K
            if (sum == k) {
                maxLen = Math.max(maxLen, i + 1);
            }

            // Case 2: Check if a prefix sum exists such that (CurrentSum - OldSum = K)
            long rem = sum - k;
            if (preSumMap.containsKey(rem)) {
                int len = i - preSumMap.get(rem);
                maxLen = Math.max(maxLen, len);
            }

            // Key Logic: Only add 'sum' to map if it doesn't exist.
            // Why? To keep the index of the *first* occurrence (leftmost) to maximize subarray length.
            // This also handles cases with 0s effectively.
            if (!preSumMap.containsKey(sum)) {
                preSumMap.put(sum, i);
            }
        }

        return maxLen;
    }

    /*
     * Approach: Greedy Two Pointers (Optimal for Positives ONLY)
     * Pattern: Sliding Window
     * Time Complexity: O(N) - The 'left' and 'right' pointers move forward at most N times each (2N).
     * Space Complexity: O(1) - No extra data structures.
     */
    public static int lenOfLongestSubArray(int[] a, int k) {
        int sum = a[0];
        int maxLen = 0;
        int left = 0, right = 0;
        int n = a.length;

        while (right < n) {
            // Key Logic: Shrink window from the left if sum exceeds K.
            // Note: This logic FAILS with negative numbers (monotonicity assumption).
            while (left <= right && sum > k) {
                sum -= a[left];
                left++;
            }

            // Check if current window matches target
            if (sum == k) {
                maxLen = Math.max(maxLen, right - left + 1);
            }

            // Expand window to the right
            right++;
            if (right < n) sum += a[right];
        }
        return maxLen;
    }
}