import java.util.Arrays;

public class First_And_Last_Occurrence {
    public static void main(String[] args) {
        int[] searchRange = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        System.out.println(Arrays.toString(searchRange));
    }

    /*
     * Approach: Double Binary Search (Explicit First & Last)
     * Pattern: Modified Binary Search
     * Time Complexity: O(log N) - Two separate binary searches.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int[] searchRange(int[] nums, int target) {
        int first = getFirstOccurrence(nums, nums.length, target);

        // Optimization: If the element isn't found in the first search,
        // it doesn't exist. Return early.
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
                // Key Logic: Match found, but there might be an earlier occurrence to the Left.
                // Update candidate and narrow search to [low, mid-1].
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
                // Key Logic: Match found, but there might be a later occurrence to the Right.
                // Update candidate and narrow search to [mid+1, high].
                low = mid + 1;
            } else if (nums[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return last;
    }

    // Approach: Unified Binary Search (Reduces code duplication)
    // Uses a boolean flag to decide whether to look Left (First) or Right (Last) on match.
    public static int getBothOccurrence(int[] nums, int n, int x, boolean isFirstOccurrence) {
        int ans = -1;
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == x) {
                ans = mid;
                if (isFirstOccurrence) high = mid - 1; // Look Left
                else low = mid + 1;                    // Look Right
            } else if (nums[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    /*
     * Approach: Lower Bound & Upper Bound (STL/Collections style)
     * Time Complexity: O(log N)
     */
    public static int[] searchRangeUsingLBAndUB(int[] nums, int target) {
        int lb = findLB(nums, nums.length, target);

        // Validation: Check if LB index is valid and actually points to the target
        if (lb == nums.length || nums[lb] != target) return new int[]{-1, -1};

        int ub = findUB(nums, nums.length, target);

        // Logic: LB points to the first instance.
        // UB points to the first element GREATER than target.
        // Therefore, the last instance is UB - 1.
        return new int[]{lb, ub - 1};
    }

    // Find Lower Bound: First index where arr[i] >= x
    public static int findLB(int[] arr, int n, int x) {
        int ans = n;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= x) {
                ans = mid;     // Candidate found, try to find a smaller index (Left)
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Find Upper Bound: First index where arr[i] > x
    public static int findUB(int[] arr, int n, int x) {
        int ans = n;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > x) {
                ans = mid;     // Candidate found, try to find a smaller index (Left)
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}