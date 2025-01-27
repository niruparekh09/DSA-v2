public class Rotate_List {
    public static ListNode rotateRight(ListNode head, int k) {
        int len = 1;
        ListNode tail = head;
        while (tail.next != null) {
            len++;
            tail = tail.next;
        }
        if (k % len == 0) return head;
        k = k % len; // If k is too large. Let's say K==201 and len =5 then we need basically just rotate array by 1.
        ListNode newLastNode = findNewLastNode(head, len - k);
        tail.next = head;
        head = newLastNode.next;
        newLastNode.next = null;
        return head;
    }

    private static ListNode findNewLastNode(ListNode head, int i) {
        ListNode temp = head;
        while (temp != null) {
            i--;
            if (i == 0) break;
            temp = temp.next;
        }
        return temp;
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
