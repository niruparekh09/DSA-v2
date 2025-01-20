public class LinkedList {
    // Method to convert an Array to A Linked List
    public static Node ConvertArrToLL(int[] arr) {
        Node head = new Node(arr[0]);
        Node mover = head;
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            mover.next = temp;
            mover = temp;
        }
        return head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6};
        Node head = ConvertArrToLL(arr);
    }

    // Node class
    public static class Node {
        int data;
        Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public Node(int data) {
            this.data = data;
        }
    }
}