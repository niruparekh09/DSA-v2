/*
 * Approach: Singly Linked List Implementation
 * Pattern: Pointer Manipulation / Linear Traversal
 * Time Complexity:
 *   - Insert/Delete at Head: O(1)
 *   - Access/Search/Insert/Delete at Index or Tail: O(N)
 * Space Complexity: O(1) - Operations are done in-place (excluding new node memory).
 */
public class LinkedList {

    // Method to convert an Array to A Linked List
    // Time Complexity: O(N) | Space Complexity: O(1)
    public static Node convertArrToLL(int[] arr) {
        Node head = new Node(arr[0]);
        Node mover = head;
        // Iterate starting from 1 since head is already created
        for (int i = 1; i < arr.length; i++) {
            Node temp = new Node(arr[i]);
            mover.next = temp; // Link current node to new node
            mover = temp;      // Move pointer forward
        }
        return head;
    }

    // Time Complexity: O(N)
    public static void printList(Node head) {
        Node temp = head;
        // Traverse until we fall off the end (temp becomes null)
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
    }

    // Time Complexity: O(N)
    public static int lengthOfList(Node head) {
        Node temp = head;
        int cnt = 0;
        while (temp != null) {
            cnt++;
            temp = temp.next;
        }
        return cnt;
    }

    // Time Complexity: O(N)
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

    // Time Complexity: O(N) - Must traverse to the end
    public static Node insertAtTail(Node head, int n) {
        // Edge Case: If list is empty, new node becomes head
        if (head == null) return new Node(n);

        Node mover = head;
        // Traverse until the LAST node (where next is null)
        while (mover.next != null) {
            mover = mover.next;
        }
        mover.next = new Node(n);
        return head;
    }

    // Time Complexity: O(1)
    public static Node insertAtHead(Node head, int n) {
        // Create new node pointing to current head, then return new node as the new head
        return new Node(n, head);
    }

    // Time Complexity: O(N)
    public static Node insertAtIndex(Node head, int idx, int n) {
        // Edge Case: Insert at start
        if (idx == 0) return insertAtHead(head, n);

        int cnt = 0;
        Node mover = head;
        Node newNode = new Node(n);

        // Logic: Stop at the node BEFORE the target index (idx - 1)
        while (mover != null && cnt < idx - 1) {
            mover = mover.next;
            cnt++;
        }

        // Edge Case: If idx is out of bounds (mover is null), handle gracefully (not shown here but implied)
        if (mover != null) {
            newNode.next = mover.next; // Connect new node to the chain
            mover.next = newNode;      // Connect previous node to new node
        }
        return head;
    }

    // Time Complexity: O(N)
    public static Node insertBeforeValue(Node head, int value, int n) {
        if (head == null) return null;

        // Edge Case: Target value is at the head
        if (head.data == value) return new Node(n, head);

        Node temp = head;
        // Logic: Look ahead at temp.next to find the value.
        // We need to stop at the node BEFORE the target.
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

    // Time Complexity: O(1)
    public static Node deleteHead(Node head) {
        if (head == null) return null;
        // Move head forward; Java Garbage Collector removes the old head
        head = head.next;
        return head;
    }

    public static Node deleteAtIndex(Node head, int idx) {
        if (head == null) return null;
        // Edge Case: Deleting the first element
        if (idx == 0) return deleteHead(head);

        int cnt = 0;
        Node prev = head;
        // Logic: Traverse to the node strictly BEFORE the target index
        while (prev != null && cnt < idx - 1) {
            prev = prev.next;
            cnt++;
        }

        // Validation: Ensure prev and the node to delete exist
        if (prev != null && prev.next != null) {
            // Unlink the node at 'idx' by skipping it
            prev.next = prev.next.next;
        }
        return head;
    }

    // Time Complexity: O(N)
    public static Node deleteTail(Node head) {
        // Edge Case: Empty list or Single node list
        if (head == null || head.next == null) return null;

        Node mover = head;
        // Logic: Stop at the SECOND to last node (mover.next.next == null)
        while (mover.next.next != null) {
            mover = mover.next;
        }
        mover.next = null; // Remove the link to the last node
        return head;
    }

    /*
     * Approach: Copy Data Trick (O(1) deletion)
     * Limitations: Cannot be used to delete the Tail node (since there is no next node to copy).
     */
    public static void deleteNode(Node node) {
        // Logic: Instead of removing the node object itself (which requires prev pointer),
        // we copy the data from the NEXT node and delete the NEXT node.
        node.data = node.next.data;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 4, 5, 6};
        Node head = convertArrToLL(arr);
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