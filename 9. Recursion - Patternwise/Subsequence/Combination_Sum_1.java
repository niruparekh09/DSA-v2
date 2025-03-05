package Subsequence;

import java.util.ArrayList;
import java.util.List;

public class Combination_Sum_1 {

    public static void main(String[] args) {
        List<List<Integer>> lists = combinationSum(new int[]{2, 3, 4, 7}, 7);
        System.out.println(lists);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        findCombinations(0, candidates, target, ans, new ArrayList<>());
        return ans;
    }

    public static void findCombinations(int idx, int[] candidates, int target, List<List<Integer>> ans, ArrayList<Integer> ds) {
        if (idx == candidates.length) {
            if (target == 0) {
                ans.add(new ArrayList<>(ds));
            }
            return;
        }

        if (candidates[idx] <= target) {
            ds.add(candidates[idx]);
            findCombinations(idx, candidates, target - candidates[idx], ans, ds);
            ds.remove(ds.size() - 1);
        }
        findCombinations(idx + 1, candidates, target, ans, ds);
    }
}
