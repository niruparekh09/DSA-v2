public class LongestOnes {
    public static void main(String[] args) {
        /*
        Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
        Output: 10
        Explanation: [0,0,1,1,`1`,`1`,1,1,1,`1`,1,1,0,0,0,1,1,1,1]
        Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
        */
        System.out.println(longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2));
    }

    public static int longestOnesBrute(int[] nums, int k) {
        int maxLen = 0;
        for (int i = 0; i < nums.length; i++) {
            int zeros = 0;
            for (int j = i; j < nums.length; j++) {
                if (nums[j] == 0) zeros++;
                if (zeros <= k) {
                    maxLen = Math.max(maxLen, j - i + 1);
                } else {
                    break; // Number of zeros is exceeded than K
                }
            }
        }
        return maxLen;
    }

    public static int longestOnes(int[] nums, int k) {
        int maxLen = 0;
        int l = 0, r = 0, zeros = 0;
        while (r < nums.length) {
            if (nums[r] == 0) zeros++; // Increase the number of zeros if encountered
            while (zeros > k) { // If the zeros exceed K, move l till it again comes under K
                if (nums[l] == 0) zeros--;
                l++;
            }
            maxLen = Math.max(maxLen, r - l + 1);
            r++; // Keep moving window size.
        }
        return maxLen;
    }

    public static int longestOnes2(int[] nums, int k) {
        int maxLen = 0;
        int l = 0, r = 0, zeroes = 0;
        while (r < nums.length) {
            if (nums[r] == 0) zeroes++; // Increase the number of zeroes if encountered
            if (zeroes > k) { // If the zeroes exceed K, move l till it again comes under K
                if (nums[l] == 0) zeroes--;
                l++;
            }
            if (zeroes <= k) {
                maxLen = Math.max(maxLen, r - l + 1);
            }
            r++; // Keep moving window size.
        }
        return maxLen;
    }
}