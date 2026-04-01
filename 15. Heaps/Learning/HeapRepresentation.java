import java.util.Arrays;

public class HeapRepresentation {

    private int[] heap;
    private int size;
    private int capacity;

    public HeapRepresentation(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.heap = new int[capacity];
    }

    /*
     * Approach: Array-Based Complete Binary Tree
     * Pattern: Level-order traversal mapping to indices
     * Time Complexity:
     * - Access Parent/Child: O(1)
     * - Insert/Delete: O(log N)
     * Space Complexity: O(N) - To store N elements in an array.
     */

    public static void main(String[] args) {
        HeapRepresentation minHeap = new HeapRepresentation(10);

        // Inserting elements to demonstrate array mapping
        int[] elements = {10, 15, 20, 17, 25};
        for (int x : elements) {
            minHeap.insert(x);
        }

        minHeap.printHeap();
    }

    // Parent Index: (i - 1) / 2
    private int getParentIndex(int i) {
        return (i - 1) / 2;
    }

    // Left Child Index: 2 * i + 1
    private int getLeftChildIndex(int i) {
        return (2 * i) + 1;
    }

    // Right Child Index: 2 * i + 2
    private int getRightChildIndex(int i) {
        return (2 * i) + 2;
    }

    /**
     * Helper methods to check existence of nodes
     */
    private boolean hasParent(int i) {
        return i > 0;
    }

    private boolean hasLeftChild(int i) {
        return getLeftChildIndex(i) < size;
    }

    private boolean hasRightChild(int i) {
        return getRightChildIndex(i) < size;
    }

    public void insert(int value) {
        if (size == capacity) throw new IllegalStateException("Heap is full");

        // Step 1: Put the value at the end of the array (maintains Complete Tree property)
        heap[size] = value;
        size++;

        // Step 2: Restore Min-Heap property (Sift-Up)
        bubbleUp(size - 1);
    }

    private void bubbleUp(int index) {
        // While parent exists and parent is larger than current node
        while (hasParent(index) && heap[getParentIndex(index)] > heap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void swap(int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }

    public void printHeap() {
        System.out.println("Array Representation: " + Arrays.toString(Arrays.copyOf(heap, size)));

        System.out.println("Tree Structure:");
        for (int i = 0; i < size / 2; i++) {
            System.out.print(" PARENT: " + heap[i]);
            if (hasLeftChild(i)) System.out.print(" | LEFT CHILD: " + heap[getLeftChildIndex(i)]);
            if (hasRightChild(i)) System.out.print(" | RIGHT CHILD: " + heap[getRightChildIndex(i)]);
            System.out.println();
        }
    }
}