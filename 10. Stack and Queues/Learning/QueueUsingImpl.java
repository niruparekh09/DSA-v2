public class QueueUsingImpl {
    public static void main(String[] args) {
        QueueImpl q = new QueueImpl();
        q.push(5);
        q.push(4);
        q.push(7);
        System.out.println(q.peek());
        q.pop();
        q.pop();
        System.out.println(q.peek());
        System.out.println(q.getSize());
    }
}

class QueueImpl {
    private final int size = 5;
    private final int[] arr = new int[size];
    private int start = -1, end = -1;
    private int currSize = 0;

    void push(int x) {
        if (currSize == size) return;
        if (start == -1 && end == -1) {
            start++;
            end++;
            arr[start] = x;
            return;
        }
        end = (end + 1) % size;
        arr[end] = x;
        currSize++;
    }

    int pop() {
        int el = arr[start];
        if (currSize == 0) return -1;
        if (currSize == -1) {
            start = -1;
            end = -1;
        } else {
            start = (start + 1) % size;
        }
        currSize--;
        return el;
    }

    int peek() {
        if (start == -1) return -1;
        return arr[start];
    }

    int getSize() {
        return currSize;
    }
}
