public class Remove_Linked_List_Elements {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null || (head.next == null && head.val == val)) return null;
        ListNode temp = head;
        ListNode dummyNode = new ListNode(-1);
        ListNode res = dummyNode;
        while (temp.next != null) {
            if (temp.val != val) {
                res.next = temp;
                res = res.next;
            }
            temp = temp.next;
        }
        // For Last Node
        if (temp.val == val) res.next = null;
        else res.next = temp;
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
