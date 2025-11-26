public class Find_Sqrt {

    public static void main(String[] args) {
        System.out.println(floorSqrt(28));
    }

    /*
     * Approach: Binary Search on Answer
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - The search space reduces by half in every iteration.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int floorSqrt(int n) {
        int ans = -1;
        // Search Range: 1 to n (Square root is always within this range for n >= 1)
        int low = 1, high = n;

        while (low <= high) {
            // Note: (low + high) / 2 might overflow for very large n; prefer low + (high-low)/2.
            int mid = (low + high) / 2;

            // Key Logic: Check if mid is a valid candidate (mid^2 <= n).
            // Caution: mid*mid can overflow int range for large inputs (use long in production).
            if ((long) mid * mid <= n) {
                ans = mid;      // Store current valid candidate
                low = mid + 1;  // Move Right: We want the *largest* integer (floor), so we try to find a bigger valid number.
            } else {
                high = mid - 1; // Move Left: mid^2 exceeded n, so mid is too big.
            }
        }
        return ans;
    }
}