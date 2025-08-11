public class BinarySubArraysWithSum {
    public static void main(String[] args) {
        /*
        Input : nums = [1, 1, 0, 1, 0, 0, 1] , goal = 3
        Output : 4
        Explanation : The subarray with sum 3 are
        [1, 1, 0, 1]
        [1, 1, 0, 1, 0]
        [1, 1, 0, 1, 0, 0]
        [1, 0, 1, 0, 0, 1]
        -----------------------
        Input : nums = [0, 0, 0, 0, 1] , goal = 0
        Output : 10
        Explanation : Some of the subarray with sum 0 are
        [0] x4
        [0, 0] x3
        [0, 0, 0] x2
        [0, 0, 0, 0]
        */
        System.out.println(numSubarraysWithSum(new int[]{1, 1, 0, 1, 0, 0, 1}, 3));
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        return sumLessThanEqual(nums, goal) - sumLessThanEqual(nums, goal - 1);
    }

    public static int sumLessThanEqual(int[] nums, int goal) {
        if (goal < 0) return 0;
        int sum = 0, count = 0;
        int l = 0, r = 0;
        while (r < nums.length) {
            sum += nums[r];

            // Shrink the window from the left side if the sum exceeds `goal`
            while (sum > goal) {
                sum -= nums[l];
                l++;
            }
            // Count all the subarrays ending at 'r' that satisfies the sum condition
            count += (r - l + 1);
            r++;
        }
        return count;
    }
}