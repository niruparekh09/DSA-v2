import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Majority_Element {
    public static void main(String[] args) {
        List<Integer> majorityElement = majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2});
        System.out.println(majorityElement.toString());
    }

    // Pattern: Hashing (Frequency Map)
    // Time: O(N) - One pass.
    // Space: O(N) - Map stores unique elements.
    public static List<Integer> majorityElement(int[] nums) {

        List<Integer> majorityElement = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int k = (int) Math.floor(n / 3); // Threshold check

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            // Logic: Check if current count exceeds threshold immediately during iteration
            if (map.get(num) > k) {
                majorityElement.add(num);
            }
        }
//        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
//            if (entry.getValue() > k) {
//                majorityElement.add(entry.getKey());
//            }
//        }
        return majorityElement;
    }

    // Pattern: Boyer-Moore Voting Algorithm (Extended variant)
    // Time: O(N) - Two passes (Generate + Verify).
    // Space: O(1) - Constant variables.
    public static List<Integer> majorityElementOptimal(int[] nums) {

        int el1 = Integer.MIN_VALUE, el2 = Integer.MIN_VALUE;
        int n = nums.length;
        int cnt1 = 0;
        int cnt2 = 0;

        // Pass 1: Candidate Generation
        // Logic: Use counters to find top 2 potential majority candidates
        for (int i = 0; i < n; i++) {
            // Pick candidate 1 if count is 0, ensuring it's distinct from candidate 2
            if (cnt1 == 0 && el2 != nums[i]) {
                cnt1 = 1;
                el1 = nums[i];
            }
            // Pick candidate 2 if count is 0, ensuring it's distinct from candidate 1
            else if (cnt2 == 0 && el1 != nums[i]) {
                cnt2 = 1;
                el2 = nums[i];
            }
            // Increment counters if matches found
            else if (nums[i] == el1) {
                cnt1++;
            } else if (nums[i] == el2) {
                cnt2++;
            }
            // Decrement both if current number matches neither (canceling out non-majority)
            else {
                cnt1--;
                cnt2--;
            }
        }

        // Pass 2: Verification
        // Logic: Candidates from Pass 1 are not guaranteed majority; we must manually recount.
        List<Integer> majorityElement = new ArrayList<>();
        cnt1 = 0;
        cnt2 = 0;
        for (int i = 0; i < n; i++) {
            if (el1 == nums[i]) cnt1++;
            if (el2 == nums[i]) cnt2++;
        }

        // Edge Case: Only add if they strictly exceed N/3
        int mini = (int) Math.floor(n / 3);
        if (cnt1 > mini) majorityElement.add(el1);
        if (cnt2 > mini) majorityElement.add(el2);
        return majorityElement;
    }
}
