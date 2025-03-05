package Subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Subsets_2 {
    public static void main(String[] args) {
        System.out.println(subsetsWithDup(new int[]{1, 2, 2}));
    }

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        generateSubsets(0, nums, ans, new ArrayList<Integer>());
        return ans;
    }

    public static void generateSubsets(int idx, int[] nums, List<List<Integer>> ans, ArrayList<Integer> ds) {
        if (idx == nums.length) {
            if (!ans.contains(ds)) {
                Collections.sort(ds);
                ans.add(new ArrayList<>(ds));
            }
            return;
        }

        ds.add(nums[idx]);
        generateSubsets(idx + 1, nums, ans, ds);
        ds.remove(ds.size() - 1);
        generateSubsets(idx + 1, nums, ans, ds);
    }
}
