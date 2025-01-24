public class Find_Middle_Of_LL {
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // Condition for even elements (where fast will jump from 2nd last elem. to null)
        // && odd elements (where fast will reach till last elem.)
        while (fast != null && fast.next != null) {
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
