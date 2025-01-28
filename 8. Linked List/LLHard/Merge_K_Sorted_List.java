import java.util.*;

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

    // TC: O(N^3) SC: O(1)
    public static ListNode mergeKListsBetter(ListNode[] lists) {
        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++) {
            head = mergeLists(head, lists[i]);
        }
        return head;
    }

    public static ListNode mergeLists(ListNode head1, ListNode head2) {
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

    public static ListNode mergeKLists(ListNode[] lists) {
        // PQ to maintain sorted order on based on node.val
        PriorityQueue<Map.Entry<Integer, ListNode>> pq = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getKey));

        // Push heads of all the Lists into PQ
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(new AbstractMap.SimpleEntry<>(node.val, node));
            }
        }

        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;

        // This loop will always give the smallest node possible
        // And we will take that out and then add to dummyNode link
        while (!pq.isEmpty()) {
            // Topmost node which will also be the node with smallest val.
            Map.Entry<Integer, ListNode> smallestNode = pq.poll();

            //Check if the current node has next node
            ListNode nextNode = smallestNode.getValue().next;
            if (nextNode != null) {
                pq.add(new AbstractMap.SimpleEntry<>(nextNode.val, nextNode));
            }

            temp.next = smallestNode.getValue();
            temp = temp.next;
        }

        return dummyNode.next;
    }

    // Due to error in leetcode we'll directly store ListNode in PQ
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0)
            return null;

        // PriorityQueue to maintain sorted order of nodes based on their value
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        // Push the heads of all the lists into the PriorityQueue
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        // Dummy node to build the merged linked list
        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;

        // Process the PriorityQueue
        while (!pq.isEmpty()) {
            // Extract the smallest node from the PriorityQueue
            ListNode smallestNode = pq.poll();

            // Add it to the merged linked list
            temp.next = smallestNode;
            temp = temp.next;

            // If the extracted node has a next node, push it into the PriorityQueue
            if (smallestNode.next != null) {
                pq.add(smallestNode.next);
            }
        }

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