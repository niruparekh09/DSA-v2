public class Reverse_LL_2 {
    public static ListNode reverseBetween(ListNode head, int left, int right) {
//        if()
//        ListNode preLeftNode = findLeft(head, left);
//        ListNode rightNode = findRight(head, right);
//        ListNode postRightNode = rightNode.next;
        return head;
    }

    private static ListNode findLeft(ListNode head, int left) {
        if (left == 1) return head;
        ListNode leftNode = head;
        left--; // To get the preLeftNode decrease left by 1 beforehand.
        while (leftNode != null) {
            left--;
            if (left == 0) break;
            leftNode = leftNode.next;
        }
        return leftNode;
    }

    private static ListNode findRight(ListNode head, int right) {
        ListNode rightNode = head;
        while (rightNode != null) {
            right--;
            if (right == 0) break;
            rightNode = rightNode.next;
        }
        return rightNode;
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
