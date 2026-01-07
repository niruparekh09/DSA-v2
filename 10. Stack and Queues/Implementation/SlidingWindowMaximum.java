import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }

    /*
     * Approach: Monotonic Decreasing Deque
     * Pattern: Sliding Window / Monotonic Queue
     * Time Complexity: O(N) - Each element is pushed and popped at most once.
     * Space Complexity: O(K) - Deque stores at most K elements (indices).
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        int j = 0;

        // Stores INDICES. The values at these indices will be in strictly decreasing order.
        // Head of Deque = Index of the Max element in the current window.
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            // Step 1: Handle Out of Bounds
            // If the index at the front is too old (outside the window [i-k+1, i]), remove it.
            if (!dq.isEmpty() && dq.peekFirst() <= i - k) dq.pollFirst();

            // Step 2: Maintain Monotonicity (Decreasing)
            // Remove elements from the back that are smaller than the current element.
            // Logic: Since nums[i] is larger and "newer", the smaller "older" elements are useless.
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) dq.pollLast();

            dq.add(i);

            // Step 3: Store Result
            // Once we have processed at least K elements (window is full), record the max.
            if (i >= k - 1) {
                ans[j] = nums[dq.peekFirst()];
                j++;
            }
        }

        return ans;
    }
}