import java.util.*;

public class Three_Sum {
    public static void main(String[] args) {
        List<List<Integer>> threeSum = threeSum(new int[]{2, -3, 0, -2, -5, -5, -4, 1, 2, -2, 2, 0, 2, -4});
        System.out.println(threeSum);
    }

    // Pattern: Brute Force
    // Time: O(N^3) - Three nested loops.
    // Space: O(N) - HashSet used for deduplication.
    public static List<List<Integer>> threeSumBrute(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> st = new HashSet<>(); // Uses Set to store unique triplets
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[k]);
                        // Sorting is necessary so HashSet can identify duplicates (e.g., [1, -1, 0] == [-1, 0, 1])
                        Collections.sort(temp);
                        st.add(temp);
                    }
                }
            }
        }
        return new ArrayList<>(st);
    }

    // Pattern: Hashing (Reduction to 2-Sum)
    // Time: O(N^2) - Two nested loops.
    // Space: O(N) - HashSet to store elements for O(1) lookup.
    public static List<List<Integer>> threeSumBetter(int[] nums) {
        Set<List<Integer>> st = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            Set<Integer> hs = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                // Logic: Fix nums[i] and nums[j], check if -(nums[i] + nums[j]) exists in the hashset
                int third = -(nums[i] + nums[j]);

                if (hs.contains(third)) {
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], third);
                    Collections.sort(temp); // Sort to ensure uniqueness in the outer Set
                    st.add(temp);
                }
                // Add current element to set for future lookups in this i-iteration
                hs.add(nums[j]);
            }
        }
        return new ArrayList<>(st);
    }

    // Pattern: Two Pointers (with Sorting)
    // Time: O(N^2) - Sorting (NlogN) + Nested Loop/Pointer movement (N^2).
    // Space: O(1) - No auxiliary data structures used (excluding output list).
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        // Sorting is prerequisite for the Two Pointer technique and duplicate handling
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // Edge Case: Skip duplicates for the 1st element to ensure unique triplets
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            int j = i + 1;
            int k = n - 1;
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum > 0) {
                    k--; // Sum too large -> decrease right pointer
                } else if (sum < 0) {
                    j++; // Sum too small -> increase left pointer
                } else {
                    // Found a valid triplet
                    List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k]);
                    ans.add(temp);

                    // Move pointers to look for other potential pairs with fixed 'i'
                    j++;
                    k--;

                    // Logic: Skip duplicates for 2nd (j) and 3rd (k) numbers to prevent repeated triplets
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }
        return ans;
    }
}