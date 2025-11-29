public class Delete_Middle_Node_Of_LL {

    /*
     * Approach: Tortoise and Hare Algorithm
     * Pattern: Fast & Slow Pointers
     * Time Complexity: O(N) - Specifically O(N/2), as we traverse to the middle in one pass.
     * Space Complexity: O(1) - In-place modification.
     */
    public ListNode deleteMiddle(ListNode head) {
        // Edge Case: If list has 0 or 1 node, deleting the middle results in an empty list.
        if (head == null || head.next == null) return null;

        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;

        // Logic: Fast moves 2 steps, Slow moves 1 step.
        // When Fast reaches the end, Slow will be exactly at the Middle node.
        while (fast != null && fast.next != null) {
            prev = slow;       // Track the node strictly BEFORE the middle
            slow = slow.next;
            fast = fast.next.next;
        }

        // Key Logic: 'prev' is the node before middle. 'slow' is the middle.
        // Skip 'slow' to delete it.
        prev.next = slow.next;

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}