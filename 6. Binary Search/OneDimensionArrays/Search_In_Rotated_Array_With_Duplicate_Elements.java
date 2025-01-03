public class Search_In_Rotated_Array_With_Duplicate_Elements {
    public static void main(String[] args) {
        System.out.println(search(new int[]{1, 1}, 1));
    }

    public static int search(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;

            // Now as we know that the array can contain duplicate element we have to put the check for a condition when
            // arr[low] == arr[mid] == arr[high] because all three pointers point at 3 and the below conditions will
            // not work for this. So to avoid this problem we have to basically trim the array because if mid is not
            // equal to target than low and high also aren't equal. So we can trim array by incr. low and decr. high by 1
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low = low + 1;
                high = high - 1;
                continue;
            }

            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target <= nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[mid] <= target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
