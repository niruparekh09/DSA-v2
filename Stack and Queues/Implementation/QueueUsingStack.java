import java.util.Stack;

public class QueueUsingStack {
    public static void main(String[] args) {

    }
}

// push: O(n) and rest: O(1)
class MyQueue1 {

    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();

    public MyQueue1() {

    }

    public void push(int x) {
        while (!input.isEmpty()) {
            output.push(input.peek());
            input.pop();
        }

        input.push(x);
        while (!output.isEmpty()) {
            input.push(output.peek());
            output.pop();
        }
    }

    public int pop() {
        return input.pop();
    }

    public int peek() {
        return input.peek();
    }

    public boolean empty() {
        return input.isEmpty();
    }
}


// push and size: O(1) and rest: O(n) -----> O(1)
class MyQueue2 {

    Stack<Integer> input = new Stack<>();
    Stack<Integer> output = new Stack<>();

    public MyQueue2() {

    }

    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        if (output.empty())
            while (!input.empty()) output.push(input.pop());
        return output.pop();
    }

    public int peek() {
        if (output.empty())
            while (!input.empty()) output.push(input.pop());
        return output.peek();
    }

    public boolean empty() {
        return input.isEmpty() && output.isEmpty();
    }
}