public class Sort_LL {

    /*
     * Approach: Merge Sort
     * Pattern: Divide & Conquer / Recursion
     * Time Complexity: O(N log N) - List is split log N times, and merging takes linear time.
     * Space Complexity: O(log N) - Recursion stack space.
     */
    public static ListNode sortList(ListNode head) {
        // Base case: A list with 0 or 1 node is already sorted.
        if (head == null || head.next == null) return head;

        // Step 1: Find the middle to divide the list into two halves.
        ListNode middle = findMiddle(head);

        // Step 2: Split the list physically.
        ListNode leftHead = head;
        ListNode rightHead = middle.next;
        middle.next = null; // Critical: Break the link to separate the two lists.

        // Step 3: Recursive calls to sort both halves.
        leftHead = sortList(leftHead);
        rightHead = sortList(rightHead);

        // Step 4: Merge the sorted halves.
        return mergeList(leftHead, rightHead);
    }

    // Helper: Standard Two-Pointer Merge Logic
    private static ListNode mergeList(ListNode leftHead, ListNode rightHead) {
        ListNode dummyNode = new ListNode(-1);
        ListNode t1 = leftHead, t2 = rightHead;
        ListNode res = dummyNode;

        while (t1 != null && t2 != null) {
            if (t1.val < t2.val) {
                res.next = t1;
                res = t1;
                t1 = t1.next;
            } else {
                res.next = t2;
                res = t2;
                t2 = t2.next;
            }
        }

        // Append remaining nodes (no loop needed as they are already sorted)
        if (t1 != null) res.next = t1;
        else res.next = t2;

        return dummyNode.next;
    }

    // Helper: Fast & Slow Pointers to find the "Left Middle"
    private static ListNode findMiddle(ListNode head) {
        ListNode slow = head, fast = head;

        // Logic: The condition (fast.next.next != null) ensures that for Even length lists,
        // 'slow' stops at the 1st middle node (e.g., 1->2->3->4, stops at 2).
        // This is required so we can split as [1,2] and [3,4].
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
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