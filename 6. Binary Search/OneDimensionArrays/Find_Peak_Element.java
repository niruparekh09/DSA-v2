public class Find_Peak_Element {
    public static void main(String[] args) {
        System.out.println(findPeakElement(new int[]{1, 2, 1, 3, 4, 5, 6, 4, 1}));
    }

    public static int findPeakElement(int[] nums) {
        int n = nums.length;

        // Edge Cases
        if (n == 1) return 0;
        if (nums[0] > nums[1]) return 0;
        if (nums[n - 1] > nums[n - 2]) return n - 1;

        // Taking pointers as 1 <-> n-1 to avoid edge cases.
        int low = 1;
        int high = n - 2;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            //Checking if mid is the peak element
            if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) return mid;

            // Checking if mid is in left part of sorted array or right.
            // Left part of the peak will have increasing order so we will eliminate that because peak won't be present
            // there.
            if (nums[mid - 1] < nums[mid]) low = mid + 1;
                // Otherwise, we are in the right half, and we should eliminate it as our peak element appears on the left
            else high = mid - 1;
        }
        return -1;
    }
}
