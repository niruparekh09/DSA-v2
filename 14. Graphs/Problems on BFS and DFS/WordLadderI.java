import java.util.*;

public class WordLadderI {
    public static void main(String[] args) {
        WordLadderI w = new WordLadderI();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        int result = w.ladderLength(beginWord, endWord, wordList);
        System.out.println(result);
    }

    /*
     * Approach: Breadth-First Search (BFS)
     * Pattern: Shortest Path in Unweighted Graph
     * Time Complexity: O(N * M * 26) - N is no. of words, M is word length. We iterate M positions * 26 chars for every word.
     * Space Complexity: O(N * M) - To store words in Queue and HashSet.
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int res = 0;

        Queue<Pair> q = new LinkedList<>();

        // Optimization: Use HashSet for O(1) lookup.
        // This Set also acts as the "Visited" array (we remove words once visited).
        Set<String> wordSet = new HashSet<>(wordList);

        q.add(new Pair(beginWord, 1));
        wordSet.remove(beginWord); // Mark start node as visited

        while (!q.isEmpty()) {
            Pair frontNode = q.remove();
            String curWord = frontNode.word;
            int curLevel = frontNode.level;

            // Goal check: If current word matches target, return the steps taken
            if (endWord.equals(curWord)) res = curLevel;

            // Logic: Generate all possible neighbors by changing one letter at a time.
            // Iterate through every character position
            for (int i = 0; i < curWord.length(); i++) {
                // Try all 26 alphabets for current position
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    char[] curWordArray = curWord.toCharArray();
                    curWordArray[i] = ch;
                    String replacedWord = new String(curWordArray);

                    // Validity Check: If the transformed word exists in the dictionary (and hasn't been visited)
                    if (wordSet.contains(replacedWord)) {
                        q.add(new Pair(replacedWord, curLevel + 1));
                        // Key Step: Remove immediately to prevent cycles and redundant processing
                        wordSet.remove(replacedWord);
                    }
                }
            }
        }

        return res;
    }

    private class Pair {
        String word;
        int level;

        public Pair(String word, int level) {
            this.word = word;
            this.level = level;
        }
    }
}