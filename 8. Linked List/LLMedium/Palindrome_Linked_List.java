public class Palindrome_Linked_List {

    /*
     * Approach: Find Middle & Reverse Second Half
     * Pattern: Fast & Slow Pointers / In-place Reversal
     * Time Complexity: O(N) - Find Mid (N/2) + Reverse (N/2) + Compare (N/2) + Restore (N/2).
     * Space Complexity: O(1) - Manipulates pointers in-place.
     */
    public boolean isPalindrome(ListNode head) {
        // Edge Case: Empty or single-node lists are palindromes
        if (head == null || head.next == null) return true;

        // Step 1: Find the middle using Tortoise & Hare
        ListNode slow = head;
        ListNode fast = head;
        // Logic: Stop when fast reaches end. 'slow' will point to the middle node (or end of first half).
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse the second half of the list (starting from slow.next)
        // 'slow.next' becomes the head of the reversed right half.
        ListNode reverseNodeHead = reverse(slow.next);

        // Step 3: Compare First Half vs Reversed Second Half
        ListNode first = head;
        ListNode second = reverseNodeHead;

        while (second != null) {
            if (first.val != second.val) {
                // Good Practice: Restore the list structure before returning failure
                reverse(reverseNodeHead);
                return false;
            }
            first = first.next;
            second = second.next;
        }

        // Step 4: Restore the original list structure (Reverse the second half back)
        reverse(reverseNodeHead);
        return true;
    }

    // Helper: Standard Iterative Reversal
    public ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode current = head;
        ListNode prev = null;
        while (current != null) {
            ListNode front = current.next;
            current.next = prev;
            prev = current;
            current = front;
        }
        return prev;
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