import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeInKList {
    /*
     * Approach: Brute Force (Pointers + Repeated Scanning)
     * Time Complexity: O(N * K) - N total elements, scan K lists for each update.
     * Space Complexity: O(K) - To store pointer objects for each list.
     */
    public int[] smallestRangeBrute(List<List<Integer>> nums) {
        if (nums.isEmpty()) return new int[]{-1, -1};
        List<Pointer> ptr = new ArrayList<>();
        int k = nums.size();

        for (List<Integer> num : nums) {
            if (num.isEmpty()) return new int[]{-1, -1};
            ptr.add(new Pointer(num, 0));
        }

        // Initial scan to find range of the first elements
        Pointer min = ptr.get(0);
        Pointer max = ptr.get(0);

        for (int i = 0; i < k; i++) {
            Pointer p = ptr.get(i);
            if (min.getValue() > p.getValue()) min = p;
            if (max.getValue() < p.getValue()) max = p;
        }

        int[] ans = new int[]{min.getValue(), max.getValue()};

        while (true) {
            Pointer oldMin = min;
            if (!oldMin.hasNext()) break;

            oldMin.moveNext(); // Slide the pointer of the list that had the minimum value

            // Recompute min and max from scratch (expensive O(K) operation)
            min = ptr.get(0);
            max = ptr.get(0);

            for (Pointer p : ptr) {
                if (p.getValue() < min.getValue()) min = p;
                if (p.getValue() > max.getValue()) max = p;
            }

            // Update answer if a narrower range is found
            if ((max.getValue() - min.getValue()) < (ans[1] - ans[0])) {
                ans = new int[]{min.getValue(), max.getValue()};
            }
        }
        return ans;
    }

    /*
     * Approach: Min-Heap + Track Max
     * Pattern: Sliding Window over K-Sorted Lists
     * Time Complexity: O(N * log K) - Each of N elements is pushed/popped once.
     * Space Complexity: O(K) - To store one element from each of the K lists.
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        if (nums.isEmpty()) return new int[]{-1, -1};

        // Min-heap stores {value, list_index, element_index}
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a.data));

        int currentMax = Integer.MIN_VALUE;

        // Step 1: Add the first element of each list and track the global maximum

        for (int i = 0; i < nums.size(); i++) {
            if (nums.get(i).isEmpty()) return new int[]{-1, -1};
            int val = nums.get(i).get(0);
            minHeap.add(new Node(val, i, 0));
            currentMax = Math.max(currentMax, val);
        }

        int[] ans = new int[]{minHeap.peek().data, currentMax};

        // Step 2: Pop the smallest, update the range, and push the next element from that same list
        while (!minHeap.isEmpty()) {
            Node minNode = minHeap.poll();

            // Update the answer if the current [min, currentMax] is smaller
            if (currentMax - minNode.data < ans[1] - ans[0]) {
                ans = new int[]{minNode.data, currentMax};
            }

            // If the list that provided the minimum has more elements, move forward
            if (minNode.j + 1 < nums.get(minNode.i).size()) {
                int nextVal = nums.get(minNode.i).get(minNode.j + 1);
                currentMax = Math.max(currentMax, nextVal);
                minHeap.add(new Node(nextVal, minNode.i, minNode.j + 1));
            } else {
                // If any list is exhausted, we cannot find a range containing all lists anymore
                break;
            }
        }
        return ans;
    }

    private class Node {
        int data, i, j;

        public Node(int data, int i, int j) {
            this.data = data;
            this.i = i;
            this.j = j;
        }
    }

    private class Pointer {
        List<Integer> list;
        int index;

        Pointer(List<Integer> list, int index) {
            this.list = list;
            this.index = index;
        }

        int getValue() {
            return list.get(index);
        }

        boolean hasNext() {
            return index + 1 < list.size();
        }

        void moveNext() {
            if (hasNext()) index++;
        }
    }
}