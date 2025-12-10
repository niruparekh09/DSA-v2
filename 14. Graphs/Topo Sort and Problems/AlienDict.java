import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AlienDict {

    public static void main(String[] args) {
        String[] dict = {"baa", "abcd", "abca", "cab", "cad"};
        int N = 5, K = 4;

        AlienDict solver = new AlienDict();
        String order = solver.findOrder(dict, N, K);
        System.out.println(order);
    }

    /*
     * Approach: Topological Sort (Kahn's Algorithm)
     * Pattern: Directed Acyclic Graph (DAG) construction from order constraints
     * Time Complexity: O(N * L + K) - N words of length L to build graph, K nodes for Topo Sort.
     * Space Complexity: O(K) - Graph stores K nodes (characters).
     */
    public String findOrder(String[] dict, int N, int K) {
        List<List<Integer>> adjList = new ArrayList<>();

        for (int i = 0; i < K; i++) {
            adjList.add(new ArrayList<>());
        }

        // Step 1: Construct the Graph
        // Compare adjacent words to find the first character mismatch.
        // The mismatched character in word1 comes BEFORE the one in word2.
        for (int i = 0; i < N - 1; i++) {
            String s1 = dict[i];
            String s2 = dict[i + 1];

            int len = Math.min(s1.length(), s2.length());

            for (int j = 0; j < len; j++) {
                char c1 = s1.charAt(j);
                char c2 = s2.charAt(j);
                if (c1 != c2) {
                    // Mismatch found: c1 -> c2 (c1 is lexicographically smaller/earlier)
                    adjList.get(c1 - 'a').add(c2 - 'a');
                    break; // Only the first mismatch determines order; remaining chars don't matter.
                }
            }
        }

        // Step 2: Perform Topological Sort
        int[] topo = topoSort(K, adjList);

        // Step 3: Convert numeric nodes back to characters
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < K; i++) {
            str.append(Character.toChars(topo[i] + 'a'));
            str.append(' ');
        }

        return str.toString();
    }

    // Helper: Standard Kahn's Algorithm
    private int[] topoSort(int N, List<List<Integer>> adjList) {
        int[] inD = new int[N];

        for (List<Integer> neighbours : adjList) {
            for (int neigh : neighbours) inD[neigh]++;
        }

        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            if (inD[i] == 0) q.add(i);
        }

        int[] ans = new int[N];
        int idx = 0;

        while (!q.isEmpty()) {
            Integer frontNode = q.poll();
            ans[idx++] = frontNode;

            for (int neigh : adjList.get(frontNode)) {
                inD[neigh]--;
                if (inD[neigh] == 0) q.add(neigh);
            }
        }

        // Note: If idx < N (cycle detected), valid ordering is impossible.
        // (Typically handled by returning empty/error, but simple array return here).
        return ans;
    }

    // TODO: https://neetcode.io/problems/foreign-dictionary/question?list=neetcode150
    public String foreignDictionary(String[] words) {
        return "";
    }

}

