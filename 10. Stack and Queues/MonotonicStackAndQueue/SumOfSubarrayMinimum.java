import java.util.Stack;

public class SumOfSubarrayMinimum {
    public static void main(String[] args) {
        System.out.println(sumSubarrayMins(new int[]{3, 1, 4, 2}));
    }

    // Time: O(n^2), Space: O(1)
    public static int sumSubarrayMinsBurte(int[] arr) {
        int total = 0, mod = 1000000007;
        for (int i = 0; i < arr.length; i++) {
            int mini = arr[i];
            for (int j = i; j < arr.length; j++) {
                mini = Math.min(mini, arr[j]);
                total = (total + mini) % mod;
            }
        }
        return total;
    }

    public static int sumSubarrayMins(int[] arr) {
        long total = 0L;
        int[] nse = findNSE(arr);
        int[] psee = findPSEE(arr);
        long mod = (long) (1e9 + 7);
        for (int i = 0; i < arr.length; i++) {
            long right = nse[i] - i;
            long left = i - psee[i];

            total = (total + (arr[i] * left * right) % mod) % mod;
        }
        return (int) total;
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
