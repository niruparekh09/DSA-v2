import java.util.ArrayList;
import java.util.Collections;

public class Merge_K_Sorted_List {

    // Brute TC:M=N*K O(M) + O(MlogM) + O(M) SC: O(M) // Arr + O(M) //New LL
    public static ListNode mergeKListsBrute(ListNode[] lists) {
        ArrayList<Integer> arr = new ArrayList<>();
        for (ListNode list : lists) {
            ListNode temp = list;
            while (temp != null) {
                arr.add(temp.val);
                temp = temp.next;
            }
        }
        if (arr.isEmpty()) return null;
        Collections.sort(arr);
        return convertArrToLL(arr);
    }

    private static ListNode convertArrToLL(ArrayList<Integer> arr) {
        ListNode newList = new ListNode(arr.getFirst());
        ListNode mover = newList;
        for (int i = 1; i < arr.size(); i++) {
            ListNode temp = new ListNode(arr.get(i));
            mover.next = temp;
            mover = temp;
        }
        return newList;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++) {
            head = mergerLists(head, lists[i]);
        }
        return head;
    }

    // TC: O(N^3) SC: O(1)
    public static ListNode mergerLists(ListNode head1, ListNode head2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode t1 = head1;
        ListNode t2 = head2;
        while (t1 != null && t2 != null) {
            if (t1.val < t2.val) {
                dummyNode.next = t1;
                dummyNode = t1;
                t1 = t1.next;
            } else {
                dummyNode.next = t2;
                dummyNode = t2;
                t2 = t2.next;
            }
        }
        if (t1 != null) dummyNode.next = t1;
        else dummyNode.next = t2;
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