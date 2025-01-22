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

    public static int lengthOfList(Node head) {
        int cnt = 1;
        Node temp = head;
        while (temp.next != null) {
            cnt++;
            temp = temp.next;
        }
        return cnt;
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

    public static Node deleteKth(Node head, int k) {
        Node temp = head;
        int cnt = 0;
        while (temp != null) {
            cnt++;
            if (cnt == k) break;
            temp = temp.next;
        }

        Node prevRef = temp.prev;
        Node nextRef = temp.next;

        //if (prevRef == null) return deleteHead(head); // It's first element
        //if (nextRef == null) return deleteTail(head); // It's last element

        if (prevRef == null && nextRef == null) return null;

            // In case of First Element
        else if (prevRef == null) {
            if (head.next == null) return null;

            Node prev = head;
            head = head.next;
            head.prev = null;
            prev.next = null;
            return head;
        }
        // In case of Last Element
        else if (nextRef == null) {
            prevRef.next = null;
            temp.prev = null;
            return head;
        }

        prevRef.next = nextRef;
        nextRef.prev = prevRef;
        prevRef = null;
        nextRef = null;
        return head;
    }

    public static void deleteNode(Node node) {
        if (node == null) return;
        Node prevRef = node.prev;
        Node nextRef = node.next;
        if (nextRef == null) { // It is the tail element
            prevRef.next = null;
            node.prev = null;
            return;
        }

        prevRef.next = nextRef;
        nextRef.prev = prevRef;
        prevRef = null;
        nextRef = null;
    }

    public static Node insertBeforeHead(Node head, int n) {
        Node newNode = new Node(n, head, null);
        head.prev = newNode;
        //newNode.next = head;
        return newNode; // It'll be new head
    }

    public static Node insertBeforeTail(Node head, int n) {
        if (head.next == null) return insertBeforeHead(head, n); // In case of single node
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        Node secondLast = last.prev;
        Node newNode = new Node(n, last, secondLast);
        last.prev = newNode;
        secondLast.next = newNode;
        return head;
    }

    public static Node insertAtKth(Node head, int k, int n) {
        if (k == 1) return insertBeforeHead(head, n);
        if (k == lengthOfList(head)) return insertBeforeTail(head, n);
        Node temp = head;
        int cnt = 0;
        while (temp != null) {
            cnt++;
            if (cnt == k) break;
            temp = temp.next;
        }
        Node prevEl = temp.prev; // previous to the kth element
        Node newNode = new Node(n, temp, prevEl);
        temp.prev = newNode;
        prevEl.next = newNode;
        return head;
    }

    public static Node reverseDLL(Node head) {
        if (head == null || head.next == null) {
            // No change is needed;
            // return the current head
            return head;
        }
        Node current = head; // Current node
        Node last = null;
        while (current != null) {
            last = current.prev;
            current.prev = current.next;
            current.next = last;
            current = current.prev;
        }

        // Here last will point the second last node of old DLL i.e. the 2nd node of reversed DLL, so we will return
        // last.prev which will be pointing to the new head.
        return last.prev;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6, 7};
        Node head = convertArrToDLL(arr);
        head = deleteHead(head);
        head = deleteTail(head);
        head = deleteKth(head, 3);
        deleteNode(head.next.next);
        head = insertBeforeHead(head, 9);
        head = insertBeforeTail(head, 12);
        head = insertAtKth(head, 3, 69);
        printList(head);
        System.out.println("\nReversed:");
        head = reverseDLL(head);
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
