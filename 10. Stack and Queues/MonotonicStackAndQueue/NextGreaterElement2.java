import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElement2 {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextGreaterElement2(new int[]{2, 1, 2, 4, 5, 6, 1, 5})));
    }

    public static int[] nextGreaterElement2(int[] nums) {
        int n = nums.length;
        int[] nge2 = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!st.isEmpty() && st.peek() <= nums[i % n]) st.pop();

            if (i < n) nge2[i] = !st.isEmpty() ? st.peek() : -1;

            st.push(nums[i % n]);
        }
        return nge2;
    }
}
