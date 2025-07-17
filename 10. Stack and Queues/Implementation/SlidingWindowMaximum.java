import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int j = 0;

        // To store idx and act as monotonic stack i.e. storing in decreasing order.
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // Remove top most idx (elem) for keeping in window k
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) dq.pollFirst();

            // To remove smaller elem's idx
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) dq.pollLast();

            dq.add(i);

            // To add the largest element in window in the window in ans.
            if (i >= k - 1) {
                ans[j] = nums[dq.peekFirst()];
                j++;
            }
        }

        return ans;
    }
}
