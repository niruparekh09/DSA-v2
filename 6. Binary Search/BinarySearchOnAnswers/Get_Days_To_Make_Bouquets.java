public class Get_Days_To_Make_Bouquets {
    public static void main(String[] args) {
        System.out.println(minDays(new int[]{7, 7, 7, 7, 12, 7, 7}, 2, 3));
    }

    /*
     * Approach: Binary Search on Answer (Minimization)
     * Pattern: Modified Binary Search / Greedy
     * Time Complexity: O(N * log(Range)) - N is array length, Range is (MaxDay - MinDay).
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int minDays(int[] bloomDay, int m, int k) {
        // Edge Case: Total flowers needed (m * k) exceeds available flowers.
        // Critical: Cast to (long) to prevent integer overflow if m and k are large.
        if ((long) bloomDay.length < ((long) m * k)) return -1;

        int ans = getMiniOrMaxi(bloomDay, false);
        // Search Space: Range of days [Min Day in Array, Max Day in Array]
        int low = getMiniOrMaxi(bloomDay, true), high = getMiniOrMaxi(bloomDay, false);

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Logic: If we can make 'm' bouquets by day 'mid', this is a valid answer.
            // But we want the *minimum* days, so we store it and try to find an earlier day (move left).
            if (isPossible(bloomDay, mid, m, k)) {
                ans = Math.min(ans, mid);
                high = mid - 1;
            } else {
                // Logic: Impossible to make 'm' bouquets. We need more time (move right).
                low = mid + 1;
            }
        }
        return ans;
    }

    // Helper: Finds Min or Max in array to define Binary Search boundaries
    public static int getMiniOrMaxi(int[] bloomDay, boolean isMini) {
        if (isMini) {
            int mini = Integer.MAX_VALUE;
            for (int el : bloomDay) {
                mini = Math.min(mini, el);
            }
            return mini;
        } else {
            int maxi = Integer.MIN_VALUE;
            for (int el : bloomDay) {
                maxi = Math.max(maxi, el);
            }
            return maxi;
        }
    }

    // Helper: Predicate Function
    // Checks if we can form 'm' bouquets of 'k' adjacent flowers by day 'mid'
    public static boolean isPossible(int[] bloomDay, int mid, int m, int k) {
        int cnt = 0, noOfB = 0;
        for (int el : bloomDay) {
            // If flower blooms by 'mid', it contributes to the current adjacent streak
            if (el <= mid) cnt++;
            else {
                // Adjacency Break: Flower hasn't bloomed.
                // Calculate how many bouquets we formed in the previous streak and reset count.
                noOfB += cnt / k;
                cnt = 0;
            }
        }
        // Add bouquets from the final streak after loop ends
        noOfB += cnt / k;

        return noOfB >= m;
    }
}