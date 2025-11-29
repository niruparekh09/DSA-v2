public class Reverse_LL {

    /*
     * Approach: Iterative (3 Pointers)
     * Pattern: In-place Reversal
     * Time Complexity: O(N) - Single pass.
     * Space Complexity: O(1) - Uses constant extra variables.
     */
    public static ListNode reverseListItr(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode current = head;
        ListNode prev = null;

        while (current != null) {
            ListNode front = current.next; // Step 1: Save the next node (don't lose the chain)
            current.next = prev;           // Step 2: Reverse the link (point backward)

            // Step 3: Move pointers one step forward
            prev = current;
            current = front;
        }
        // 'prev' ends up at the new Head (the original last node)
        return prev;
    }

    /*
     * Approach: Recursive
     * Pattern: Recursion / Backtracking
     * Time Complexity: O(N) - Visits every node.
     * Space Complexity: O(N) - Stack depth equal to list length.
     */
    public static ListNode reverseListRecursive(ListNode head) {
        // Base Case: Return the last node, which becomes the new Head.
        if (head == null || head.next == null) return head;

        // Recursive Step: Go until the end. 'newHead' carries the original tail back to the top.
        ListNode newHead = reverseListRecursive(head.next);

        // Logic (Post-Recursion): Reversal happens during backtracking.
        ListNode front = head.next; // 'front' is the node *after* current 'head'
        front.next = head;          // Make 'front' point BACK to 'head'
        head.next = null;           // Break the old forward link to prevent cycles

        return newHead;
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