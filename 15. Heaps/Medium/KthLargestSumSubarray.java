import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestSumSubarray {
    /*
     * Approach: Brute Force (Generate all, then Sort)
     * Time Complexity: O(N^2 * log(N^2)) - Generating N^2 subarrays and sorting them.
     * Space Complexity: O(N^2) - Storing all possible subarray sums in a list.
     */
    public int kthLargestBrute(int[] arr, int k) {
        List<Integer> list = new ArrayList<>();

        // Generate all possible subarray sums
        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];
                list.add(sum);
            }
        }

        Collections.sort(list);

        // K-th largest is at (Size - K) index in a sorted list
        return list.get(list.size() - k);
    }

    /*
     * Approach: Min-Heap of size K
     * Pattern: Top K Elements / Sliding Window Sums
     * Time Complexity: O(N^2 * log K) - N^2 subarrays, each heap operation is log K.
     * Space Complexity: O(K) - Efficiently maintains only K largest sums.
     */
    public int kthLargest(int[] arr, int k) {
        // Min-Heap acts as a filter to keep only the 'K' largest sums found so far.
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int counter = 0;

        for (int i = 0; i < arr.length; i++) {
            int sum = 0;
            for (int j = i; j < arr.length; j++) {
                sum += arr[j];

                // Step 1: Fill the heap until it contains K elements.
                if (counter < k) {
                    minHeap.offer(sum);
                    counter++;
                }
                // Step 2: If current sum is larger than the smallest in our "Top K" set (root),
                // replace the root to keep the heap updated with the largest values.
                else {
                    if (minHeap.peek() < sum) {
                        minHeap.poll();
                        minHeap.add(sum);
                    }
                }
            }
        }

        // The smallest element in the min-heap is the k-th largest sum.
        return minHeap.peek();
    }
}