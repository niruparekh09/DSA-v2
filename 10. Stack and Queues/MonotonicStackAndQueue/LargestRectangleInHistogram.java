import java.util.Arrays;
import java.util.Stack;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        System.out.println(largestRectangleAreaOptimal(new int[]{2, 1, 5, 6, 2, 3}));
    }

    // We need to find out PSE and NSE for a given element.
    // Max Area  = Max(arr[i] * (NSE[i] - PSE[i] - 1))
    public static int largestRectangleArea(int[] heights) {
        int[] nse = findNSE(heights), pse = findPSE(heights);
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            int exp = heights[i] * (nse[i] - pse[i] - 1);
            ans = Math.max(ans, exp);
        }
        return ans;
    }

    public static int[] findNSE(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
            ans[i] = st.isEmpty() ? arr.length : st.peek();
            st.push(i);
        }

        System.out.println(Arrays.toString(ans));
        return ans;
    }

    public static int[] findPSE(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) st.pop();
            ans[i] = st.isEmpty() ? -1 : st.peek();
            st.push(i);
        }

        System.out.println(Arrays.toString(ans));
        return ans;
    }

    public static int largestRectangleAreaOptimal(int[] heights) {
        int ans = 0;

        
        return ans;
    }
}
