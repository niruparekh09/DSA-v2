import java.util.ArrayList;
import java.util.List;

public class MinHeapImpl {
    private List<Integer> arr;

    public void initializeHeap() {
        arr = new ArrayList<>();
    }

    /*
     * Approach: Dynamic Array-based Min-Heap
     * Pattern: Heap Operations (Insert, Extract, Update)
     * Time Complexity: O(log N) for Insert/Extract/Change; O(1) for GetMin
     * Space Complexity: O(N) to store elements in the List
     */
    public void insert(int key) {
        // Add to the end to maintain complete binary tree property
        arr.add(key);
        heapifyUp(arr.size() - 1);
    }

    public void changeKey(int index, int newVal) {
        // If new value is smaller, move up; if larger, sink down
        if (arr.get(index) > newVal) {
            arr.set(index, newVal);
            heapifyUp(index);
        } else {
            arr.set(index, newVal);
            heapifyDown(index);
        }
    }

    public void extractMin() {
        if (arr.isEmpty()) return;
        int n = arr.size();

        // Replace root with the last element and remove last to maintain structure
        arr.set(0, arr.get(n - 1));
        arr.remove(n - 1);
        heapifyDown(0);
    }

    public boolean isEmpty() {
        return arr.isEmpty();
    }

    public int getMin() {
        return arr.get(0);
    }

    public int heapSize() {
        return arr.size();
    }

    /**
     * Sift-Up: Moves a node up to restore Min-Heap property (parent <= child)
     */
    public void heapifyUp(int ind) {
        int parentInd = (ind - 1) / 2;

        if (ind > 0 && arr.get(parentInd) > arr.get(ind)) {
            swap(parentInd, ind);
            heapifyUp(parentInd);
        }
    }

    /**
     * Sift-Down: Moves a node down to restore Min-Heap property
     */
    public void heapifyDown(int ind) {
        int leftInd = 2 * ind + 1;
        int rightInd = 2 * ind + 2;
        int smallestInd = ind;

        // Determine the smallest among parent and its two children
        if (leftInd < arr.size() && arr.get(leftInd) < arr.get(smallestInd)) {
            smallestInd = leftInd;
        }
        if (rightInd < arr.size() && arr.get(rightInd) < arr.get(smallestInd)) {
            smallestInd = rightInd;
        }

        if (smallestInd != ind) {
            swap(smallestInd, ind);
            heapifyDown(smallestInd);
        }
    }

    private void swap(int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}