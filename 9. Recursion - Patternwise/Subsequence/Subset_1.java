package Subsequence;

import java.util.ArrayList;
import java.util.List;

public class Subset_1 {

    public static void main(String[] args) {
        System.out.println(subsets(new int[]{1, 2, 3}));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        generateSubset(nums, 0, new ArrayList<>(), res);
        return res;
    }

    public static void generateSubset(int[] nums, int index, List<Integer> current, List<List<Integer>> res) {
        if (index == nums.length) {
            res.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[index]);
        generateSubset(nums, index + 1, current, res);
        current.removeLast();
        generateSubset(nums, index + 1, current, res);
    }
}
