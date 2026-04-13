import java.util.*;

public class ReplaceElementsByTheirRank {
    /*
     * Approach: Sorted Set (TreeSet)
     * Pattern: Value-to-Rank Mapping
     * Time Complexity: O(N log N) - TreeSet insertion and iteration.
     * Space Complexity: O(N) - Storing unique elements in the map and set.
     */
    public List<Integer> replaceWithRank(List<Integer> arr) {
        // Step 1: Extract unique elements and sort them automatically using TreeSet.
        Set<Integer> set = new TreeSet<>(arr);

        // Step 2: Map each sorted unique element to its corresponding rank.
        Map<Integer, Integer> map = new HashMap<>();
        int r = 1;
        for (int elem : set) {
            map.put(elem, r++);
        }

        // Step 3: Replace original elements with their ranks from the map.
        List<Integer> ans = new ArrayList<>();
        for (int elem : arr) {
            ans.add(map.get(elem));
        }

        return ans;
    }

    /*
     * Approach: Min-Heap (Priority Queue)
     * Pattern: Sorted Processing with Original Index Tracking
     * Time Complexity: O(N log N) - Heap operations for N elements.
     * Space Complexity: O(N) - To store [value, index] pairs in the heap.
     */

    public List<Integer> replaceWithRankHeap(List<Integer> arr) {
        // minHeap stores pairs of [value, original_index] to sort by value
        // while remembering where the value came from.
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(a[0], b[0])
        );

        for (int i = 0; i < arr.size(); i++) {
            minHeap.add(new int[]{arr.get(i), i});
        }

        // Pre-fill result list to allow 'set' operations by index.
        Integer[] res = new Integer[arr.size()];

        int rank = 1;
        Integer prevValue = null;

        while (!minHeap.isEmpty()) {
            int[] poll = minHeap.poll();
            int value = poll[0];
            int index = poll[1];

            /* * Logic for Duplicates:
             * If the current value is different from the previous one,
             * it deserves a higher rank. Otherwise, it shares the same rank.
             */
            if (prevValue != null && value != prevValue) {
                rank++;
            }

            // Place the rank at the value's original position.
            res[index] = rank;
            prevValue = value;
        }

        return Arrays.asList(res);
    }
}