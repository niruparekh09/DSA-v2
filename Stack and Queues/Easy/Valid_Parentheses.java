import java.util.Stack;

public class Valid_Parentheses {
    public static void main(String[] args) {
        System.out.println(isValid("{}[]("));
    }

    // TC: O(N) && SC: O(N)
    public static boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (Character c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[')
                st.push(c);
            else {
                if (st.isEmpty()) return false;
                char ch = st.pop();
                if ((c == ')' && ch == '(') || (c == '}' && ch == '{') || (c == ']' && ch == '[')) continue;
                else return false;
            }
        }
        return st.isEmpty();
    }
}