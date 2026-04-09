import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class KthLargestElementInAStream {
    int k;
    PriorityQueue<Integer> minHeap;

    /*
     * Approach: Min-Heap of fixed size K
     * Pattern: Top K elements in a Stream
     * Time Complexity: Constructor O(N log K), Add O(log K)
     * Space Complexity: O(K) - Constant space relative to the stream size.
     */
    public KthLargestElementInAStream(int k, int[] nums) {
        this.k = k;
        this.minHeap = new PriorityQueue<>();

        // Initialize heap with the first K largest elements from the input array.
        for (int i = 0; i < nums.length; i++) {
            add(nums[i]);
        }
    }

    /**
     * Maintains a Min-Heap of size K. The root (smallest) is the k-th largest overall.
     */
    public int add(int val) {
        // Step 1: If heap isn't full, just add the new element.
        if (minHeap.size() < k) {
            minHeap.offer(val);
        }
        // Step 2: If new value is larger than the k-th largest (root), replace the root.
        else if (minHeap.peek() < val) {
            minHeap.poll();
            minHeap.offer(val);
        }

        return minHeap.peek();
    }
}

class KthLargestElementInAStreamBrute {
    private List<Integer> numsList;
    private int k;

    /*
     * Approach: Brute Force (List and Sort)
     * Time Complexity: Add O(N log N) - Sorting the entire list every time a number is added.
     * Space Complexity: O(N) - Stores every single number in the stream.
     */
    public KthLargestElementInAStreamBrute(int k, int[] nums) {
        numsList = new ArrayList<>();
        for (int num : nums) {
            numsList.add(num);
        }
        this.k = k;
    }

    public int add(int val) {
        // Append value, sort the entire list, and return element at (size - k)
        numsList.add(val);
        Collections.sort(numsList);
        return numsList.get(numsList.size() - k);
    }
}