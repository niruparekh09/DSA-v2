import java.util.Arrays;

public class AssignCookies {
    /*
     * Approach: Greedy Algorithm with Two Pointers
     * Pattern: Optimal Matching (Assigning limited resources to meet demands)
     * Time Complexity: O(N log N + M log M) - Sorting both arrays (g and s).
     * Space Complexity: O(log N + log M) - Sorting overhead in Java.
     */
    public int findContentChildren(int[] g, int[] s) {
        // Step 1: Sort both arrays to satisfy smaller greed factors first.
        // This is the "Greedy" choice: using the smallest possible cookie for a child.
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0; // Pointer for cookies (s)
        int j = 0; // Pointer for children (g)
        int ans = 0;


        // Step 2: Iterate through cookies and children to find matches.
        while (i < s.length && j < g.length) {
            // Check if current cookie 'i' can satisfy child 'j'
            if (g[j] <= s[i]) {
                // If yes, assign cookie, move to next child and next cookie
                ans++;
                j++;
                i++;
            } else {
                // If the cookie is too small, skip it and try a larger cookie
                // for the same child.
                i++;
            }
        }

        // Final count of satisfied children
        return ans;
    }
}