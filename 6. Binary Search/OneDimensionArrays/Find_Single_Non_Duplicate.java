public class Find_Single_Non_Duplicate {
    public static void main(String[] args) {
        System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 3, 3, 4, 4, 8, 8}));
    }

    public static int singleNonDuplicate(int[] nums) {
        int n = nums.length;
        //Edge cases
        if (n == 1) return nums[0];
        if (nums[0] != nums[1]) return nums[0];
        if (nums[n - 1] != nums[n - 2]) return nums[n - 1];

        // Using these pointers two avoid edge cases
        int low = 1, high = n - 2;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            // If mid is our single element. Checking if it's not equal to both left and right element
            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) return nums[mid];

            // Checking if single element exists in left. If it's not we will eliminate the left half.
            if ((mid % 2 == 1 && nums[mid] == nums[mid - 1]) || // If the mid is in odd index than the same number should be in even idx just before
                    (mid % 2 == 0 && nums[mid] == nums[mid + 1])) { // Vise versa for mid in even index.
                // If true, then It means that it is following even-odd pattern for equal pairs and single element is not
                // present in this half to disrepute the flow. So we will eliminate this half.
                low = mid + 1;
            } else {
                // It is present in left half so we will eliminate the right half and flow has been disrupted in left half.
                high = mid - 1;
            }
        }
        return -1;
    }
}
