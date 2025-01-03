public class Minimum_In_Rotated_Sorted_Array {
    public static void main(String[] args) {
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2}));
    }

    public static int findMin(int[] nums) {
        int ans = Integer.MAX_VALUE;
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            // Left half is sorted
            if (nums[low] <= nums[mid]) {

                //We can check if search space is already sorted and if so than we can set ans and break out of loop.
                if (nums[low] <= nums[high]) {
                    ans = Math.min(ans, nums[low]);
                    break;
                }

                // We will get the low element in this sorted half i.e. the left most element and compare it with ans
                // and will set the minimum as the ans
                ans = Math.min(ans, nums[low]);
                // Now once we find the min from this sorted half we will eliminate this half and search is there is
                // smaller number present in second half
                low = mid + 1;
            } else { // Right half is sorted
                // This will work as vice versa of above if statement because right half is sorted here. The min. elem.
                // in this half will be mid (i.e. this half is from mid----high).
                ans = Math.min(ans, nums[mid]);
                // We will eliminate this half after we get the min from this half and then check in the other half.
                high = mid - 1;
            }
        }
        return ans;
    }
}