public class Floor_And_Ceil {
    public static void main(String[] args) {
        int[] floorAndCeil = getFloorAndCeil(3, new int[]{1, 2, 8, 10, 11, 12, 19});
        System.out.println(floorAndCeil[0] + " " + floorAndCeil[1]);
    }

    /*
     * Approach: Two Binary Searches
     * Pattern: Binary Search
     * Time Complexity: O(log N) - Two separate logarithmic traversals.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int[] getFloorAndCeil(int x, int[] arr) {
        int floor = -1, ceil = -1;

        // Pass 1: Find Floor (Largest element <= x)
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] <= x) {
                floor = arr[mid]; // Found a candidate
                // Key Logic: We want the LARGEST value <= x.
                // So, we move to the Right to see if a bigger valid number exists.
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // Pass 2: Find Ceil (Smallest element >= x)
        low = 0;
        high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] >= x) {
                ceil = arr[mid]; // Found a candidate
                // Key Logic: We want the SMALLEST value >= x.
                // So, we move to the Left to see if a smaller valid number exists.
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return new int[]{floor, ceil};
    }
}