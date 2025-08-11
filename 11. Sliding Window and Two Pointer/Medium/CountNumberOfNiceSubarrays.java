public class CountNumberOfNiceSubarrays {
    public static void main(String[] args) {
        /*
        Input: nums = [1,1,2,1,1], k = 3
        Output: 2
        Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
        */
        System.out.println(numberOfSubarrays(new int[]{1, 1, 2, 1, 1}, 3));
    }

    public static int numberOfSubarrays(int[] nums, int k) {
        return countLessThanEqual(nums, k) - countLessThanEqual(nums, k - 1);
    }

    public static int countLessThanEqual(int[] nums, int k) {
        if (k < 0) return 0;
        int count = 0, sum = 0, l = 0, r = 0;
        while (r < nums.length) {
            sum += nums[r] % 2;
            while (sum > k) {
                sum -= nums[l] % 2;
                l++;
            }

            count += (r - l + 1);
            r++;
        }
        return count;
    }
}