import java.util.Arrays;

public class Candy {
    /*
     * Approach: Two-Pass Greedy
     * Pattern: Satisfying Bi-directional Constraints
     * Time Complexity: O(N) - Two linear passes through the array.
     * Space Complexity: O(N) - To store the candies array.
     */
    public int candyBrute(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];

        // Rule 1: Every child must have at least one candy.
        Arrays.fill(candies, 1);


        // Step 1: Left-to-Right Pass
        // Greedy Choice: If the current child has a higher rating than the left neighbor,
        // give them one more candy than the left neighbor.
        for (int i = 1; i < n; i++) {
            if (ratings[i - 1] < ratings[i]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        // Step 2: Right-to-Left Pass
        // Greedy Choice: If the current child has a higher rating than the right neighbor,
        // ensure they have more candies than the right neighbor while maintaining the
        // constraint from the first pass (using Math.max).
        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i + 1] < ratings[i]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int ans = 0;
        for (int candy : candies) ans += candy;

        return ans;
    }

    /*
     * Approach: Slope Method (Optimized Greedy)
     * Pattern: Peak and Valley Analysis
     * Time Complexity: O(N) - Single pass through the ratings.
     * Space Complexity: O(1) - No extra array used, only variables.
     */
    public int candy(int[] ratings) {
        int n = ratings.length;
        if (n == 0) return 0;

        int i = 1, ans = 1;


        while (i < n) {
            // Case 1: Flat surface - ratings are equal.
            // Greedy Choice: Reset to 1 candy as no neighbor constraint is violated.
            if (ratings[i] == ratings[i - 1]) {
                ans += 1;
                i++;
                continue;
            }

            // Case 2: Upward Slope
            int peak = 1;
            while (i < n && ratings[i] > ratings[i - 1]) {
                peak += 1;
                ans += peak;
                i++;
            }

            // Case 3: Downward Slope
            int down = 0;
            while (i < n && ratings[i] < ratings[i - 1]) {
                down += 1;
                ans += down;
                i++;
            }

            /* * The "Peak" adjustment:
             * A peak belongs to both an upward slope and a downward slope.
             * It must be large enough to satisfy both sides. If the downward
             * slope is longer than the upward slope, we must increase the peak.
             */
            if (down >= peak) {
                ans += (down - peak + 1);
            }
        }

        return ans;
    }
}