package Hard;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Majority_Element {
    public static void main(String[] args) {
        List<Integer> majorityElement = majorityElement(new int[]{1, 1, 1, 3, 3, 2, 2, 2});
        System.out.println(majorityElement.toString());
    }

    public static List<Integer> majorityElement(int[] nums) {
        List<Integer> majorityElement = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;
        int k = (int) Math.floor(n / 3);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
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

    public static List<Integer> majorityElementOptimal(int[] nums) {
        int el1 = Integer.MIN_VALUE, el2 = Integer.MIN_VALUE;
        int n = nums.length;
        int cnt1 = 0;
        int cnt2 = 0;
        for (int i = 0; i < n; i++) {
            if (cnt1 == 0 && el2 != nums[i]) {
                cnt1 = 1;
                el1 = nums[i];
            } else if (cnt2 == 0 && el1 != nums[i]) {
                cnt2 = 1;
                el2 = nums[i];
            } else if (nums[i] == el1) {
                cnt1++;
            } else if (nums[i] == el2) {
                cnt2++;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        List<Integer> majorityElement = new ArrayList<>();
        cnt1 = 0;
        cnt2 = 0;
        for (int i = 0; i < n; i++) {
            if (el1 == nums[i]) cnt1++;
            if (el2 == nums[i]) cnt2++;
        }
        int mini = (int) Math.floor(n / 3);
        if (cnt1 > mini) majorityElement.add(el1);
        if (cnt2 > mini) majorityElement.add(el2);
        return majorityElement;
    }
}
