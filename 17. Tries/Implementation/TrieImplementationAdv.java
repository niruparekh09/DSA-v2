public class TrieImplementationAdv {
    TrieNode root;

    /*
     * Approach: Advanced Trie (with Counter Tracking)
     * Pattern: Word and Prefix Frequency Tracking
     * Time Complexity: O(L) for all operations (Insert, Count, Erase)
     * Space Complexity: O(N * L) - N words of average length L.
     */
    public TrieImplementationAdv() {
        root = new TrieNode();
    }

    /**
     * Inserts a word and updates frequency counters at each node.
     * cntPrefix tracks how many words pass through this character.
     * cntEndWith tracks how many words finish exactly at this node.
     */
    public void insert(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }

            node = node.get(ch);
            // Increment prefix count for every node in the path
            node.increasePrefix();
        }
        // Increment the end count for the final character
        node.increaseEnd();
    }

    /**
     * Returns the exact number of times 'word' has been inserted.
     */
    public int countWordsEqualTo(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) return 0;
            node = node.get(word.charAt(i));
        }

        return node.getEnd();
    }

    /**
     * Returns the number of words that have 'prefix' as their starting sequence.
     */
    public int countWordsStartingWith(String prefix) {
        TrieNode node = root;

        for (int i = 0; i < prefix.length(); i++) {
            if (!node.containsKey(prefix.charAt(i))) return 0;
            node = node.get(prefix.charAt(i));
        }

        return node.getPrefix();
    }

    /**
     * Removes one instance of the word from the Trie by decrementing counters.
     */
    public void erase(String word) {
        TrieNode node = root;

        // Optimization: In a real-world scenario, you might want to check
        // if the word exists before starting to decrement.
        for (int i = 0; i < word.length(); i++) {
            if (!node.containsKey(word.charAt(i))) return;

            node = node.get(word.charAt(i));
            // Decrement the prefix count along the path
            node.reducePrefix();

            // If it's the last character, decrement the end count
            if (i == word.length() - 1) node.deleteEnd();
        }
    }

    /**
     * Enhanced TrieNode that stores frequency metadata.
     */
    private class TrieNode {
        private final TrieNode[] links = new TrieNode[26];
        private int cntEndWith = 0;
        private int cntPrefix = 0;

        public TrieNode() {
        }

        boolean containsKey(char key) {
            return links[key - 'a'] != null;
        }

        TrieNode get(char key) {
            return links[key - 'a'];
        }

        void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        void increaseEnd() {
            cntEndWith++;
        }

        void increasePrefix() {
            cntPrefix++;
        }

        void deleteEnd() {
            cntEndWith--;
        }

        void reducePrefix() {
            cntPrefix--;
        }

        public int getEnd() {
            return cntEndWith;
        }

        public int getPrefix() {
            return cntPrefix;
        }
    }
}