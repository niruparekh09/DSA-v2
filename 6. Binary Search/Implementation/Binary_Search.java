public class Binary_Search {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 5, 9, 12};
        int num = recursiveSearch(arr, 0, arr.length - 1, 9);
        System.out.println(num);
    }

    /*
     * Approach: Iterative Binary Search (Preferred)
     * Pattern: Divide & Conquer
     * Time Complexity: O(log N) - Search space is halved in each iteration.
     * Space Complexity: O(1) - No extra space or stack frames used.
     */
    private static int search(int[] arr, int key) {
        int low = 0, high = arr.length - 1, mid = 0;

        // Loop runs until the search space is exhausted (pointers cross)
        while (low <= high) {
            // Key Logic: Prevent Integer Overflow.
            // (low + high) / 2 can exceed Integer.MAX_VALUE for large arrays.
            mid = low + (high - low) / 2;

            if (arr[mid] == key) {
                return mid;
            } else if (arr[mid] < key) {
                // Target is in the right half (larger values)
                low = mid + 1;
            } else {
                // Target is in the left half (smaller values)
                high = mid - 1;
            }
        }
        return -1; // Element not found
    }

    /*
     * Approach: Recursive Binary Search
     * Space Complexity: O(log N) - Uses stack memory due to recursion depth.
     */
    private static int recursiveSearch(int[] arr, int low, int high, int key) {
        // Base Case: Search space invalid (element not found)
        if (low > high) {
            return -1;
        }

        int mid = low + (high - low) / 2;

        if (arr[mid] == key) return mid;

            // Recursive Step: Call function for the specific half
        else if (arr[mid] > key) return recursiveSearch(arr, low, mid - 1, key);
        else return recursiveSearch(arr, mid + 1, high, key);
    }
}