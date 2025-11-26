import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Two_Sum {
    public static void main(String[] args) {
        int[] arr = twoSum(new int[]{2, 7, 11, 15}, 9);
        System.out.println(Arrays.toString(arr));
    }

    /*
     * Approach: One-pass Hash Table
     * Pattern: Hashing
     * Time Complexity: O(N) - We traverse the list containing N elements only once.
     * Space Complexity: O(N) - The extra space required depends on the number of items stored in the map.
     */
    public static int[] twoSum(int[] nums, int target) {
        // Map stores Value -> Index. Allows O(1) lookup to find if the complement exists.
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int moreNeeded = target - num;

            // Key Logic: Check if the 'complement' (number needed to reach target) was already visited.
            if (map.containsKey(moreNeeded)) {
                Integer i1 = map.get(moreNeeded);
                return new int[]{i1, i};
            }

            // Store current number and index to be checked against future elements.
            map.put(num, i);
        }

        // Edge Case: No solution found
        return new int[]{-1, -1};
    }
}