public class StackUsingLL {
    public static void main(String[] args) {
        StackClass st = new StackClass();
        st.push(5);
        st.push(7);
        st.push(12);
        System.out.println("Size: " + st.getSize() + " ---- Peek: " + st.peek());
        st.pop();
        System.out.println(st.pop());
        System.out.println(st.getSize());
    }
}

class StackClass {
    private StackNode top;
    private int size;

    StackClass() {
        this.top = null;
        this.size = 0;
    }

    public void push(int x) {
        StackNode newNode = new StackNode(x);
        newNode.next = top;
        top = newNode;
        size++;
    }

    public int pop() {
        if (top == null) throw new RuntimeException("Stack is empty");
        int topData = top.data;
        top = top.next;
        size--;
        return topData;
    }

    public int peek() {
        if (top == null) throw new RuntimeException("Stack is empty");
        else return top.data;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public void printStack() {
        StackNode temp = top;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    private static class StackNode {
        int data;
        StackNode next;

        public StackNode(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
