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

    // Pattern: Hashing (Reduction to 2-Sum within nested loops)
    // Time: O(N^3) - Three nested loops.
    // Space: O(N) - HashSets used for tracking elements and unique quadruplets.
    public static List<List<Integer>> fourSumBetter(int[] nums, int target) {
        int n = nums.length;
        HashSet<List<Integer>> hs = new HashSet<>(); // Stores unique quadruplets

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // HashSet acts as a lookup for the 4th element (similar to 2-Sum)
                HashSet<Long> temp = new HashSet<>();

                for (int k = j + 1; k < n; k++) {
                    long sum = nums[i] + nums[j];
                    sum += nums[k];
                    long num = (target - sum); // The 4th number needed

                    // Check if the 4th number exists in the set (visited in current k-loop)
                    if (temp.contains(num)) {
                        List<Integer> tempList = Arrays.asList(nums[i], nums[j], nums[k], (int) num);
                        // Sort to ensure standard format for duplicate detection in the outer HashSet
                        Collections.sort(tempList);
                        hs.add(tempList);
                    }
                    // Add current number to set for future lookups in this iteration
                    temp.add((long) nums[k]);
                }
            }
        }
        return new ArrayList<>(hs);
    }

    // Pattern: Sorting + Two Pointers (Optimal)
    // Time: O(N^3) - Two loops + one linear scan (two pointers).
    // Space: O(1) - No auxiliary data structures used (excluding return list).
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        // Sorting is crucial for the Two Pointer technique and duplicate handling
        Arrays.sort(nums);

        for (int i = 0; i < n; i++) {
            // Edge Case: Skip duplicates for the 1st number
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            for (int j = i + 1; j < n; j++) {
                // Edge Case: Skip duplicates for the 2nd number
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // Two pointers initialization for the remaining two numbers
                int k = j + 1;
                int l = n - 1;

                while (k < l) {
                    // Use long to prevent integer overflow during summation
                    long sum = nums[i];
                    sum += nums[j];
                    sum += nums[k];
                    sum += nums[l];

                    if (sum > target) {
                        l--; // Sum too large, move right pointer left
                    } else if (sum < target) {
                        k++; // Sum too small, move left pointer right
                    } else {
                        List<Integer> temp = List.of(nums[i], nums[j], nums[k], nums[l]);
                        ans.add(temp);
                        k++;
                        l--;

                        // Optimization: Skip duplicates for 3rd and 4th numbers to prevent repeated quads
                        while (k < l && nums[k] == nums[k - 1]) k++;
                        while (k < l && nums[l] == nums[l + 1]) l--;
                    }
                }
            }
        }
        return ans;
    }
}
