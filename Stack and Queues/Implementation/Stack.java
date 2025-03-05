public class Stack {
    public static void main(String[] args) {
        StackImpl st = new StackImpl();

        st.push(1);
        st.push(3);
        st.push(5);
        System.out.println("Top of Stack " + st.top());
        System.out.println("Size of Stack " + st.size());
        System.out.println("Popping an element " + st.pop());
        System.out.println("Size of Stack " + st.size());
    }
}

class StackImpl {
    int size = 10000;
    int[] arr = new int[size];
    int top = -1;

    void push(int x) {
        top++;
        arr[top] = x;
    }

    int pop() {
        if (top == -1) {
            throw new RuntimeException("Stack is empty");
        }
        int x = arr[top];
        top--;
        return x;
    }

    int top() {
        return arr[top];
    }

    int size() {
        return top + 1;
    }
}