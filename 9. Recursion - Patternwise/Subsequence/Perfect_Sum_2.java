package Subsequence;

import java.util.ArrayList;

public class Perfect_Sum_2 {
    public static void main(String[] args) {
        System.out.println(perfectSum(new int[]{5, 2, 3, 10, 6, 8}, 10));
    }

    public static int perfectSum(int[] nums, int target) {
        int[] count = new int[1];
        ArrayList<Integer> ds = new ArrayList<>();
        generatePerfectSum(nums, 0, ds, count, target, 0);
        return count[0];
    }

    // Only print first subsequence which gives the perfect sum
    public static boolean generatePerfectSum(int[] nums, int idx, ArrayList<Integer> current, int[] count, int target, int sum) {
        if (idx == nums.length) {
            if (sum == target) {
                System.out.println(current);
                count[0]++;
                return true;
            }
            return false;
        }

        current.add(nums[idx]);
        sum += nums[idx];
        if (generatePerfectSum(nums, idx + 1, current, count, target, sum)) return true;
        sum -= nums[idx];
        current.removeLast();
        if (generatePerfectSum(nums, idx + 1, current, count, target, sum)) return true;
        else return false;
    }
}
