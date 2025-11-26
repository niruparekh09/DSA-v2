import java.util.*;

public class Four_Sum {
    public static void main(String[] args) {
        List<List<Integer>> lists = fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        System.out.println(lists);
    }

/*    public static List<List<Integer>> fourSum(int[] nums, int target) {
        // Basically the brute approach is to run four loops and get the value of i, j, k and l.
        // TC will be O(n^4) so no point writing this solution.
    }*/

    public static List<List<Integer>> fourSumBetter(int[] nums, int target) {
        int n = nums.length;
        HashSet<List<Integer>> hs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                HashSet<Long> temp = new HashSet<>();
                for (int k = j + 1; k < n; k++) {
                    long sum = nums[i] + nums[j];
                    sum += nums[k];
                    long num = (target - sum);
                    if (temp.contains(num)) {
                        List<Integer> tempList = Arrays.asList(nums[i], nums[j], nums[k], (int) num);
                        Collections.sort(tempList);
                        hs.add(tempList);
                    }
                    temp.add((long) nums[k]);
                }
            }
        }
        return new ArrayList<>(hs);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int k = j + 1;
                int l = n - 1;
                while (k < l) {
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];
                    if (sum > target) {
                        l--;
                    } else if (sum < target) {
                        k++;
                    } else {
                        List<Integer> temp = List.of(nums[i], nums[j], nums[k], nums[l]);
                        ans.add(temp);
                        k++;
                        l--;
                        while (k < l && nums[k] == nums[k - 1]) k++;
                        while (k < l && nums[l] == nums[l + 1]) l--;
                    }
                }
            }
        }
        return ans;
    }
}
