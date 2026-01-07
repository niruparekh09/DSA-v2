import java.util.Stack;

public class Valid_Parentheses {
    public static void main(String[] args) {
        System.out.println(isValid("{}[]("));
    }

    /*
     * Approach: Stack-based Matching
     * Pattern: Stack / LIFO (Last-In, First-Out)
     * Time Complexity: O(N) - Single pass through the string.
     * Space Complexity: O(N) - Worst case: string contains only opening brackets.
     */
    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();

        for (Character c : s.toCharArray()) {
            // Logic: Push opening brackets to stack to resolve them later.
            if (c == '(' || c == '{' || c == '[') {
                st.push(c);
            }
            // Logic: Handle closing brackets
            else {
                // Edge Case: Closing bracket encountered with no corresponding opening bracket.
                if (st.isEmpty()) return false;

                char ch = st.pop(); // Get the most recently opened bracket

                // Key Logic: Check if the popped opening bracket matches the current closing bracket.
                if ((c == ')' && ch == '(') || (c == '}' && ch == '{') || (c == ']' && ch == '[')) {
                    continue;
                } else {
                    return false; // Mismatch found (e.g., "(]")
                }
            }
        }

        // Edge Case: Stack must be empty. If not, we have unmatched opening brackets (e.g., "(()").
        return st.isEmpty();
    }
}