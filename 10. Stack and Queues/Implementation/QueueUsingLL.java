public class QueueUsingLL {
    public static void main(String[] args) {
        QueueClass q = new QueueClass();
        q.enqueue(5);
        q.enqueue(4);
        q.enqueue(9);
        q.enqueue(7);
        System.out.println(q.getSize());
        System.out.println(q.peek());
        q.dequeue();
        System.out.println(q.dequeue());
        System.out.println(q.getSize());
    }
}

class QueueClass {
    private QueueNode front = null;
    private QueueNode rear = null;
    private int size = 0;

    public boolean isEmpty() {
        return front == null;
    }

    public int peek() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        return front.data;
    }

    public void enqueue(int n) {
        QueueNode newNode = new QueueNode(n);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
    }

    public int dequeue() {
        if (isEmpty()) throw new RuntimeException("Queue is empty");
        int el = front.data;
        front = front.next;
        if (front == null) { // If the queue is now empty, update rear as well
            rear = null;
        }
        size--;
        return el;
    }

    public int getSize() {
        return size;
    }

    private static class QueueNode {
        int data;
        QueueNode next;

        public QueueNode(int data) {
            this.data = data;
            this.next = null;
        }
    }
}
