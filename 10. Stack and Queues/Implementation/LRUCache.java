import java.util.HashMap;
import java.util.Map;

/*
 * Approach: HashMap + Doubly Linked List
 * Pattern: System Design / Cache Eviction
 * Time Complexity: O(1) - For both get() and put() operations.
 * Space Complexity: O(C) - Where C is the capacity of the cache.
 */
class LRUCache {
    // Dummy Head (Most Recently Used side) and Dummy Tail (Least Recently Used side)
    Node head = new Node(0, 0), tail = new Node(0, 0);
    Map<Integer, Node> map = new HashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        // Initialize DLL: head <-> tail.
        // Dummy nodes eliminate null checks during insert/remove.
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            // Key Logic: Accessing an item makes it the "Most Recently Used".
            // Move it from its current position to the front (right after head).
            remove(node);
            insert(node);
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        // Case 1: Update existing key. Remove old instance so we can insert the fresh one at Head.
        if (map.containsKey(key)) {
            remove(map.get(key));
        }

        // Case 2: Capacity Full. Evict the Least Recently Used item.
        // Logic: The LRU item is always located right before the Dummy Tail.
        if (map.size() == capacity) {
            remove(tail.prev);
        }

        // Insert new node as the Most Recently Used (at Head)
        insert(new Node(key, value));
    }

    // Helper: Always inserts right after Dummy Head (MRU Position)
    private void insert(Node node) {
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    // Helper: Unlinks node from DLL and removes from Map in O(1)
    private void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private class Node {
        Node prev, next;
        int key, value;

        Node(int _key, int _value) {
            key = _key;
            value = _value;
        }
    }
}