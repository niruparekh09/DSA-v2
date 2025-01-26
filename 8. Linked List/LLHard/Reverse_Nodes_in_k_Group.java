public class Reverse_Nodes_in_k_Group {
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        ListNode nextNode = null, prevNode = null;
        while (temp != null) {
            ListNode kthNode = findKthNode(temp, k);

            // If the Kth node is null (not a complete group)
            if (kthNode == null) {
                // If there was a previous group, link the last node to the current node
                if (prevNode != null) prevNode.next = temp;
                break;
            }

            nextNode = kthNode.next; // Store the next node
            kthNode.next = null; // Disconnect the group/segment because we can't reverse a part connect list.
            reverseList(temp); // Reverse the list

            // Adjust the head if the reversal starts from the head
            if (temp == head) {
                head = kthNode;
            } else {
                // Link the last node of the previous group to the reversed group
                // 3(head)---1(prevNode) -> 6(kthNode)---4(temp)
                prevNode.next = kthNode;
            }

            // The temp will be the new prevNode as it is reversed, i.e. 1(temp,head)---3(kthNode) will become
            // 3(kthNode,head)---1(prevNode, temp). So we will set it as prevNode
            prevNode = temp;

            // The nextNode will be the new temp. 1(temp,head)---3(kth)-4(nextNode)
            // after reversal->
            // 3(head)---1(prevNode, old temp)-4(nextNode,newHead)
            temp = nextNode;
        }
        return head;
    }

    private static void reverseList(ListNode head) {
        ListNode currentNode = head;
        ListNode prev = null;
        while (currentNode != null) {
            ListNode nextNode = currentNode.next;
            currentNode.next = prev;
            prev = currentNode;
            currentNode = nextNode;
        }
    }

    private static ListNode findKthNode(ListNode head, int k) {
        ListNode kthNode = head;
        while (kthNode != null) {
            k--;
            if (k == 0) break;
            kthNode = kthNode.next;
        }
        return kthNode;
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
