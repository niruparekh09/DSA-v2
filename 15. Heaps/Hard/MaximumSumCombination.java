import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class MaximumSumCombination {
    /*
     * Approach: Brute Force (All Combinations)
     * Time Complexity: O(N * M * log(N * M)) - Generating all pairs and sorting via heap.
     * Space Complexity: O(N * M) - To store all possible sums in the heap.
     */
    public int[] maxSumCombinationsBrute(int[] nums1, int[] nums2, int k) {
        // Max-Heap to store all possible sums.
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                maxHeap.add(new int[]{nums1[i] + nums2[j], i, j});
            }
        }

        int[] ans = new int[k];
        int i = 0;
        while (k > 0 && !maxHeap.isEmpty()) {
            ans[i++] = maxHeap.poll()[0];
            k--;
        }
        return ans;
    }

    /*
     * Approach: Optimized Greedy with Max-Heap and Two Pointers
     * Pattern: BFS-like exploration on a sorted 2D-like space
     * Time Complexity: O(N log N + K log K) - Sorting plus K heap operations.
     * Space Complexity: O(K) - To store the visited set and heap elements.
     */
    public int[] maxSumCombinations(int[] nums1, int[] nums2, int k) {
        // Step 1: Sort both arrays. The largest sum will involve the largest elements (last indices).
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int n = nums1.length;
        int m = nums2.length;

        // Max-Heap stores: [Sum, Index in nums1, Index in nums2]
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));

        // Visited set prevents adding the same index pair (i, j) multiple times.
        Set<String> visited = new HashSet<>();


        // Step 2: Start with the absolute maximum (last element of both sorted arrays).
        maxHeap.add(new int[]{nums1[n - 1] + nums2[m - 1], n - 1, m - 1});
        visited.add((n - 1) + "," + (m - 1));

        int[] ans = new int[k];

        // Step 3: Extract the top of the heap K times.
        // For every (i, j) extracted, the next candidates are (i-1, j) and (i, j-1).
        for (int i = 0; i < k; i++) {
            int[] curr = maxHeap.poll();
            ans[i] = curr[0];

            int r = curr[1], c = curr[2];

            // Candidate 1: Move left in nums1
            if (r - 1 >= 0 && !visited.contains((r - 1) + "," + c)) {
                maxHeap.add(new int[]{nums1[r - 1] + nums2[c], r - 1, c});
                visited.add((r - 1) + "," + c);
            }

            // Candidate 2: Move left in nums2
            if (c - 1 >= 0 && !visited.contains(r + "," + (c - 1))) {
                maxHeap.add(new int[]{nums1[r] + nums2[c - 1], r, c - 1});
                visited.add(r + "," + (c - 1));
            }
        }

        return ans;
    }
}