public class JumpGameTwo {
    /*
     * Approach: Greedy (Level-by-Level BFS Simulation)
     * Pattern: Range-Based Greedy
     * Time Complexity: O(N) - Each element is visited at most once.
     * Space Complexity: O(1) - Constant space to track pointers and jumps.
     */
    public int jump(int[] nums) {
        // [l, r] represents the current range of indices reachable with 'jumps' number of jumps.
        int l = 0, r = 0;
        int n = nums.length;
        int jumps = 0;


        // Continue jumping until the range [l, r] includes the last index.
        while (r < n - 1) {
            int farthest = 0;

            /* * Greedy Choice: Within the current reachable range, find the
             * index that allows us to jump the furthest in the next step.
             * This effectively simulates a Breadth-First Search "level."
             */
            for (int i = l; i <= r; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }

            // Move to the next "level":
            // The new left boundary is just past the current right boundary.
            l = r + 1;
            // The new right boundary is the maximum distance we could reach from the previous level.
            r = farthest;

            jumps++;
        }

        return jumps;
    }
}