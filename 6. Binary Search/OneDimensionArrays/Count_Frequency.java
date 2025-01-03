public class Count_Frequency {
    public static void main(String[] args) {
        int freq = countFreq(new int[]{8, 9, 10, 12, 12, 12}, 12);
        System.out.println(freq);
    }

    public static int countFreq(int[] arr, int target) {
        int[] ans = searchRange(arr, target);
        if (ans[0] == -1) return 0;
        return (ans[1] - ans[0] + 1);
    }

    public static int[] searchRange(int[] nums, int target) {
        int first = getFirstOccurrence(nums, nums.length, target);
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
}
