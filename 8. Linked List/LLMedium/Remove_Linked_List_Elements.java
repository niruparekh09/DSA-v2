public class Remove_Linked_List_Elements {

    /*
     * Approach: Iterative filtering with Dummy Node
     * Pattern: Dummy Head / Sentinel Node
     * Time Complexity: O(N) - Single pass through the list.
     * Space Complexity: O(1) - In-place modification of pointers.
     */
    public ListNode removeElements(ListNode head, int val) {
        // Edge Case: Handle empty list or single-node list removal
        if (head == null || (head.next == null && head.val == val)) return null;

        ListNode temp = head;

        // Dummy Node: Acts as a stable anchor. Allows us to modify the 'head'
        // without writing separate logic for when the first node is removed.
        ListNode dummyNode = new ListNode(-1);
        ListNode res = dummyNode;

        // Iterate until the SECOND to last node
        while (temp.next != null) {
            // Logic: If current node is valid, append it to our result chain
            if (temp.val != val) {
                res.next = temp;
                res = res.next;
            }
            temp = temp.next;
        }

        // Edge Case: Handle the Last Node explicitly (since loop stopped early)
        if (temp.val == val) {
            res.next = null; // Last node needs removal, so terminate list here
        } else {
            res.next = temp; // Last node is valid, append it
        }

        return dummyNode.next; // Return the actual head
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