public class Odd_Even_LL {

    /*
     * Approach: Two Pointers (Leapfrog)
     * Pattern: LinkedList In-place Manipulation
     * Time Complexity: O(N) - Single pass through the list.
     * Space Complexity: O(1) - No extra data structures used; modified pointers directly.
     */
    public static ListNode oddEvenList(ListNode head) {
        // Edge Case: Empty list or single node requires no reordering.
        if (head == null || head.next == null) return head;

        ListNode odd = head;
        ListNode even = head.next;

        // Key: Save the head of the even chain to attach it to the odd tail later.
        ListNode evenHead = head.next;

        // Loop Condition: Check 'even' and 'even.next' because 'even' is always ahead.
        // This handles both even and odd length lists safely.
        while (even != null && even.next != null) {
            // Logic: "Leapfrog" over the next node to connect to the subsequent one.
            // odd connects to next odd; even connects to next even.
            odd.next = odd.next.next;
            even.next = even.next.next;

            // Advance pointers to the newly connected nodes
            odd = odd.next;
            even = even.next;
        }

        // Final Step: Connect the Tail of the Odd list to the Head of the Even list.
        odd.next = evenHead;

        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}