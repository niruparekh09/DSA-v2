public class Sort_LL {

    public static ListNode sortList(ListNode head) {
        // Base case: If the list is empty or has only one node, it's already sorted.
        if (head == null || head.next == null) return head;

        // Find the middle node to split the list into two halves.
        ListNode middle = findMiddle(head);

        // Split the list into left and right halves.
        ListNode leftHead = head, rightHead = middle.next;
        middle.next = null; // Break the list into two parts.

        // Recursively sort both halves.
        leftHead = sortList(leftHead);
        rightHead = sortList(rightHead);

        // Merge the sorted halves and return the result.
        return mergeList(leftHead, rightHead);
    }

    private static ListNode mergeList(ListNode leftHead, ListNode rightHead) {
        // Dummy node to act as the starting point for the merged list.
        ListNode dummyNode = new ListNode(-1);
        ListNode t1 = leftHead, t2 = rightHead;
        ListNode res = dummyNode; // Pointer for constructing the merged list.

        // Merge the two lists by comparing node values.
        while (t1 != null && t2 != null) {
            if (t1.val < t2.val) {
                res.next = t1; // Add the smaller node to the result.
                res = t1;
                t1 = t1.next;
            } else {
                res.next = t2; // Add the smaller node to the result.
                res = t2;
                t2 = t2.next;
            }
        }

        // Append any remaining nodes from either list.
        if (t1 != null) res.next = t1;
        else res.next = t2;

        return dummyNode.next; // Return the head of the merged list.
    }

    private static ListNode findMiddle(ListNode head) {
        // Use two pointers (slow and fast) to find the middle of the list.
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;      // Move slow pointer one step.
            fast = fast.next.next; // Move fast pointer two steps.
        }
        return slow; // Slow pointer will point to the middle node.
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
