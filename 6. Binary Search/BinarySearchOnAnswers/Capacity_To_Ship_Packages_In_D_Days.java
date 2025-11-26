public class Capacity_To_Ship_Packages_In_D_Days {
    public static void main(String[] args) {
        System.out.println(shipWithinDays(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 5));
    }

    /*
     * Approach: Binary Search on Answer (Minimization)
     * Pattern: Modified Binary Search / Greedy
     * Time Complexity: O(N * log(Sum - Max)) - Binary search over the capacity range, iterating array each time.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int shipWithinDays(int[] weights, int days) {
        int low = Integer.MIN_VALUE, high = 0;

        // Step 1: Define Search Space
        // Low: Heaviest single package (Ship must be at least this big to carry the largest item).
        // High: Sum of all weights (Ship carries everything in 1 day).
        for (int weight : weights) {
            high += weight;
            low = Math.max(low, weight);
        }

        // Step 2: Binary Search for minimum capacity
        while (low <= high) {
            int mid = low + (high - low) / 2; // 'mid' is the test Capacity
            int daysReq = daysReq(weights, mid);

            // Decision Logic:
            // If calculated days <= target days, this capacity is VALID.
            // However, we want the MINIMUM capacity, so we try to squeeze it smaller (eliminate right).
            if (daysReq <= days) {
                high = mid - 1;
            }
            // Logic: It takes too many days. Capacity is too small. Must increase.
            else {
                low = mid + 1;
            }
        }
        // In minimization problems, 'low' converges to the optimal answer.
        return low;
    }

    // Helper: Greedy simulation to count days needed for a specific ship capacity
    private static int daysReq(int[] weights, int capacity) {
        int days = 1, load = 0;
        for (int weight : weights) {
            // If adding this package exceeds capacity, ship sails today.
            // Package goes to the next day.
            if (load + weight > capacity) {
                days = days + 1;
                load = weight; // Reset load to current package weight
            } else {
                load += weight;
            }
        }
        return days;
    }
}