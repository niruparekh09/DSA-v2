public class Maximum_Sub_array_Sum {
    public static void main(String[] args) {
        System.out.println(sumKadane(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
    }

    /*
     * Approach: Kadane's Algorithm
     * Pattern: Dynamic Programming Patterns / Greedy
     * Time Complexity: O(N) - Single pass through the array.
     * Space Complexity: O(1) - Uses constant extra space.
     */
    private static int sumKadane(int[] arr) {
        int sum = 0;
        int maxi = Integer.MIN_VALUE; // Initialized to min to handle all-negative arrays

        for (int element : arr) {
            // Extend the current subarray
            sum = sum + element;

            // Check if the current running sum is the best we've seen so far
            if (sum > maxi) {
                maxi = sum;
            }

            // Key Logic: If the running sum drops below 0, it acts as a "burden" to future elements.
            // We discard the current sequence and reset sum to 0 to start fresh.
            if (sum < 0) {
                sum = 0;
            }
        }
        return maxi;
    }
}