import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLists {
    /*
     * Approach: Min-Heap (Priority Queue)
     * Pattern: Multi-way Merge / K-way Merge
     * Time Complexity: O(N * log K) - N is total nodes, K is number of lists.
     * Space Complexity: O(K) - To maintain the heap of K elements.
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;

        // Min-Heap stores the current head of each of the K linked lists.
        // It always provides the smallest available node across all lists.
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        ListNode head = null, tail = null;

        // Step 1: Initial state - add the first node of every non-empty list.
        for (ListNode list : lists) {
            if (list != null) minHeap.add(list);
        }


        // Step 2: Extract the minimum and move the pointer forward in that specific list.
        while (!minHeap.isEmpty()) {
            if (head == null) {
                // Initialize the merged list with the absolute smallest node.
                head = minHeap.poll();
                tail = head;

                // If the extracted node has a successor, push it to the heap.
                if (head.next != null) {
                    minHeap.add(head.next);
                }
            } else {
                // Append the next smallest node to the growing merged list.
                tail.next = minHeap.poll();
                tail = tail.next;

                // Maintain the heap size by adding the next node from the used list.
                if (tail.next != null) {
                    minHeap.add(tail.next);
                }
            }
        }

        return head;
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}