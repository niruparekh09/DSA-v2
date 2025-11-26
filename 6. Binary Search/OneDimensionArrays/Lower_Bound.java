public class Lower_Bound {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 3, 7, 8, 9, 9, 9, 11};
        int lb = findLB(arr, arr.length, 1);
        System.out.println(lb);

        System.out.println(findFloor(new int[]{10143, 29122, 30010}, 23112));
    }

    /*
     * Approach: Lower Bound (Binary Search)
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Standard binary search traversal.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int findLB(int[] arr, int n, int x) {
        int ans = n; // Default answer is 'n' (index out of bounds) if all elements < x
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Key Logic: Find smallest index where arr[idx] >= x.
            if (arr[mid] >= x) {
                ans = mid;      // Candidate found
                high = mid - 1; // Look Left to see if there is an earlier valid index
            } else {
                low = mid + 1;  // Value is smaller than x, need to move Right
            }
        }
        return ans;
    }

    /*
     * Approach: Find Floor Index
     * Pattern: Binary Search
     * Time Complexity: O(log N)
     */
    public static int findFloor(int[] arr, int k) {
        int ans = -1;
        int low = 0, high = arr.length - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            // Logic: If element > k, it's too big to be a floor. Move Left.
            if (arr[mid] > k) {
                high = mid - 1;
            }
            // Logic: Element <= k. It's a valid candidate.
            // But we want the LARGEST possible number <= k, so we look Right.
            else {
                ans = mid;
                low = mid + 1;
            }
        }
        return ans; // Returns index of the floor element
    }
}