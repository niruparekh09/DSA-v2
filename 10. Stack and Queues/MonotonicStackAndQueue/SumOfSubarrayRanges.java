import java.util.Stack;

public class SumOfSubarrayRanges {
    public static void main(String[] args) {
        System.out.println(subArrayRangesBrute(new int[]{1, 2, 3}));
        System.out.println(subArrayRanges(new int[]{1, 2, 3}));
    }

    // Time: O(n^2), Space: O(1)
    public static long subArrayRangesBrute(int[] nums) {
        long total = 0L;
        for (int i = 0; i < nums.length; i++) {
            int mini = nums[i], max = nums[i];
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < mini) mini = nums[j];
                if (nums[j] > max) max = nums[j];
                total += max - mini;
            }
        }
        return total;
    }

    public static long subArrayRanges(int[] nums) {
        return sumOfSubArrayMaximum(nums) - sumOfSubArrayMinimum(nums);
    }

    public static long sumOfSubArrayMaximum(int[] nums) {
        long total = 0L;
        int[] ngee = findNGEE(nums);
        int[] pge = findPGE(nums);
        for (int i = 0; i < nums.length; i++) {
            int right = ngee[i] - i;
            int left = i - pge[i];
            total += (long) nums[i] * left * right;
        }
        return total;
    }

    public static int[] findNGEE(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        for (int i = nums.length - 1; i >= 0; i--) {
            while (!st.empty() && nums[st.peek()] < nums[i]) st.pop();
            res[i] = st.empty() ? nums.length : st.peek();
            st.push(i);
        }
        return res;
    }

    public static int[] findPGE(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < nums.length; i++) {
            while (!st.empty() && nums[st.peek()] <= nums[i]) st.pop();
            res[i] = st.empty() ? -1 : st.peek();
            st.push(i);
        }
        return res;
    }


    public static long sumOfSubArrayMinimum(int[] arr) {
        long total = 0L;
        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr);
        for (int i = 0; i < arr.length; i++) {
            long right = nse[i] - i;
            long left = i - psee[i];
            total += (long) arr[i] * left * right;
        }
        return total;
    }

    public static int[] findNSE(int[] arr) {
        int[] nse = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.empty() && arr[st.peek()] >= arr[i]) st.pop();

            nse[i] = st.empty() ? arr.length : st.peek();
            st.push(i);
        }
        return nse;
    }

    public static int[] findPSEE(int[] arr) {
        int[] psee = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!st.empty() && arr[st.peek()] > arr[i]) st.pop();

            psee[i] = st.empty() ? -1 : st.peek();
            st.push(i);
        }
        return psee;
    }
}