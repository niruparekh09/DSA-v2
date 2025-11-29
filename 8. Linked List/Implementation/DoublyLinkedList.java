/*
 * Approach: Doubly Linked List Implementation
 * Pattern: Pointer Manipulation (Prev & Next)
 * Time Complexity:
 * - Head operations: O(1)
 * - Tail/Index operations: O(N) (Traverse needed)
 * - Reversal: O(N)
 * Space Complexity: O(1) - Operations performed in-place.
 */
public class DoublyLinkedList {

    // Time Complexity: O(N) | Space Complexity: O(1)
    public static Node convertArrToDLL(int[] arr) {
        Node head = new Node(arr[0], null, null);
        Node prev = head;
        // Logic: Maintain 'prev' pointer to link back to the previous node.
        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i], null, prev);
            prev.next = newNode;
            prev = newNode;
        }
        return head;
    }

    // Time Complexity: O(N)
    public static void printList(Node head) {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    // Time Complexity: O(N)
    public static int lengthOfList(Node head) {
        int cnt = 1;
        Node temp = head;
        // Logic: Iterates until temp.next is null to count nodes.
        // Note: Assumes head is not null (cnt starts at 1).
        while (temp.next != null) {
            cnt++;
            temp = temp.next;
        }
        return cnt;
    }

    // Time Complexity: O(1)
    public static Node deleteHead(Node head) {
        if (head == null || head.next == null) return null;

        Node prev = head;
        head = head.next;

        // Logic: Break the backward link of the NEW head and forward link of OLD head.
        head.prev = null;
        prev.next = null;
        return head;
    }

    // Time Complexity: O(N) - Must traverse to find the tail.
    public static Node deleteTail(Node head) {
        if (head == null || head.next == null) return null;

        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        // Logic: Go to the second-to-last node and nullify its 'next' pointer.
        Node secondLast = last.prev;
        secondLast.next = null;
        last.prev = null; // Clean up the old tail.
        return head;
    }

    // Time Complexity: O(N) - Worst case traversal to K.
    public static Node deleteKth(Node head, int k) {
        Node temp = head;
        int cnt = 0;
        // Logic: Traverse to the Kth node.
        while (temp != null) {
            cnt++;
            if (cnt == k) break;
            temp = temp.next;
        }

        Node prevRef = temp.prev;
        Node nextRef = temp.next;

        // Edge Case: Single element list (both prev and next are null).
        if (prevRef == null && nextRef == null) return null;

            // Edge Case: Deleting the Head (prev is null).
        else if (prevRef == null) {
            if (head.next == null) return null;
            Node prev = head;
            head = head.next;
            head.prev = null;
            prev.next = null;
            return head;
        }
        // Edge Case: Deleting the Tail (next is null).
        else if (nextRef == null) {
            prevRef.next = null;
            temp.prev = null;
            return head;
        }

        // Logic: Internal node deletion - stitch prevRef and nextRef together.
        prevRef.next = nextRef;
        nextRef.prev = prevRef;
        // Clean up deleted node references.
        prevRef = null;
        nextRef = null;
        return head;
    }

    // Time Complexity: O(1)
    public static void deleteNode(Node node) {
        if (node == null) return;
        Node prevRef = node.prev;
        Node nextRef = node.next;

        // Edge Case: Tail node.
        if (nextRef == null) {
            prevRef.next = null;
            node.prev = null;
            return;
        }

        // Logic: Link the previous node directly to the next node.
        prevRef.next = nextRef;
        nextRef.prev = prevRef;
        // Clean up.
        prevRef = null;
        nextRef = null;
    }

    // Time Complexity: O(1)
    public static Node insertBeforeHead(Node head, int n) {
        Node newNode = new Node(n, head, null);
        // Logic: Update old head's 'prev' to point to new node.
        head.prev = newNode;
        return newNode;
    }

    // Time Complexity: O(N)
    public static Node insertBeforeTail(Node head, int n) {
        if (head.next == null) return insertBeforeHead(head, n);

        Node last = head;
        while (last.next != null) {
            last = last.next;
        }

        // Logic: Insert 'newNode' between 'last.prev' (secondLast) and 'last'.
        Node secondLast = last.prev;
        Node newNode = new Node(n, last, secondLast);
        last.prev = newNode;
        secondLast.next = newNode;
        return head;
    }

    // Time Complexity: O(N)
    public static Node insertAtKth(Node head, int k, int n) {
        if (k == 1) return insertBeforeHead(head, n);
        // Recalculate length is expensive here (O(N)), making this O(2N) effectively.
        if (k == lengthOfList(head)) return insertBeforeTail(head, n);

        Node temp = head;
        int cnt = 0;
        while (temp != null) {
            cnt++;
            if (cnt == k) break;
            temp = temp.next;
        }

        // Logic: Insert 'newNode' between 'temp' (current Kth) and 'prevEl' (K-1).
        Node prevEl = temp.prev;
        Node newNode = new Node(n, temp, prevEl);
        temp.prev = newNode;
        prevEl.next = newNode;
        return head;
    }

    /*
     * Approach: Reversing DLL (In-Place)
     * Logic: Swap .prev and .next pointers for every node.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static Node reverseDLL(Node head) {
        if (head == null || head.next == null) return head;

        Node current = head;
        Node last = null;

        while (current != null) {
            // Logic: Swap prev and next pointers.
            last = current.prev;
            current.prev = current.next;
            current.next = last;

            // Logic: Move to the "next" node.
            // Since we swapped pointers, 'current.prev' is actually the *old* next.
            current = current.prev;
        }

        // Edge Case: 'last' ends up at the *second* node of the new list.
        // The new head is last.prev (which was the old tail).
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

    public static class Node {
        int data;
        Node next, prev;

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