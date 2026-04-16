class TrieImplementation {
    private final TrieNode root;

    /*
     * Approach: Trie (Prefix Tree)
     * Pattern: Retrieval Tree / Digital Tree
     * Time Complexity:
     * - Insert: O(L), Search: O(L), StartsWith: O(L)
     * - where L is the length of the word/prefix.
     * Space Complexity: O(N * L) - Worst case, where N is the number of words.
     */
    public TrieImplementation() {
        root = new TrieNode();
    }

    /**
     * Inserts a word into the trie.
     * Iterates through each character, creating new nodes if the path doesn't exist.
     */
    public void insert(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            // If the current character is not present, create a new node at that index
            if (!node.containsKey(ch)) {
                node.put(ch, new TrieNode());
            }
            // Move to the child node corresponding to the character
            node = node.get(ch);
        }
        // Mark the end of the word
        node.setEnd();
    }

    /**
     * Returns true if the word is in the trie.
     * Must match the full word and the final node must have flag = true.
     */
    public boolean search(String word) {
        TrieNode node = root;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (!node.containsKey(ch)) return false;
            node = node.get(ch);
        }

        // Return true only if the current node is marked as the end of a word
        return node.isEnd();
    }

    /**
     * Returns true if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode node = root;

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!node.containsKey(ch)) return false;
            node = node.get(ch);
        }

        // If we successfully traversed the prefix, it exists in the trie
        return true;
    }

    /**
     * Internal Node structure representing each character's "link" to the next.
     */
    private class TrieNode {
        // Standard English alphabet size (a-z)
        TrieNode[] links = new TrieNode[26];
        boolean flag = false; // "isEndOfWord" flag

        public TrieNode() {
        }

        boolean containsKey(char ch) {
            return links[ch - 'a'] != null;
        }

        TrieNode get(char ch) {
            return links[ch - 'a'];
        }

        void put(char ch, TrieNode node) {
            links[ch - 'a'] = node;
        }

        void setEnd() {
            flag = true;
        }

        boolean isEnd() {
            return flag;
        }
    }
}