import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement1 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement(new int[]{2, 1, 2, 4, 5, 6, 1, 5})));
    }

    public static int[] nextGreaterElement(int[] nums) {
        int n = nums.length;
        int[] nge = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums[i]) st.pop();
            nge[i] = !st.isEmpty() ? st.peek() : -1;
            st.push(nums[i]);
        }
        return nge;
    }

    // Leetcode
    public int[] nextGreaterElementLeetcode(int[] nums1, int[] nums2) {
        int[] res = new int[nums2.length];
        Stack<Integer> st = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums2[i]) st.pop();

            res[i] = st.isEmpty() ? -1 : st.peek();

            st.push(nums2[i]);
        }

        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums2[j] == nums1[i]) {
                    ans[i] = res[j];
                    break;
                }
            }
        }
        return ans;
    }
}
