package Subsequence;

import java.util.ArrayList;
import java.util.Collections;

public class Subset_Sum_1 {
    public static void main(String[] args) {
        System.out.println(subsetSums(new int[]{2, 3}));
    }

    public static ArrayList<Integer> subsetSums(int[] arr) {
        ArrayList<Integer> ans = new ArrayList<>();
        findSubsetSum(0, arr, ans, 0);
        Collections.sort(ans);
        return ans;
    }

    public static void findSubsetSum(int idx, int[] arr, ArrayList<Integer> ans, int sum) {
        if (idx == arr.length) {
            ans.add(sum);
            return;
        }

        findSubsetSum(idx + 1, arr, ans, sum + arr[idx]);
        findSubsetSum(idx + 1, arr, ans, sum);
    }
}
