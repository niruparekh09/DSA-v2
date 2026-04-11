public class JumpGameOne {
    /*
     * Approach: Greedy Algorithm
     * Pattern: Range Coverage / Reachability
     * Time Complexity: O(N) - Single pass through the array.
     * Space Complexity: O(1) - Only one variable used to track reach.
     */
    public boolean canJump(int[] nums) {
        int n = nums.length;

        // Tracks the furthest index we can currently reach.
        int maxInd = 0;


        for (int i = 0; i < n; i++) {
            /* * If our current index 'i' exceeds the maximum reachable index,
             * it means we've hit a gap that cannot be crossed.
             */
            if (i > maxInd) return false;

            /* * Greedy Choice: Update the maximum reach by comparing the current
             * max reach with the potential jump from the current index.
             */
            maxInd = Math.max(maxInd, i + nums[i]);

            // Optimization: If maxInd already reaches the last index, we can stop early.
            if (maxInd >= n - 1) return true;
        }

        return true;
    }
}