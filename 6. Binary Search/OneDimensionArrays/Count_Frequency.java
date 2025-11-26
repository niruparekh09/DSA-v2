public class Count_Frequency {
    public static void main(String[] args) {
        int freq = countFreq(new int[]{8, 9, 10, 12, 12, 12}, 12);
        System.out.println(freq);
    }

    /*
     * Approach: Double Binary Search (First & Last Occurrence)
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Two separate binary searches are performed.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int countFreq(int[] arr, int target) {
        int[] ans = searchRange(arr, target);

        // Edge Case: Target not found
        if (ans[0] == -1) return 0;

        // Logic: Total count = (Last Index - First Index) + 1
        return (ans[1] - ans[0] + 1);
    }

    public static int[] searchRange(int[] nums, int target) {
        int first = getFirstOccurrence(nums, nums.length, target);

        // Optimization: If first occurrence isn't found, the element doesn't exist.
        if (first == -1) return new int[]{-1, -1};

        int last = getLastOccurrence(nums, nums.length, target);
        return new int[]{first, last};
    }

    public static int getFirstOccurrence(int[] nums, int n, int x) {
        int first = -1;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == x) {
                first = mid;
                // Key Logic: We found the target, but there might be another one to the LEFT.
                // Eliminate the right side to find the *start* boundary.
                high = mid - 1;
            } else if (nums[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return first;
    }

    private static int getLastOccurrence(int[] nums, int n, int x) {
        int last = -1;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == x) {
                last = mid;
                // Key Logic: We found the target, but there might be another one to the RIGHT.
                // Eliminate the left side to find the *end* boundary.
                low = mid + 1;
            } else if (nums[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return last;
    }
}