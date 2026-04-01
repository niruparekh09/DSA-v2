import java.util.ArrayList;
import java.util.List;

public class MaxHeapImpl {
    private List<Integer> arr;

    public void initializeHeap() {
        arr = new ArrayList<>();
    }

    /*
     * Approach: Dynamic Array-based Max-Heap
     * Pattern: Heap Operations (Insert, Extract, Update)
     * Time Complexity: O(log N) for Insert/Extract/Change; O(1) for GetMax
     * Space Complexity: O(N) to store elements in the List
     */
    public void insert(int key) {
        // Append to end and bubble up to restore Max-Heap property (parent >= child)
        arr.add(key);
        heapifyUp(arr.size() - 1);
    }

    public void changeKey(int index, int newVal) {
        // If new value is smaller, sink down; if larger, bubble up
        if (arr.get(index) > newVal) {
            arr.set(index, newVal);
            heapifyDown(index);
        } else {
            arr.set(index, newVal);
            heapifyUp(index);
        }
    }

    public void extractMax() {
        if (arr.isEmpty()) return;
        int n = arr.size();

        // Swap root with last element and remove last to maintain complete tree
        arr.set(0, arr.get(n - 1));
        arr.remove(n - 1);
        heapifyDown(0);
    }

    public boolean isEmpty() {
        return arr.isEmpty();
    }

    public int getMax() {
        return arr.get(0);
    }

    public int heapSize() {
        return arr.size();
    }

    /**
     * Sift-Up: Moves a node up if it is greater than its parent
     */
    public void heapifyUp(int ind) {
        int parentInd = (ind - 1) / 2;

        if (ind > 0 && arr.get(parentInd) < arr.get(ind)) {
            swap(parentInd, ind);
            heapifyUp(parentInd);
        }
    }

    /**
     * Sift-Down: Moves a node down if it is smaller than its children
     */
    public void heapifyDown(int ind) {
        int leftInd = ind * 2 + 1;
        int rightInd = ind * 2 + 2;
        int biggestInd = ind;

        // Identify the largest among parent and children
        if (leftInd < arr.size() && arr.get(leftInd) > arr.get(biggestInd)) {
            biggestInd = leftInd;
        }
        if (rightInd < arr.size() && arr.get(rightInd) > arr.get(biggestInd)) {
            biggestInd = rightInd;
        }

        if (biggestInd != ind) {
            swap(biggestInd, ind);
            heapifyDown(biggestInd);
        }
    }

    private void swap(int i, int j) {
        int temp = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, temp);
    }
}