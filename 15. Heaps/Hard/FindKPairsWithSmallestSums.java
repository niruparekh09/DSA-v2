import java.util.*;

public class FindKPairsWithSmallestSums {
    /*
     * Approach: Min-Heap with Coordinate Exploration (Greedy/BFS Hybrid)
     * Pattern: K-way Merge / Shortest Path on a 2D Grid
     * Time Complexity: O(K log K) - We perform K extractions from the heap.
     * Space Complexity: O(K) - To store visited pairs and heap elements.
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0)
            return ans;

        int n = nums1.length;
        int m = nums2.length;

        // Step 1: Ensure arrays are sorted.
        // The smallest sum is guaranteed to be (nums1[0] + nums2[0]).
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        /* * Min-Heap stores: {Sum, Index i in nums1, Index j in nums2}
         * This heap acts as a "frontier" to find the next smallest possible sum.
         */
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));

        // Set prevents processing the same (i, j) pair multiple times.
        Set<String> visited = new HashSet<>();


        // Step 2: Initialize heap with the absolute smallest pair.
        minHeap.add(new int[]{nums1[0] + nums2[0], 0, 0});
        visited.add(0 + "," + 0);

        // Step 3: Extract the current smallest and explore its neighbors.
        // Neighbors are (i + 1, j) and (i, j + 1).
        for (int i = 0; i < k && !minHeap.isEmpty(); i++) {
            int[] curr = minHeap.poll();
            int r = curr[1];
            int c = curr[2];

            ans.add(List.of(nums1[r], nums2[c]));

            // Explore "Right" (Increment nums1 index)
            if (r + 1 < n && !visited.contains((r + 1) + "," + c)) {
                minHeap.add(new int[]{nums1[r + 1] + nums2[c], r + 1, c});
                visited.add((r + 1) + "," + c);
            }

            // Explore "Down" (Increment nums2 index)
            if (c + 1 < m && !visited.contains(r + "," + (c + 1))) {
                minHeap.add(new int[]{nums1[r] + nums2[c + 1], r, c + 1});
                visited.add(r + "," + (c + 1));
            }
        }

        return ans;
    }
}