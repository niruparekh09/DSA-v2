public class Max_Profit {
    public static void main(String[] args) {
        System.out.println(BuySell(new int[]{7, 1, 5, 3, 6, 4}));
    }

    /*
     * Approach: One Pass (Tracking Minimum)
     * Pattern: Dynamic Programming Patterns (State: Min Price so far)
     * Time Complexity: O(N) - Single iteration through the prices.
     * Space Complexity: O(1) - Only stores min price and max profit.
     */
    private static int BuySell(int[] arr) {
        // Edge Case: Handle empty array or single element if necessary (not shown but implied)

        // 'mini' keeps track of the lowest buying price encountered so far
        int mini = arr[0], profit = 0;

        for (int i = 1; i < arr.length; i++) {
            // Logic: Calculate profit if we sold TODAY given the minimum buy price seen previously
            int cost = arr[i] - mini;

            // Update max profit if today's trade is better than previous best
            profit = Math.max(cost, profit);

            // Key Logic: Update the minimum price. We want to buy as low as possible.
            // This ensures subsequent calculations use the lowest possible buy price.
            mini = Math.min(arr[i], mini);
        }
        return profit;
    }
}