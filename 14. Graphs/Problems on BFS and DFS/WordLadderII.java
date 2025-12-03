import java.util.*;

public class WordLadderII {
    public static void main(String[] args) {
        WordLadderII w = new WordLadderII();

        System.out.println(w.findLadders("hit", "cog", new ArrayList<>(
                List.of("hot", "dot", "dog", "lot", "log", "cog"))).toString());
    }

    /*
     * Approach: BFS Storing Paths
     * Pattern: Shortest Path in Unweighted Graph / BFS
     * Time Complexity: O(N * M * 26 * PathLen) - Standard BFS but heavier due to copying paths.
     * Space Complexity: O(N * PathLen) - Storing all possible paths in the Queue.
     */
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        // Set for O(1) lookup and to manage visited nodes
        Set<String> wordSet = new HashSet<>(wordList);

        // Queue stores entire PATHS (sequences) instead of just nodes
        Queue<List<String>> q = new LinkedList<>();

        // Initialize queue with the starting path
        q.add(new ArrayList<>(List.of(beginWord)));

        // Mark beginWord as visited by removing it from availability set
        wordSet.remove(beginWord);

        // Helper list to track words visited strictly at the CURRENT level.
        // Why? Because multiple paths might reach the same word at the same level (which is allowed),
        // but we want to prevent reaching it again in FUTURE levels.
        List<String> usedOnLevel = new ArrayList<>();
        usedOnLevel.add(beginWord);

        int level = 0;
        List<List<String>> ans = new ArrayList<>();

        while (!q.isEmpty()) {
            List<String> frontSeq = q.remove();

            // Level Change Logic:
            // If the path size increases, it means we finished processing the previous level.
            // Now we "commit" the removals from wordSet to prevent future revisits.
            if (frontSeq.size() > level) {
                level++;
                wordSet.removeAll(usedOnLevel);
                usedOnLevel.clear();
            }

            // Get the last word in the current path to explore neighbors
            String word = frontSeq.get(frontSeq.size() - 1);

            // Goal Check
            if (word.equals(endWord)) {
                // Only add if it's the first solution found (shortest) OR same length as existing solutions
                if (ans.isEmpty()) ans.add(frontSeq);
                else if (ans.get(0).size() == frontSeq.size()) ans.add(frontSeq);
                // Note: We continue processing the queue to find other paths of the SAME length.
            }

            // Neighbor Generation (a-z replacement)
            for (int i = 0; i < word.length(); i++) {
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] curWordArr = word.toCharArray();
                    curWordArr[i] = ch;
                    String replacedWord = new String(curWordArr);

                    // Check validity against available words
                    if (wordSet.contains(replacedWord)) {
                        // Action: Add new word to path and enqueue the NEW path
                        frontSeq.add(replacedWord);
                        q.add(new ArrayList<>(frontSeq));

                        // Mark as used for this level (to be removed from global set later)
                        usedOnLevel.add(replacedWord);

                        // Backtrack: Remove the added word to reuse 'frontSeq' for next iteration
                        frontSeq.remove(frontSeq.size() - 1);
                    }
                }
            }
        }

        return ans;
    }

    // A tad bit efficient problem to solve the TLE in LC
    public List<List<String>> findLaddersLC(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        return ans;
    }
}