public class Odd_Even_LL {

    public static ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = head.next; // First even elem
        while(even != null && even.next != null){
            odd.next = odd.next.next; // Pointing to next even elem
            even.next = even.next.next; // Pointing to next odd elem

            odd = odd.next; // as next now has the address to next odd elem.
            even = even.next; // as next now has the address to next even elem.
        }
        odd.next = evenHead;
        return head;
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