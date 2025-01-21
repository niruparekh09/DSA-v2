public class DoublyLinkedList {

    public static Node convertArrToDLL(int[] arr) {
        Node head = new Node(arr[0], null, null);
        Node prev = head;
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i], null, prev); // New node's prev will point to previous element
            prev.next = newNode; // Previous elem's next will point to new node
            prev = newNode;
        }
        return head;
    }

    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    public static Node deleteHead(Node head) {
        if (head == null || head.next == null) return null; // In case of no/one elem.

        Node prev = head;
        head = head.next; // This will set the second elem as head.
        head.prev = null; // The prev/back link will break.
        prev.next = null; // This will remove the next link from the original head.
        return head;
    }

    public static Node deleteTail(Node head) {
        if (head == null || head.next == null) return null; // In case of no/one elem.
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        Node secondLast = last.prev;
        secondLast.next = null;
        last.prev = null;
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6, 7};
        Node head = convertArrToDLL(arr);
        head = deleteHead(head);
        head = deleteTail(head);
        printList(head);
    }

    // Node class
    public static class Node {
        int data;
        Node next;
        Node prev; // can also be said back

        public Node(int data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }

        public Node(int data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }
}
