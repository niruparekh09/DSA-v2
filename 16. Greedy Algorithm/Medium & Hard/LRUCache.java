import java.util.LinkedHashMap;
import java.util.Map;

class LRUCache {
    /*
     * Approach: Using Java's LinkedHashMap
     * Pattern: Least Recently Used (LRU) Cache
     * Time Complexity: O(1) for both get and put operations.
     * Space Complexity: O(Capacity) - To store the cache entries.
     */
    LinkedHashMap<Integer, Integer> lru;
    int capacity;

    /**
     * The LinkedHashMap constructor is used with 'accessOrder' set to true.
     * This automatically moves an element to the end of the map whenever
     * it is accessed (get/put), keeping the least recently used elements
     * at the head (beginning) of the map.
     */
    public LRUCache(int capacity) {
        // capacity, load factor, accessOrder (true = LRU, false = insertion order)
        this.lru = new LinkedHashMap<>(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    public int get(int key) {
        // Returns the value and automatically marks it as "most recently used"
        // because accessOrder is true.
        return lru.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        // If the key exists, updating it also moves it to the "most recent" position.
        if (lru.containsKey(key)) {
            lru.put(key, value);
            return;
        }

        /* * Eviction Policy:
         * If the cache is at capacity, we remove the first entry.
         * In a LinkedHashMap with accessOrder=true, the entry at the head
         * of the map is the Least Recently Used.
         */
        if (lru.size() == capacity) {
            // iterator().next() gets the key at the head of the insertion/access order.
            lru.remove(lru.keySet().iterator().next());
        }

        lru.put(key, value);
    }
}