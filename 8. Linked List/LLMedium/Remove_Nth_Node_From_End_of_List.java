public class Remove_Nth_Node_From_End_of_List {

    /*
     * Approach: Two Pointers (Gap Method)
     * Pattern: Fast & Slow Pointers / Sliding Window
     * Time Complexity: O(N) - Single pass (Fast moves N steps, Slow moves N-n steps).
     * Space Complexity: O(1) - In-place pointer manipulation.
     */
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;

        // Step 1: Move 'fast' pointer N steps ahead.
        // This creates a gap of N nodes between 'fast' and the start.
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // Edge Case: If fast reaches null, it means N equals the length of the list.
        // Therefore, we need to remove the Head node.
        if (fast == null) return head.next;

        // Step 2: Move both pointers until 'fast' reaches the last node.
        // When 'fast' is at the end, 'slow' will be exactly at the (Nth + 1) node from the end.
        // i.e., 'slow' is at the node immediately BEFORE the target node.
        ListNode slow = head;
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // Step 3: Delete the target node by skipping it.
        slow.next = slow.next.next;

        return head;
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