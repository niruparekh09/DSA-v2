package Easy;

import java.util.HashMap;

public class Missing_Number {
    public static void main(String[] args) {
        final int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 9};
        System.out.println(missingNumber(nums, nums.length));
    }

    public static int missingNumber1(int[] nums) {
        for (int i = 0; i <= nums.length; i++) {
            int flag = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] == i) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 0) return i;
        }
        return -1;
    }

    public static int missingNumber2(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i <= nums.length; i++) {
            map.put(i, 0);
        }

        for (int num : nums) {
            map.put(num, 1);
        }

        for (int i = 0; i <= nums.length; i++) {
            if (map.get(i) == 0) return i;
        }
        return -1;
    }

    public static int missingNumber3(int[] nums) {
        int n = nums.length;
        int sum1 = (n * (n + 1)) / 2;
        int sum2 = 0;
        for (int num : nums) {
            sum2 += num;
        }
        return sum1 - sum2;
    }

    // This solution can only be used when the array doesn't contain 0 in it and is sorted.
    public static int missingNumber(int[] nums, int N) {
        int xor1 = 0, xor2 = 0;

        for (int i = 0; i < N - 1; i++) {
            xor2 = xor2 ^ nums[i]; // XOR of array elements
            xor1 = xor1 ^ (i + 1); //XOR up to [1...N-1]
        }
        xor1 = xor1 ^ N; //XOR up to [1...N]

        return (xor1 ^ xor2); // the missing number
    }
}
