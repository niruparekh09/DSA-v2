package Subsequence;

import java.util.ArrayList;

public class Is_Subset_Present_With_Sum_K {
    public static void main(String[] args) {
        System.out.println(isSubsetPresent(5, 14, new int[]{4, 2, 5, 6, 7}));
    }

    public static boolean isSubsetPresent(int n, int k, int[] a) {
        return generateSubsets(a, 0, new ArrayList<>(), k, n, 0);
    }

    public static boolean generateSubsets(int[] nums, int index, ArrayList<Integer> current, int target, int len, int sum) {
        if (index == len) {
            if (sum == target) {
                System.out.println(current.toString());
                return true;
            }
            return false;
        }

        sum += nums[index];
        current.add(nums[index]);
        if (generateSubsets(nums, index + 1, current, target, len, sum)) return true;
        sum -= nums[index];
        current.remove(current.size() - 1);
        if (generateSubsets(nums, index + 1, current, target, len, sum)) return true;
        return false;
    }
}
