public class Reverse_LL_2 {

    /*
     * Approach: One-Pass Iterative Reversal (Dummy Node)
     * Pattern: Linked List In-place Manipulation
     * Time Complexity: O(N) - Single pass to reach 'right' index.
     * Space Complexity: O(1) - Constant pointer manipulation.
     */
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // Edge Case: No need to reverse if indices are the same
        if (head == null || left == right) return head;

        // Dummy Node: Essential for handling the case where left == 1 (Head changes)
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 'prev' will eventually point to the node strictly BEFORE the sublist (left - 1)
        ListNode prev = dummy;

        // Step 1: Move 'prev' to position (left - 1)
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // 'curr' points to the start of the sub-list to be reversed.
        // Note: After reversal, 'curr' will become the TAIL of the sub-list.
        ListNode curr = prev.next;

        // Step 2: Reverse the sublist strictly between 'left' and 'right'
        // Logic: We don't swap values. We pluck the 'nextNode' and move it to the front (after prev).
        // We repeat this (right - left) times.
        for (int i = 0; i < right - left; i++) {
            ListNode nextNode = curr.next;

            curr.next = nextNode.next;  // 1. Detach nextNode from chain
            nextNode.next = prev.next;  // 2. Point nextNode to the current front of sublist
            prev.next = nextNode;       // 3. Move nextNode to the very front (after prev)
        }

        return dummy.next;
    }

    // Note: The helper methods provided in the snippet (findLeft/findRight) represent a
    // Multi-Pass approach (O(2N)), which is less optimal than the One-Pass solution above.

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