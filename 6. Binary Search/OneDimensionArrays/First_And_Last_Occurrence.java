import java.util.Arrays;

public class First_And_Last_Occurrence {
    public static void main(String[] args) {
        int[] searchRange = searchRange(new int[]{5, 7, 7, 8, 8, 10}, 8);
        System.out.println(Arrays.toString(searchRange));
    }

    public static int[] searchRange(int[] nums, int target) {
        int first = getFirstOccurrence(nums, nums.length, target);
        if (first == -1) return new int[]{-1, -1};
        int last = getLastOccurrence(nums, nums.length, target);
        //int first = search(nums, nums.length, target, true);
        //int last = search(nums, nums.length, target, false);
        return new int[]{first, last};
    }

    public static int getFirstOccurrence(int[] nums, int n, int x) {
        int first = -1;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == x) {
                first = mid;
                high = mid - 1; // Search if the number is present on even bigger index in the left part of the array
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
                low = mid + 1; // Search if the number is present on even bigger index in the right part of the array
            } else if (nums[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return last;
    }

    // Rather than making 2 different functions we will take of we want the firstOccurrence or not and we can add
    // an if-else to check accordingly
    public static int getBothOccurrence(int[] nums, int n, int x, boolean isFirstOccurrence) {
        int ans = -1;
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == x) {
                ans = mid;
                if (isFirstOccurrence) high = mid - 1;
                else low = mid + 1;
            } else if (nums[mid] < x) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    public static int[] searchRangeUsingLBAndUB(int[] nums, int target) {
        int lb = findLB(nums, nums.length, target);
        if (lb == nums.length || nums[lb] != target) return new int[]{-1, -1};
        int ub = findUB(nums, nums.length, target);
        return new int[]{lb, ub - 1};
    }

    public static int findLB(int[] arr, int n, int x) {
        int ans = n;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] >= x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static int findUB(int[] arr, int n, int x) {
        int ans = n;
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] > x) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}
