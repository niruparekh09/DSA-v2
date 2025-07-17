import java.util.Stack;

public class StockSpanner {
    Stack<pair> st = new Stack<pair>();
    int idx = -1;

    public StockSpanner() {
        st.clear();
        idx = -1;
    }

    public int next(int price) {
        int ans;
        idx += 1;
        while (!st.empty() && st.peek().val <= price) st.pop();
        ans = idx - (st.empty() ? -1 : st.peek().idx);
        st.push(new pair(price, idx));
        return ans;
    }
}

class pair {
    int val;
    int idx;

    public pair(int price, int idx) {
        this.val = price;
        this.idx = idx;
    }
}