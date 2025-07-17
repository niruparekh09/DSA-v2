import java.util.HashMap;
import java.util.Map;

class Node {
    Node prev, next;
    int key, value;

    Node(int _key, int _value) {
        key = _key;
        value = _value;
    }
}

class LRUCache {
    Node head = new Node(0, 0), tail = new Node(0, 0);
    Map<Integer, Node> map = new HashMap<>();
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node); // remove from its original position
            insert(node); // insert next to the head
            return node.value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        // If elem is present then remove it as it will be inserted with new value
        if (map.containsKey(key)) {
            remove(map.get(key));
        }

        // If Capacity is full so remove The Least use element
        if (map.size() == capacity) {
            remove(tail.prev); // Removing the last node before tail as it is LU
        }
        insert(new Node(key, value)); // Inserting New Node
    }

    private void insert(Node node) {
        map.put(node.key, node);
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    private void remove(Node node) {
        map.remove(node.key);
        node.prev.next = node.next; // Node's prev will point to node's next rather than node
        node.next.prev = node.prev; // Node's next will point to node's prev rather than node
    }
}