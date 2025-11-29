public class Sort_Zero_One_And_Two_In_LL {

    /*
     * Approach: 3 Dummy Pointers (Partitioning)
     * Pattern: Linked List In-place Manipulation
     * Time Complexity: O(N) - Single pass to segregate nodes.
     * Space Complexity: O(1) - Reuses existing nodes (just changing links), no new memory allocation.
     */
    static Node segregate(Node head) {
        Node temp = head;

        // Create dummy sentinels to act as heads for the three buckets.
        // This avoids null checks when initializing the lists.
        Node zeroHead = new Node(-1);
        Node oneHead = new Node(-1);
        Node twoHead = new Node(-1);

        // Pointers to track the current tail of each sub-list
        Node zero = zeroHead;
        Node one = oneHead;
        Node two = twoHead;

        while (temp != null) {
            // Logic: Partition the list by rewiring existing nodes to appropriate buckets.
            if (temp.data == 0) {
                zero.next = temp;
                zero = temp;
            } else if (temp.data == 1) {
                one.next = temp;
                one = temp;
            } else {
                two.next = temp;
                two = temp;
            }
            temp = temp.next;
        }

        // Merge Logic: Connect Zero Tail -> One Head -> Two Head.
        // Edge Case: If 'One' list is empty, connect Zero Tail directly to 'Two' Head.
        zero.next = oneHead.next != null ? oneHead.next : twoHead.next;

        // Connect One Tail to Two Head
        one.next = twoHead.next;

        // Critical Step: Ensure the list terminates.
        // The last node of 'two' might still point to a node from the original list, causing a cycle.
        two.next = null;

        return zeroHead.next;
    }

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            next = null;
        }
    }
}