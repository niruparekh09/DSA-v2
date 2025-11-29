public class Rotate_List {

    /*
     * Approach: Form Ring & Break
     * Pattern: Linked List Traversal / Math
     * Time Complexity: O(N) - Traverse once for length, once to find pivot.
     * Space Complexity: O(1) - In-place pointer manipulation.
     */
    public static ListNode rotateRight(ListNode head, int k) {
        // Edge Case: Empty list, single node, or k=0
        if (head == null || head.next == null || k == 0) return head;

        // Step 1: Calculate length and find the original Tail
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }

        // Optimization: If k is a multiple of len, list state doesn't change.
        if (k % len == 0) return head;

        k = k % len; // Handle large k (e.g., rotating length 5 by 7 is same as by 2)

        // Step 2: Identify the NEW Tail.
        // Logic: Rotating right by K means the last K nodes move to the front.
        // The new tail is therefore at position (Len - K) from the start.
        ListNode newLastNode = findNewLastNode(head, len - k);

        // Step 3: Re-wire connections
        tail.next = head;           // Connect old tail to old head (Form a Ring)
        head = newLastNode.next;    // The node AFTER new tail becomes the New Head
        newLastNode.next = null;    // Break the ring at the new tail

        return head;
    }

    private static ListNode findNewLastNode(ListNode head, int i) {
        ListNode temp = head;
        while (temp != null) {
            i--;
            if (i == 0) break;
            temp = temp.next;
        }
        return temp;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}