import java.util.Stack;

public class Min_Stack {
    public static void main(String[] args) {

    }
}

class MinStack_1 {

    Stack<Pair> st;

    public MinStack_1() {
        st = new Stack<>();
    }

    public void push(int val) {
        if (st.isEmpty()) {
            st.push(new Pair(val, val));
        } else {
            st.push(new Pair(val, Math.min(st.peek().min, val)));
        }
    }

    public void pop() {
        st.pop();
    }

    public int top() {
        return st.peek().val;
    }

    public int getMin() {
        return st.peek().min;
    }

    private class Pair {
        int val;
        int min;

        public Pair(int val, int min) {
            this.val = val;
            this.min = min;
        }
    }
}

class MinStack_2 {

    Stack<Long> st = new Stack<>();
    Long mini;

    public MinStack_2() {
        mini = Long.MAX_VALUE;
    }

    public void push(int val) {
        Long value = Long.valueOf(val);
        if (st.isEmpty()) {
            mini = value;
            st.push(value);
        } else {
            if (mini > value) {
                mini = value;
                st.push(2 * value - mini);
            } else {
                st.push(value);
            }
        }
    }

    public void pop() {
        if (st.isEmpty()) return;

        Long val = st.pop();
        if (val < mini) {
            mini = 2 * mini - val;
        }
    }

    public int top() {
        Long val = st.peek();
        if (val < mini) {
            return mini.intValue();
        }
        return val.intValue();
    }

    public int getMin() {
        return mini.intValue();
    }
}

class MinStack_3 {

    private StackNode top;

    public MinStack_3() {
        this.top = null;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void push(int val) {
        StackNode node = new StackNode(val);
        if (top != null) {
            node.minTillNow = Math.min(top.minTillNow, val);
        }
        node.next = top;
        top = node;
    }

    public void pop() {
        top = top.next;
    }

    public int top() {
        if (isEmpty()) return -1;
        return top.data;
    }

    public int getMin() {
        if (isEmpty()) return Integer.MIN_VALUE;
        return top.minTillNow;
    }

    private class StackNode {
        private int data;
        private int minTillNow;
        private StackNode next;

        StackNode(int data) {
            this.data = data;
            this.minTillNow = data;
            next = null;
        }
    }
}