package Subsequence;

import java.util.ArrayList;

public class Perfect_Sum_1 {
    public static void main(String[] args) {
        System.out.println(perfectSum(new int[]{5, 2, 3, 10, 6, 8}, 10));
    }

    public static int perfectSum(int[] nums, int target) {
        return generatePerfectSum(nums, 0, new ArrayList<>(), target, 0);
    }

    public static int generatePerfectSum(int[] nums, int idx, ArrayList<Integer> current, int target, int sum) {
        if (idx == nums.length) {
            if (sum == target) return 1;
            return 0;
        }

        current.add(nums[idx]);
        sum += nums[idx];
        int l = generatePerfectSum(nums, idx + 1, current, target, sum);
        sum -= nums[idx];
        current.removeLast();
        int r = generatePerfectSum(nums, idx + 1, current, target, sum);
        return l + r;
    }
}
