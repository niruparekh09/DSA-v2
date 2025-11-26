public class Find_Nth_Root {

    public static void main(String[] args) {
        System.out.println(nthRoot(4, 49));
    }

    /*
     * Approach: Binary Search on Answer
     * Pattern: Binary Search / Math
     * Time Complexity: O(N * log M) - Binary Search runs log(M) times, and power calc is O(N).
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int nthRoot(int n, int m) {
        // Search Range: [1, m]. The Nth root is always <= m.
        int low = 1, high = m;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Check mid^n against m using a helper (returns status codes)
            int val = pow(mid, n, m);

            if (val == 1) {
                return mid; // Found exact root
            } else if (val == 2) {
                // Logic: mid^n > m. Answer must be smaller. Eliminate Right half.
                high = mid - 1;
            } else {
                // Logic: mid^n < m. Answer must be larger. Eliminate Left half.
                low = mid + 1;
            }
        }
        return -1;
    }

    // Helper: Safe Power Calculation
    // Returns: 1 (Equal), 2 (Greater), 0 (Smaller)
    public static int pow(int mid, int n, int m) {
        long ans = 1;
        for (int i = 1; i <= n; i++) {
            ans = ans * mid;

            // Key Optimization & Safety:
            // If ans. exceeds m at any point, stop immediately.
            // This prevents Long Overflow and unnecessary computations.
            if (ans > m) return 2;
        }
        if (ans == m) return 1;
        return 0;
    }
}