import java.util.HashMap;
import java.util.Map;

/*
 * Approach: Two HashMaps + Doubly Linked Lists (Buckets)
 * Pattern: System Design / Frequency Map
 * Time Complexity: O(1) - All operations (get, put, freq update) involve Map/DLL lookups.
 * Space Complexity: O(N) - Storing nodes in map and lists.
 */
public class LFUCache {

    // Map 1: Key -> Node (Fast access to the actual data)
    final Map<Integer, Node> keyMap;

    // Map 2: Frequency -> DLL (Buckets of nodes with the same frequency)
    // Why DLL? To handle the "LRU within LFU" tie-breaker efficiently.
    final Map<Integer, DLL> freqMap;

    int capacity;
    int minFreq; // Critical: Tracks the minimum frequency to know what to evict
    int curSize;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.curSize = 0;
        this.keyMap = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        Node node = keyMap.get(key);
        if (node == null) return -1;

        // Logic: Accessing a node increases its frequency
        updateFreq(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        // Case 1: Key exists. Update value and increase frequency.
        if (keyMap.containsKey(key)) {
            Node node = keyMap.get(key);
            node.val = value;
            updateFreq(node);
            return;
        }

        // Case 2: Key does not exist. Insert new node.

        // Step 2a: Evict if full
        if (curSize == capacity) {
            // Logic: Get the list of the minimum frequency.
            // The 'Tail' of this list is the LRU node among the LFU nodes.
            DLL minList = freqMap.get(minFreq);
            Node toRemove = minList.tail.prev;

            keyMap.remove(toRemove.key);
            minList.removeNode(toRemove);
            curSize--;
        }

        // Step 2b: Insert new node
        curSize++;
        // New nodes always start with Frequency 1, so minFreq becomes 1
        minFreq = 1;

        Node newNode = new Node(key, value);

        // Add to Freq Map (Bucket 1) and Key Map
        DLL list = freqMap.getOrDefault(1, new DLL());
        list.addFront(newNode);
        freqMap.put(1, list);
        keyMap.put(key, newNode);
    }

    // Core Logic: Moves a node from Freq List [X] to Freq List [X+1]
    private void updateFreq(Node node) {
        int curFreq = node.cnt;
        DLL prevList = freqMap.get(curFreq);

        // Remove from current frequency bucket
        prevList.removeNode(node);

        // Key Logic: Maintain minFreq.
        // If we just removed the LAST node from the minFreq bucket,
        // the overall minimum frequency increases by 1.
        if (curFreq == minFreq && prevList.size == 0) {
            minFreq++;
        }

        // Move to next frequency bucket
        node.cnt++;
        DLL nextList = freqMap.getOrDefault(node.cnt, new DLL());
        nextList.addFront(node);
        freqMap.put(node.cnt, nextList);
    }

    // --- Helper Classes ---

    // Standard Doubly Linked List Node
    static class Node {
        int key, val, cnt;
        Node next, prev;
        Node(int key, int val) {
            this.key = key;
            this.val = val;
            this.cnt = 1; // Initial frequency
        }
    }

    // Helper DLL wrapper to manage head/tail easily
    static class DLL {
        int size;
        Node head, tail;

        DLL() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        // Add to front (Most Recently Used position for this frequency)
        void addFront(Node node) {
            Node temp = head.next;
            node.next = temp;
            node.prev = head;
            head.next = node;
            temp.prev = node;
            size++;
        }

        // Remove specific node (O(1))
        void removeNode(Node node) {
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.print(cache.get(1) + " "); // 1 (freq 1->2)
        cache.put(3, 3); // Evicts 2 (freq 1, LRU)
        System.out.print(cache.get(2) + " "); // -1
        System.out.print(cache.get(3) + " "); // 3 (freq 1->2)
        cache.put(4, 4); // Evicts 1 (freq 2, LRU compared to 3)
        System.out.print(cache.get(1) + " "); // -1
        System.out.print(cache.get(3) + " "); // 3
        System.out.print(cache.get(4) + " "); // 4
    }
}