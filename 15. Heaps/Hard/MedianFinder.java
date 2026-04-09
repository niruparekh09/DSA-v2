import java.util.Collections;
import java.util.PriorityQueue;

public class MedianFinder {
    // minHeap stores the larger half of the numbers
    PriorityQueue<Integer> minHeap;
    // maxHeap stores the smaller half of the numbers
    PriorityQueue<Integer> maxHeap;

    /*
     * Approach: Two Heaps (Balancing)
     * Pattern: Median Tracking in Data Stream
     * Time Complexity: O(log N) for addNum, O(1) for findMedian
     * Space Complexity: O(N) to store all elements
     */
    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    /**
     * Logic: Maintain two halves of the data. The Max-Heap top gives the largest
     * of the small half, and the Min-Heap top gives the smallest of the large half.
     */
    public void addNum(int num) {
        // Step 1: Decide which heap the number belongs to based on the current max of the small half.
        if (!maxHeap.isEmpty() && num <= maxHeap.peek()) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }

        // Step 2: Rebalance heaps so they differ in size by at most 1 element.
        // We maintain the property: maxHeap.size() >= minHeap.size()
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    /**
     * If total elements are even, median is the average of both heap tops.
     * If total elements are odd, maxHeap top is the median.
     */
    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            // Even number of elements: average of the two middle elements
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        } else {
            // Odd number of elements: the top of maxHeap is the middle element
            return maxHeap.peek();
        }
    }
}