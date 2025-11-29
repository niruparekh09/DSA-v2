import java.util.*;

public class Merge_K_Sorted_List {

    /*
     * Approach: Brute Force (Collect & Sort)
     * Pattern: Sorting
     * Time Complexity: O(N*K * log(N*K)) - Sorting the collected list dominates.
     * Space Complexity: O(N*K) - Requires new list to store all values.
     */
    public static ListNode mergeKListsBrute(ListNode[] lists) {
        ArrayList<Integer> arr = new ArrayList<>();
        // Step 1: Flatten all lists into a single array
        for (ListNode list : lists) {
            ListNode temp = list;
            while (temp != null) {
                arr.add(temp.val);
                temp = temp.next;
            }
        }
        if (arr.isEmpty()) return null;

        // Step 2: Sort and rebuild Linked List
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

    /*
     * Approach: Iterative Merging (One by One)
     * Pattern: Two Pointers (Repeated)
     * Time Complexity: O(K^2 * N) - Inefficient. The merged list grows larger with every iteration.
     * Space Complexity: O(1) - In-place merging.
     */
    public static ListNode mergeKListsBetter(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode head = lists[0];
        // Logic: Merge List[0] with List[1], then result with List[2], etc.
        for (int i = 1; i < lists.length; i++) {
            head = mergeLists(head, lists[i]);
        }
        return head;
    }

    // Standard "Merge Two Sorted Lists" Helper
    public static ListNode mergeLists(ListNode head1, ListNode head2) {
        ListNode dummyNode = new ListNode(-1);
        ListNode t1 = head1;
        ListNode t2 = head2; // Typo in original code fixed (was head1)
        ListNode curr = dummyNode;

        while (t1 != null && t2 != null) {
            if (t1.val < t2.val) {
                curr.next = t1; // Fixed original logic to use curr
                t1 = t1.next;
            } else {
                curr.next = t2;
                t2 = t2.next;
            }
            curr = curr.next;
        }
        if (t1 != null) curr.next = t1;
        else curr.next = t2;
        return dummyNode.next;
    }

    // (Older version using Map.Entry - redundant but logic is same as above)
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

    /*
     * Approach: Min-Heap / Priority Queue (Optimal)
     * Pattern: K-way Merge / Heap
     * Time Complexity: O(N*K * log(K)) - We process all N*K nodes, heap operations take log(K).
     * Space Complexity: O(K) - Heap stores at most K nodes (one from each list).
     */
    public ListNode mergeKListsOptimal(ListNode[] lists) {
        if (lists.length == 0) return null;

        // Logic: Min-Heap ensures we always pick the global smallest element
        // among the current heads of the K lists.
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.val, b.val));

        // Step 1: Push the HEAD of every non-empty list into the PQ.
        // This initializes our pool of candidates.
        for (ListNode node : lists) {
            if (node != null) {
                pq.add(node);
            }
        }

        ListNode dummyNode = new ListNode(-1);
        ListNode temp = dummyNode;

        // Step 2: Process nodes
        while (!pq.isEmpty()) {
            // Greedy: Extract the smallest node currently available
            ListNode smallestNode = pq.poll();

            temp.next = smallestNode;
            temp = temp.next;

            // Critical Step: Replenish the heap.
            // If the extracted node has a next node, add it to the PQ.
            // This maintains the "competition" between the lists.
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