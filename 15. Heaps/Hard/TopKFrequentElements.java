import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    /*
     * Approach: Frequency Map + Min-Heap of size K
     * Pattern: Top K Elements / Frequent Item Tracking
     * Time Complexity: O(N log K) - N to build map, N log K to maintain heap.
     * Space Complexity: O(N) - To store the frequency map.
     */
    public int[] topKFrequent(int[] nums, int k) {
        // Step 1: Count frequency of each element using a HashMap.
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }


        /* * Step 2: Use a Min-Heap to track the K most frequent elements.
         * By using a Min-Heap of size K, we discard the elements with the
         * lowest frequency, leaving only the "Top K" in the heap.
         */
        PriorityQueue<int[]> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            // Add current element as {value, frequency}
            minHeap.add(new int[]{entry.getKey(), entry.getValue()});

            // If heap size exceeds K, remove the element with the smallest frequency.
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // Step 3: Extract the remaining K elements from the heap into the result array.
        int[] ans = new int[k];
        int i = 0;

        while (!minHeap.isEmpty()) {
            ans[i++] = minHeap.poll()[0];
        }

        return ans;
    }
}