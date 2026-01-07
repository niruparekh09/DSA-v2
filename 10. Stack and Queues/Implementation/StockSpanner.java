import java.util.Stack;

/*
 * Approach: Monotonic Decreasing Stack
 * Pattern: Monotonic Stack (Previous Greater Element)
 * Time Complexity: O(1) Amortized - Each element is pushed once and popped at most once across all calls.
 * Space Complexity: O(N) - In worst case (strictly decreasing prices), stack holds all elements.
 */
public class StockSpanner {
    Stack<pair> st = new Stack<>();
    int idx = -1; // Tracks global day count to calculate index distances

    public StockSpanner() {
        st.clear();
        idx = -1;
    }

    public int next(int price) {
        int ans;
        idx += 1;

        // Key Logic: Pop elements smaller than or equal to current price.
        // Why? Because they are "shadowed" by the current price and can never be
        // the "Previous Greater Element" for any future day.
        // This maintains a Monotonic Decreasing Stack.
        while (!st.empty() && st.peek().val <= price) st.pop();

        // Logic: Span = Current Index - Index of Previous Greater Element.
        // If stack is empty, it means current price is greater than ALL previous prices.
        ans = idx - (st.empty() ? -1 : st.peek().idx);

        // Push current price to act as a potential "barrier" for future prices.
        st.push(new pair(price, idx));

        return ans;
    }

    private class pair {
        int val;
        int idx;

        public pair(int price, int idx) {
            this.val = price;
            this.idx = idx;
        }
    }
}