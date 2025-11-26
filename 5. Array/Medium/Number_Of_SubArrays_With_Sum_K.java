import java.util.HashMap;
import java.util.Map;

public class Number_Of_SubArrays_With_Sum_K {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, -3, 1, 1, 1, 4, 2, -3};
        int k = 3;
        int cnt = findAllSubArraysWithGivenSum(arr, k);
        System.out.println("The number of sub-arrays is: " + cnt);
    }

    /*
     * Approach: Prefix Sum Frequency Map
     * Pattern: Prefix Sum / Hashing
     * Time Complexity: O(N) - Single iteration.
     * Space Complexity: O(N) - Map stores distinct prefix sums.
     */
    private static int findAllSubArraysWithGivenSum(int[] arr, int k) {
        int n = arr.length;
        Map<Integer, Integer> mpp = new HashMap<>();
        int preSum = 0, cnt = 0;

        // Key Initialization: Handle subarrays that start from index 0.
        // If preSum eventually equals k, then (preSum - k) is 0. We need a count of 1 for this.
        mpp.put(0, 1);

        for (int i = 0; i < n; i++) {
            preSum += arr[i];

            // Key Logic: Check how many times (preSum - k) has appeared previously.
            // If (CurrentSum - OldSum = k), then the subarray between them sums to k.
            int remove = preSum - k;
            cnt += mpp.getOrDefault(remove, 0);

            // Update Map: Store/Update frequency of the current prefix sum.
            // Contrast: Unlike "Longest Subarray" (where we store index only once),
            // here we strictly need the COUNT of occurrences.
            mpp.put(preSum, mpp.getOrDefault(preSum, 0) + 1);
        }
        return cnt;
    }
}