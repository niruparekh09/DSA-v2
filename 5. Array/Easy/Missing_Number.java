import java.util.HashMap;

public class Missing_Number {
    public static void main(String[] args) {
        final int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 9};
        System.out.println(missingNumber(nums, nums.length));
    }

    /**
     * Time Complexity: O(N^2) - Nested loops
     * Space Complexity: O(1) - Constant extra space
     */
    public static int missingNumber1(int[] nums) {
        // Iterate from 0 to n (inclusive) where n is the length of the array. Handles the case where 'n' is missing.
        for (int i = 0; i <= nums.length; i++) {
            int flag = 0;
            // Iterate through the array to check if 'i' exists.
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == i) {
                    flag = 1; // Set flag if 'i' is found in nums.
                    break;
                }
            }
            // If 'i' is not found (flag is still 0), it's the missing number.
            if (flag == 0) return i;
        }
        return -1; // Should not reach here if there is a missing number in the range [0, n].  Return -1 for edge cases where input is invalid or no number is missing.
    }

    /**
     * Time Complexity: O(N) - Iterating through the array a few times.
     * Space Complexity: O(N) - HashMap to store number presence.
     */
    public static int missingNumber2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        // Initialize map with all numbers from 0 to n.
        for (int i = 0; i <= nums.length; i++) {
            map.put(i, 0);
        }

        // Mark numbers present in the array.
        for (int num : nums) {
            map.put(num, 1);
        }

        // Find the missing number by checking for 0 in the map.
        for (int i = 0; i <= nums.length; i++) {
            if (map.get(i) == 0) return i;
        }
        return -1; // Handle edge case: no missing number in range [0, n]
    }

    /**
     * Time Complexity: O(N) - Single iteration through the array.
     * Space Complexity: O(1) - Constant extra space.
     */
    public static int missingNumber3(int[] nums) {
        int n = nums.length;
        int sum1 = (n * (n + 1)) / 2; // Calculate the expected sum of numbers from 0 to n.
        int sum2 = 0;
        for (int num : nums) {
            sum2 += num; // Calculate the actual sum of numbers in the array.
        }
        return sum1 - sum2; // The difference between expected and actual sum is the missing number.
        // Edge case: This approach assumes no overflow. Could be an issue with very large 'n'.
    }

    /**
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    // This solution can only be used when the array doesn't contain 0 in it and is sorted.
    public static int missingNumber(int[] nums, int N) {
        int xor1 = 0, xor2 = 0;

        // XOR of array elements and expected sequence (1 to N-1)
        for (int i = 0; i < N - 1; i++) {
            xor2 = xor2 ^ nums[i]; // XOR of array elements
            xor1 = xor1 ^ (i + 1); //XOR up to [1...N-1]
        }
        xor1 = xor1 ^ N; // Include N in the XOR

        return (xor1 ^ xor2); // XORing the two XORs gives the missing number. Assumes only one number is missing.
    }
}
