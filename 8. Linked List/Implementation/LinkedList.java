public class LinkedList {
    // Method to convert an Array to A Linked List
    public static Node convertArrToLL(int[] arr) {
        Node head = new Node(arr[0]);
        Node mover = head;
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            mover.next = temp;
            mover = temp;
        }
        return head;
    }

    // Method to print the list
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    // Method to provide length of the list
    public static int lengthOfList(Node head) {
        Node temp = head;
        int cnt = 0;
        while (temp != null) {
            cnt++;
            temp = temp.next;
        }
        return cnt;
    }

    // Method to check if the element is present in LL or not
    public static int search(Node head, int key) {
        Node temp = head;
        int idx = -1;
        while (temp != null) {
            idx++;
            if (temp.data == key) return idx;
            temp = temp.next;
        }
        return -1;
    }

    // Method to insert a node at the end of the LL
    public static Node insertAtTail(Node head, int n) {
        Node mover = head;
        while (mover.next != null) { // Mover will be on the last element via this
            mover = mover.next;
        }
        mover.next = new Node(n);
        return head;
    }

    // Method to insert a node ar beginning
    public static Node insertAtHead(Node head, int n) {
        /*Node newNode = new Node(n);
        newNode.next = head;
        head = newNode;
        return head;*/
        // This can be also done directly. You are basically creating new Node with its next pointing to head.
        // Then you are returning new Node which will be than init to the head in main().
        return new Node(n, head);
    }

    public static Node insertAtIndex(Node head, int idx, int n) {
        if (idx == 0 || idx == lengthOfList(head)) insertAtHead(head, n);
        int cnt = 0;
        Node mover = head;
        Node newNode = new Node(n);
        while (cnt < idx - 1) { // This will end when mover reaches the element just before the idx i.e. idx-1
            mover = mover.next;
            cnt++;
        }
        newNode.next = mover.next;
        mover.next = newNode;
        return head;
    }

    public static Node insertBeforeValue(Node head, int value, int n) {
        if (head == null) return null;
        if (head.data == value) return new Node(n, head);
        Node temp = head;
        while (temp.next != null) {
            if (temp.next.data == value) {
                Node newNode = new Node(n, temp.next);
                temp.next = newNode;
                break;
            }
            temp = temp.next;
        }
        return head;
    }

    public static Node deleteHead(Node head) {
        head = head.next;
        return head;
    }

    public static Node deleteAtIndex(Node head, int idx) { // If the ques. states kth elem than rather than doing idx-1 just use k
        if (idx == 0 || idx == lengthOfList(head) - 1) return deleteHead(head);
        int cnt = 0;
        Node prev = head;
        while (cnt < idx - 1) { // This will end when prev reaches the element just before the idx i.e. idx-1
            prev = prev.next;
            cnt++;
        }
        // The element before the index will point to the element after the index.
        // So the element at the index will be removed from the chain.
        prev.next = prev.next.next;
        return head;
    }

    public static Node deleteTail(Node head) {
        if (head == null || head.next == null) return null;

        Node mover = head;
        while (mover.next.next != null) { // Here mover will reach till the second last element
            mover = mover.next;
        }
        mover.next = null;
        return head;
    }

    public static void deleteNode(Node node) {
        node.data = node.next.data;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6};
        Node head = convertArrToLL(arr);
        //System.out.println(lengthOfList(head));
        //System.out.println(search(head, 5));
        head = insertAtTail(head, 12);
        head = insertAtHead(head, -4);
        head = insertAtIndex(head, 3, 14);
        head = deleteHead(head);
        head = deleteTail(head);
        head = deleteAtIndex(head, 2);
        printList(head);
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
            this.next = null;
        }
    }
}