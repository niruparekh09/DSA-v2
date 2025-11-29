public class Add_Two_Numbers {

    /*
     * Approach: Elementary Math with Dummy Node
     * Pattern: LinkedList / Math
     * Time Complexity: O(max(N, M)) - Iterates through the longer of the two lists.
     * Space Complexity: O(max(N, M)) - New list created for result (excluding input).
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode t1 = l1;
        ListNode t2 = l2;
        // Dummy Node: Simplifies code by avoiding separate logic for the 'head' of the result list.
        ListNode dummyNode = new ListNode(-1);
        ListNode curr = dummyNode;
        int carry = 0;

        // Logic: Loop runs while *either* list has elements.
        // Uneven lengths are handled by treating null nodes as having value 0.
        while (t1 != null || t2 != null) {
            int sum = carry;

            // Add values if nodes exist
            if (t1 != null) sum += t1.val;
            if (t2 != null) sum += t2.val;

            // Math: 'sum % 10' gives the digit to store, 'sum / 10' gives the carry for next position.
            ListNode newNode = new ListNode(sum % 10);
            carry = sum / 10;

            curr.next = newNode;
            curr = curr.next;

            // Move input pointers forward safely
            if (t1 != null) t1 = t1.next;
            if (t2 != null) t2 = t2.next;
        }

        // Edge Case: If carry remains after processing all nodes (e.g., 50 + 50 = 100), add a new node.
        if (carry != 0) {
            curr.next = new ListNode(carry);
        }

        // Return dummyNode.next, which is the actual head of the result list.
        return dummyNode.next;
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