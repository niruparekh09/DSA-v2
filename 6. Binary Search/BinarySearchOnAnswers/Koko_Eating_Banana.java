public class Koko_Eating_Banana {

    public static void main(String[] args) {
        System.out.println(minEatingSpeed(new int[]{3, 6, 7, 11}, 8));
    }

    /*
     * Approach: Binary Search on Answer (Minimization)
     * Pattern: Modified Binary Search
     * Time Complexity: O(N * log(Max(Piles))) - Search over speed range [1, Max], iterating array each time.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int minEatingSpeed(int[] piles, int h) {
        // Search Space: Speed ranges from 1 banana/hr to Max(piles) bananas/hr.
        // Note: Eating faster than the largest pile doesn't save time (min 1 hour per pile), so Max is the upper bound.
        long low = 1, high = getMax(piles);

        while (low <= high) {
            long mid = low + (high - low) / 2; // 'mid' represents the eating speed 'k'

            // Logic: If current speed takes MORE than 'h' hours, it's too slow.
            // We must eat faster, so eliminate the left half (increase speed).
            if (getHours(piles, mid) > h) low = mid + 1;
            else
                // Logic: Speed is sufficient (total hours <= h).
                // But we want the *minimum* speed, so try slower speeds (eliminate right half).
                high = mid - 1;
        }
        // In minimization problems, 'low' converges to the smallest valid value.
        return (int) low;
    }

    // Helper: Predicate Function
    // Calculates total hours needed to eat all bananas at speed 'mid'
    public static long getHours(int[] piles, long mid) {
        long totalHours = 0;
        for (int pile : piles) {
            // Key Math: Hours = ceil(pile / speed).
            // Example: Pile 7, Speed 3 -> 3 hours (3, 3, 1).
            totalHours += (long) Math.ceil((double) pile / (double) mid);
        }
        return totalHours;
    }

    public static int getMax(int[] piles) {
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            max = Math.max(max, pile);
        }
        return max;
    }
}