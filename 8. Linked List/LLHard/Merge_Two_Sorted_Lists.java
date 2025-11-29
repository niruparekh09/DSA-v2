public class Merge_Two_Sorted_Lists {

    /*
     * Approach: Iterative with Dummy Node
     * Pattern: Two Pointers / Merge Logic
     * Time Complexity: O(N + M) - We iterate through both lists at most once.
     * Space Complexity: O(1) - In-place rewiring of pointers; no new nodes allocated.
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode t1 = list1;
        ListNode t2 = list2;

        // Dummy Node: Acts as a sentinel to handle the head of the new list without null checks.
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode; // Tracks the tail of the merged list

        // Iterate while both lists have nodes to compare
        while (t1 != null && t2 != null) {
            // Greedy Logic: Pick the smaller value to maintain sorted order
            if (t1.val < t2.val) {
                temp.next = t1;
                temp = t1;      // Advance result tail
                t1 = t1.next;   // Advance list1
            } else {
                temp.next = t2;
                temp = t2;
                t2 = t2.next;
            }
        }

        // Key Optimization: Once one list is exhausted, simply attach the remainder of the other.
        // No loop needed here because the remaining elements are already sorted.
        if (t1 != null) temp.next = t1;
        if (t2 != null) temp.next = t2;

        return dummyNode.next; // Return actual head
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