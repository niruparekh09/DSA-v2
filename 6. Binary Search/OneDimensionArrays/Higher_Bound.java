public class Higher_Bound {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 7, 8, 9, 9, 9, 11};
        int hb = findHB(arr, arr.length, 8);
        System.out.println(hb);
    }

    /*
     * Approach: Upper Bound (Binary Search)
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Standard binary search traversal.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int findHB(int[] arr, int n, int x) {
        // Default answer is 'n' (out of bounds) if no element is greater than x
        int ans = n;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Key Logic: We look for the smallest index where arr[mid] > x (Strictly Greater).
            if (arr[mid] > x) {
                ans = mid;      // Found a candidate
                high = mid - 1; // Look Left to see if there's an earlier occurrence
            } else {
                low = mid + 1;  // Current element is <= x, need to look Right
            }
        }
        return ans;
    }
}