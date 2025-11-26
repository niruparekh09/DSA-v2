public class Kth_Missing_Positive_Number {
    public static void main(String[] args) {
        System.out.println(findKthPositive(new int[]{2, 3, 4, 7, 11}, 5));
    }

    /*
     * Approach: Binary Search on Indices
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Standard binary search on the array indices.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int findKthPositive(int[] arr, int k) {
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // Key Logic: Calculate how many numbers are missing up to index 'mid'.
            // Ideally, arr[mid] should be (mid + 1). Example: index 0 should hold 1.
            // Difference = Actual Value - Expected Value.
            int missing = arr[mid] - (mid + 1);

            // Decision:
            // If missing count < k, the k-th missing number must be to the RIGHT (larger value).
            if (missing < k) {
                low = mid + 1;
            }
            // If missing count >= k, we have passed the k-th missing number. Look LEFT.
            else {
                high = mid - 1;
            }
        }

        // Derivation of Return Formula:
        // At the end, 'high' points to the index where missing < k.
        // 'low' points to the index where missing >= k.
        // The answer is (k + low).
        // Why? Ans = arr[high] + (k - missing_at_high)
        //      Ans = arr[high] + k - (arr[high] - (high + 1))
        //      Ans = k + high + 1
        return k + high + 1;
    }
}