public class Delete_Middle_Node_Of_LL {
    // Tc: O(N/2) SC: O(1)
    public ListNode deleteMiddle(ListNode head) {
        if (head == null || head.next == null) return null; // Delete single Node
        ListNode slow = head;
        ListNode fast = head;
        ListNode prev = null;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
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
