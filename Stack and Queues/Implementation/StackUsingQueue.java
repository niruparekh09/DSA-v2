import java.util.LinkedList;
import java.util.Queue;

public class StackUsingQueue {
    public static void main(String[] args) {

    }
}

// push: O(n) and rest: O(1)
class MyStack {

    Queue<Integer> q = new LinkedList<>();

    public MyStack() {

    }

    public void push(int x) {
        q.add(x);
        for (int i = 0; i < q.size() - 1; i++) {
            q.add(q.remove());
        }
    }

    public int pop() {
        return q.remove();
    }

    public int top() {
        if (q.isEmpty()) throw new RuntimeException("");
        return q.peek();
    }

    public boolean empty() {
        return q.isEmpty();
    }
}