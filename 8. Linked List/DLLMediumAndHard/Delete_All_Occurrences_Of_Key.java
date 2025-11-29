public class Delete_All_Occurrences_Of_Key {

    /*
     * Approach: Linear Traversal (In-place Deletion)
     * Pattern: Doubly Linked List Pointer Manipulation
     * Time Complexity: O(N) - Single pass through the list.
     * Space Complexity: O(1) - Modifying pointers directly.
     */
    public static Node deleteAllOccurOfX(Node head, int x) {
        Node temp = head;
        while (temp != null) {
            // Found a node to delete
            if (temp.data == x) {
                // Edge Case: Deleting the Head node
                if (temp == head) {
                    head = head.next;
                }

                Node nextNode = temp.next;
                Node prevNode = temp.prev;

                // Logic: Stitch the neighbors together to bypass 'temp'.
                // 1. If not Tail: Link next node BACK to prev.
                if (nextNode != null) nextNode.prev = prevNode;

                // 2. If not Head (or updated Head): Link prev node FORWARD to next.
                if (prevNode != null) prevNode.next = nextNode;

                // Critical: Move temp to nextNode because 'temp' is now removed/garbage.
                temp = nextNode;
            } else {
                // Standard traversal if no deletion occurred
                temp = temp.next;
            }
        }
        return head;
    }

    public static class Node {
        int data;
        Node next;
        Node prev;

        Node(int data) {
            this.data = data;
            next = prev = null;
        }
    }
}