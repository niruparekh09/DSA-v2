package Subsequence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination_Sum_2 {
    public static void main(String[] args) {
        System.out.println(combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(candidates);
        findCombination(0, candidates, target, ans, new ArrayList<>());
        return ans;
    }

    public static void findCombination(int idx, int[] candidates, int target, List<List<Integer>> ans, ArrayList<Integer> ds) {
        if (target == 0) {
            ans.add(new ArrayList<>(ds));
        }

        for (int i = idx; i < candidates.length; i++) {
            if (i > idx && candidates[i] == candidates[i - 1])
                continue; // The number is repetitive so we to avoid duplicates
            if (candidates[i] > target) break; // The number is bigger that the target so no point going further

            ds.add(candidates[i]);
            findCombination(i + 1, candidates, target - candidates[i], ans, ds);
            ds.remove(ds.size() - 1);
        }
    }
}
